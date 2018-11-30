package com.kiss.message.status;

public class MessageStatusCode {

    public static final Integer LOCKING = 30060;
    public static final Integer APP_NOT_EXIST = 30010;
    public static final Integer CONTENT_IS_ILLEGAL = 30050;
    public static final Integer TEMPLATE_NOT_EXIST = 30020;
    public static final Integer TEMPLATE_LOCALE_NOT_EXIST = 30021;
    public static final Integer DATA_PARSE_TO_JSON_ERROR = 30030;

    public static final Integer CLIENT_NAME_IS_EMPTY = 1001;
    public static final Integer CLIENT_NAME_IS_EXIST = 1002;
    public static final Integer CLIENT_STATUS_IS_EMPTY = 1003;
    public static final Integer CREATE_CLIENT_FAILED = 1004;
    public static final Integer CLIENT_ID_IS_EMPTY = 1005;
    public static final Integer CLIENT_NOT_EXIST = 1006;


    public static final Integer CREATE_TEMPLATE_FAILED = 2001;
    public static final Integer TEMPLATE_UNIQID_IS_EMPTY = 2002;
    public static final Integer TEMPLATE_UNIQID_IS_EXIST = 2003;

}
