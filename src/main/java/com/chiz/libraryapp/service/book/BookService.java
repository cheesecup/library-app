package com.chiz.libraryapp.service.book;

import com.chiz.libraryapp.domain.book.Book;
import com.chiz.libraryapp.domain.user.User;
import com.chiz.libraryapp.dto.book.request.BookCreateRequest;
import com.chiz.libraryapp.dto.book.request.BookLoanRequest;
import com.chiz.libraryapp.dto.book.request.BookReturnRequest;
import com.chiz.libraryapp.repository.book.BookRepository;
import com.chiz.libraryapp.repository.user.UserLoanHistoryRepository;
import com.chiz.libraryapp.repository.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final UserLoanHistoryRepository userLoanHistoryRepository;
    private final UserRepository userRepository;

    public BookService(BookRepository bookRepository,
                       UserLoanHistoryRepository userLoanHistoryRepository,
                       UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userLoanHistoryRepository = userLoanHistoryRepository;
        this.userRepository = userRepository;
    }

    //책 등록
    @Transactional
    public void saveBook(BookCreateRequest request) {
        bookRepository.save(new Book(request));
    }

    //책 대출 정보 저장
    @Transactional
    public void loanBook(BookLoanRequest request) {
        //책 존재여부 확인
        Book book = bookRepository.findByName(request.getBookName())
                .orElseThrow(IllegalArgumentException::new);
        
        //반납 여부 확인
        if (userLoanHistoryRepository.existsByBookNameAndIsReturn(request.getBookName(), false)) {
            throw new IllegalArgumentException("@@@해당 책은 현재 반납되지 않았습니다@@@");
        }
        
        //유저 존재여부 확인
        User user = userRepository.findByName(request.getUserName())
                .orElseThrow(IllegalArgumentException::new);
        
        //대출 정보 저장
        user.loanBook(request.getBookName());
    }
    
    //책 반납 정보 저장
    @Transactional
    public void returnBook(BookReturnRequest request) {
        User user = userRepository.findByName(request.getUserName())
                .orElseThrow(IllegalArgumentException::new);

        user.returnBook(request.getBookName());

    }
}
