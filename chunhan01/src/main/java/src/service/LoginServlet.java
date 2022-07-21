package src.service;



import src.utils.MysqlUtil;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 *
 * 创建人:晋港
 * 最近一次修改时间:7月5日
 * 功能:验证登录的servlet
 */
@WebServlet("/LoginServlet1")
public class LoginServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/json;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		System.out.println("前端的入参为:账户名:"+account+";密码:"+ password);
		String sql = "select id,account,password from user where account = '"+account+"' and password = '"+password+"'";
		String[] columns = {"id","account","password"};
		// 查询的本质是二维表
		ArrayList<String[]> arrayLists = MysqlUtil.showUtil(sql, columns);
		for (String[] arrayList : arrayLists) {
			System.out.println(arrayList[0]);
		}
		String json = "";
		if (arrayLists.size() == 1) {
			HttpSession userSession = request.getSession();
			userSession.setAttribute("userId", arrayLists.get(0)[0]);
			Cookie userCookie = new Cookie("userId", arrayLists.get(0)[0]);
			userCookie.setPath("/");
			response.addCookie(userCookie);
			json = "{\"code\":\"000\",\"message\":\"success\"}";
		}else {
			json = "{\"code\":\"999\",\"message\":\"failed\"}";
		}
		response.getWriter().append(json);
	}

}

