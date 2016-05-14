package com.demo.common.constants;

public final class Constants
{
    public static final String USER_SESSION = "USER_SESSION";

    public static final String USER_OBJ_SESSION = "USER_OBJ_SESSION";

    public static final String LOGIN_SESSION = "LOGIN_SESSION";

    public static final String APP_SESSION_PREFIX = "APP_SESSION_";

    public static final String APP_SESSION = "APP_SESSION_%s";

    public static final String DELENABLE_SESSION = "delEnable_";

    public static final Integer CORE_APP_ID = Integer.valueOf(0);

    public static final String MAIL_ATTACHMENT = "MAIL_ATTACHMENT";

    public static final String MAIL_INFO = "mailInfo";

    public static final String MAIL_LIST = "mailList";

    public static final String MAIL_LIST_PAGINATION = "mailPagination";

    public static final String MAIL_TYPE = "mailType";

    public static final String MAIL_READ_FLAG = "mailReadFlag";

    public static final int DIC_ITEM_FORM = 1;

    public static final int DIC_ITEM_DOC = 2;

    public static final int DIC_ITEM_PRINT = 3;

    public static final int DIC_ITEM_REGISTER = 4;

    public static final int DIC_ITEM_CASEDIRECTLY = 5;

    public static final int DIC_ITEM_REVOKEREC = 6;

    public static final int DIC_ITEM_TOZELING = 7;

    public static final int DIC_ITEM_SUBMITREC = 8;

    public static final int DIC_ITEM_ADD = 9;

    public static final int DIC_ITEM_EDIT = 10;

    public static final int DIC_ITEM_DELETE = 11;

    public static final int DIC_ITEM_MAPINFO = 14;

    public static final int DIC_ITEM_BAN = 15;

    public static final int DIC_ITEM_CANCLEBAN = 16;

    public static final int DIC_ITEM_ADVICE = 17;

    public static final int FIELD_TYPE_COMMON = 101;

    public static final int FIELD_TYPE_DOCCODE = 103;

    public static final int FIELD_TYPE_DICT = 104;

    public static final int FIELD_TYPE_SIGN = 105;

    public static final int FIELD_TYPE_FILE = 106;

    public static final int LIST_TODO = 1;

    public static final int LIST_DONE = 2;

    public static final int LIST_QUERY = 101;

    public static final int LIST_DEALDONE = 102;

    public static final int LIST_ARCHIVE = 103;

    public static final int LIST_BAN = 104;

    public static final int LIST_SUP = 105;

    public static final int LIST_ADJUST = 106;

    public static final int LIST_DISCARD = 108;

    public static final int LIST_SUSPEND = 107;

    public static final int LIST_AUTHORISE = 109;

    public static final int BIZ_TYPE_NORMAL = 0;

    public static final int BIZ_TYPE_SUB = 1;

    public static final class ScopeType
    {
        public static final Integer SCOPE_ALL = Integer.valueOf(-1);

        public static final Integer SCOPE_HUMAN_ME = Integer.valueOf(0);

        public static final Integer SCOPE_UNIT_ME = Integer.valueOf(1);

        public static final Integer SCOPE_CANTON_ME = Integer.valueOf(2);

        public static final Integer SCOPE_HUMAN = Integer.valueOf(3);

        public static final Integer SCOPE_UNIT = Integer.valueOf(4);
    }
}