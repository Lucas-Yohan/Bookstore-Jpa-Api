package com.lyohan.bookstore_jpa.controllers;


import com.lyohan.bookstore_jpa.dtos.BookRecordDto;
import com.lyohan.bookstore_jpa.models.BookModel;
import com.lyohan.bookstore_jpa.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/bookstore/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @PostMapping
    public ResponseEntity<BookModel> saveBook(@RequestBody BookRecordDto bookDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.saveBook(bookDto));
    }

    @GetMapping
    public ResponseEntity<List<BookModel>> getAllBooks(){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getAllBooks());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable UUID id){
        bookService.deleteBook(id);
        return ResponseEntity.status(HttpStatus.OK).body("Book deleted successfully");
    }

}
