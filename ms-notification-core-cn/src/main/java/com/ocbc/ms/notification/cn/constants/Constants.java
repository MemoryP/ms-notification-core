package com.ocbc.ms.notification.cn.constants;

import java.io.File;

public class Constants {
    public static final String USER_HOME = System.getProperty("user.home");
    public static final String CN_COUNTRY_CODE = "cn";
    public static final String USER_DIR = System.getProperty("user.dir");
    public static final String PATH_SEP;
    public static final String LINE_SEP;
    public static final String DAS_CONF_PATH;
    public static final String DAS_APP_DAO_PREFIX = "dao";
    public static final String DAS_APP_DAO_CONF_FILE = "dao.properties";
    public static final String DAS_DAO_CONFIG_FILE;
    public static final String API_SEP = "/";
    public static final String API_ROOT_PATH = "/api";
    public static final String DAS_APP_SERVICE_CONF_SUFFIX = ".xml";
    public static final String SERVICE_CODE = "Service.Code";
    public static final String SERVICE_NAME = "Service.Name";
    public static final String SERVICE_CN_NAME = "Service.CnName";
    public static final String SERVICE_DESC = "Service.Desc";
    public static final String INTERFACE_CLASS = "Service.Interface";
    public static final String SERVICE_CLASS = "Service.Service";
    public static final String SERVICE_URL = "Service.Url";
    public static final String WORKFLOW_CLASS = "Service.Workflow";
    public static final String SERVICE_VERSION = "Service.Version";
    public static final String SERVICE_RELEASE_VERSION = "Service.ReleaseVersion";
    public static final String SERVICE_RETRIES = "Service.Retries";
    public static final String SERVICE_TIMEOUT = "Service.Timeout";
    public static final String DATASOURCE_TYPE = "Service.DataSourceType";
    public static final String SERVICE_REQUEST = "Request.Field";
    public static final String SERVICE_RESPONSE = "Response.Field";
    public static final String SERVICE_VALIDATOR = "Validators.Validator";
    public static final String REVOCATION_SERVICE_CODE = "RevocationService.Code";
    public static final String REVOCATION_SERVICE_NAME = "RevocationService.Name";
    public static final String REVOCATION_SERVICE_DESC = "RevocationService.Desc";
    public static final String SERVICE_PARAMETERS = "ServiceParameters.Parameter";
    public static final String DAS_MESSAGE_ID = "DasMessageId";
    public static final String DAS_REQUEST_DATE = "DasRequestDate";
    public static final String DAS_REQUEST_TIME = "DasRequestTime";
    public static final String DAS_RESPONSE_DATE = "DasResponseDate";
    public static final String DAS_RESPONSE_TIME = "DasResponseTime";
    public static final String DAS_RESULT_CODE = "DasResultCode";
    public static final String DAS_EXTERNAL_RESULT_CODE = "DasExternalResultCode";
    public static final String DAS_RESULT_MESSAGE = "DasResultMessage";
    public static final String DAS_RESULT_DETAILS = "DasResultDetails";
    public static final String DAS_SUCCESS_CODE = "0000";
    public static final String DAS_DEFAULT_ERROR = "DAS.SYS.99";
    public static final String DAS_RUNTIME_ERROR = "DAS.SYS.01";
    public static final String DAS_WORKFLOW_ERROR = "DAS.SYS.02";
    public static final String DAS_FIELD_NOT_EXIST = "DAS.SYS.03";
    public static final String DAS_FIELD_LEN_ERROR = "DAS.SYS.04";
    public static final String DAS_DATA_CONV_CODE_ERROR = "DAS.SYS.05";
    public static final String DAS_FIELD_IS_NULL = "DAS.SYS.06";
    public static final String DAS_DATA_CHECK_ERROR = "DAS.SYS.07";
    public static final String DAS_DATA_CONVERT_ERROR = "DAS.SYS.08";
    public static final String DAS_FIELD_MAP_NOT_EXIST = "DAS.SYS.09";
    public static final String DAS_NOT_ALLOW_SUB_MODIFY = "DAS.SYS.10";
    public static final String DAS_NOT_ALLOW_FUNC_MODIFY = "DAS.SYS.11";
    public static final String DAS_SERVICE_CONFIG_NOT_EXIST = "DAS.SYS.12";
    public static final String DAS_SERVICE_VALIDATOR_ERROR = "DAS.SYS.13";
    public static final String DAS_DATE_DATA_ERROR = "DAS.SYS.14";
    public static final String DAS_INVOKE_NO_SUBSERVICE = "DAS.SYS.15";
    public static final String DAS_SERVICE_APPID_NOT_EXIST = "DAS.SYS.16";
    public static final String DAS_SERVICE_APPCONTAINER_NOT_EXIST = "DAS.SYS.17";
    public static final String DAS_RESULT_CODE_IS_NULL = "DAS.SYS.18";
    public static final String DAS_DATABASE_ERROR = "DAS.SYS.50";
    public static final String DAS_BEGIN_TRANSCATION = "DAS.SYS.51";
    public static final String DAS_COMMIT_TRANSCATION = "DAS.SYS.52";
    public static final String DAS_ROLLBACK_TRANSCATION = "DAS.SYS.53";
    public static final String DAS_CLOSE_TRANSCATION = "DAS.SYS.54";
    public static final String DAS_MAC_CHECK_ERROR = "DAS.SYS.55";
    public static final String DAS_MODEL_CHECK_ERROR = "DAS.SYS.56";
    public static final String DAS_DAO_ERROR = "DAS.SYS.57";
    public static final String DAS_CLIENT_TIMEOUT = "DAS.SYS.58";
    public static final String DAS_RESULT_FAILED = "DAS.RESULT.FAILED";
    public static final String DAS_RESULT_UNKNOWN = "DAS.RESULT.UNKNOWN";

    private Constants() {
    }

    static {
        PATH_SEP = File.separator;
        LINE_SEP = System.getProperty("line.separator");
        DAS_CONF_PATH = USER_DIR + PATH_SEP + "config";
        DAS_DAO_CONFIG_FILE = DAS_CONF_PATH + PATH_SEP + "dao.properties";
    }
}
