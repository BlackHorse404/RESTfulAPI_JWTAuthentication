package com.example.demo_restfulapi.controller.services;

import com.example.demo_restfulapi.models.Account;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@EntityScan
public class RequestService {

    private static String sendRequest(String JSON, String APIdes){
        String responsereSult = null;
        try{
            // create connect to API
            URL url = new URL (APIdes);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            //config property to create API connection
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type","application/json");
            con.setDoOutput(true);


            // send JSON to API
            try(OutputStream os = con.getOutputStream()) {
                byte[] input = JSON.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // receive response from api
            try(BufferedReader br = new BufferedReader(
                    new InputStreamReader(con.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                responsereSult = response.toString();
                System.out.println(responsereSult);
            }
        }
        catch (Exception err){
            System.err.println(err.getMessage());
        }

        return responsereSult;
    }

    public static String requestLogin(Account acc){
        return sendRequest(acc.toString(),"http://localhost:8080/auth/signin");
    }
}
