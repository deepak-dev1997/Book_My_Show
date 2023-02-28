package com.example.Book_My_Show.EntryDtos;

import com.example.Book_My_Show.Enums.Genre;
import com.example.Book_My_Show.Enums.Language;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class MovieEntryDto {


    private String movieName;


    private Genre genre;

    private double rating;

    private int duration;


    private Language language;
}
