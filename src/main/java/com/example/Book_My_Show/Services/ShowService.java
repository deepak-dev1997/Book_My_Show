package com.example.Book_My_Show.Services;

import com.example.Book_My_Show.Convertors.ShowConvertors;
import com.example.Book_My_Show.Entities.*;
import com.example.Book_My_Show.EntryDtos.ShowEntryDto;
import com.example.Book_My_Show.Enums.SeatType;
import com.example.Book_My_Show.Repository.MovieRepository;
import com.example.Book_My_Show.Repository.ShowRepository;
import com.example.Book_My_Show.Repository.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowService {

    @Autowired
    ShowRepository showRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    TheatreRepository theatreRepository;

    public String addShow(ShowEntryDto showEntryDto) throws Exception{
        // first create show entity from entry dto
        Show show= ShowConvertors.entryToEntity(showEntryDto);

        //now we need to set attributes for foreign key but first we need to get the movie and theatre
        Movie movie = movieRepository.findById(showEntryDto.getMovieId()).get();
        Theater theater=theatreRepository.findById(showEntryDto.getTheaterId()).get();

        //setting foreign key attributes
        show.setMovie(movie);
        show.setTheater(theater);

        //creating show list to add it in theater entity
        List<ShowSeat> showSeatList=createShowSeatEntity(showEntryDto,show);

        //we will now update the parent attribute
        List<Show> showList=movie.getShowList();
        showList.add(show);
        movie.setShowList(showList);

        movieRepository.save(movie);
        List<Show> showList1=theater.getShowList();
        showList1.add(show);
        theater.setShowList(showList1);

        theatreRepository.save(theater);

        return "Show Added successfully";



    }

    private List<ShowSeat> createShowSeatEntity(ShowEntryDto showEntryDto,Show show){
        List<ShowSeat> showSeatList=new ArrayList<>();
        List<TheaterSeat> theaterSeatList=show.getTheater().getTheaterSeatList();

        for(TheaterSeat theaterSeat : theaterSeatList){
            ShowSeat showSeat= ShowSeat.builder().seatNo(theaterSeat.getSeatNo())
                    .seatType(theaterSeat.getSeatType())
                    .build();
            if(showSeat.getSeatType().equals(SeatType.CLASSIC)){
                showSeat.setPrice(showEntryDto.getClassicSeatPrice());
            }
            else{
                showSeat.setPrice(showEntryDto.getPremiumSeatPrice());
            }
            showSeatList.add(showSeat);
            showSeat.setShow(show);

        }
        return showSeatList;

    }
}
