package com.example.zhangirakzhan.service;


import com.example.zhangirakzhan.util.ContinuousCompletableFuture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
@Slf4j
public class CompletableFutureService {
    private static final String API_ENDPOINT_1 = "http://localhost:8090/products/";
    private static final String API_ENDPOINT_2 = "http://localhost:8090/api/auth/all";
    private static final String API_ENDPOINT_3 = "http://localhost:8090/api/auth/email/1";



    public void requestingData() throws ExecutionException, InterruptedException {
        ContinuousCompletableFuture<String> future1;
        ContinuousCompletableFuture<String> future2;
        ContinuousCompletableFuture<String> future3;



        future1 = ContinuousCompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
                requestApiData(API_ENDPOINT_1);
            } catch (InterruptedException | IOException ex) {
                log.error("Error during ContinuousCompletableFuture execution", ex);
            }
            return "successfully completed";
        });

        future1.thenAcceptAsync(s -> {
            long t = future1.getElapsedTime();
            log.info("Elapsed {} ms \"{}\"", t, s);
        });

        future2 = ContinuousCompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
                requestApiData(API_ENDPOINT_2);
            } catch (InterruptedException | IOException ex) {
                log.error("Error during ContinuousCompletableFuture execution", ex);
            }
            return "successfully completed";
        });

        future2.thenAcceptAsync(s -> {
            long t = future1.getElapsedTime();
            log.info("Elapsed {} ms \"{}\"", t, s);
        });

        future3 = ContinuousCompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
                requestApiData(API_ENDPOINT_3);
            } catch (InterruptedException | IOException ex) {
                log.error("Error during ContinuousCompletableFuture execution", ex);
            }
            return "successfully completed";
        });

        future3.thenAcceptAsync(s -> {
            long t = future1.getElapsedTime();
            log.info("Elapsed {} ms \"{}\"", t, s);
        });

        CompletableFuture<Void> combinedFuture
                = ContinuousCompletableFuture.allOf(future1, future2, future3);
        combinedFuture.get();

        log.info(">>>>>  FUTURE ONE {}", future1.isDone());
        log.info(">>>>>  FUTURE TWO {}", future2.isDone());
        log.info(">>>>> FUTURE THREE {}", future3.isDone());

    }


    private String requestApiData(String apiUrl) throws IOException {
        StringBuilder response = new StringBuilder();

        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        }

        return response.toString();
    }
}
