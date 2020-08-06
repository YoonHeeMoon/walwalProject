package com.web.blog.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.web.blog.model.search.Search;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class SearchServiceCom {
	//private static String URL = "http://localhost:8080/csearch?location=CITY%3A1693265%3AUNKNOWN%3AUNKNOWN&destinationid=1693265&destination=%EC%B6%98%EC%B2%9C,%20%EA%B0%95%EC%9B%90,%20%ED%95%9C%EA%B5%AD&checkin=2020-08-11&checkout=2020-08-12&room=1&adult=2&children=0";
	
	public ArrayList<Search> getComDatas(String location, String destinationid, String destination, String checkin, String checkout, String room, String adult, String children) throws IOException {
		String COM_URL = "https://kr.hotels.com/search.do?resolved-location="+location+"&f-amid=64&destination-id="+destinationid+"&q-destination="+destination+"&q-check-in="+checkin+"&q-check-out="+checkout+"&q-rooms="+room+"&q-room-0-adults="+adult+"&q-room-0-children="+children;
		
		ArrayList<Search> list = new ArrayList<>();
		Document doc = Jsoup.connect(COM_URL).userAgent("chrome").get();

	    Elements prices = doc.select("div.price");
        Elements names = doc.select(".p-name");
        Elements images = doc.select("img.u-photo.use-bgimage.featured-img-desktop");
        
	    //가격 추출
	    for(Element price : prices) {
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
	    
        // 이름 추출
        int idx = 0;
        for(Element name: names) {
            list.get(idx++).setS_name(name.text());
        }
        
        // 이미지 추출
        idx = 0;
        boolean flag = true; // 이미지 태그를 모두 받아오기 때문에, 제일 첫번째의 로고 이미지는 빼주기 위한 Boolean
        for(Element image: images) {
        System.out.println(image.toString());
            if (flag) {
                flag = false;
                continue;
            }

            String url = image.attr("src").toString();
            list.get(idx++).setS_img(url);
        }


		return list;
	}
}