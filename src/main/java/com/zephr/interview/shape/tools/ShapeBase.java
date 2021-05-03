package com.zephr.interview.shape.tools;

import com.zephr.interview.service.ServiceHandler;

import java.util.HashMap;
import java.util.Map;

import static com.zephr.interview.util.ShapeUtils.dataToJson;

abstract public class ShapeBase implements Shape, ServiceHandler {
    @Override
    public String getResponse() {
        Map<String, Object> response = new HashMap<>();
        response.put("area", calculateArea().join());
        response.put("perimeter", calculatePerimeter().join());
        return dataToJson(response);
    }
}
