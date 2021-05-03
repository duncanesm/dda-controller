package com.zephr.interview.shape;

import com.zephr.interview.shape.tools.ShapeBase;

import java.util.concurrent.CompletableFuture;

public class Circle extends ShapeBase {

    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public CompletableFuture<Double> calculateArea() {
        CompletableFuture cf = CompletableFuture.completedFuture(Math.PI * (radius * radius));
        return cf;
    }

    @Override
    public CompletableFuture<Double> calculatePerimeter() {
        CompletableFuture cf = CompletableFuture.completedFuture(2 * Math.PI * radius);
        return cf;
    }

}
