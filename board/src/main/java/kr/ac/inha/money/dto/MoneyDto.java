package kr.ac.inha.money.dto;

import lombok.Data;

@Data
public class MoneyDto {

	private int id;

	private String incomeYn;

	private String title;

	private String category;

	private int amount;

	private String createdDate;
}
