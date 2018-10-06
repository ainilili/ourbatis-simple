package org.nico.ourbatis.controller;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.nico.ourbatis.domain.User;
import org.nico.ourbatis.entity.Page;
import org.nico.ourbatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserMapper userMapper;
	
	@GetMapping("/")
	public List<User> list(){
		return userMapper.selectList(new User());
	}
	
	@GetMapping("/{id}")
	public User get(@PathVariable int id){
		return userMapper.selectById(id);
	}
	
	@PostMapping("/")
	public int add(@RequestBody User user){
		user.setId(null);
		return userMapper.insertSelective(user);
	}
	
	@PutMapping("/")
	public int update(@RequestBody User user){
		return userMapper.update(user);
	}
	
	@DeleteMapping("/{id}")
	public int delete(@PathVariable int id){
		return userMapper.deleteById(id);
	}
	
	@PostMapping("/page")
	public List<User> page(@RequestParam long start, @RequestParam long end, @RequestParam String orderBy){
		return userMapper.selectPage(Page.start(start, end, new User().setCityId(2), orderBy));
	}
	
	@PostMapping("/test")
	@ResponseBody
	public String test(){
		StringBuilder builder = new StringBuilder();
		
		User user = new User();
		user.setAddress(UUID.randomUUID().toString());
		user.setAge(18);
		user.setBalance(new BigDecimal("10.1"));
		user.setCityId(2);
		user.setName("Nico");
		
		//自定义
//		builder.append(userMapper.selectDefined() + System.lineSeparator());
//		builder.append(userMapper.selectUserDefined() + System.lineSeparator());
		
		//插入
		int modify = userMapper.insert(user);
		builder.append("插入测试：" + modify + System.lineSeparator());
		
		user.setId(null);
		modify = userMapper.insertBatch(Arrays.asList(user, user));
		builder.append("批量插入测试：" + modify + System.lineSeparator());
		
		user.setId(null);
		modify = userMapper.insertSelective(user);
		builder.append("插入不为空测试：" + modify + System.lineSeparator());
		
		//查询
		user = userMapper.selectById(user.getId());
		builder.append("ID查询测试：" + user + System.lineSeparator());
		
		user = userMapper.selectEntity(new User().setAddress(user.getAddress()));
		builder.append("实体查询测试：" + user + System.lineSeparator());
		
		List<User> users = userMapper.selectPage(Page.start(1L, 2L, new User()));
		builder.append("分页查询测试：" + users + System.lineSeparator());
		
		long count = userMapper.selectCount(new User());
		builder.append("数量查询测试：" + count + System.lineSeparator());
		
		users = userMapper.selectList(new User().setAddress(user.getAddress()));
		builder.append("列表查询测试：" + users + System.lineSeparator());
		
		Integer id = userMapper.selectId(new User().setAddress(user.getAddress()));
		builder.append("查询ID测试：" + id + System.lineSeparator());
		
		List<Integer> ids = userMapper.selectIds(new User().setAddress(user.getAddress()));
		builder.append("查询ID列表测试：" + ids + System.lineSeparator());
		
		//更新
		user.setAge(19);
		modify = userMapper.update(user);
		builder.append("更新测试：" + modify + System.lineSeparator());
		
		user.setAge(20);
		modify = userMapper.updateBatch(Arrays.asList(user));
		builder.append("批量更新测试：" + modify + System.lineSeparator());
		
		modify = userMapper.updateSelective(new User().setId(user.getId()).setAge(100));
		builder.append("更新不为空测试：" + modify + System.lineSeparator());
		
		//删除
		user.setAge(100);
		modify = userMapper.delete(user);
		builder.append("删除测试：" + modify + System.lineSeparator());
		
		user.setId(null);
		userMapper.insert(user);
		modify = userMapper.deleteBatch(Arrays.asList(user.getId()));
		builder.append("批量删除测试：" + modify + System.lineSeparator());
		
		user.setId(null);
		userMapper.insert(user);
		modify = userMapper.deleteById(user.getId());
		builder.append("主键删除测试：" + modify + System.lineSeparator());
		
		return builder.toString();
	}
}
