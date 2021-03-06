package com.web.blog.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.web.blog.model.search.SearchDetail;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class SearchDetailService {
    // private static String ARIBNB_URL = "https://www.airbnb.co.kr/s/gangneung/homes?tab_id=home_tab&refinement_paths%5B%5D=%2Fhomes&checkin=2020-07-30&checkout=2020-07-31&adults=2&source=structured_search_input_header&search_type=search_query";
    public SearchDetail getAirbnbDatas(String link,String type) throws IOException, InterruptedException {
        // String ARIBNB_URL = "link";
        
        String ARIBNB_URL = link;
        String types = type;
        SearchDetail detailsearch = new SearchDetail();
        Document doc = Jsoup.connect(ARIBNB_URL).userAgent("chrome").get();
        // doc으로 받아온 html 코드에서 class name으로 가격 분류.
        // 전체 데이터
        Elements dong = new Elements();
        if(types.equals("1")){
           dong = doc.select("a._5twioja");
        }
        else if(types.equals("2")){
            dong = doc.select("span.postal-addr");
        }
        // Elements images = doc.getElementsByTag("img");
        // Elements images = doc.select("div._1h6n1zu img[src]");
        while(dong.text().length() == 0 && types.equals("1")){
            Thread.sleep(500);
            doc = Jsoup.connect(ARIBNB_URL).get();
            dong = doc.select("a._5twioja");
        }
        // 동 추출!
        int idx = 0;
        
        detailsearch.setD_dong(dong.text());
        

         //이미지추출
         if(types.equals("1")){
         Elements images = doc.getElementsByTag("img");
         for(Element image: images) {
             
             String url = image.attr("src").toString();
             if(idx == 0){
                 detailsearch.setD_img1(url);
             }
             if(idx == 1){
                 detailsearch.setD_img2(url);
             }
             if(idx == 2){
                 detailsearch.setD_img3(url);
             }
             idx++;
             if(idx == 3)
                 break;
         }
        }
        else if(types.equals("2")){
            Elements images2 = doc.select(".canvas.widget-carousel-enabled").select("li.image");
            for(Element e :images2){
                String url = e.attr("data-desktop").toString();
                if(idx == 0){
                    detailsearch.setD_img1(url);
                }
                if(idx == 1){
                    detailsearch.setD_img2(url);
                }
                if(idx == 2){
                    detailsearch.setD_img3(url);
                }
                idx++;
                if(idx == 3)
                    break;
            }
        }
         
        // // 이미지 추출!
        // idx = 0;
        // boolean flag = true; // 이미지 태그를 모두 받아오기 때문에, 제일 첫번째의 로고 이미지는 빼주기 위한 Boolean
        // for(Element image: images) {
        //     if (flag) {
        //         flag = false;
        //         continue;
        //     }

        //     String url = image.attr("src").toString();
        //     list.get(idx++).setD_img(url);
        // }



        return detailsearch;
    }
}