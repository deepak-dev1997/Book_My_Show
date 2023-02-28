package com.example.Book_My_Show.Convertors;

import com.example.Book_My_Show.Entities.Show;
import com.example.Book_My_Show.EntryDtos.ShowEntryDto;

public class ShowConvertors {

    public static Show entryToEntity(ShowEntryDto showEntryDto){
        Show show=Show.builder().showDate(showEntryDto.getLocalDate()).showTime(showEntryDto.getLocalTime())
                .showType(showEntryDto.getShowType()).build();
        return show;
    }
}
