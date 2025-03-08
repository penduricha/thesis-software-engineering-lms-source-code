package com.example.backend_service.services;

public class ConvertNameField {
    public static String convertSnakeToCamel(String key) {
        StringBuilder result = new StringBuilder();
        boolean toUpperCase = false;
        for (char c : key.toCharArray()) {
            if (c == '_') {
                toUpperCase = true;
            } else {
                if (toUpperCase) {
                    result.append(Character.toUpperCase(c));
                    toUpperCase = false;
                } else {
                    result.append(c);
                }
            }
        }
        return result.toString();
    }
}
