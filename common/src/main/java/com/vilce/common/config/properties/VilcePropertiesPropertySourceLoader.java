package com.vilce.common.config.properties;

import org.springframework.boot.env.OriginTrackedMapPropertySource;
import org.springframework.boot.env.PropertySourceLoader;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @Description: 重写PropertiesPropertySourceLoader类，系统内部的PropertiesPropertySourceLoader类的优先级是2147483647，
 *  * 设置@Order注解，值是2147483646，优先级比系统自带的高
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.common.config.properties.VilcePropertiesPropertySourceLoader
 * @Author: 雷才哲
 * @Date: 2020/11/10 13:24
 * @Version: 1.0
 */
@Order(1)
public class VilcePropertiesPropertySourceLoader implements PropertySourceLoader {

    private static final String XML_FILE_EXTENSION = ".xml";

    @Override
    public String[] getFileExtensions() {
        return new String[] { "properties", "xml" };
    }

    @Override
    public List<PropertySource<?>> load(String name, Resource resource) throws IOException {
        Map<String, ?> properties = loadProperties(resource);
        if (properties.isEmpty()) {
            return Collections.emptyList();
        }
        return Collections
                .singletonList(new OriginTrackedMapPropertySource(name, Collections.unmodifiableMap(properties), true));
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private Map<String, ?> loadProperties(Resource resource) throws IOException {
        String filename = resource.getFilename();
        if (filename != null && filename.endsWith(XML_FILE_EXTENSION)) {
            return (Map) PropertiesLoaderUtils.loadProperties(resource);
        }
        return new VilceOriginTrackedPropertiesLoader(resource).load();
    }
}
