package org.nico.ourbatis.mapper;

import org.nico.ourbatis.domain.User;

public interface UserMapper extends BaseMapper<User, Integer>{

	public long selectUserDefined();
}
