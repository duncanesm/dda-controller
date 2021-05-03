package com.zephr.interview.service;

import java.util.Map;

public class IncomingRequest {
    private String type;
    Map<String, Object> details;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, Object> getDetails() {
        return details;
    }

    public void setDetails(Map<String, Object> details) {
        this.details = details;
    }
}
