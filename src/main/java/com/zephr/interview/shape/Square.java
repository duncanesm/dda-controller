package com.zephr.interview.shape;

import com.zephr.interview.shape.tools.ShapeBase;

import java.util.concurrent.CompletableFuture;

public class Square extends ShapeBase {

    private double edge;

    public Square(double edge) {
        this.edge = edge;
    }

    public double getEdge() {
        return edge;
    }

    public void setEdge(double edge) {
        this.edge = edge;
    }

    @Override
    public CompletableFuture<Double> calculateArea() {
        CompletableFuture cf = CompletableFuture.completedFuture(edge * edge);
        return cf;
    }

    @Override
    public CompletableFuture<Double> calculatePerimeter() {
        CompletableFuture cf = CompletableFuture.completedFuture(4 * edge);
        return cf;
    }
}
