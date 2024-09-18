package com.springboot.blog.controller;

import com.springboot.blog.entity.Document;
import com.springboot.blog.service.Impl.DocumentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("document")
public class DocumentController {

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadImage(@RequestParam("name") String name, @RequestParam(name = "description", required = false) String description, @RequestParam("file") MultipartFile[] file) throws IOException {

        try {
            Document data = documentService.uploadDocument(name, description, file);
            return new ResponseEntity<>(data, HttpStatus.OK);
        } catch (IOException ex) {
            throw new IOException("Could not save file ", ex);
        }
    }

}
