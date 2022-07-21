package src.service;

import src.utils.MysqlUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/city")
public class CityServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        String id = request.getParameter("id");

        String sql= "";

        if(id == null){

           sql = "select name,pid,id from area where pid = 0";
        }
       else {
           sql =  "select name,pid,id from area where pid = "+id;
        }
        String[] sheng = {"name","pid","id"};
        String sheng1 = MysqlUtil.getJsonBySql(sql,sheng);
        System.out.println(sheng1);
        response.getWriter().write(sheng1);
    }
}
