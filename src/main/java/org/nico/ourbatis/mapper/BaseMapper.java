package org.nico.ourbatis.mapper;

public interface BaseMapper<T,K> extends OurbatisMapper<T, K>{

	public long selectDefined();
}