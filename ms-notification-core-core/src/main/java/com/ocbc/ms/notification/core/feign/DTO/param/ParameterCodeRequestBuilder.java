package com.ocbc.ms.notification.core.feign.DTO.param;

import java.util.ArrayList;
import java.util.List;

public class ParameterCodeRequestBuilder {
    private String code;
    private List<CommonFilter> filters = new ArrayList<>();
    private List<CommonOrder> orders = new ArrayList<>();

    public ParameterCodeRequestBuilder setCode(String code) {
        this.code = code;
        return this;
    }

    public ParameterCodeRequestBuilder addFilter(String name, Object value, String operator) {
        CommonFilter filter = new CommonFilter();
        filter.setName(name);
        filter.setValue(value);
        filter.setOperator(operator);
        this.filters.add(filter);
        return this;
    }

    public ParameterCodeRequestBuilder addOrder(String name, String value) {
        CommonOrder order = new CommonOrder();
        order.setName(name);
        order.setValue(value);
        this.orders.add(order);
        return this;
    }

    public ParameterCodeRequest build() {
        ParameterCodeRequest request = new ParameterCodeRequest();
        request.setCode(this.code);
        request.setFilter(this.filters);
        request.setOrder(this.orders);
        return request;
    }
}
