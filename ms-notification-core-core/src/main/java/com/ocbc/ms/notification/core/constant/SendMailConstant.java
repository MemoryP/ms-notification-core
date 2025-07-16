package com.ocbc.ms.notification.core.constant;

public class SendMailConstant {

    /**
     * Email Type
     */
    public static final String EMAIL_TYPE_HTML = "HTML";
    public static final String EMAIL_TYPE_PLAIN = "PLAIN";

    /**
     * Send info
     */
    public static final String TO = "to";
    public static final String FROM = "from";
    public static final String FILEPATH = "filePath";
    public static final String SUBJECT = "subject";
    public static final String MESSAGE = "message";
    public static final String MSGS = "msgs";
    public static final String PAYLOAD = "payload";
    public static final String CC = "cc";
    public static final String BCC = "bcc";
    public static final String SENDER = "sender";
    public static final String FILE = "file";
    public static final String WORKPHONE = "workPhone";
    public static final boolean ISHTML = true;
    public static final String EMAIL_TYPE = "email_type";

    public static final String STARTTLS_ENABLE = "mail.smtp.starttls.enable";
    public static final String CONNECTION_TIMEOUT = "mail.smtp.connectiontimeout";
    public static final String TIMEOUT = "mail.smtp.timeout";
    public static final String WRITE_TIMEOUT = "mail.smtp.writetimeout";
    public static final String TLS_REQUIRED = "mail.smtp.starttls.required";
    public static final String MAIL_DEBUG = "mail.debug";

    public static final String SMTP_SERVER_TYPE = "SMTP_Server_Type";
    public static final String MAIL_PRIORITY = "priority";


    public static final String SMTP_SERVER_INB = "INB";
    public static final String SMTP_SERVER_BV = "BV";
    public static final String SMTP_SERVER_BV_OG = "BV-OG";

    public static final String DEFAULT_TYPE = "EMAIL";
    public static final String EMAIL = "email";
    public static final String SMS = "SMS";
    public static final String PUSHALERT = "PUSHALERT";
    public static final String SUCCESS = "success";
    public static final String FAILURE = "failure";

    private SendMailConstant() {
    }

}
