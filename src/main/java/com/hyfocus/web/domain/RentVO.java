package com.hyfocus.web.domain;

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
}
