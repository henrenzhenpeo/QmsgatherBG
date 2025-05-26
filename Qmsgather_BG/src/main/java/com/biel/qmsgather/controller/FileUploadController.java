package com.biel.qmsgather.controller;


import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/bg/FileUpload")
@CrossOrigin
@Api(tags = "bg 图片上传接口")
public class FileUploadController {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @PostMapping("/uploadMultipleFiles")
    public ResponseEntity<String> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        for (MultipartFile file : files) {
            if (file.isEmpty()) {
                continue; // next pls
            }
            try {
                byte[] bytes = file.getBytes();
                Path path = Paths.get(uploadDir + file.getOriginalFilename());
                System.out.println("path:"+path);
                Files.write(path, bytes);
            } catch (IOException e) {
                e.printStackTrace();
                return ResponseEntity.badRequest().body("Failed to upload " + file.getOriginalFilename());
            }
        }
        return ResponseEntity.ok("Upload successful");
    }

}
