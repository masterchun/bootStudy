package com.sist.web.vo;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;

public class main {
    public static void main(String[] args) throws IOException {

        StringBuilder urlBuilder = new StringBuilder(
                "http://api.koreafilm.or.kr/openapi-data2/wisenut/search_api/search_json2.jsp"
        );

        urlBuilder.append("?collection=kmdb_new2");
        urlBuilder.append("&nation=" + URLEncoder.encode("대한민국", "UTF-8"));
        urlBuilder.append("&ServiceKey=3D98C68XM4WU6T60SXI5");
        urlBuilder.append("&" + URLEncoder.encode("val001","UTF-8") + "=" + URLEncoder.encode("2018", "UTF-8")); 
        urlBuilder.append("&" + URLEncoder.encode("val002","UTF-8") + "=" + URLEncoder.encode("01", "UTF-8"));

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");

        System.out.println("Response code: " + conn.getResponseCode());

        BufferedReader rd;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "UTF-8"));
        }

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }

        rd.close();
        conn.disconnect();

        System.out.println(sb.toString());
    }
}
