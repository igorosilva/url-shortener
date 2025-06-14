package com.example.urlShortener.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.io.Serial;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ShortenerException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -7719557710192039251L;

    private String description;
    private HttpStatus httpStatus;
}
