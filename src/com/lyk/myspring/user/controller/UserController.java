package com.lyk.myspring.user.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.lyk.myspring.user.model.User;
import com.lyk.myspring.user.service.UserService;  
  

  
@Controller  
@RequestMapping("/user")  
public class UserController {  
    @Autowired
    private UserService<User> userService;  
      
    @RequestMapping("/showUser")  
    public String toIndex(HttpServletRequest request,Model model){  
        int userId = Integer.parseInt(request.getParameter("id"));  
        User user = this.userService.selectByPrimaryKey(userId);  
        model.addAttribute("user", user);  
        return "showUser";  
    }  
    
    @RequestMapping(value = "/uploadWar", method = RequestMethod.POST )
    public String uploadWar(MultipartFile multipartFile){
    	return "";
    }
    
    @RequestMapping(value="/getUserInfo")  
    public String getUserInfo(HttpServletRequest request){  
        String currentUser = (String)request.getSession().getAttribute("currentUser");  
        System.out.println("当前登录的用户为[" + currentUser + "]");  
        request.setAttribute("currUser", currentUser);  
        return "/user/info";  
    }  
    
    
    
}  
