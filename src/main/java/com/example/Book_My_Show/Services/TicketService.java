package com.example.Book_My_Show.Services;

import com.example.Book_My_Show.Convertors.TicketConvertors;
import com.example.Book_My_Show.Entities.Show;
import com.example.Book_My_Show.Entities.ShowSeat;
import com.example.Book_My_Show.Entities.Ticket;
import com.example.Book_My_Show.Entities.User;
import com.example.Book_My_Show.EntryDtos.TicketEntryDto;
import com.example.Book_My_Show.Repository.ShowRepository;
import com.example.Book_My_Show.Repository.TicketRepository;
import com.example.Book_My_Show.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    ShowRepository showRepository;

    @Autowired
    UserRepository userRepository;

    public String addTicket(TicketEntryDto ticketEntryDto) throws  Exception{
        //steps to follow
        //convert ticket entry Dto to ticket Entity
        Ticket ticket= TicketConvertors.entryToEntity(ticketEntryDto);

        //validation to check whether requested seats are available or not
        boolean isValidRequest=checkValidityOfRequestedSeats(ticketEntryDto);
        if(isValidRequest==false) {
            throw new Exception("Seats you have selected is already booked . Please try with some other seats");
        }

        //we should consider that requested seats are valid

        //now calculate the total amount
        //need to get price of classic and premium shows and get the totla amount

        Show show= showRepository.findById(ticketEntryDto.getShowId()).get();
        List<ShowSeat> showSeatList=show.getShowSeatList();
        List<String> requestedSeats=ticketEntryDto.getRequestedSeats();

        int total=0;
        for (ShowSeat s : showSeatList){
            String seatNo=s.getSeatNo();
            if(requestedSeats.contains(seatNo)){
                total+=s.getPrice();
                s.setBooked(true);
                s.setBookedAt(new Date());
            }
        }

        ticket.setTotalAmount(total);

        //setting the other attributes
        ticket.setMovieName(show.getMovie().getMovieName());

        ticket.setShowDate(show.getShowDate());
        ticket.setShowTime(show.getShowTime());

        ticket.setTheatreName(show.getTheater().getName());


        //set the seat
        String allottedSeats=getAllotedSeatsfromShowSeat(ticketEntryDto.getRequestedSeats());
        ticket.setBookedSeats(allottedSeats);



        //setting the foreign keys
        ticket.setShow(show);
        User user=userRepository.findById(ticketEntryDto.getUserId()).get();
        ticket.setUser(user);

        //save the parents
        ticket= ticketRepository.save(ticket);
        show.getTicketList().add(ticket);
        user.getBookedTickets().add(ticket);

        showRepository.save(show);
        userRepository.save(user);





        return "Tickets booked successfully";
    }

    private boolean checkValidityOfRequestedSeats(TicketEntryDto ticketEntryDto){
        int showId=ticketEntryDto.getShowId();
        List<String> requestedSeats=ticketEntryDto.getRequestedSeats();

        Show show=showRepository.findById(showId).get();

        List<ShowSeat> showSeatList = show.getShowSeatList();

        for(ShowSeat ss: showSeatList){
            String seatNo= ss.getSeatNo();
            if(requestedSeats.contains(seatNo) && ss.isBooked()==true){
                return false;
            }
        }
        return true;


    }

    private String getAllotedSeatsfromShowSeat(List<String> list){
        StringBuilder stringBuilder=new StringBuilder();
        for(String s: list){
            stringBuilder.append(s+",");
        }
        return stringBuilder.toString();
    }
}
