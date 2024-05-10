package com.example.spetsrestapi.security;

import com.example.spetsrestapi.model.entity.Account;
import com.example.spetsrestapi.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountService.findByUsername(username);
        if (account == null) {
           throw new UsernameNotFoundException("User Not Found!");
        }

        UserSecurity userSecurity = new UserSecurity(account);
        return userSecurity;
    }

}