package com.example.Book_My_Show.Controller;

import com.example.Book_My_Show.EntryDtos.TheaterEntryDto;
import com.example.Book_My_Show.Services.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/theater")
public class TheaterContrioller {

    @Autowired
    TheaterService theaterService;


    public String addTheater(@RequestBody TheaterEntryDto theaterEntryDto){
        try{
            return theaterService.addTheater(theaterEntryDto);
        }catch (Exception e){
            return "Theater cannot be added";
        }

    }

}
