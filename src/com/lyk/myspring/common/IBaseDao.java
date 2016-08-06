package com.lyk.myspring.common;


public interface IBaseDao<T> {

    public int deleteByPrimaryKey(Integer id);

    public int insert(T record);

    public int insertSelective(T record);

    public  T selectByPrimaryKey(Integer id);

    public int updateByPrimaryKeySelective(T record);

    public int updateByPrimaryKey(T record);
}
