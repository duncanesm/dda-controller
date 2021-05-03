package com.zephr.interview.shape;

import com.zephr.interview.shape.tools.Shape;
import com.zephr.interview.shape.tools.ShapeBase;

import java.util.concurrent.CompletableFuture;

public class Doughnut extends ShapeBase {

    private Shape out;
    private Shape in;

    public Doughnut(Shape out, Shape in) {
        this.out = out;
        this.in = in;
    }

    @Override
    public CompletableFuture<Double> calculateArea() {
        CompletableFuture cf = out.calculateArea().thenCombine(in.calculateArea(), (o, i) -> o - i);
        return cf;
    }

    @Override
    public CompletableFuture<Double> calculatePerimeter() {
        CompletableFuture cf = out.calculatePerimeter().thenCombine(in.calculatePerimeter(), (o, i) -> o + i);
        return cf;
    }
}
