package com.example.toyproject1_wst.Repository;

import com.example.toyproject1_wst.Model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AccountRepository  extends JpaRepository<Account,Long> {
    Optional<Account> findByUserId(String userId); // userId로 검색하는 메서드 추가
    Optional<Account> findByUserCode(int userCode);
}
