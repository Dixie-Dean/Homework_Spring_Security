package com.example.dixie.controller;

import jakarta.annotation.security.RolesAllowed;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AppController {

    @GetMapping("/greet")
    public ResponseEntity<String> greet() {
        return ResponseEntity.ok("Greetings from the AppController!");
    }

    @GetMapping("/farewell")
    public ResponseEntity<String> tellFarewell() {
        return ResponseEntity.ok("Fare thee well, user! See you soon!");
    }

    @GetMapping("/ban")
    public ResponseEntity<String> ban() {
        return ResponseEntity.ok("User was banned!");
    }

    @GetMapping("/delete-message")
    public ResponseEntity<String> deleteMessage() {
        return ResponseEntity.ok("Message was deleted");
    }

    @GetMapping("/read")
    @Secured("ROLE_READ")
    public ResponseEntity<String> readMethodOnly() {
        return ResponseEntity.ok("READ");
    }

    @GetMapping("/write")
    @RolesAllowed("WRITE")
    public ResponseEntity<String> writeMethodOnly() {
        return ResponseEntity.ok("WRITE");
    }

    @GetMapping("/write-delete")
    @PreAuthorize("hasAnyRole('ROLE_WRITE', 'ROLE_DELETE')")
    public ResponseEntity<String> writeOrDeleteMethod() {
        return ResponseEntity.ok("PreAuthorize: WRITE or DELETE");
    }

    @GetMapping("/username")
    @PreAuthorize("#username == authentication.principal.username")
    public ResponseEntity<String> checkUsername(@RequestParam String username) {
        return ResponseEntity.ok(username + " entered!");
    }

}
