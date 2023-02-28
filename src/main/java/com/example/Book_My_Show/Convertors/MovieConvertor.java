package com.example.Book_My_Show.Convertors;

import com.example.Book_My_Show.Entities.Movie;
import com.example.Book_My_Show.EntryDtos.MovieEntryDto;

public class MovieConvertor {

    public static Movie convertEntrytoEntity(MovieEntryDto movieEntryDto){

        Movie movie=Movie.builder().genre(movieEntryDto.getGenre()).movieName(movieEntryDto.getMovieName())
                .rating(movieEntryDto.getRating()).duration(movieEntryDto.getDuration())
                .language(movieEntryDto.getLanguage()).build();

        return movie;
    }



}
