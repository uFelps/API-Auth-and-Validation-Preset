package com.api.test.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/professores")
public class ProfessorController {

    //coordenador

    @PreAuthorize("hasRole('COORDENADOR')")
    @GetMapping
    public ResponseEntity<List<String>> listarAulas(){
        return ResponseEntity.ok(Arrays.asList("Djalma", "TOn", "Alexandre"));
    }
}
