package com.zephr.interview;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zephr.interview.service.ResponsePayload;
import com.zephr.interview.shape.Poly;
import com.zephr.interview.service.ServiceController;
import com.zephr.interview.shape.tools.Pair;
import com.zephr.interview.util.ShapeUtils;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.zephr.interview.utils.TestUtils.printResult;
import static org.junit.Assert.assertEquals;
import static spark.Spark.*;

public class PolyTest {

    @Test
    public void testPost() throws IOException {
        port(8080);
        post("/receiver", ServiceController.handleReceiverPost);
        awaitInitialization();
        URL url = new URL("http://localhost:8080/receiver");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("type", "poly");

        List<Pair> pairs = new ArrayList<>();
        pairs.add(new Pair(0,0));
        pairs.add(new Pair(0,1));
        pairs.add(new Pair(1,1));
        pairs.add(new Pair(1,0));

        Map<String, Object> details = new HashMap<>();
        details.put("pairs", pairs);
        parameters.put("details", details);

        con.setDoOutput(true);
        DataOutputStream out = new DataOutputStream(con.getOutputStream());
        out.writeBytes(ShapeUtils.dataToJson(parameters));
        out.flush();
        out.close();

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
        assertEquals("Circle Area Calculated Correctly", payload.getArea(), 1, 0.001);
        assertEquals("Circle Perimeter Calculated Correctly", payload.getPerimeter(), 4, 0.001);
        printResult("Poly", parameters, payload, 1, 4);
        stop();
    }

    @Test
    public void calculateArea() {
        List<Pair> pairs = new ArrayList<>();
        pairs.add(new Pair(0,0));
        pairs.add(new Pair(0,1));
        pairs.add(new Pair(1,1));
        pairs.add(new Pair(1,0));
        Poly s = new Poly(pairs);
        assertEquals(1, s.calculateArea().join(), 0);
    }

    @Test
    public void calculatePerimeter() {
        List<Pair> pairs = new ArrayList<>();
        pairs.add(new Pair(0,0));
        pairs.add(new Pair(0,1));
        pairs.add(new Pair(1,1));
        pairs.add(new Pair(1,0));
        Poly s = new Poly(pairs);
        assertEquals(4, s.calculatePerimeter().join(), 0);
    }
}