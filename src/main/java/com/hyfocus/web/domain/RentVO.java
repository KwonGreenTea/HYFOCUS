package com.hyfocus.web.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RentVO {

	private int rentNo;
	private String camName;
	private String lensName;
	private String bag;
	private String tripod;
	private String stuInfo;
	private Date createdDate;
	private String rentChk;
	private String returnChk;
	
	private String formattedCreatedDate;

    public void setFormattedCreatedDate(Date createdDate) {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");
    	
        this.formattedCreatedDate = sdf.format(createdDate);
    }
}
