package kr.ac.inha.board.login.service;

import java.util.List;

import kr.ac.inha.board.login.dto.MembersDto;
import kr.ac.inha.board.login.dto.RegistDto;

public interface LoginService {
	List<MembersDto> selectAllMembers() throws Exception;

	String saveRegisterInfo(RegistDto mbr) throws Exception;

	String saveCertification(MembersDto mbr) throws Exception;
}
