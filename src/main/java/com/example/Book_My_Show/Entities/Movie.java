package com.example.Book_My_Show.Entities;

import com.example.Book_My_Show.Enums.Genre;
import com.example.Book_My_Show.Enums.Language;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "movies")
@Data
@NoArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true,nullable = false)
    private String movieName;

    @Enumerated(value = EnumType.STRING)
    private Genre genre;

    private double rating;

    private int duration;

    @Enumerated(value = EnumType.STRING)
    private Language language;






}