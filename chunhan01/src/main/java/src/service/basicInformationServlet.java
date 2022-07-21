package src.service;

import src.utils.MysqlUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @project: chunhan01
 * @description:
 * @author: 王凯旋
 * @date: 2022/7/14 16:02:13
 * @version: 1.0
 */
@WebServlet("/basicInformationServlet")
public class basicInformationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        String userId = request.getParameter("userId");
        System.out.println("前端传来的数据是userID:"+userId);
        String sql = "SELECT id,school,edu,address,job_exp FROM `user` where id="+userId+";";
        String[] columns={"id","school","edu","address","job_exp"};
        String json = MysqlUtil.getJsonBySql(sql,columns);
        response.getWriter().append(json);

    }
}
