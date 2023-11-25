package com.micro.accounts.mapper;

import com.micro.accounts.dto.CustomerDto;
import com.micro.accounts.entity.Customers;

public class CustomerMapper {

    public static CustomerDto mapToCustomerDto(Customers customer, CustomerDto customerDto) {
        customerDto.setName(customer.getName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setMobileNumber(customer.getMobileNumber());
        return customerDto;
    }

    public static Customers mapToCustomer(CustomerDto customerDto, Customers customer) {
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setMobileNumber(customerDto.getMobileNumber());
        return customer;
    }
}
