package com.example.mysite.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.mysite.domain.Guestbook;
import com.example.mysite.dto.JSONResult;
import com.example.mysite.service.GuestbookService;

//@CrossOrigin( origins = { "http://localhost:8080" }, maxAge = 4800 )
@Controller( "guestbookAPIController" )
@RequestMapping( "/guestbook/api" )
public class GuestbookController {
	
	@Autowired
	private GuestbookService guestbookService;
	
	@ResponseBody
	@RequestMapping( value="/delete", method=RequestMethod.POST )
	public JSONResult delete( @ModelAttribute Guestbook guestbook ){
		boolean result = guestbookService.deleteMessage( guestbook );
		return JSONResult.success( result ? guestbook.getNo() : -1 );
	}
	
	@ResponseBody
	@RequestMapping( "/list" )
	public JSONResult list( 
		@RequestParam( value="sno", required=true, defaultValue="0" ) Long startNo ) {
		List<Guestbook> list = guestbookService.getMessageList( startNo );
		return JSONResult.success( list );
	}
	
	@ResponseBody	
	@RequestMapping( value="/add", method=RequestMethod.POST )
	public JSONResult add( @RequestBody Guestbook guestbook ) {
		guestbookService.writeMessage( guestbook );
		return JSONResult.success( guestbook );
	}
}
