package com.example.backend_service.others;

import java.util.Map;

public class DoubleGetMap {
    public static Double getDoubleValue(Map<String, Object> map, String key) {
        Object value = map.get(key);
        if (value instanceof Double || value instanceof Integer) {
            //assert value instanceof Double;
            assert value instanceof Double;
            return (Double) value;
        }
//        else if (value instanceof Integer) {
//            return Double.valueOf((Integer) value);
//        }
        return null;
    }
}
