package kr.ac.inha.board.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.inha.board.login.dto.MembersDto;
import kr.ac.inha.board.login.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;

	@Override
	public void updateUserRole(MembersDto dto) throws Exception {
		userMapper.updateUserRole(dto);
	}
}
