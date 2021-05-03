package com.zephr.interview.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zephr.interview.shape.Circle;
import com.zephr.interview.shape.Doughnut;
import com.zephr.interview.shape.Poly;
import com.zephr.interview.shape.Square;
import com.zephr.interview.shape.tools.Pair;
import com.zephr.interview.shape.tools.Shape;
import spark.Request;
import spark.Response;
import spark.Route;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.zephr.interview.util.ShapeUtils.dataToJson;

public class ServiceController {

    public static Route handleReceiverPost = (Request request, Response response) -> {
        ObjectMapper mapper = new ObjectMapper();
        IncomingRequest inComingRequest = mapper.readValue(request.body(), IncomingRequest.class);
        switch (inComingRequest.getType()) {
            case "square":
                return getSquare(inComingRequest).getResponse();
            case "circle":
                return getCircle(inComingRequest).getResponse();
            case "doughnut":
                return getDoughnut(inComingRequest).getResponse();
            case "poly":
                return getPoly(inComingRequest).getResponse();
        }
        return null;
    };

    private static Square getSquare(IncomingRequest inComingRequest) {
        int edge = (int) inComingRequest.getDetails().get("edge");
        return new Square(edge);
    }

    private static Circle getCircle(IncomingRequest inComingRequest) {
        double radius = (double) inComingRequest.getDetails().get("radius");
        return new Circle(radius);
    }

    private static Doughnut getDoughnut(IncomingRequest inComingRequest) throws IOException {
        Shape outShape = getShapefromMap((Map<String, String>) inComingRequest.getDetails().get("out"));
        Shape inShape  = getShapefromMap((Map<String, String>) inComingRequest.getDetails().get("in"));
        Doughnut d = new Doughnut(outShape, inShape);
        return d;
    }

    private static Poly getPoly(IncomingRequest inComingRequest) {
        List<Pair> pairs = new ArrayList<>();
        List<Map>  pairsMap = (List<Map>) inComingRequest.getDetails().get("pairs");
        for (int i = 0; i < pairsMap.size(); i++) {
            int x = (int) pairsMap.get(i).get("x");
            int y = (int) pairsMap.get(i).get("y");
            pairs.add(new Pair(x,y));
        };
        Poly p = new Poly(pairs);
        return p;
    }

    private static Shape getShapefromMap(Map<String, String> data) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String json = dataToJson(data);
        IncomingRequest inComingRequest = mapper.readValue(json, IncomingRequest.class);
        switch (inComingRequest.getType()) {
            case "square":
                return getSquare(inComingRequest);
            case "circle":
                getCircle(inComingRequest);
            case "doughnut":
                return getDoughnut(inComingRequest);
            case "poly":
                return getPoly(inComingRequest);
        }
        return null;
    }
}
