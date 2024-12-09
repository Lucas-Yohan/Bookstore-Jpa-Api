package com.lyohan.bookstore_jpa.services;

import com.lyohan.bookstore_jpa.dtos.BookRecordDto;
import com.lyohan.bookstore_jpa.models.BookModel;
import com.lyohan.bookstore_jpa.models.ReviewModel;
import com.lyohan.bookstore_jpa.repositories.AuthorRepository;
import com.lyohan.bookstore_jpa.repositories.BookRepository;
import com.lyohan.bookstore_jpa.repositories.PublisherRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;


@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository, PublisherRepository publisherRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.publisherRepository = publisherRepository;
    }

    @Transactional
    public BookModel saveBook(BookRecordDto bookDto){
        BookModel newBook = new BookModel();
        newBook.setTitle(bookDto.title());
        newBook.setPublisher(publisherRepository.findById(bookDto.publisherId()).get());
        newBook.setAuthors(new HashSet<>(authorRepository.findAllById(bookDto.authorsId())));

        ReviewModel reviewModel = new ReviewModel();
        reviewModel.setComment(bookDto.reviewComment());
        reviewModel.setBook(newBook);
        newBook.setReview(reviewModel);

        return bookRepository.save(newBook);

    }

    @Transactional(readOnly = true)
    public List<BookModel> getAllBooks(){
        return bookRepository.findAll();
    }

    @Transactional
    public void deleteBook(UUID id){
        bookRepository.deleteById(id);
    }


}
