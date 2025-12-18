package com.sist.web.vo;

import java.util.Date;

import lombok.Data;

/*
 * ID             NOT NULL NUMBER         
NAME           NOT NULL VARCHAR2(200)  
URL                     VARCHAR2(500)  
IMAGE                   VARCHAR2(1000) 
TELEPHONE               VARCHAR2(50)   
PRICE_MIN               NUMBER         
PRICE_MAX               NUMBER         
STREET_ADDRESS          VARCHAR2(300)  
LOCALITY                VARCHAR2(100)  
REGION                  VARCHAR2(100)  
POSTAL_CODE             VARCHAR2(20)   
COUNTRY                 VARCHAR2(100)  
DESCRIPTION             CLOB           
KEYWORDS                VARCHAR2(500)  
REG_DATE                DATE           
JJIMCOUNT               NUMBER         
REPLYCOUNT              NUMBER         
LIKE_COUNT              NUMBER         
HIT                     NUMBER    
 */
@Data
public class CampingVO {
	private int id, price_min, price_max;
	private String name, url, image, telephone, street_address, 
		locality, region, postal_code, country, description, keywords, dbday;
	private Date regdate;
}
