package kr.ac.inha.money.dto;

import lombok.Data;

@Data
public class DashboardDto {

	private String category;

	private int income;
	
	private int expense;

	private String createdDate;
}
