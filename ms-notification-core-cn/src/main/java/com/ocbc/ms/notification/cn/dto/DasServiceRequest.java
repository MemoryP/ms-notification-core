//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ocbc.ms.notification.cn.dto;

import java.util.HashMap;
import java.util.Map;

public class DasServiceRequest {
    private final Map<String, Object> dataFields = new HashMap();

    public DasServiceRequest() {
    }

    public String get(String key) {
        Object v = this.dataFields.get(key);
        return v == null ? null : (String)TypeConverter.convert(v, String.class);
    }

    public <T> T get(String key, Class<T> classOfT) {
        Object v = this.dataFields.get(key);
        return v == null ? null : TypeConverter.convert(v, classOfT);
    }

    public Object getObject(String key) {
        return this.dataFields.get(key);
    }

    public Map<String, Object> getAllFields() {
        return this.dataFields;
    }

    public void put(String key, Object value) {
        this.dataFields.put(key, value);
    }
}
