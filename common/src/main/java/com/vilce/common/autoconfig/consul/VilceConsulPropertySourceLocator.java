package com.vilce.common.autoconfig.consul;

import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.Response;
import com.ecwid.consul.v1.kv.model.GetValue;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cloud.bootstrap.config.PropertySourceLocator;
import org.springframework.cloud.consul.config.ConsulConfigProperties;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.CompositePropertySource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertySource;
import org.springframework.util.ReflectionUtils;

import java.util.*;

/**
 * @author Spencer Gibb
 */
@Order(-1)
public class VilceConsulPropertySourceLocator implements PropertySourceLocator {

    private static final Log log = LogFactory.getLog(VilceConsulPropertySourceLocator.class);

    private final ConsulClient consul;

    private final ConsulConfigProperties properties;

    private final List<String> contexts = new ArrayList<>();

    private final LinkedHashMap<String, Long> contextIndex = new LinkedHashMap<>();

    public VilceConsulPropertySourceLocator(ConsulClient consul,
                                       ConsulConfigProperties properties) {
        this.consul = consul;
        this.properties = properties;
    }

    @Deprecated
    public List<String> getContexts() {
        return this.contexts;
    }

    public LinkedHashMap<String, Long> getContextIndexes() {
        return this.contextIndex;
    }

    @Override
    public PropertySource<?> locate(Environment environment) {
        if (environment instanceof ConfigurableEnvironment) {
            ConfigurableEnvironment env = (ConfigurableEnvironment) environment;

            String appName = this.properties.getName();

            if (appName == null) {
                appName = env.getProperty("spring.application.name");
            }

            List<String> profiles = Arrays.asList(env.getActiveProfiles());

            String prefix = this.properties.getPrefix();

            List<String> suffixes = new ArrayList<>();
            if (this.properties.getFormat() != ConsulConfigProperties.Format.FILES) {
                suffixes.add("/");
            }
            else {
                suffixes.add(".yml");
                suffixes.add(".yaml");
                suffixes.add(".properties");
            }

            String defaultContext = getContext(prefix,
                    this.properties.getDefaultContext());

            for (String suffix : suffixes) {
                this.contexts.add(defaultContext + suffix);
            }
            for (String suffix : suffixes) {
                addProfiles(this.contexts, defaultContext, profiles, suffix);
            }

            String baseContext = getContext(prefix, appName);

            for (String suffix : suffixes) {
                this.contexts.add(baseContext + suffix);
            }
            for (String suffix : suffixes) {
                addProfiles(this.contexts, baseContext, profiles, suffix);
            }

            Collections.reverse(this.contexts);

            CompositePropertySource composite = new CompositePropertySource("consul");

            for (String propertySourceContext : this.contexts) {
                try {
                    VilceConsulPropertySource propertySource = null;
                    if (this.properties.getFormat() == ConsulConfigProperties.Format.FILES) {
                        Response<GetValue> response = this.consul.getKVValue(
                                propertySourceContext, this.properties.getAclToken());
                        addIndex(propertySourceContext, response.getConsulIndex());
                        if (response.getValue() != null) {
                            VilceConsulFilesPropertySource filesPropertySource = new VilceConsulFilesPropertySource(
                                    propertySourceContext, this.consul, this.properties);
                            filesPropertySource.init(response.getValue());
                            propertySource = filesPropertySource;
                        }
                    }
                    else {
                        propertySource = create(propertySourceContext, this.contextIndex);
                    }
                    if (propertySource != null) {
                        composite.addPropertySource(propertySource);
                    }
                }
                catch (Exception e) {
                    if (this.properties.isFailFast()) {
                        log.error(
                                "Fail fast is set and there was an error reading configuration from consul.");
                        ReflectionUtils.rethrowRuntimeException(e);
                    }
                    else {
                        log.warn("Unable to load consul config from "
                                + propertySourceContext, e);
                    }
                }
            }

            return composite;
        }
        return null;
    }

    private String getContext(String prefix, String context) {
        if (StringUtils.isEmpty(prefix)) {
            return context;
        }
        else {
            return prefix + "/" + context;
        }
    }

    private void addIndex(String propertySourceContext, Long consulIndex) {
        this.contextIndex.put(propertySourceContext, consulIndex);
    }

    private VilceConsulPropertySource create(String context, Map<String, Long> contextIndex) {
        VilceConsulPropertySource propertySource = new VilceConsulPropertySource(context,
                this.consul, this.properties);
        propertySource.init();
        addIndex(context, propertySource.getInitialIndex());
        return propertySource;
    }

    private void addProfiles(List<String> contexts, String baseContext,
                             List<String> profiles, String suffix) {
        for (String profile : profiles) {
            contexts.add(baseContext + this.properties.getProfileSeparator() + profile
                    + suffix);
        }
    }

}
