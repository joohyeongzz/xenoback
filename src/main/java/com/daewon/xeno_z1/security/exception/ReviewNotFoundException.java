package com.daewon.xeno_z1.security.exception;

import jakarta.persistence.EntityNotFoundException;

public class ReviewNotFoundException extends EntityNotFoundException {
    public ReviewNotFoundException() {
        super("유효하지 않은 리뷰 아이디입니다.");
    }
}
