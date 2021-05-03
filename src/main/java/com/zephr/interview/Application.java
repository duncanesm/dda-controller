package com.zephr.interview;
import com.zephr.interview.service.ServiceController;

import static spark.Spark.*;

public class Application {
    public static void main(String[] args) {
        port(8080);
        post("/receiver", ServiceController.handleReceiverPost);
    }
}
