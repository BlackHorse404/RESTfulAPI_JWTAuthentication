package com.example.demo_restfulapi.Controller;

import com.example.demo_restfulapi.Models.User;
import com.example.demo_restfulapi.Models.Book;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/")
public class HomeController {
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
}
