package com.example.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.mysite.domain.Guestbook;
import com.example.mysite.service.GuestbookService;

@Controller
@RequestMapping( "/guestbook" )
public class GuestbookController {
	
	@Autowired
	private GuestbookService guestbookService;
	
	@RequestMapping( "" )
	public String index( Model model ){
		List<Guestbook> list = guestbookService.getMessageList();
		model.addAttribute( "list", list );
		return "guestbook/index";
	}
	
	@RequestMapping( value="/delete/{no}", method=RequestMethod.GET )
	public String deleteform( @PathVariable( "no" ) Long no, Model model ){
		model.addAttribute( "no", no );
		return "guestbook/delete";
	}
	
	@RequestMapping( value="/delete", method=RequestMethod.POST )
	public String delete( @ModelAttribute Guestbook guestbook ){
		System.out.println( guestbook );
		guestbookService.deleteMessage( guestbook );
		return "redirect:/guestbook";
	}
	
	@RequestMapping( value="/add", method=RequestMethod.POST )
	public String add( @ModelAttribute Guestbook guestbook ) {
		guestbookService.writeMessage( guestbook );
		return "redirect:/guestbook";
	}
	
	@RequestMapping( "/ajax" )
	public String ajax() {
		return "guestbook/index-ajax";
	}
}