package kr.ac.inha.money.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.inha.money.dto.DashboardDto;
import kr.ac.inha.money.dto.MoneyDto;
import kr.ac.inha.money.mapper.MoneyMapper;

@Service
public class MoneyServiceImpl implements MoneyService {
	@Autowired
	private MoneyMapper moneyMapper;

	@Override
	public List<MoneyDto> selectMoneyList() throws Exception {
		return moneyMapper.selectMoneyList();
	}

	@Override
	public void insertMoney(MoneyDto money) throws Exception {
		moneyMapper.insertMoney(money);
	}

	@Override
	public List<DashboardDto> getMonthlySummaryList() throws Exception {
		return moneyMapper.getMonthlySummaryList();
	}

	@Override
	public List<DashboardDto> getCategorySummaryList() throws Exception {
		return moneyMapper.getCategorySummaryList();
	}

}