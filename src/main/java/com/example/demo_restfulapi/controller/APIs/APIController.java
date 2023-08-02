package com.example.demo_restfulapi.controller.APIs;

import com.example.demo_restfulapi.controller.services.FileStorageService;
import com.example.demo_restfulapi.models.Book;
import com.example.demo_restfulapi.models.Signature;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfSignatureAppearance;
import com.itextpdf.text.pdf.PdfStamper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


@RestController @RequestMapping("/api/")
public class APIController {
    @Value("classpath:static/keystore/keystore.p12")
    Resource resourceFile;
    @Value("classpath:static/images/bg.jpg")
    Resource backgroundSign;

    private final FileStorageService fileStorageService;

    public APIController(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    private static Map<Integer, Book> lBook = new HashMap();

    static {
        Book b1 = new Book(1,"book 1");
        Book b2 = new Book(2,"book 2");
        Book b3 = new Book(3,"book 3");

        lBook.put(b1.getId(),b1);
        lBook.put(b2.getId(),b2);
        lBook.put(b3.getId(),b3);
    }

    @GetMapping("/books")
    public ResponseEntity<Object> getListBook(){
        return new ResponseEntity<>(lBook, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addBook(@RequestBody Book newBook){
        System.out.println(newBook.toString());
        if(!newBook.isNull())
        {
            lBook.put(newBook.getId(), newBook);
            return new ResponseEntity<>("Add Successful",HttpStatus.CREATED);
        }
        else
            return new ResponseEntity<>("Add Fail",HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/uploadFileToSign")
    public ResponseEntity<Object> Signature(@RequestParam("upFile") MultipartFile upFile){

        if(upFile != null){
            String fileName = fileStorageService.storeFile(upFile);
            System.out.println(fileStorageService.getFileStorageLocation().toString());
            char[] pwdArray = "ducphat".toCharArray();
            try{
                //load keystore
                String alias = "tomcat";
                KeyStore ks = KeyStore.getInstance("pkcs12");
                ks.load(new FileInputStream(resourceFile.getFile()), pwdArray);

                //get key and cert from keystore
                Certificate[] cert = ks.getCertificateChain(alias);
                PrivateKey priKey = (PrivateKey)ks.getKey(alias, pwdArray);

                //sign file handle
                PdfReader reader = new PdfReader(upFile.getBytes());

                FileOutputStream os = new FileOutputStream(fileStorageService.getFileSignedStorageLocation().toString()+"\\signed.pdf");
                PdfStamper stamper = PdfStamper.createSignature(reader, os, '\0');
                PdfSignatureAppearance appearance = stamper.getSignatureAppearance();
                appearance.setReason("TEST SIGNATURE");
                appearance.setLocation("HCM - VN");
                appearance.setImage(Image.getInstance(backgroundSign.getFile().getAbsolutePath()));
                com.itextpdf.text.Rectangle temp = reader.getPageSize(reader.getNumberOfPages());
                appearance.setVisibleSignature(new Rectangle(temp.getWidth()-200, 140, temp.getWidth()-20, 60), reader.getNumberOfPages(), alias);
                appearance.setCrypto(priKey, cert, null, PdfSignatureAppearance.WINCER_SIGNED);
                stamper.close();

            }
            catch (Exception err) {
                System.err.println(err.getMessage());
            }
        }
        return new ResponseEntity<>("Upload success",HttpStatus.OK);
    }
}