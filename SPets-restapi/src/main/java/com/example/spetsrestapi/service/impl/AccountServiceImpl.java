package com.example.spetsrestapi.service.impl;

import com.example.spetsrestapi.model.entity.Account;
import com.example.spetsrestapi.repository.AccountRepository;
import com.example.spetsrestapi.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account findById(long id) {
        return accountRepository.findById(id).orElse(null);
    }

    @Override
    public Account findByUsername(String username) {
        return accountRepository.findByUsername(username).orElse(null);
    }

    @Override
    public Account save(Account user) {
        return accountRepository.saveAndFlush(user);
    }

    @Override
    public boolean deleteById(long id) {
        try {
            accountRepository.deleteById(id);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

}
