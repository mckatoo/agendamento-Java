package io.ikatoo.config;

import java.util.concurrent.TimeUnit;

public class SecurityConstants {

    static final String SECRET = "ikatoo";
    static final String TOKEN_PREFIX = "Bearer ";
    static final String HEADER_STRING = "Authorization";
    static final String SIGN_UP_URL = "/sign-up";
    static final Long EXPIRATION_TIME = 86400000L;

//    public static void main(String[] args) {
//        System.out.println(TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS));
//    }

}
