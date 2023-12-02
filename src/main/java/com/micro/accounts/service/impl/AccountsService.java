package com.micro.accounts.service.impl;

import com.micro.accounts.constants.AccountsConstants;
import com.micro.accounts.constants.CustomerConstant;
import com.micro.accounts.dto.AccountsDto;
import com.micro.accounts.dto.CustomerDto;
import com.micro.accounts.entity.Accounts;
import com.micro.accounts.entity.Customers;
import com.micro.accounts.exception.CustomerAlreadyExistsException;
import com.micro.accounts.exception.ResourceNotFoundException;
import com.micro.accounts.mapper.AccountsMapper;
import com.micro.accounts.mapper.CustomerMapper;
import com.micro.accounts.repository.AccountRepository;
import com.micro.accounts.repository.CustomerRepository;
import com.micro.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountsService implements IAccountsService {

    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {

         Customers customer = CustomerMapper.mapToCustomer(customerDto , new Customers());

        Optional<Customers>optionalCustomer=customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if (optionalCustomer.isPresent()){
            throw new CustomerAlreadyExistsException(CustomerConstant.mobilePhoneAlreadyExistsException +  optionalCustomer.get().getMobileNumber());
        }
        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("Emp 1");
        customerRepository.save(customer);


         accountRepository.save(createNewAccount(customer));
    }



    private Accounts createNewAccount(Customers customer){
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        Long randomAccountNumber=1000000000L + new Random().nextInt(900000000);


        newAccount.setAccountNumber(randomAccountNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        return newAccount;
    }

    @Override
    public CustomerDto fetchAccount(String mobileNumber) {

        Customers customer= customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
              ()->new ResourceNotFoundException("Customer","Mobile Number",mobileNumber));

        Accounts account = accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                ()->new ResourceNotFoundException("account","Customer Id",customer.getCustomerId().toString())
        );

     CustomerDto customerDto= CustomerMapper.mapToCustomerDto(customer , new CustomerDto());

     customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(account, new AccountsDto()));

     return customerDto;
    }

    @Override
    public Boolean updateAccount(CustomerDto customerDto) {

        boolean isUpdated = false;
        AccountsDto accountsDto = customerDto.getAccountsDto();
        if(accountsDto !=null ){
            Accounts accounts = accountRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "AccountNumber", accountsDto.getAccountNumber().toString())
            );
            AccountsMapper.mapToAccounts(accountsDto, accounts);
            accounts = accountRepository.save(accounts);

            Long customerId = accounts.getCustomerId();
            Customers customer = customerRepository.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "CustomerID", customerId.toString())
            );
            CustomerMapper.mapToCustomer(customerDto,customer);
            customerRepository.save(customer);
            isUpdated = true;
        }
        return  isUpdated;

    }

    @Override
    public Boolean deleteAccount( String mobileNumber) {
        Customers customer= customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()->new ResourceNotFoundException("Customer","Mobile Number",mobileNumber));
        accountRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        return true;
    }

}
