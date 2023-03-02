package com.example.Book_My_Show.Controller;

import com.example.Book_My_Show.EntryDtos.TicketEntryDto;
import com.example.Book_My_Show.Services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    TicketService ticketService;

    @PostMapping("/book")
    public String bookTicket(@RequestBody TicketEntryDto ticketEntryDto){
    try {
        return ticketService.addTicket(ticketEntryDto);
    }catch (Exception e){
        return e.getMessage();
    }
    }

}
