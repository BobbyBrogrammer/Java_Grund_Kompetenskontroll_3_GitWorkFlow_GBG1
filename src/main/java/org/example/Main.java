package org.example;

import org.example.service.CompletionService;


public class Main {
    public static void main(String[] args) {
            CompletionService completionService = new CompletionService();

            completionService.completeProcess(
                    "kund@example.com",
                    "ABC123",
                    "car"
            );
        }
    }
