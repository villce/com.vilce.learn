package com.vilce.common.utils;

import com.google.common.collect.Maps;
import com.vilce.common.model.vo.BaseRequest;
import com.vilce.common.utils.io.IOUtils;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description: request工具类
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.common.utils.RequestUtils
 * @Author: 雷才哲
 * @Date: 2019/12/5 15:53
 * @Version: 1.0
 */
@SuppressWarnings("all")
public class RequestUtils {

    /**
     * unknown
     */
    private static final String UNKNOWN = "unknown";
    private static final String LOCAL_IP = "127.0.0.1";

    /**
     * 获取客户单IP地址
     */
    public static String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 判断请求IP是否是内网IP
     */
    public static boolean isInnerIp(String ip) {
        String reg = "((192\\.168|172\\.([1][6-9]|[2]\\d|3[01]))"
                + "(\\.([2][0-4]\\d|[2][5][0-5]|[01]?\\d?\\d)){2}|"
                + "^(\\D)*10(\\.([2][0-4]\\d|[2][5][0-5]|[01]?\\d?\\d)){3})";
        Pattern p = Pattern.compile(reg);
        Matcher matcher = p.matcher(ip);
        return matcher.find();
    }

    /**
     * 获取服务器端的IP
     */
    public static String getServerIp() {
        try {
            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = allNetInterfaces.nextElement();
                Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress ip = addresses.nextElement();
                    //loopback地址即本机地址，IPv4的loopback范围是127.0.0.0 ~ 127.255.255.255
                    if (ip != null
                            && ip instanceof Inet4Address
                            && !ip.isLoopbackAddress()
                            && ip.getHostAddress().indexOf(":") == -1) {
                        return ip.getHostAddress();
                    }
                }
            }
        } catch (Exception e) {
        }
        return LOCAL_IP;
    }

    /**
     * 获取用户当前请求的HttpServletRequest
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes attributes = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
        return attributes.getRequest();
    }

    /**
     * 获取当前请求的HttpServletResponse
     */
    public static HttpServletResponse getResponse() {
        ServletRequestAttributes attributes = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
        return attributes.getResponse();
    }

     /**
     * 获取请求入参
     *
     * @param request
     * @return
     */
    public static Map<String, Object> getParameterMap(HttpServletRequest request) {
        Map<String, Object> paramMap = new LinkedHashMap<>();
        if(request instanceof RequestWrapper){
            RequestWrapper requestWrapper = (RequestWrapper) request;
            Map<String, Object> body = getParameterMap(requestWrapper.getRequestBody());
            if (!CollectionUtils.isEmpty(body)) {
                paramMap.putAll(body);
            }
        }
        Enumeration<String> names = request.getParameterNames();
        while (names.hasMoreElements()) {
            String key = names.nextElement();
            paramMap.put(key, request.getParameter(key));
        }

        return paramMap;
    }

    /**
     * 获取参数对象
     *
     * @param params
     * @return
     */
    public static Map<String, Object> getParameterMap(byte[] params) {
        try {
            return JSONUtils.toObject(params, Map.class);
        } catch (Exception e) {
            return convertParameterToMap(IOUtils.toString(params, "utf-8"));
        }
    }

    /**
     * 将参数转换为Map类型
     *
     * @param param
     * @return
     */
    public static Map<String, Object> convertParameterToMap(String param) {
        if (StringUtils.isEmpty(param)) {
            return Collections.emptyMap();
        }
        Map<String, Object> pMap = Maps.newLinkedHashMap();
        String[] pArray = StringUtils.split(param, "&");
        for (int i = 0; i < pArray.length; i++) {
            String[] array = StringUtils.split(pArray[i], "=");
            if (array.length == 2) {
                pMap.put(array[0], array[1]);
            }
        }
        return pMap;
    }

    /**
     * 获取请求参数
     *
     * @param invocation 切面方法入口
     * @return
     */
    public static Map<String, Object> getRequestParam(MethodInvocation invocation) {
        Object[] args = invocation.getArguments();
        Method method = invocation.getMethod();
        Parameter[] parameters = method.getParameters();
        if (ArrayUtils.isEmpty(parameters)) {
            return Collections.emptyMap();
        }
        Map<String, Object> paramMap = new LinkedHashMap<>();
        HttpServletRequest request = RequestUtils.getRequest();
        for (int i = 0; i < parameters.length; i++) {
            if (args[i] instanceof HttpServletResponse) {
                continue;
            }
            if (args[i] instanceof HttpServletRequest) {
                Enumeration<String> params = request.getParameterNames();
                while (params.hasMoreElements()) {
                    String key = params.nextElement();
                    paramMap.put(key, request.getParameter(key));
                }
            } else if (args[i] instanceof BaseRequest) {
                request.setAttribute(parameters[i].getName(), args[i]);
                paramMap.put(parameters[i].getName(), args[i]);
            } else if (args[i] instanceof MultipartFile) {
                paramMap.put(parameters[i].getName(), ((MultipartFile) args[i]).getOriginalFilename());
            } else if (args[i] instanceof File) {
                paramMap.put(parameters[i].getName(), ((File) args[i]).getPath());
            } else if (args[i] instanceof Throwable) {
                //参数信息异常，忽略
            } else {
                paramMap.put(parameters[i].getName(), args[i]);
            }
        }
        paramMap = HiddenUtils.hidden(paramMap, request.getRequestURI());
        return JSONUtils.toJavaBean(JSONUtils.toJSONPrettyString(paramMap), Map.class);
    }

    /**
     * 获取返回结果对象
     *
     * @param body 返回结果字节数组
     * @return
     */
    public static Object getResponseBody(byte[] body) {
        try {
            return JSONUtils.toObject(body, Object.class);
        } catch (Exception e) {
            return IOUtils.toString(body, "UTF-8");
        }
    }

    /**
     * 获取访问URI地址
     *
     * @return
     */
    public static String getRequestURI() {
        return getRequest().getRequestURI();
    }
}
