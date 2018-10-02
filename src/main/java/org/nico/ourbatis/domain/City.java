package org.nico.ourbatis.domain;

import org.nico.ourbatis.annotation.RenderPrimary;

/** 
 * City
 * 
 * @author nico
 */
public class City {

	@RenderPrimary
	private Integer id;
	
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
