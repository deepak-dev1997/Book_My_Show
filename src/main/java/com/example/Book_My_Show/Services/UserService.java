package com.example.Book_My_Show.Services;

import com.example.Book_My_Show.Entities.User;
import com.example.Book_My_Show.EntryDtos.UserEntryDto;
import com.example.Book_My_Show.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public void addUser(UserEntryDto userEntryDto){
        //Here we need to convert and save
        /*
        in old method is you create an object and set attributes
        or we can you the builder annnotatin .
        which can help us to create an object in faster way
        Importance -
        Write on the top of that class whose object had to be created
        it always require all Args constructor

         */
        User user=User.builder().age(userEntryDto.getAge()).name(userEntryDto.getName()).email(userEntryDto.getEmail()).address(userEntryDto.getAddress()).mobileNo(userEntryDto.getMobileNo()).build();

        //This is to set all of the attributes in one go

        userRepository.save(user);
    }
}
