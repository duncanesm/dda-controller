package com.zephr.interview.shape;

import com.zephr.interview.shape.tools.Pair;
import com.zephr.interview.shape.tools.ShapeBase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class Poly extends ShapeBase {
    private List<Pair> pairs = new ArrayList<>();

    public Poly(List<Pair> pairs) {
        this.pairs = pairs;
    }

    @Override
    public CompletableFuture<Double> calculateArea() {
        double area = 0;
        for (int i=0; i<pairs.size(); i++) {
            int x1 = pairs.get(i).x;
            int x2 = pairs.get((i+1) % pairs.size()).x;
            int y1 = pairs.get(i).y;
            int y2 = pairs.get((i+1) % pairs.size()).y;
            area += (x2 - x1) * (y1 + y2) / 2;
        }
        CompletableFuture cf = CompletableFuture.completedFuture(area);
        return cf;
    }

    @Override
    public CompletableFuture<Double> calculatePerimeter() {
       double perimeter = 0;
       for (int i=0; i<pairs.size(); i++) {
           int x1 = pairs.get(i).x;
           int x2 = pairs.get((i+1) % pairs.size()).x;
           int y1 = pairs.get(i).y;
           int y2 = pairs.get((i+1) % pairs.size()).y;
           perimeter += Math.sqrt( Math.pow(x2-x1, 2) + Math.pow(y2-y1, 2) );
       }
        CompletableFuture cf = CompletableFuture.completedFuture(perimeter);
        return cf;
    }
}
