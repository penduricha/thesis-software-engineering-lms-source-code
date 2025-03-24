package com.example.backend_service.others;
import java.util.*;
public class LongFromMap {

    public LongFromMap() {
    }

    public Long getLongFromMap(Map<String, Object> map, String key) {
        Object value = map.get(key);
        if (value instanceof Number) {
            return ((Number) value).longValue();
        } else {
            //System.out.println("Giá trị không phải là kiểu Number cho key: " + key);
            return null;
        }
    }
}
