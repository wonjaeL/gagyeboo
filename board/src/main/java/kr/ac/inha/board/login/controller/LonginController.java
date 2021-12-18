package kr.ac.inha.board.login.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.ac.inha.board.login.dto.MembersDto;
import kr.ac.inha.board.login.dto.RegistDto;
import kr.ac.inha.board.login.dto.ResultDto;
import kr.ac.inha.board.login.service.LoginService;
import kr.ac.inha.board.login.service.UserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LonginController {
	@Autowired
	private LoginService loginService;

	@Autowired
	private UserService userService;

	@RequestMapping("/login/login.do")
	public ModelAndView login() throws Exception {
		return new ModelAndView("login/login");
	}

	@RequestMapping("/login/register.do")
	public ModelAndView register() throws Exception {
		return new ModelAndView("login/register");
	}

	@PostMapping(value = "/login/register.ajax", produces = "application/json; charset=utf8")
	@ResponseBody
	public String saveRegisterInfo(@RequestBody RegistDto mbr) throws Exception {
		ObjectMapper mapper = new ObjectMapper();

		ResultDto result = new ResultDto();

		log.info(mbr.toString());

		String rtn = loginService.saveRegisterInfo(mbr);
		if (rtn.equals("Ok")) {
			result.setStatus("Ok");
			result.setErrMsg("");
		} else {
			result.setStatus("Error");
			result.setErrMsg(rtn);
		}

		String jsonString = mapper.writeValueAsString(result);

		log.info("result = " + result);

		return jsonString;
	}

	@RequestMapping(value = "/login/certification.do", method = RequestMethod.GET)
	public String certification(MembersDto mbr) throws Exception {
		String rtn = loginService.saveCertification(mbr);
		if (rtn.equals("Ok")) {
			return "redirect:/money/list.do";
		}
		return "redirect:/login/login.do?error";
	}

	@RequestMapping("/user/list.do")
	public ModelAndView userList() throws Exception {
		ModelAndView mv = new ModelAndView("/user/list");
		List<MembersDto> list = loginService.selectAllMembers();
		mv.addObject("list", list);
		return mv;
	}

	@PostMapping(value = "/user", produces = "application/json; charset=utf8")
	public ResponseEntity<String> updateUserRole(@RequestBody MembersDto membersDto) throws Exception {
		try {
			userService.updateUserRole(membersDto);
			return ResponseEntity.status(HttpStatus.CREATED).body("권한이 변경되었습니다");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@RequestMapping("/login/no-permission.do")
	public ModelAndView noPermission() throws Exception {
		return new ModelAndView("/user/no-permission");
	}

}
