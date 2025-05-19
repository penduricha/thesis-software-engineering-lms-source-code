package com.example.backend_service.controllers;


import com.example.backend_service.models.DetailMarkStudent;

import com.example.backend_service.services.DetailMarkStudentService;

import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class DetailMarkStudentController {
    private final DetailMarkStudentService detailMarkStudentService;

    public DetailMarkStudentController(DetailMarkStudentService detailMarkStudentService) {
        this.detailMarkStudentService = detailMarkStudentService;
    }

    @GetMapping("/detail-mark-student/get-list-detail-mark-student/{markStudentID}")
    public ResponseEntity<List<DetailMarkStudent>> getDetailMarkStudentsByMarkStudent_MarkStudentID(@PathVariable Long markStudentID)
            throws HttpClientErrorException {
        return ResponseEntity.ok(detailMarkStudentService
                .getDetailMarkStudentsByMarkStudent_MarkStudentID(markStudentID));
    }
}
