package kr.ac.inha.money.service;

import java.util.List;

import kr.ac.inha.money.dto.DashboardDto;
import kr.ac.inha.money.dto.MoneyDto;

public interface MoneyService {
	List<MoneyDto> selectMoneyList() throws Exception;

	void insertMoney(MoneyDto money) throws Exception;

	List<DashboardDto> getMonthlySummaryList() throws Exception;

	List<DashboardDto> getCategorySummaryList() throws Exception;
}