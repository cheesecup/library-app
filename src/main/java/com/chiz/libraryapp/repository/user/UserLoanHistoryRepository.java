package com.chiz.libraryapp.repository.user;

import com.chiz.libraryapp.domain.user.UserLoanHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLoanHistoryRepository extends JpaRepository<UserLoanHistory, Long> {

    boolean existsByBookNameAndIsReturn(String bookName, boolean isReturn);

}
