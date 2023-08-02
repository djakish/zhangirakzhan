package com.example.zhangirakzhan.controller;
import com.example.zhangirakzhan.service.CompletableFutureService;
import org.springframework.web.bind.annotation.*;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(path = "/complete")
public class CompletableController {
    private final CompletableFutureService completableFutureService;
    public CompletableController(
            CompletableFutureService completableFutureService
    ) {
        this.completableFutureService = completableFutureService;
    }

    @GetMapping(value = "/")
    public void doFutures() throws ExecutionException, InterruptedException {
        this.completableFutureService.requestingData();
    }
}
