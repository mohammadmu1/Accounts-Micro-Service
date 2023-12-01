package com.micro.accounts.service;

import com.micro.accounts.dto.CustomerDto;

public interface IAccountsService {
    //@BodyReq customerDto
    void createAccount(CustomerDto customerDto);

    CustomerDto fetchAccount(String mobileNumber);
}
