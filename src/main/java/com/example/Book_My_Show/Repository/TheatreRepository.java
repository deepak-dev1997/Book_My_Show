package com.example.Book_My_Show.Repository;

import com.example.Book_My_Show.Entities.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheatreRepository extends JpaRepository<Theater,Integer> {
}
