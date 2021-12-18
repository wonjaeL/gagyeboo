package kr.ac.inha.board.login.service;

import kr.ac.inha.board.login.dto.MembersDto;

public interface UserService {
	void updateUserRole(MembersDto dto) throws Exception;
}
