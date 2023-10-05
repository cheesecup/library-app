package com.chiz.libraryapp.dto.book.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookLoanRequest {

    private String userName;
    private String bookName;

}
