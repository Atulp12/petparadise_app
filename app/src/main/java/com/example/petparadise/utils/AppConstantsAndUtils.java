package com.example.petparadise.utils;


public class AppConstantsAndUtils {

    public static final String STATUS_SUCCESS = "1";
    //public static final String STATUS_SUCCESS = "true";
    public static final Integer REQUEST_TIMEOUT = 640000; //2Min
    /*******************************************PREFERENCE************************************************************/
    /*  SHARED PREFERENCE*/
    public static final String USER_ID = "user_id";

    public static final String CUSTOMER_ID = "cust_id";

    public static final String BRANCH_ID = "branch_id";
    public static final String PET_NAME = "pets_name";
    public static final String PACKAGES = "packages";
    public static final String GROOM_ID = "grooming_id";
    public  static final String USER_NAME = "username";
    public static final String SHARED_PREFERENCE_NAME = "name";
    public static final String SHARED_PREFERENCE_NAME_BACK_UP = "back_up_token";
    public static final String PREF_HEADER_REQ_TOKEN = "header_token";
    public static final String LAST_SIGNIN_TIME = "last_signin_time";
    public static final String PREF_USER_LOGE_IN = "pref_user_loge_in";
    public static final String LAST_SIGNIN_USERID = "last_signin_userid";
    public static final String LAST_SIGNIN_PASSWORD = "last_signin_password";
    public static final String USERNAME = "username";
    public static final String CUST_NAME = "Customer_Name";
    public static final String INVOICE_NO = "invoice_no";
    public static final String MOBILE_NO = "Contact_No";
    public static final String INVOICE_PACKAGE = "packages";

    public static final String WAIT = "status_wait";
    public static final String TABLE_NO = "table_no";
    public static final String USERTYPE = "user_type";
    public static final String NAME = "name";

    public static final String STATUS_LOGIN = " ";

    public static final String STATUS = "status";

    /***************************------------------------------------------------All URL's---------*******************************/
    /*All URL's*/
    private static final String BASE_URL = "https://cyberathon.com/pets/index.php/api/";
    public static final String LOGIN_URL = BASE_URL + "Login/signin";

    public static final String SIGNUP_URL = BASE_URL+"Login/signup";

    public static final String SELECT_PACKAGE = BASE_URL+"Customer/add_package";

    public static final String CUSTOMER_LIST = BASE_URL + "Customer/customer_list";

    public static final String CUSTOMER_DETAIL = BASE_URL + "Customer/fetch_customer";
    public static final String BRANCH_LIST = BASE_URL + "Customer/branch_list";

    public static final String CUSTOMER_FETCH = BASE_URL + "Customer/fetch_customer";

    public static final String CUSTOMER_UPDATE = BASE_URL + "Customer/update_customer";

    public static final String WAITING_LIST = BASE_URL + "Customer/waiting_list";

    public static final String TRACK_LIST = BASE_URL + "Customer/branch_list";

    public static final String FETCH_PACKAGE = BASE_URL + "Customer/fetchpackage_againstCustomer";
    public static final String AUTO_INCREMENT = BASE_URL + "Customer/autoincrementGroomingId";
    public static final String ADD_GROOMING_ID = BASE_URL + "Customer/add_grooming";
    public static final String START_TIME = BASE_URL + "Customer/update_startTime";
    public static final String WASHING_SERVICE = BASE_URL + "Customer/update_progress1";
    public static final String DRY_SERVICE = BASE_URL + "Customer/update_progress2";
    public static final String HAIR_SERVICE = BASE_URL + "Customer/update_progress3";
    public static final String STOP_SERVICE = BASE_URL + "Customer/add_service1";
    public static final String ADD_GROOMING1 = BASE_URL + "Customer/add_grooming1";
    public static final String GENERATE_INVOICE = BASE_URL + "Customer/autoincrementInvNo";
    public static final String ADD_INVOICE = BASE_URL + "Customer/add_invoice";
    public static final String NEXT_REMINDER = BASE_URL + "Customer/add_nextDay_reminder";
    public static final String DONE_GROOMING = BASE_URL + "Customer/done_grooming";


}
