package com.lyk.myspring.user.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.lyk.myspring.common.BaseService;
import com.lyk.myspring.user.dao.IUserDao;
import com.lyk.myspring.user.model.User;

@Service("userService") 
public class UserService<User> extends BaseService<User>{
	private static final Logger logger = LoggerFactory.getLogger(UserService.class); 
	@Autowired	
	private IUserDao<User> userDao;
	
	@Override
	public IUserDao<User> getDao(){
		return userDao;
	}	
	public User getUserById(int userId) {   
	        return userDao.selectByPrimaryKey(userId); 
	}
	public List<String> getPermissionByUsername(String name){
		return userDao.getPermissionByUsername(name);
	}
	
	public User getUserByName(String name){
		System.out.println("------调用了UserService-----");
		User user = userDao.selectByUsername(name);
		return user;
	}
	
	
	//@Scheduled(cron = "1 0/1 * * * *")
    private  void reportCurrentTime() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		System.err.println(dateFormat.format(new Date()));
        logger.error("sdf");
       // userDao.selectByUsername("");
    }
}
