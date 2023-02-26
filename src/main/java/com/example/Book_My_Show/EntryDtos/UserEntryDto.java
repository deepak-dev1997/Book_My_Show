package com.example.Book_My_Show.EntryDtos;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class UserEntryDto {

    private String name;

    private int age;


    private String email;


    private String mobileNo;

    private String address;


}
