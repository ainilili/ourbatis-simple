package org.nico.ourbatis.mapper;

import java.util.List;

public interface BaseDao<T,K>{

    
    /**
     * 根据主键查询单一记录
     * @param  key
     * @return T
     */
    public  T selectByPrimaryKey(K key);
    
     /**
     * 根据条件查询多条记录
     * @param T t
     * @return List<T>
     */
    public  List<T> selectByEntityList(T t);
    
     /**
     * 根据条件查询单条记录
     * @param  t
     * @return List<T>
     */
    public  T selectByEntity(T t);
    
     /**
     * 根据条件查询ID
     * @param  t
     * @return K
     */
     public  K selectById(T t);
  
     /**
     * 根据条件查询ID集合
     * @param  t
     * @return List<K>
     */
     public  List<K> selectByIds(T t);
    
     /**
     * 根据主键删除单条记录
     * @param key
     *
     */
    public int deleteByPrimaryKey(K key);
    
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
     *  根据主键修改
     * @param t
     * @return int
     */
    public int updateByPrimaryKey(T t);
    
     /**
     *  批量插入
     * @param t
     * @return int
     */
    public int insertBatch(List<T> t);
    
     /**
     *  根据主键批量修改
     * @param t
     * @return int
     */
    public int updateBatch(List<T> t);
    
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
    public int deleteByEntity(T t);

     /**
     *  根据ids 查询
     * @param  keys
     * @return List<T>
     */
    public List<T> selectEntityListByPrimaryKeys(List<K> keys);
    
     
}