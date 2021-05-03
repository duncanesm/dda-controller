package com.zephr.interview;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zephr.interview.service.ResponsePayload;
import com.zephr.interview.service.ServiceController;
import com.zephr.interview.shape.Doughnut;
import com.zephr.interview.shape.Square;
import com.zephr.interview.util.ShapeUtils;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static com.zephr.interview.utils.TestUtils.printResult;
import static org.junit.Assert.assertEquals;
import static spark.Spark.*;
import static spark.Spark.stop;

public class DoughnutTest {

    @Test
    public void testPostNo1() throws IOException {
        port(8080);
        post("/receiver", ServiceController.handleReceiverPost);
        awaitInitialization();
        URL url = new URL("http://localhost:8080/receiver");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("type", "doughnut");
        Map<String, Object> details = new HashMap<>();

        // outer shape
        Map<String, Object> out = new HashMap<>();
        out.put("type", "square");
        Map<String, Object> Outdetails = new HashMap<>();
        Outdetails.put("edge", 15);
        out.put("details", Outdetails);
        details.put("out", out);

        // inner shape
        Map<String, Object> in = new HashMap<>();
        in.put("type", "square");
        Map<String, Object> inDetails = new HashMap<>();
        inDetails.put("edge", 5);
        in.put("details", inDetails);
        details.put("in", in);

        parameters.put("details", details);

        con.setDoOutput(true);
        DataOutputStream ops = new DataOutputStream(con.getOutputStream());
        ops.writeBytes(ShapeUtils.dataToJson(parameters));
        ops.flush();
        ops.close();

        int status = con.getResponseCode();

        assertEquals("status code correct", status, 200);
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line+"\n");
        }
        br.close();
        ObjectMapper mapper = new ObjectMapper();
        ResponsePayload payload = mapper.readValue(sb.toString(), ResponsePayload.class);
        assertEquals("Square Area Calculated Correctly", payload.getArea(), 200, 0.001);
        assertEquals("Square Perimeter Calculated Correctly", payload.getPerimeter(), 80, 0.001);
        printResult("Doughnut", parameters, payload, 200, 80);
        stop();
    }

    @Test
    public void testPostNo2() throws IOException {
        port(8080);
        post("/receiver", ServiceController.handleReceiverPost);
        awaitInitialization();
        URL url = new URL("http://localhost:8080/receiver");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");

        String json = new String("{\"type\":\"doughnut\",\"details\":{\"in\":{\"type\":\"doughnut\",\"details\":{\"in\":{\"type\":\"square\",\"details\":{\"edge\":3}},\"out\":{\"type\":\"square\",\"details\":{\"edge\":10}}}},\"out\":{\"type\":\"doughnut\",\"details\":{\"in\":{\"type\":\"square\",\"details\":{\"edge\":5}},\"out\":{\"type\":\"square\",\"details\":{\"edge\":15}}}}}}");
        con.setDoOutput(true);
        DataOutputStream ops = new DataOutputStream(con.getOutputStream());
        ops.writeBytes(json);
        ops.flush();
        ops.close();

        int status = con.getResponseCode();

        assertEquals("status code correct", status, 200);
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line+"\n");
        }
        br.close();
        ObjectMapper mapper = new ObjectMapper();
        ResponsePayload payload = mapper.readValue(sb.toString(), ResponsePayload.class);
        assertEquals("Square Area Calculated Correctly", payload.getArea(), 109, 0.001);
        assertEquals("Square Perimeter Calculated Correctly", payload.getPerimeter(), 132, 0.001);
        printResult("Doughnut", json, payload, 109, 132);
        stop();
    }


    @Test
    public void calculateArea() {
        Doughnut s = new Doughnut(new Square(15), new Square(5));
        assertEquals(200, s.calculateArea().join(), 0);
    }

    @Test
    public void calculatePerimeter() {
        Doughnut s = new Doughnut(new Square(20), new Square(5));
        assertEquals(100, s.calculatePerimeter().join(), 0);
    }
}