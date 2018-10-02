package org.nico.ourbatis.domain;

import java.math.BigDecimal;

import org.nico.ourbatis.annotation.RenderPrimary;

/** 
 * User
 * 
 * @author nico
 */
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

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

}
