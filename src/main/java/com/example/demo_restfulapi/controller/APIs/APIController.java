package com.example.demo_restfulapi.controller.APIs;

import com.example.demo_restfulapi.models.Book;
import com.example.demo_restfulapi.models.Signature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/")
public class APIController {
    @Value("classpath:static/keystore/keystore.p12")
    Resource resourceFile;
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

    @GetMapping("/getsignature")
    public ResponseEntity<Object> getSignature(){
        char[] pwdArray = "ducphat".toCharArray();
        try{
            //load keystore
            String alias = "tomcat";
            KeyStore ks = KeyStore.getInstance("pkcs12");
            ks.load(new FileInputStream(resourceFile.getFile()), pwdArray);

            //get key and cert from keystore
            Certificate cert = ks.getCertificate(alias);
            PrivateKey priKey = (PrivateKey)ks.getKey(alias, pwdArray);
            Signature signature = new Signature(cert, priKey, alias);
            return new ResponseEntity<>(signature,HttpStatus.OK);

        }
        catch (Exception err){
            System.err.println(err.getMessage());
        }
        return new ResponseEntity<>(null,HttpStatus.UNAUTHORIZED);
    }

}
