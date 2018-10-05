package org.nico.ourbatis.mapper;

import java.util.List;

public interface BaseMapper<T,K>{

    
    /**
     * 根据主键查询单一记录
     * @param  key
     * @return T
     */
    public  T selectById(K key);
    
    /**
     * 根据条件查询单条记录
     * @param  t
     * @return List<T>
     */
    public  T selectEntity(T t);
    
     /**
     * 根据条件查询多条记录
     * @param T t
     * @return List<T>
     */
    public  List<T> selectList(T t);
    
     /**
     * 根据条件查询ID
     * @param  t
     * @return K
     */
     public  K selectId(T t);
  
     /**
     * 根据条件查询ID集合
     * @param  t
     * @return List<K>
     */
     public  List<K> selectIds(T t);
    
     /**
     * 根据主键删除单条记录
     * @param key
     *
     */
    public int deleteById(K key);
    
     /**
     * 插入单条记录（全字段） 
     * @param t
     * @return int
     */
    public int insert(T t);
    
     /**
     * 插入单条记录（字段为空则不添加） 
     * @param t
     * @return int
     */
    public int insertSelective(T t);
    
    /**
     *  批量插入
     * @param t
     * @return int
     */
    public int insertBatch(List<T> list);
    
     /**
     *  根据主键修改
     * @param t
     * @return int
     */
    public int update(T t);
    
    /**
     *  根据主键修改(修改不为空的)
     * @param t
     * @return int
     */
    public int updateSelective(T t);
    
     /**
     *  根据主键批量修改
     * @param t
     * @return int
     */
    public int updateBatch(List<T> list);
    
    /**
     *  根据主键批量删除
     * @param t
     * @return int
     */
    public int deleteBatch(List<K> list);

   /**
     *  根据某些条件删除
     * @param  t
     * @return int
     */
    public int delete(T t);

    
     
}