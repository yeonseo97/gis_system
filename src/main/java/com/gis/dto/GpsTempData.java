package com.gis.dto;

import lombok.Data;

@Data
public class GpsTempData {
	private String car;
	private String date;
	private String time;
	private double x;
	private double y;
}