package com.example.fibonacci_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FibonacciController {

    @GetMapping("/fibonacci")
    public String getFibonacci(@RequestParam(value = "n", defaultValue = "10") int n) {
        if (n < 0) {
            return "Input must be a non-negative integer.";
        }
        return "Fibonacci sequence up to " + n + " terms: " + calculateFibonacci(n);
    }

    private String calculateFibonacci(int n) {
        StringBuilder result = new StringBuilder();
        int num1 = 0, num2 = 1;

        for (int i = 0; i < n; i++) {
            result.append(num1).append(i == n - 1 ? "" : ", ");
            int nextNum = num1 + num2;
            num1 = num2;
            num2 = nextNum;
        }
        return result.toString();
    }
}
