package com.example.Book_My_Show.Entities;

import com.example.Book_My_Show.Enums.Genre;
import com.example.Book_My_Show.Enums.Language;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "movies")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
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


    //this is parent of show
    @OneToMany(mappedBy = "movie" , cascade = CascadeType.ALL)
    private List<Show> showList=new ArrayList<>();






}
