package src.service;


import src.utils.MysqlUtil;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 创建人:王珣
 * 最近修改时间:7月5日
 * 功能:接收并检验用户注册信息的servlet
 */
@WebServlet("/RegisteredServlet1")
public class RegisteredServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		String school = request.getParameter("school");
		String address = request.getParameter("address");
		String edu = request.getParameter("edu");
		String job_exp = request.getParameter("job_exp");
		String phone = request.getParameter("phone");
		System.out.println("前端入参是： 账户:"+account + " 密码:" + password + " 学校:" + school + " 地址:" + address + " 学历:" + edu + " 工作经验:" + job_exp + " 手机号:" + phone);
		String sql = "insert into user(account,password,school,address,edu,job_exp,phone) values('"+account+"','"+password+"','"+school+"','"+address+"','"+edu+"','"+job_exp+"','"+phone+"')";
		int count = MysqlUtil.add(sql);
		String json = "";
		if(count == 1) {
			json = "{\"code\":\"000\",\"message\":\"success\"}";
		}else {
			json = "{\"code\":\"999\",\"message\":\"failed\"}";
		}
		response.getWriter().append(json);
	}


}
