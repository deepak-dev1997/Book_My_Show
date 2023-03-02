package com.example.Book_My_Show.Convertors;

import com.example.Book_My_Show.Entities.Ticket;
import com.example.Book_My_Show.EntryDtos.TicketEntryDto;

public class TicketConvertors {

    public static Ticket entryToEntity(TicketEntryDto ticketEntryDto){
        Ticket ticket= Ticket.builder().build();
        return  ticket;
    }
}
