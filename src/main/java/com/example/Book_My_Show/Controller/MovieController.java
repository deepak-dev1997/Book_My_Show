package com.example.Book_My_Show.Controller;

import com.example.Book_My_Show.Entities.Movie;
import com.example.Book_My_Show.EntryDtos.MovieEntryDto;
import com.example.Book_My_Show.Services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    MovieService movieService;


    @PostMapping("/add")
    public ResponseEntity<String> addMovie(@RequestBody MovieEntryDto movieEntryDto){
        try{
        return new ResponseEntity<>(movieService.addMovie(movieEntryDto),HttpStatus.CREATED);
        }catch (Exception e){
        return new ResponseEntity<>(e.getMessage()+"Movie cannot be added", HttpStatus.BAD_REQUEST);
        }
    }


}
