package com.web.blog.controller.search;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.blog.model.BasicResponse;
import com.web.blog.model.search.Search;
import com.web.blog.model.search.SearchDetail;
import com.web.blog.service.SearchDetailService;
import com.web.blog.service.SearchService;
import com.web.blog.service.SearchServiceCom;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
        @ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
        @ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
        @ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

@CrossOrigin(origins = { "http://localhost:3000" })
@RestController
public class SearchController {

    @Autowired
    SearchService service;
    
    @Autowired
    SearchServiceCom cService;

    @GetMapping("/search")
    @ApiOperation(value = "검색")
    public ResponseEntity<List<Search>> login(@RequestParam(required = true) final String selected,
    @RequestParam(required = true) final String checkin,@RequestParam(required = true) final String checkout,
    @RequestParam(required = true) final String people,
    final HttpSession session) throws IOException {
        List<Search> list = service.getAirbnbDatas(selected,checkin,checkout,people);
        List<Search> clist = cService.getComDatas(selected,checkout,checkin,people);
        list.addAll(clist);
        
        Collections.sort(list, new Comparator<Search>(){
            @Override
            public int compare(Search s1, Search s2) {
                if (Integer.parseInt(s1.getS_price().replace(",", ""))< (Integer.parseInt(s2.getS_price().replace(",", "")))) {
                    return -1;
                } else{
                    return 1;
                }
            }
        });
   

        return new ResponseEntity<List<Search>>(list, HttpStatus.OK);
    }

    @Autowired
    SearchDetailService dService;
    
    @GetMapping("/detailsearch")
    @ApiOperation(value = "세부검색")
    public ResponseEntity<SearchDetail> dSearch(@RequestParam(required = true) final String link,
    @RequestParam(required = true) final String type,final HttpSession session)throws IOException,
            InterruptedException {
        
        SearchDetail dlist = dService.getAirbnbDatas(link,type);

        return new ResponseEntity<SearchDetail>(dlist, HttpStatus.OK);
    }
}