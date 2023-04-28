package com.example.globallogicchallenge.statics;

import java.util.regex.Pattern;

public class Static {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static final Pattern VALID_PASSWORD_REGEX =
            Pattern.compile("(?=.*?[A-Z])(?=.*?\\d.*?\\d)(?!.*\\s)[a-zA-Z\\d]{8,12}$", Pattern.CASE_INSENSITIVE);

    public static final String SECRET = "asdfSFS34wfsdfsdfSDSD32dfsddDDerQSNCK34SOWEK5354fdgdf4";

}