package org.nico.ourbatis.controller;

import java.util.List;

import org.nico.ourbatis.domain.User;
import org.nico.ourbatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserMapper userMapper;
	
	@GetMapping("/")
	public List<User> list(){
		return userMapper.selectByEntityList(new User());
	}
	
	@GetMapping("/{id}")
	public User get(@PathVariable int id){
		return userMapper.selectByPrimaryKey(id);
	}
	
	@PostMapping("/")
	public int add(@RequestBody User user){
		user.setId(null);
		return userMapper.insertSelective(user);
	}
	
	@PutMapping("/")
	public int update(@RequestBody User user){
		return userMapper.updateByPrimaryKey(user);
	}
	
	@DeleteMapping("/{id}")
	public int delete(@PathVariable int id){
		return userMapper.deleteByPrimaryKey(id);
	}
}
