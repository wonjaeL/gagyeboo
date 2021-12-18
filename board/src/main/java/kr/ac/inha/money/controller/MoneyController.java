package kr.ac.inha.money.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.ac.inha.money.dto.MoneyDto;
import kr.ac.inha.money.service.MoneyService;

@Controller
public class MoneyController {
	@Autowired
	private MoneyService moneyService;

	@RequestMapping("/")
	public String defaultPage() throws Exception {
		return "redirect:/money/list.do";
	}

	@RequestMapping("/money/list.do")
	public ModelAndView pageList() throws Exception {
		ModelAndView mv = new ModelAndView("/money/list");
		List<MoneyDto> list = moneyService.selectMoneyList();
		mv.addObject("list", list);
		return mv;
	}

	@RequestMapping("/money/add.do")
	public ModelAndView pageAdd() throws Exception {
		ModelAndView mv = new ModelAndView("/money/add");
		List<MoneyDto> list = moneyService.selectMoneyList();
		mv.addObject("list", list);
		return mv;
	}

	@RequestMapping("/money/dashboard.do")
	public ModelAndView pageDashboard() throws Exception {
		ModelAndView mv = new ModelAndView("/money/dashboard");
		List<MoneyDto> list = moneyService.selectMoneyList();
		mv.addObject("list", list);
		return mv;
	}

	@PostMapping("/money")
	public ResponseEntity<String> createItem(@RequestBody MoneyDto moneyDto) throws Exception {
		try {
			moneyService.insertMoney(moneyDto);
			return ResponseEntity.status(HttpStatus.CREATED).body("Inserted");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@GetMapping(value = "/money/dashboard")
	public ResponseEntity<Object> getMonthlySummaryList() {
		try {
			return ResponseEntity.ok(moneyService.getMonthlySummaryList());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@GetMapping(value = "/money/dashboard/category")
	public ResponseEntity<Object> getCategorySummaryList() {
		try {
			return ResponseEntity.ok(moneyService.getCategorySummaryList());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

}