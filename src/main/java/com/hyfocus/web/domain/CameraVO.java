package com.hyfocus.web.domain;

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
public class CameraVO {

	private int camNo;
	private String camName;
	private int camCount;
	private String camType;
}
