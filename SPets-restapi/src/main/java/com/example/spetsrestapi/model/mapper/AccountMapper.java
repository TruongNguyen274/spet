package com.example.spetsrestapi.model.mapper;

import com.example.spetsrestapi.model.entity.Account;
import com.example.spetsrestapi.model.request.AccountRequest;
import com.example.spetsrestapi.model.response.AccountResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    // Map Entity to Response
    AccountResponse toResponse(Account user);

    List<AccountResponse> toResponseList(List<Account> userList);

    // Map Request to Entity
    Account toEntity(AccountRequest accountRequest);

    List<Account> toEntityList(List<AccountRequest> accountRequestList);

}
