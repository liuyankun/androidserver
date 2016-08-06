package com.lyk.myspring.common;

import java.lang.reflect.Method;

public abstract class BaseService<T> {

    public abstract IBaseDao<T> getDao();
	
	public T selectByPrimaryKey(Integer id){
		return getDao().selectByPrimaryKey(id);
	}
	
}
