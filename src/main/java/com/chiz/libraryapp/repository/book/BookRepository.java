package com.chiz.libraryapp.repository.book;

import com.chiz.libraryapp.domain.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByName(String name);
}
