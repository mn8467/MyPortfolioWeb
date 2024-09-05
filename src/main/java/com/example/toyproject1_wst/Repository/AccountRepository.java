package com.example.toyproject1_wst.Repository;

import com.example.toyproject1_wst.Model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AccountRepository  extends JpaRepository<Account,Long> {
    List<Account> findAllByEmail(String email);

}
