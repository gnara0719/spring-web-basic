package com.codeit.springwebbasic.controller;

import com.codeit.springwebbasic.entity.Product;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/products") // 공통 url 매핑
public class ProductController {

    // DB가 없으니
    private Map<Long, Product> productMap = new HashMap<>();

    // 상품의 시리얼넘버를 순차 생성
    private long nextId = 1;

    public ProductController() {
        productMap.put(nextId, new Product(nextId, "에어컨", 100000));
        nextId++;
        productMap.put(nextId, new Product(nextId, "세탁기", 150000));
        nextId++;
        productMap.put(nextId, new Product(nextId, "공기청정기", 300000));
        nextId++;
    }

    // 1. 쿼리스트링 읽기 -> url?name=value&name=value&.....
    // 데이터가 url에 노출되어도 크게 문제없는 방식 (조회 -> 검색어, 게시물 조회에서 글 번호 등...)
    // ?id=???&price=???

//    @RequestMapping(value = "/products", method = RequestMethod.GET)
//    @GetMapping("/products")
//    public Product getProducts(HttpServletRequest request) {
//        // HttpServletRequest: 요청 관련 정보를 담은 객체
//        String id = request.getParameter("id");
//        String price = request.getParameter("price");
//        System.out.println("id = " + id);
//        System.out.println("price = " + price);
//        return productMap.get(Long.parseLong(id));
//    } -> 전통적인 방식 httpServlet을 스프링이 좋아하지않음

    // localhost:8081/products?id=???&price=???
    @GetMapping // 공통 url을 매핑해서 괄호를 지움
    public Product getProduct(
//            @RequestParam("id") Long id, -> 변수명이 같으면 생략가능!! 항상 같을 순 없으니 적는걸 권장...
            Long id, @RequestParam(value="price", required = false, defaultValue = "5000") int price) {
        // RequestParam 원형: 값, 필수인지(true, false), 없다면 기본값(default)
        System.out.println("id = " + id);
        System.out.println("price = " + price);
        return productMap.get(id);
    }

    // localhost:8081/product/34 -> 1번 상품 조회 (url 경로상에 데이터가 존재하는 경우)
    @GetMapping("/{id}")    // 얘도 공통url부분 삭제!
    public Product getProduct(@PathVariable("id") Long id) {    // 변수명이 같으면 @PathVariable Long id 라고 써도 됌
        System.out.println("id = " + id);
        return productMap.get(id);
    }

}
