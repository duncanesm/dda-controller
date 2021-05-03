package com.zephr.interview.shape.tools;

import java.util.concurrent.CompletableFuture;

public interface Shape {

	CompletableFuture<Double> calculateArea();

	CompletableFuture<Double> calculatePerimeter();

}
