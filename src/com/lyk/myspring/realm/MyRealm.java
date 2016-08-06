package com.lyk.myspring.realm;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import com.lyk.myspring.user.model.User;
import com.lyk.myspring.user.service.UserService;

/**
 * 自定义的指定Shiro验证用户登录的类
 * 
 * @see 在本例中定义了2个用户:jadyer和玄玉,jadyer具有admin角色和admin:manage权限,玄玉不具有任何角色和权限
 * @create Sep 29, 2013 3:15:31 PM
 * @author 玄玉<http://blog.csdn.net/jadyer>
 */
public class MyRealm extends AuthorizingRealm {

	private UserService<User> userService;

	public UserService<User> getUserService() {
		return userService;
	}

	public void setUserService(UserService<User> userService) {
		this.userService = userService;
	}
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String currentUsername = (String) super.getAvailablePrincipal(principals);
		List<String> permissionList = userService.getPermissionByUsername(currentUsername);
		if(!permissionList.isEmpty()){
			SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
			 simpleAuthorInfo.addStringPermissions(permissionList);
			 return simpleAuthorInfo;
		}
		return null;
	}

	/**
	 * 验证当前登录的Subject
	 * 
	 * @see 经测试:本例中该方法的调用时机为LoginController.login()方法中执行Subject.login()时
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
			throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		System.out.println(
				"验证当前Subject时获取到token为" + ReflectionToStringBuilder.toString(token, ToStringStyle.MULTI_LINE_STYLE));
	    User user = userService.getUserByName(token.getUsername());
		 if(null != user){
		 AuthenticationInfo authcInfo = new
		 SimpleAuthenticationInfo(user.getUserName(), user.getPassword(),
		 this.getName());
		 this.setSession("currentUser", user);
		 return authcInfo;
		 }else{
		 return null;
		 }	
    }

	/**
	 * 将一些数据放到ShiroSession中,以便于其它地方使用 
	 * @see 比如Controller,使用时直接用HttpSession.getAttribute(key)就可以取到
	 */
	private void setSession(Object key, Object value) {
		Subject currentUser = SecurityUtils.getSubject();
		if (null != currentUser) {
			Session session = currentUser.getSession();
			System.out.println("Session默认超时时间为[" + session.getTimeout() + "]毫秒");
			if (null != session) {
				session.setAttribute(key, value);
			}
		}
	}
}