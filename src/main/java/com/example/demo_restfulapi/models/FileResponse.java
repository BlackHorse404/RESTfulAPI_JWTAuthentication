package com.example.demo_restfulapi.models;

import org.springframework.boot.autoconfigure.domain.EntityScan;
@EntityScan
public class FileResponse {
    private String filename;
    private String UriDownload;
    private long size;
    private String status;

    public FileResponse(String filename, String uriDownload, long size, String status) {
        this.filename = filename;
        UriDownload = uriDownload;
        this.size = size;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public String getFilename() {
        return filename;
    }

    public String getUriDownload() {
        return UriDownload;
    }

    public long getSize() {
        return size;
    }
}
