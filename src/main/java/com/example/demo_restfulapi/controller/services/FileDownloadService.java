package com.example.demo_restfulapi.controller.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

@Service
public class FileDownloadService {
    private Path foundFile;
    private Path dirPath;

    @Autowired
    public FileDownloadService(Environment env){
        dirPath = Paths.get(env.getProperty("app.file.signed-dir", "./uploads/signedFiles"));
    }

    public Resource getFileAsResource(String fileCode) throws IOException {

        Files.list(dirPath).forEach(file -> {
            if (file.getFileName().toString().startsWith(fileCode)) {
                foundFile = file;
            }
        });

        if (foundFile != null) {
            return new UrlResource(foundFile.toUri());
        }

        return null;
    }
}
