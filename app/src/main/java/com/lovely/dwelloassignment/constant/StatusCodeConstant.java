package com.lovely.dwelloassignment.constant;

public interface StatusCodeConstant {

    /**
     * The constant DEFAULT_STATUS_CODE.
     */
    int DEFAULT_STATUS_CODE = 900;
    /**
     * The constant UNAUTHORIZED.
     */
    int UNAUTHORIZED = 401;

    /**
     * The constant USER_NOT_FOUND.
     */
    int USER_NOT_FOUND = 404;
    /**
     * The constant SESSION_EXPIRED.
     */
    int SESSION_EXPIRED = UNAUTHORIZED;

    /**
     * The constant CONFLICT_THAT_ENTERED_EMAIL_OR_USERNAME_ALREADY_EXIST_AND_USER_MAY_HAVE_FORGOTTEN_PASSWORD.
     */
    int CONFLICT = 409;

    /**
     * The constant GONE.
     */
    int GONE = 410;

}
