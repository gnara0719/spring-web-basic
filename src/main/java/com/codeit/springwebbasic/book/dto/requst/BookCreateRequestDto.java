package com.codeit.springwebbasic.book.dto.requst;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

// DTO (Data Transfer Object)
// 비즈니스 로직없이 순수하게 전달하고 싶은 데이터를 담는 용도로 사용하는 객체
// 요청 DTO, 응답 DTO로 나누어 사용, 꼭 필요한 데이터만 정제해서 전달하는 용도로 사용합니다.
// 1. 필요없는 정보까지 노출될 위험, 2. 도메인이 바꾸면 다른 스펙도 전부 변경 3. 검증 로직 배치의 애매함

@Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
public class BookCreateRequestDto {

    /*
    @NotNull: Null만 허용하지 않음.        ""," " 허용
    @NotEmpty: Null, "" 허용하지 않음.    " " 허용
    @NotBlank: Null, "", " " 허용하지 않음.
     */
    @NotBlank(message = "제목은 필수입니다.")
//    @Size(min = 2, max = 10, message = "title은 2글자 이상 10글자 이하여야 합니다.")
    private String title;

    @NotBlank(message = "저자는 필수입니다.")
    private String author;

    @NotBlank(message = "ISBN은 필수입니다.")
    // 정규표현식(Regular Expression): 문자열의 일정한 패턴을 표현하는 형식 언어
    @Pattern(regexp = "\\d{13}", message = "ISBM은 13자리 숫자여야 합니다.")
    private String isbn;

    @NotBlank(message = "출판사는 필수입니다.")
    private String publisher;

    @PastOrPresent(message = "출판일은 과거 또는 현재여야 합니다.")
    private LocalDate publishedDate;
}
