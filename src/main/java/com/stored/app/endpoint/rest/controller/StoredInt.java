package com.stored.app.endpoint.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Random;

@RestController
public class StoredInt {

    private static final String FILE_PATH = "/tmp/stored-int.txt";

    @GetMapping("/stored-int")
    public ResponseEntity<Integer> geStoredInt() {
        File file = new File(FILE_PATH);

        try {
            if (file.exists()) {
                String content = Files.readString(file.toPath());
                int storedValue = Integer.parseInt(content.trim());
                return ResponseEntity.ok(storedValue);
            } else {
                int randomInt = new Random().nextInt(1000);
                Files.writeString(file.toPath(), String.valueOf(randomInt));
                return ResponseEntity.ok(randomInt);
            }
        } catch (IOException | NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
