//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ocbc.ms.notification.cn.dto;

import java.util.HashMap;
import java.util.Map;

public class DasServiceResponse {
    private final Map<String, Object> dataFields = new HashMap();

    public DasServiceResponse() {
    }

    public boolean hasField(String key) {
        return this.dataFields.containsKey(key);
    }

    public Object getObject(String key) {
        return this.dataFields.get(key);
    }

    public String get(String key) {
        return (String)this.get(key, String.class);
    }

    public <T> T get(String key, Class<T> classOfT) {
        Object v = this.dataFields.get(key);
        return v == null ? null : TypeConverter.convert(v, classOfT);
    }

    public Map<String, Object> getFields() {
        return this.dataFields;
    }

    public void put(String key, Object value) {
        this.dataFields.put(key, value);
    }

    public boolean getResult() {
        String resultCode = (String)this.get("DasResultCode", String.class);
        return resultCode != null && !resultCode.isEmpty() && resultCode.equals("0000");
    }

    public String getRetCode() {
        return (String)this.get("DasResultCode", String.class);
    }

    public String getRetMsg() {
        return (String)this.get("DasResultMessage", String.class);
    }

    public String getRetDetails() {
        return (String)this.get("DasResultDetails", String.class);
    }
}
