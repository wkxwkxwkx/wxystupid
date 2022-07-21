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
 * @description: 取消收藏的servlet
 * @author: 王凯旋
 * @date: 2022/7/14 14:40:33
 * @version: 1.0
 */
@WebServlet("/DelCollection")
public class DelCollection extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        String id = request.getParameter("id");
        String json = "";
        System.out.println("前端传来的参数id:"+id);
        String sqlDelCollection = "delete from collection WHERE id="+id;
        int del = MysqlUtil.del(sqlDelCollection);
        if(del ==1){
            json = "{\"code\":\"000\",\"message\":\"success\"}";
        }else {
            json = "{\"code\":\"999\",\"message\":\"failed\"}";
        }
        response.getWriter().write(json);

    }
}
