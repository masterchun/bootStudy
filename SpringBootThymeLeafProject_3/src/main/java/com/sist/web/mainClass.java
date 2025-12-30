package com.sist.web;

import java.io.*;
import java.net.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import java.util.List;
import org.apache.ibatis.annotations.Select;

import com.sist.web.mapper.TheaterMapper;
import com.sist.web.vo.TheaterVO;

import lombok.RequiredArgsConstructor;

@Component
public class mainClass {
	@Autowired
	private static TheaterMapper mapper;
	
    public static void main(String[] args) {
        try {
        	List<TheaterVO> list = mapper.theaterListData();
        	for(TheaterVO vo:list) {
        		System.out.println(vo.getTheater_name());
        	}
        			
            String apiKey = "9025e4ec6a12fd152259c7dd9e0ae102";
            String query = URLEncoder.encode("강남 메가박스", "UTF-8");
            String targetName = "메가박스 강남";

            String apiUrl =
                    "https://dapi.kakao.com/v2/local/search/keyword.json?query=" + query;

            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", "KakaoAK " + apiKey);

            BufferedReader br =
                    new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) sb.append(line);

            br.close();

            JSONObject json = new JSONObject(sb.toString());
            JSONArray documents = json.getJSONArray("documents");

            for (int i = 0; i < documents.length(); i++) {
                JSONObject place = documents.getJSONObject(i);

                if (place.getString("place_name").equals(targetName)) {
                    System.out.println("이름: " + place.getString("place_name"));
                    System.out.println("지번 주소: " + place.getString("address_name"));
                    System.out.println("도로명 주소: " + place.getString("road_address_name"));
                    System.out.println("위도(y): " + place.getString("y"));
                    System.out.println("경도(x): " + place.getString("x"));
                    System.out.println("카카오맵 링크: " + place.getString("place_url"));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
