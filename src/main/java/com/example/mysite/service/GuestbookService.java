package com.example.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.mysite.domain.Guestbook;
import com.example.mysite.repository.GuestbookRepository;

@Service
@Transactional
public class GuestbookService {
	
	@Autowired
	private GuestbookRepository guestbookRepository;
	
	public List<Guestbook> getMessageList() {
		return guestbookRepository.findAll();
	}
	
	public List<Guestbook> getMessageList( Long startNo ) {
		return guestbookRepository.findAll( startNo );
	}	
	
	public boolean deleteMessage( Guestbook guestbook ){
		return guestbookRepository.remove( guestbook );
	}
	
	public void writeMessage( Guestbook guestbook ) {
		guestbookRepository.save(guestbook);
	}
}