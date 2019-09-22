package com.infotech.book.ticket.app.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infotech.book.ticket.app.dao.TicketBookingDao;
import com.infotech.book.ticket.app.entities.Ticket;

@Service
public class TicketBookingService {

	Logger logger= LoggerFactory.getLogger(TicketBookingService.class);

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private TicketBookingDao ticketBookingDao;
	
	public Ticket getTicketById(Integer ticketId) throws JsonProcessingException {
		Ticket ticket=ticketBookingDao.findById(ticketId).orElse(null);
		logger.info("------------------>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+ objectMapper.writeValueAsString(ticket));
		return ticket;
	}
	public Iterable<Ticket> getAllBookedTickets() {
		return ticketBookingDao.findAll();
	}
}
