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

        if(theaterEntryDto.getName()==null||theaterEntryDto.getLocation()==null){
            throw new Exception("Name and location should valid");
        }


        //first we need to create seat
        //then I need to add the same int theater attribute
        //before that i need to create a theater object from theater entry DTo


        Theater theaterEntity = TheaterConvertors.convertEntryToEntity(theaterEntryDto);
        List<TheaterSeat> theaterSeatEntityList = createTheaterSeats(theaterEntryDto,theaterEntity);

        theaterEntity.setTheaterSeatList(theaterSeatEntityList);
        theatreRepository.save(theaterEntity);

        return "Theater Added successfully";
    }

    private List<TheaterSeat> createTheaterSeats(TheaterEntryDto theaterEntryDto, Theater theat){
        int noClassicSeats = theaterEntryDto.getClassicSeatCount();
        int noPremiumSeats = theaterEntryDto.getPremiumSeatCount();

        List<TheaterSeat> theaterSeatEntityList = new ArrayList<>();

        //Created the classic Seats
        for(int count = 1;count<=noClassicSeats;count++){

            //We need to make a new TheaterSeatEntity
            TheaterSeat theaterSeatEntity = TheaterSeat.builder()
                    .seatType(SeatType.CLASSIC).seatNo(count+"C")
                    .theater(theat).build();

            theaterSeatEntityList.add(theaterSeatEntity);
        }


        //Create the premium Seats
        for(int count=1;count<=noPremiumSeats;count++){

            TheaterSeat theaterSeatEntity = TheaterSeat.builder().
                    seatType(SeatType.PREMIUM).seatNo(count+"P").theater(theat).build();

            theaterSeatEntityList.add(theaterSeatEntity);
        }

        //Not saving the child here
        return theaterSeatEntityList;

    }

}
