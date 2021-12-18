package kr.ac.inha.money.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.ac.inha.money.dto.DashboardDto;
import kr.ac.inha.money.dto.MoneyDto;

@Mapper
public interface MoneyMapper {
	List<MoneyDto> selectMoneyList() throws Exception;

	void insertMoney(MoneyDto money) throws Exception;

	List<DashboardDto> getMonthlySummaryList() throws Exception;

	List<DashboardDto> getCategorySummaryList() throws Exception;
}