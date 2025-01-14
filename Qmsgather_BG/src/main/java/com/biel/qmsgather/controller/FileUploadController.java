package com.biel.qmsgather.controller;

import com.biel.qmsgather.util.Result;
import io.swagger.annotations.Api;
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

    private static final String UPLOAD_DIR = "/Users/dafenqi/Downloads/uploads/";

    @PostMapping("/uploadMultipleFiles")
    public ResponseEntity<String> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        for (MultipartFile file : files) {
            if (file.isEmpty()) {
                continue; // next pls
            }
            try {
                byte[] bytes = file.getBytes();
                Path path = Paths.get(UPLOAD_DIR + file.getOriginalFilename());
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
