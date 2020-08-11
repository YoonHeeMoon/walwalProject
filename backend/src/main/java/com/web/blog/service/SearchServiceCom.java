package com.web.blog.service;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.web.blog.model.search.Search;

@Service
public class SearchServiceCom {
	//private static String URL = "http://localhost:8080/csearch?location=CITY%3A1693265%3AUNKNOWN%3AUNKNOWN&destinationid=1693265&destination=%EC%B6%98%EC%B2%9C,%20%EA%B0%95%EC%9B%90,%20%ED%95%9C%EA%B5%AD&checkin=2020-08-11&checkout=2020-08-12&room=1&adult=2&children=0";
//	public ArrayList<Search> getComDatas(String location, String destinationid, String destination, String checkin, String checkout, String room, String adult, String children) throws IOException {
//		String COM_URL = "https://kr.hotels.com/search.do?resolved-location="+location+"&f-amid=64&destination-id="+destinationid+"&q-destination="+destination+"&q-check-in="+checkin+"&q-check-out="+checkout+"&q-rooms="+room+"&q-room-0-adults="+adult+"&q-room-0-children="+children;
	public ArrayList<Search> getComDatas(String selected,String checkout,String checkin,String people) throws IOException {
		
		String COM_LOCA = null,COM_DID = null,COM_QD = null;
		String room="1";
		
		if(selected.equals("seoul")) {
			COM_LOCA= "CITY%3A759818%3AUNKNOWN%3AUNKNOWN";
			COM_DID="759818";
			COM_QD="%EC%84%9C%EC%9A%B8,%20%ED%95%9C%EA%B5%AD";
		}
		else if(selected.equals("incheon")) {
			COM_LOCA= "CITY%3A757364%3AUNKNOWN%3AUNKNOWN";
			COM_DID="757364";
			COM_QD="%EC%9D%B8%EC%B2%9C,%20%EC%9D%B8%EC%B2%9C,%20%ED%95%9C%EA%B5%AD";
		}
		else if(selected.equals("daejeon")) {
			COM_LOCA= "CITY%3A754647%3AUNKNOWN%3AUNKNOWN";
			COM_DID="754647";
			COM_QD="%EB%8C%80%EC%A0%84,%20%ED%95%9C%EA%B5%AD";
		}
		else if(selected.equals("daegu")) {
			COM_LOCA= "CITY%3A1639042%3AUNKNOWN%3AUNKNOWN";
			COM_DID="1639042";
			COM_QD="%EB%8C%80%EA%B5%AC,%20%ED%95%9C%EA%B5%AD";
		}
		else if(selected.equals("Gwangju")) {
			COM_LOCA= "CITY%3A759560%3AUNKNOWN%3AUNKNOWN";
			COM_DID="759560";
			COM_QD="%EA%B4%91%EC%A3%BC,%20%EC%A0%84%EB%82%A8,%20%ED%95%9C%EA%B5%AD";
		}
		else if(selected.equals("Busan")) {
			COM_LOCA= "CITY%3A1639028%3AUNKNOWN%3AUNKNOWN";
			COM_DID="1639028";
			COM_QD="%EB%B6%80%EC%82%B0,%20%ED%95%9C%EA%B5%AD";
		}
		else if(selected.equals("Ulsan")) {
			COM_LOCA= "CITY%3A761311%3AUNKNOWN%3AUNKNOWN";
			COM_DID="761311";
			COM_QD="%EC%9A%B8%EC%82%B0,%20%ED%95%9C%EA%B5%AD";
		}
		else if(selected.equals("Jeju")) {
			COM_LOCA= "REGION%3A1644457%3AUNKNOWN%3AUNKNOWN";
			COM_DID="1644457";
			COM_QD="%EC%A0%9C%EC%A3%BC%EB%8F%84,%20%EC%A0%9C%EC%A3%BC%EC%8B%9C,%20%ED%95%9C%EA%B5%AD";
		}
		else if(selected.equals("gyeong-gi")) {
			COM_LOCA= "REGION%3A10235259%3AUNKNOWN%3AUNKNOWN";
			COM_DID="10235259";
			COM_QD="%EA%B2%BD%EA%B8%B0,%20%ED%95%9C%EA%B5%AD";
		}
		else if(selected.equals("Gangwon-do")) {
			COM_LOCA= "REGION%3A10235260%3AUNKNOWN%3AUNKNOWN";
			COM_DID="10235260";
			COM_QD="%EA%B0%95%EC%9B%90,%20%ED%95%9C%EA%B5%AD";
		}
		else if(selected.equals("chungcheongnamdo")) {
			COM_LOCA= "REGION%3A10235262%3AUNKNOWN%3AUNKNOWN";
			COM_DID="10235262";
			COM_QD="%EC%B6%A9%EC%B2%AD%EB%82%A8%EB%8F%84,%20%ED%95%9C%EA%B5%AD";
		}
		else if(selected.equals("Chung-cheongbukdo")) {//
			COM_LOCA= "GEO_LOCATION%3A%EB%8C%80%ED%95%9C%EB%AF%BC%EA%B5%AD%20%EC%B6%A9%EC%B2%AD%EB%B6%81%EB%8F%84%7C36.8%7C127.7%3AGEOCODE%3AUNKNOWN";
			COM_DID="";
			COM_QD="%EB%8C%80%ED%95%9C%EB%AF%BC%EA%B5%AD%20%EC%B6%A9%EC%B2%AD%EB%B6%81%EB%8F%84";
		}
		else if(selected.equals("Jeollabukdo")) {
			COM_LOCA= "CITY%3A10405232%3AUNKNOWN%3AUNKNOWN";
			COM_DID="10405232";
			COM_QD="%EC%A0%84%EB%9D%BC%EB%B6%81%EB%8F%84,%20%EC%A0%84%EB%B6%81,%20%ED%95%9C%EA%B5%AD";
		}
		else if(selected.equals("Jeollanam-do")) {
			COM_LOCA= "REGION%3A10235264%3AUNKNOWN%3AUNKNOWN";
			COM_DID="10235264";
			COM_QD="%EC%A0%84%EB%82%A8,%20%ED%95%9C%EA%B5%AD";
		}
		else if(selected.equals("Gyeongsangbuk-do")) {
			COM_LOCA= "REGION%3A10235265%3AUNKNOWN%3AUNKNOWN";
			COM_DID="10235265";
			COM_QD="%EA%B2%BD%EC%83%81%EB%B6%81%EB%8F%84,%20%ED%95%9C%EA%B5%AD";
		}
		else if(selected.equals("Gyeongsangnam-do")) {
			COM_LOCA= "REGION%3A10235266%3AUNKNOWN%3AUNKNOWN";
			COM_DID="10235266";
			COM_QD="%EA%B2%BD%EC%83%81%EB%82%A8%EB%8F%84,%20%ED%95%9C%EA%B5%AD";
		}
		
		
		String COM_URL = "https://kr.hotels.com/search.do?resolved-location="+COM_LOCA+"&f-amid=64&destination-id="+COM_DID+"&q-destination="+COM_QD+"&q-check-in="+checkin+"&q-check-out="+checkout+"&q-rooms=1&q-room-0-adults="+people+"&q-room-0-children=0";
		ArrayList<Search> list = new ArrayList<>();
		Document doc = Jsoup.connect(COM_URL).userAgent("chrome").get();
		Elements articles = doc.select("article");
		
		for(Element article : articles) {
			Search searchVO = new Search();
			searchVO.setS_price(article.select("div.price").select("ins").text().replace("₩",""));
			System.out.println(article.select("div.price").select("ins").text().replace("₩",""));
			if(searchVO.getS_price().equals("")) continue;
			
			searchVO.setS_name(article.select(".p-name").text());
			System.out.println(article.select(".p-name").text());
			
			searchVO.setS_link("https://kr.hotels.com"+article.select("section.hotel-wrap aside a.cta").attr("href").toString());
			System.out.println("https://kr.hotels.com"+article.select("section.hotel-wrap aside a.cta").attr("href").toString());
			
			searchVO.setS_img(article.select("img.featured-img-desktop").attr("style").toString().split("\'")[1].split("\\?")[0]);
			System.out.println(article.select("img.featured-img-desktop").attr("style").toString().split("\'")[1].split("\\?")[0]);
			
			searchVO.setS_type("2");
			list.add(searchVO);
			
		}
		
		
//	    Elements prices = doc.select("div.price");
//        Elements names = doc.select(".p-name");
//        Elements links = doc.select("section.hotel-wrap aside a.cta");
//        Elements images = doc.select("img.featured-img-desktop");
//       
//	    //가격 추출
//	    for(Element price : prices) {
//	           StringBuffer p = new StringBuffer();
//	            Search searchEle = new Search();
//	            
//	            for(int i = price.text().length() - 1; ; i--){
//	                if (price.text().charAt(i) == '₩'){
//	                    p.append(price.text().substring(i+1, price.text().length()));                    
//	                    break;
//	                } 
//	            }
//	            searchEle.setS_price(p.toString());
//	            list.add(searchEle);
//	        }
//	    
//        // 이름 추출
//        int idx = 0;
//        for(Element name: names) {
//            list.get(idx++).setS_name(name.text());
//        }
//        
//       // 이미지 추출
//        idx = 0;
//        for(Element image: images) {
//            String url = image.attr("style").toString();
//            //System.out.println(url.toString().split("\'")[1].split("\\?")[0]);
//            list.get(idx++).setS_img(url.toString().split("\'")[1].split("\\?")[0]);
//        }     
//        
//        
//        //링크
//        idx = 0;
//        String s ;
//        for(Element link: links) {
//            s= "https://kr.hotels.com";
//            s+=link.attr("href").toString();
////            System.out.println(s);
//            list.get(idx++).setS_link(s);
//        }

        
		return list;
	}
	
	
	
	
}