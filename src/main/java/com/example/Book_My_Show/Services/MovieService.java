package com.example.Book_My_Show.Services;

import com.example.Book_My_Show.Convertors.MovieConvertor;
import com.example.Book_My_Show.Entities.Movie;
import com.example.Book_My_Show.EntryDtos.MovieEntryDto;
import com.example.Book_My_Show.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public String addMovie(MovieEntryDto movieEntryDto) throws  Exception{
        Movie movie= MovieConvertor.convertEntrytoEntity(movieEntryDto);
        movieRepository.save(movie);
        return "Movie added Successfully";
    }
}
