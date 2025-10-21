package com.codeit.springwebbasic.practice;

import com.codeit.springwebbasic.entity.Product;
import lombok.*;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/practice/api/v1")
@RestController
public class PracticeController {

    @GetMapping("/welcome")
    public String welcome() { return  "Welcome to Spring Web"; }

    @GetMapping("/product/{id}")
    public String getProduct(@PathVariable String id) { return  "Product ID: " + id; }

    @GetMapping("/books")
    public String books(String author, String genre) { return "Author: " + author + ", Genre: " + genre; }

    @GetMapping("/search")
    public String search(String query, @RequestParam(required = false, defaultValue = "1") int page) {
        return "검색어: " + query + ", 요청 페이지: " + page;
    }
}

// 문제 1
// 요청 url: /practice/api/v1/welcome
// 요청 데이터: X
// 리턴: "Welcome to Spring Web!"

// 문제 2
// 요청 url: /practice/api/v1/product/{id}
// 요청 데이터: 아이디가 url에 포함되어 옴
// 리턴: "Product ID: ~~~"

// 문제 3
// 요청 url: /practice/api/v1/books
// 요청 데이터: 쿼리 스트링으로 author, genre
// 리턴: "Author: ???, Genre: ???"

// 문제 4
// 요청 url: /practice/api/v1/search
// 요청 데이터: 쿼리 스트링으로 query(검색어), page(페이지, 값이 오지 않는다면 기본값 1)
// 리턴: "검색어: ???, 요청 페이지: ???"
