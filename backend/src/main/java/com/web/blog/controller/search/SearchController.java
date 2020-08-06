package com.web.blog.controller.search;

import com.web.blog.model.search.Search;
import com.web.blog.service.SearchService;
import com.web.blog.service.SearchServiceCom;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import javax.servlet.http.HttpSession;

import com.web.blog.model.BasicResponse;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiOperation;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
        @ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
        @ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
        @ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

@CrossOrigin(origins = { "http://localhost:3000" })
@RestController
public class SearchController {

    @Autowired
    SearchService service;

    @GetMapping("/search")
    @ApiOperation(value = "검색")
    public ResponseEntity<List<Search>> login(@RequestParam(required = true) final String selected,
    @RequestParam(required = true) final String checkin,@RequestParam(required = true) final String checkout,
    @RequestParam(required = true) final String people,
    final HttpSession session) throws IOException {
        List<Search> list = service.getAirbnbDatas(selected,checkin,checkout,people);

        for (Search li : list) {
            System.out.println(li.getS_name() + " " + li.getS_img());
        }

        return new ResponseEntity<List<Search>>(list, HttpStatus.OK);
    }
    
    @Autowired
    SearchServiceCom cService;
    
    @GetMapping("/csearch")
    @ApiOperation(value = "호텔 검색")
    public ResponseEntity<List<Search>> csearch(@RequestParam(required = true) final String location,
    		@RequestParam(required = true) final String destinationid,
    		@RequestParam(required = true) final String destination,
    	    @RequestParam(required = true) final String checkin,
    	    @RequestParam(required = true) final String checkout,
    	    @RequestParam(required = true) final String room,
    	    @RequestParam(required = true) final String adult,
    	    @RequestParam(required = true) final String children,
    	    final HttpSession session) throws IOException{
    	
        	System.out.println("콘트롤러");
        	List<Search> clist = cService.getComDatas(location, destinationid, destination, checkin, checkout, room, adult, children);
        	
        	  for (Search li : clist) {
                  System.out.println(li.getS_img() + " " + li.getS_name());
              }
    
        return new ResponseEntity<List<Search>>(clist, HttpStatus.OK);
    
    }
    
}