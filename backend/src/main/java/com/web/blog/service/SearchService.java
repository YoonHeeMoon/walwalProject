package com.web.blog.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.web.blog.model.search.Search;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class SearchService {
    // private static String ARIBNB_URL = "https://www.airbnb.co.kr/s/gangneung/homes?tab_id=home_tab&refinement_paths%5B%5D=%2Fhomes&checkin=2020-07-30&checkout=2020-07-31&adults=2&source=structured_search_input_header&search_type=search_query";
    public ArrayList<Search> getAirbnbDatas(String selected, String checkin, String checkout,String people) throws IOException {
        String ARIBNB_URL = "https://www.airbnb.co.kr/s/" +selected + "/homes?tab_id=home_tab&refinement_paths%5B%5D=%2Fhomes&checkin=" + checkin + "&checkout=" + checkout + "&adults=" + people + "&source=structured_search_input_header&search_type=search_query";
        
        ArrayList<Search> list = new ArrayList<>();
        Document doc = Jsoup.connect(ARIBNB_URL).get();

        // doc으로 받아온 html 코드에서 class name으로 가격 분류.
        // 전체 데이터
        Elements prices = doc.select("span._1p7iugi"); 
        Elements names = doc.select("div._1c2n35az");
        Elements images = doc.getElementsByTag("img");
        Elements links = doc.select("a._gjfol0");
        Elements infos = doc.select("div._kqh46o");
        // Elements images = doc.select("div._1h6n1zu img[src]");

        // 가격 스트링 추출!
       for(Element price : prices){
            StringBuffer p = new StringBuffer();
            Search searchEle = new Search();
            for(int i = price.text().length() - 1; ; i--){
                if (price.text().charAt(i) == '₩'){
                    p.append(price.text().substring(i+1, price.text().length()));                    
                    break;
                } 
            }
            searchEle.setS_price(p.toString());
            list.add(searchEle);
        }

        // 이름 추출!
        int idx = 0;
        for(Element name: names) {
            list.get(idx++).setS_name(name.text());
        }

        ////////////// 나중에 고쳐야 할 것 같음 ///////////
        // 이미지 추출!
        idx = 0;
        boolean flag = true; // 이미지 태그를 모두 받아오기 때문에, 제일 첫번째의 로고 이미지는 빼주기 위한 Boolean
        for(Element image: images) {
            if (flag) {
                flag = false;
                continue;
            }

            String url = image.attr("src").toString();
            list.get(idx++).setS_img(url);
        }

        //링크추출!!
        idx = 0;
        String s ;
        for(Element link: links) {

            s= "https://airbnb.com";

            s+=link.attr("href").toString();
            list.get(idx++).setS_link(s);
        }
        idx = 0;
        for(Search s1 :list) {
        	s1.setS_type("1");
        }

        //부가설명 추출!!
        idx = 0;
        int idx2 = 0;
        StringBuffer p = new StringBuffer();
        for(Element info: infos) {
            p.append(info.text());
            p.append(" · ");
            if(idx%2 == 1){
                p.delete(p.length()-2, p.length()-1);
                list.get(idx2++).setS_info(p.toString());
                p.setLength(0);
            }
            idx++;
        }

        return list;
    }
    
    
}