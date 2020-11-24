package com.example.dairbordzimbabwe.utils;

import android.util.Patterns;

import java.util.regex.Pattern;

public class Validator {

    public static boolean validate_phone_number ( String phone_number ) {
        String phone_number_pattern = "/2637[13478][0-9]{7}$";

        return Pattern.matches( phone_number_pattern, phone_number );

    }
}
