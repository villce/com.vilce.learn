package com.vilce.common.model.enums;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description: 返回枚举类
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.common.model.enums.ResultStatus
 * @Author: 雷才哲
 * @Date: 2020/8/26 18:09
 * @Version: 1.0
 */
public enum ResultStatus {

    /**
     * 执行成功
     */
    OK(0, "SUCCESS"),
    ERROR(-1, "未知异常"),
    NOT_FOUND(404, "Not Found"),
    BUSSINESS_EXCEPTION(100000, "业务异常"),
    AUTH_EXCEPTION(110000, "身份认证失败"),
    AUTH_EXPIRE(110010, "身份认证过期"),
    PERMISSION_DENY(110011, "权限不匹配"),
    ACCOUNT_NOT_EXIT(110020, "账号不存"),
    ACCOUNTPASSWORD_ERROR(110021, "账号/密码错误"),
    VALIDATECODE_ERROR(110022, "验证码错误"),

    DATA_EXCEPTION(120000, "数据异常"),
    DATA_NOT_FOUND(120001, "数据不存在"),
    DATA_COMPRESS_EXCEPTION(120002, "数据压缩异常"),
    DATA_DE_COMPRESS_EXCEPTION(120003, "数据解压缩异常"),
    DATA_CALCULATION_EXCEPTION(120004, "数据计算异常"),
    DATA_PARSE_EXCEPTION(120005, "数据转换异常"),

    API_NOT_FOUND_EXCEPTION(120002, "API不存在"),
    SYSTEM_EXCEPTION(200000, "系统异常"),
    RUNTIME_EXCEPTION(200010, "运行时异常"),
    NULL_POINTER_EXCEPTION(200020, "空指针异常"),
    CLASS_CAST_EXCEPTION(200030, "类型转换异常"),
    IO_EXCEPTION(200040, "IO异常"),
    INDEX_OUTOF_BOUNDS_EXCEPTION(200050, "数组越界异常"),
    PARAM_EXCEPTION(200060, "参数异常"),
    METHOD_ARGUMENT_TYPE_MISMATCH_EXCEPTIION(200061, "参数类型不匹配"),
    MISSING_SERVLET_REQUEST_PARAMETER_EXCEPTION(200062, "缺少参数"),
    REQUEST_BODY_MESSAGE(200063, "注解@RequestBody标识的请求体缺失"),
    HTTP_REQUEST_METHOD_NOT_SUPPORTED_EXCEPTION(200070, "不支持的method类型"),
    REMOTE_EXCEPTION(30000, "通信异常"),
    DATABASE_EXCEPTION(31000, "数据库异常"),
    REDIS_EXCEPTION(32000, "Redis异常"),
    APIRESOURCE_EXCEPTION(33000, "远程接口异常"),

    API404_EXCEPTION(33001, "远程接口不存在"),
    API500_EXCEPTION(33002, "远程接口服务错误"),

    READ_RESOURSE_EXCEPTION(34001, "读取资源异常"),
    READ_RESOURSE_NOT_FOUND_EXCEPTION(34002, "资源不存在异常"),

    ENCODING_EXCEPTION(35001, "编码异常"),
    ENCODING_UNSUPPORTED_EXCEPTION(35002, "编码不支持异常"),

    JSON_SERIALIZE_EXCEPTION(36000, "序列化数据异常"),
    JSON_DESERIALIZE_EXCEPTION(37001, "反序列化数据异常"),

    DATE_PARSE_EXCEPTION(38000, "日期转换异常");

	private static final Logger LOGGER = LoggerFactory.getLogger(ResultStatus.class);


	private int status;
	private String message;

	ResultStatus(int status, String message){
		this.status = status;
		this.message = message;
	}

	public static int getStatus(String define){
		try {
			return ResultStatus.valueOf(define).status;
		} catch (IllegalArgumentException e) {
			LOGGER.error("undefined error code: {}", define);
			return ERROR.getStatus();
		}
	}

	public static String getMessage(String define){
		try {
			return ResultStatus.valueOf(define).message;
		} catch (IllegalArgumentException e) {
			LOGGER.error("undefined error code: {}", define);
			return ERROR.getMessage();
		}

	}

	public static String getMessage(int status){
		for(ResultStatus err : ResultStatus.values()){
			if(err.status==status){
				return err.message;
			}
		}
		return "errorCode not defined ";
	}

	public int getStatus(){
		return status;
	}

	public String getMessage(){
		return message;
	}

}

