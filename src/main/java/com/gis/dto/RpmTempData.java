package com.gis.dto;

import lombok.Data;

@Data
public class RpmTempData {
	private String car;
	private String date;
	private String time;
	private int rpmLevel;
}