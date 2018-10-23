package org.nico.ourbatis.controller;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.nico.ourbatis.domain.city.City;
import org.nico.ourbatis.mapper.CityMapper;
import org.nico.ourbatis.mapper.CityMapper;
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
@RequestMapping("/city")
public class CityController {

	@Autowired
	private CityMapper cityMapper;
	
	@GetMapping("/")
	public List<City> list(){
		return cityMapper.selectList(new City());
	}
	
	@GetMapping("/{id}")
	public City get(@PathVariable String id){
		return cityMapper.selectById(id);
	}
	
	@PostMapping("/")
	public int add(@RequestBody City city){
		city.setId(null);
		return cityMapper.insertSelective(city);
	}
	
	@PutMapping("/")
	public int update(@RequestBody City city){
		return cityMapper.update(city);
	}
	
	@DeleteMapping("/{id}")
	public int delete(@PathVariable String id){
		return cityMapper.deleteById(id);
	}
	
	@PostMapping("/test")
	public String test(){
		StringBuilder builder = new StringBuilder();
		
		City city = new City();
		city.setId(UUID.randomUUID().toString());
		city.setName("上海");
		
		//插入
		int modify = cityMapper.insert(city);
		builder.append("插入测试：" + modify + System.lineSeparator());
		
		city.setId(UUID.randomUUID().toString());
		modify = cityMapper.insertBatch(Arrays.asList(city));
		builder.append("批量插入测试：" + modify + System.lineSeparator());
		
		city.setId(UUID.randomUUID().toString());
		modify = cityMapper.insertSelective(city);
		builder.append("插入不为空测试：" + modify + System.lineSeparator());
		
		//查询
		city = cityMapper.selectById(city.getId());
		builder.append("ID查询测试：" + city + System.lineSeparator());
		
		city = cityMapper.selectEntity(new City().setName(city.getName()));
		builder.append("实体查询测试：" + city + System.lineSeparator());
		
		List<City> citys = cityMapper.selectList(new City().setName(city.getName()));
		builder.append("列表查询测试：" + citys + System.lineSeparator());
		
		String id = cityMapper.selectId(new City().setName(city.getName()));
		builder.append("查询ID测试：" + id + System.lineSeparator());
		
		List<String> ids = cityMapper.selectIds(new City().setName(city.getName()));
		builder.append("查询ID列表测试：" + ids + System.lineSeparator());
		
		//更新
		city.setName("北京");
		modify = cityMapper.update(city);
		builder.append("更新测试：" + modify + System.lineSeparator());
		
		city.setName("深圳");
		modify = cityMapper.updateBatch(Arrays.asList(city));
		builder.append("批量更新测试：" + modify + System.lineSeparator());
		
		//删除
		modify = cityMapper.delete(city);
		builder.append("删除测试：" + modify + System.lineSeparator());
		
		return builder.toString();
	}
}
