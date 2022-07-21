package src.service;


import src.utils.MysqlUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/CollectionServlet1")
public class CollectionServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        String jobId = request.getParameter("jobId");
        String userId =request.getParameter("userId");
        String json ="";
        String sql = "select * from collection where user_id="+userId;
        String[] columns ={"id","job_name","company","job_address","salary","source"};
        json = MysqlUtil.getJsonBySql(sql, columns);
        response.getWriter().append(json);
    }
}
