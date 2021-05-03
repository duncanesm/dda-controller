package com.zephr.interview.utils;

import com.zephr.interview.service.ResponsePayload;

import java.util.Map;

import static com.zephr.interview.util.ShapeUtils.dataToJson;
import static com.zephr.interview.util.ShapeUtils.prettyPrintJson;

public class TestUtils {
    public static void printResult(String shape, Map<String, Object> parameters, ResponsePayload payload,
                                   double expectedArea, double expectedPerimeter) {
        System.out.println("-----------------------------------------------------");
        System.out.println(shape + " Rest Test Request:  \n" + dataToJson(parameters));
        System.out.println(shape + " Rest Test Response: \n" + dataToJson(payload));
        System.out.println(shape + " Area      Calculated Correctly: calculated:"
                + payload.getArea() + " expected: " + expectedArea);
        System.out.println(shape + " Perimeter Calculated Correctly: calculated:"
                + payload.getPerimeter() + " expected: " + expectedPerimeter);
    }

    public static void printResult(String shape, String parameters, ResponsePayload payload,
                                   double expectedArea, double expectedPerimeter) {
        System.out.println("-----------------------------------------------------");
        System.out.println(shape + " Rest Test Request:  \n" + prettyPrintJson(parameters));
        System.out.println(shape + " Rest Test Response: \n" + dataToJson(payload));
        System.out.println(shape + " Area      Calculated Correctly: calculated:"
                + payload.getArea() + " expected: " + expectedArea);
        System.out.println(shape + " Perimeter Calculated Correctly: calculated:"
                + payload.getPerimeter() + " expected: " + expectedPerimeter);
    }

    /*
     */
}
