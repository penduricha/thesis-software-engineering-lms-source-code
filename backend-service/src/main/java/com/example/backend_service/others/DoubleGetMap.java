package com.example.backend_service.others;

import java.util.Map;

public class DoubleGetMap {
    public static Double getDoubleValue(Map<String, Object> map, String key) {
        Object value = map.get(key);
        if (value instanceof Double) {
            return (Double) value;
        }
        return null;
    }
}
