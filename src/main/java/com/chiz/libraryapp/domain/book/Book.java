package com.chiz.libraryapp.domain.book;

import com.chiz.libraryapp.dto.book.request.BookCreateRequest;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    @Column(nullable = false)
    private String name;

    protected Book() {
    }

    public Book(BookCreateRequest request) {
        if (request.getName() == null || request.getName().isBlank()) {
            throw new IllegalArgumentException(String.format("잘못된 name(%s)이 들어왔습니다.", name));
        }
        this.name = request.getName();
    }
}
