package src.service;



import src.utils.MysqlUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 创建人:王垚
 * 最近修改时间:7月5日
 * 作用:搜索结果页面显示岗位信息的servlet
 */
@WebServlet("/ResultServlet1")
public class ResultServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        //前端的入参globalSearch , 作用: 岗位显示的限制条件
        String globalSearch = request.getParameter("global");
        System.out.println("前端的入参:搜索条件:"+globalSearch);

        String show = "SELECT `id`,`job_name`,`uptime`,`salary`,`job_address`,`edu`,`job_exp`,`company`,`source`,`company_logo` FROM job where  job_name like '%"+globalSearch+"%' or `describe` like '%"+globalSearch+"%'";
        String[] columns ={"id","job_name","uptime","salary","job_address","edu","job_exp","company","source","company_logo"};

        String json = MysqlUtil.getJsonBySql(show, columns);

        response.getWriter().append(json);
//        response.sendRedirect("/chunhan01/login.html");

    }
}
