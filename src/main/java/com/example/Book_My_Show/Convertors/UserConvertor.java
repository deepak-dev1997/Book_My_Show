package com.example.Book_My_Show.Convertors;

import com.example.Book_My_Show.Entities.User;
import com.example.Book_My_Show.EntryDtos.UserEntryDto;

public class UserConvertor {

    //static is kept to avoid calling it via objects or instances
    public static User convertDtoToEntity(UserEntryDto userEntryDto){

    User user= User.builder().age(userEntryDto.getAge()).email(userEntryDto.getEmail()).name(userEntryDto.getName())
            .mobileNo(userEntryDto.getMobileNo()).address(userEntryDto.getEmail()).build();
    return user;
    }
}
