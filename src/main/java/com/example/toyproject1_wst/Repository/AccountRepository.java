package com.example.toyproject1_wst.Repository;

import com.example.toyproject1_wst.Model.Account;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;


@Repository
public interface AccountRepository  extends JpaRepository<Account,Long> {
    Optional<Account> findByUserId(String userId); // userId로 검색하는 메서드 추가
    Optional<Account> findByUserCode(int userCode);

    @Query("SELECT a.userCode FROM Account a WHERE a.userId = :userId")
    int findUserCodeByUserId(@Param("userId") String userId);

    @Transactional
    @Modifying
    @Query("UPDATE Account a SET a.userName = :userName, a.password = :password WHERE a.userCode = :userCode")
    int updateAccount(@Param("userCode") int userCode, @Param("userName") String userName, @Param("password") String password);
}
