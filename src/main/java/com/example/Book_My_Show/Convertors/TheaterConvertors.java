package com.example.Book_My_Show.Convertors;

import com.example.Book_My_Show.Entities.Theater;
import com.example.Book_My_Show.EntryDtos.TheaterEntryDto;

public class TheaterConvertors {

    public static Theater convertEntryToEntity(TheaterEntryDto theaterEntryDto){
        Theater theater=Theater.builder().name(theaterEntryDto.getName()).location(theaterEntryDto.getLocation())
                .build();
        return theater;
    }
}
