package com.anytime.studymaker.domain.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
@Data
public class Header<T> {
    //    API 통신시간
    private LocalDateTime transactionTime;

    //    API 응답 코드
    private String resultCode;

    //    API 부가 설명
    private String description;

    private T data;

    //    OK
    public static <T> Header<T> ok() {
        return Header.<T>builder().transactionTime(LocalDateTime.now()).resultCode("200").description("OK").build();
    }

    //    data OK
    public static <T> Header<T> ok(T data) {
        return Header.<T>builder().transactionTime(LocalDateTime.now()).resultCode("200").description("OK").data(data).build();
    }

    //    ERROR
    public static <T> Header<T> error(String description) {
        return Header.<T>builder().transactionTime(LocalDateTime.now()).resultCode("400").description(description).build();
    }
}
