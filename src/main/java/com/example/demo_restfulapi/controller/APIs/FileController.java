package com.example.demo_restfulapi.controller.APIs;

import com.example.demo_restfulapi.controller.services.FileDownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class FileController {

    @Autowired
    private final FileDownloadService downloadService;

    public FileController(FileDownloadService downloadService){
        this.downloadService = downloadService;
    }

    @GetMapping("/downloadFile/{pathFile}")
    public ResponseEntity<Object> DownloadFile(@PathVariable String pathFile) throws IOException {
        Resource resource = null;
        try {
            resource = downloadService.getFileAsResource(pathFile);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }

        if (resource == null) {
            return new ResponseEntity<>("File not found", HttpStatus.NOT_FOUND);
        }

        String contentType = "application/octet-stream";
        String headerValue = "attachment; filename=\"" + resource.getFile().getName() + "\"";

        return ResponseEntity.ok()
                .contentLength(resource.getFile().length())
                .header(HttpHeaders.CONTENT_TYPE, contentType)
                .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
                .body(resource);
    }
}
