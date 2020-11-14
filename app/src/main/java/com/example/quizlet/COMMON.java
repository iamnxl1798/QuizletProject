package com.example.quizlet;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class COMMON {
    final public static String DB_NAME= "QuizletDB";
    final public static String REGEX_EMAIL="^[a-z][a-z0-9_\\.]{5,32}@[a-z0-9]{2,}(\\.[a-z0-9]{2,4}){1,2}$";
    final public static String REGEX_PASS="^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$";

    private Pattern pattern;
    private Matcher matcher;

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public COMMON() {
        pattern = Pattern.compile(EMAIL_PATTERN);
    }

    public boolean validateEmail(final String hex) {

        matcher = pattern.matcher(hex);
        return matcher.matches();

    }
}
