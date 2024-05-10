package com.example.spetsrestapi.service;

import com.example.spetsrestapi.model.entity.Account;

import java.util.List;

public interface AccountService {

    List<Account> findAll();

    Account findById(long id);

    Account findByUsername(String username);

    Account save(Account user);

    boolean deleteById(long id);

}
