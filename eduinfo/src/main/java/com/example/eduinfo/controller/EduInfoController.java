package com.example.eduinfo.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/eduinfo/*")
public class EduInfoController {
	
	@GetMapping(value = "/pages/{pageNo}/{numOfRows}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public String getList(@PathVariable("pageNo") int pageNo,
			@PathVariable("numOfRows") int numOfRows) {
		
		String spec = "http://apis.data.go.kr/6260000/BusanTblIeduScedService/getTblIeduScedInfo?serviceKey=%2BTfdyWHgnjXZZur5Fjm75n8PPMmdQMH9sd9f6rKyFtUJTNWq%2BFo%2FeQ6AVHgM1bkaxEufRd0QR5U7wF4mzzKFdw%3D%3D";
		spec += "&resultType=json";
		spec += "&numOfRows=" + numOfRows;
		spec += "&pageNo=" + pageNo;
		
		BufferedReader reader = null;
		StringBuilder sb = new StringBuilder();
		
		try {
			URL url = new URL(spec);
			
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			
			reader = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
			
			String line = "";
			
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			
			con.disconnect();
			reader.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(sb.toString());
		return sb.toString();
		
	} // getList
	
}
