package com.uplooking.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.sun.org.apache.xml.internal.security.keys.content.RetrievalMethod;
import com.uplooking.pojo.User;
import com.uplooking.service.UserService;

public class UserInterceptor implements HandlerInterceptor {

	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
		//盗链
		if(request.getSession().getAttribute("user")==null){
			response.sendRedirect(request.getContextPath()+"/user/login.do?op=1");
			return false;
		}
		User user = (User) request.getSession().getAttribute("user");
		//管理员 直接放行
		if("1043".equals(user.getUstatus())){
			return true;
		}
		//获取路由
		String url = "";
		if (object instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) object;
			url = handlerMethod.getMethodAnnotation(RequestMapping.class).value()[0];
		}
		//获取用户名
		String name = user.getUname();
		//权限验证
		if(userService.isPass(name, url)){
			return true;
		}
		response.sendRedirect(request.getContextPath()+"/user/login.do?op=2");
		return false;
	}

}
