package org.nico.ourbatis.mapper;

public interface BaseMapper<T,K> extends SimpleMapper<T, K>{

	public long selectDefined();
}