package com.example.spetsrestapi;

import com.example.spetsrestapi.model.entity.Account;
import com.example.spetsrestapi.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SpetsRestapiApplication implements CommandLineRunner {

    @Autowired
    private AccountService accountService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(SpetsRestapiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Account account = accountService.findByUsername("admin");
        if (account == null) {
            account = new Account();
            account.setFullName("admin");
            account.setUsername("admin");
            account.setEmail("admin@mail.com");
            account.setPassword(passwordEncoder.encode("123456"));
            account.setRole("ADMIN");
            account.setStatus(true);
            accountService.save(account);
        }
    }

}
