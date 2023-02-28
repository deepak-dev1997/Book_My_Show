package com.example.Book_My_Show.Services;

import com.example.Book_My_Show.Convertors.TheaterConvertors;
import com.example.Book_My_Show.Entities.Theater;
import com.example.Book_My_Show.Entities.TheaterSeat;
import com.example.Book_My_Show.EntryDtos.TheaterEntryDto;
import com.example.Book_My_Show.Enums.SeatType;
import com.example.Book_My_Show.Repository.TheaterSeatReposittory;
import com.example.Book_My_Show.Repository.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheaterService {
    @Autowired
    TheatreRepository theatreRepository;

    @Autowired
    TheaterSeatReposittory theaterSeatReposittory;

    public String addTheater(TheaterEntryDto theaterEntryDto) throws Exception{

        //first we need to create seat
        //then I need to add the same int theater attribute
        //before that i need to create a theater object from theater entry DTo
        Theater theater= TheaterConvertors.convertEntryToEntity(theaterEntryDto);

        List<TheaterSeat> theaterSeatList= createTheaterSeats(theaterEntryDto,theater);


        return "Theater and seats added successfully";
    }

    private List<TheaterSeat> createTheaterSeats(TheaterEntryDto theaterEntryDto, Theater theat){
        int noClassicSeats=theaterEntryDto.getClassicSeatCount();
        int noPremiumSeats=theaterEntryDto.getPremiumSeatCount();

        List<TheaterSeat> theaterSeatList=new ArrayList<>();
        for(int a=1;a<=noClassicSeats;a++){
            TheaterSeat theaterSeat= TheaterSeat.builder()
                    .seatType(SeatType.CLASSIC).seatNo(a+"C").theater(theat).build();

            theaterSeatList.add(theaterSeat);

        }
        for(int a=1;a<=noPremiumSeats;a++){
            TheaterSeat theaterSeat=TheaterSeat.builder().seatType(SeatType.PREMIUM).seatNo(a+"P").theater(theat).build();
            theaterSeatList.add(theaterSeat);
        }
        theaterSeatReposittory.saveAll(theaterSeatList);
        return theaterSeatList;
    }

}
