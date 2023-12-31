package com.micro.accounts.entity;


import com.micro.accounts.dto.AccountsDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Setter@Getter@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Customers extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "native")
    @GenericGenerator(name = "native",strategy = "native")
    @Column(name = "customer_id")
    private Long customerId;

    private String name;

    private String email;

    private String mobileNumber;



}
