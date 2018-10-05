package org.nico.ourbatis.domain;

import org.nico.ourbatis.annotation.RenderPrimary;

/** 
 * City
 * 
 * @author nico
 */
public class City {

	@RenderPrimary
	private String id;
	
	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public City setName(String name) {
		this.name = name;
		return this;
	}

}
