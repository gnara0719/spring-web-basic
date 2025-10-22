package com.codeit.springwebbasic.member.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberCreateRequestDto {

    // 데이터: name: string(필수), email: string(필수), phone: string(필수, 전번 형식 검사 필요)

    @NotBlank(message = "이름은 필수입니다.")
    private String name;
    @NotBlank(message = "email은 필수입니다.")
    @Email(message = "올바른 이메일 형식이 아닙니다.") // @가 들어가 있는지 검사
    private String email;
    @NotBlank(message = "전화번호는 필수입니다.")
    @Pattern(regexp = "^\\d{3}-\\d{3,4}-\\d{4}$", message = "올바른 전화번호 형식이 아닙니다.")
    private String phone;
}
