package com.example.Book_My_Show.Repository;

import com.example.Book_My_Show.Entities.TheaterSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheaterSeatReposittory extends JpaRepository<TheaterSeat,Integer> {
}
