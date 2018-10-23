package org.nico.ourbatis.domain.user;

import java.math.BigDecimal;

import org.nico.ourbatis.annotation.MapperBy;
import org.nico.ourbatis.annotation.RenderPrimary;
import org.nico.ourbatis.mapper.UserMapper;

/** 
 * User
 * 
 * @author nico
 */
@MapperBy(UserMapper.class)
public class User {

	@RenderPrimary
	private Integer id;
	
	private Integer cityId;
	
	private Integer age;
	
	private String name;
	
	private String address;
	
	private BigDecimal balance;

	public Integer getId() {
		return id;
	}

	public User setId(Integer id) {
		this.id = id;
		return this;
	}

	public Integer getCityId() {
		return cityId;
	}

	public User setCityId(Integer cityId) {
		this.cityId = cityId;
		return this;
	}

	public Integer getAge() {
		return age;
	}

	public User setAge(Integer age) {
		this.age = age;
		return this;
	}

	public String getName() {
		return name;
	}

	public User setName(String name) {
		this.name = name;
		return this;
	}

	public String getAddress() {
		return address;
	}

	public User setAddress(String address) {
		this.address = address;
		return this;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "User [id=" + id + "]";
	}

}
