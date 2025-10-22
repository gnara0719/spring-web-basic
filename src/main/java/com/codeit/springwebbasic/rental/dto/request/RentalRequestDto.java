package com.codeit.springwebbasic.rental.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RentalRequestDto {

    // 문자열이 아닌 정수 타입에는 NotNull만 작성할 수 있음.
    @NotNull(message = "회원 아이디는 필수입니다.")
    private Long memberId;
    @NotNull(message = "도서 아이디는 필수입니다.")
    private Long bookId;

}
