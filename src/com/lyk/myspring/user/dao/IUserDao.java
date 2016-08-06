package com.lyk.myspring.user.dao;


import java.util.List;

import com.lyk.myspring.common.IBaseDao;
import com.lyk.myspring.user.model.User;

public interface IUserDao<User> extends IBaseDao<User>{
	public List<String> getPermissionByUsername(String userName);
	public User selectByUsername(String userName);
}
