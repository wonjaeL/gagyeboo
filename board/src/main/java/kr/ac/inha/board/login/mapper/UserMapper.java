package kr.ac.inha.board.login.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.ac.inha.board.login.dto.MembersDto;

@Mapper
public interface UserMapper {
	void updateUserRole(MembersDto dto);
}
