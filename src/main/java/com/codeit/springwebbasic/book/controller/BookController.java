package com.codeit.springwebbasic.book.controller;

import com.codeit.springwebbasic.book.dto.requst.BookCreateRequestDto;
import com.codeit.springwebbasic.book.dto.response.BookResponseDto;
import com.codeit.springwebbasic.book.entity.Book;
import com.codeit.springwebbasic.book.repository.BookRepository;
import com.codeit.springwebbasic.book.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor    // final 변수를 초기화하는 매개값을 전달받는 생성자
public class BookController {

    private final BookService bookService;
    private final BookRepository bookRepository;

    /* 도서 등록
    {
        "title": string,
        "author": string,
        "isbn": string,
        "publisher": string,
        "publishedDate": date
    }
     */
    @RequestMapping(value = "/api/books", method = RequestMethod.POST)
    public BookResponseDto createBook(@Valid @RequestBody BookCreateRequestDto requestDto) {
        Book book = bookService.createBook(requestDto);
        return BookResponseDto.from(book);
    }

    @GetMapping("/api/books/{id}")
    public BookResponseDto getBook(@PathVariable Long id) {
        Book book = bookService.getBook(id);
        return BookResponseDto.from(book);
    }
}
