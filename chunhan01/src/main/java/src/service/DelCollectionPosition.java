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
 * @description: 从岗位表删除收藏的岗位
 * @author: 王凯旋
 * @date: 2022/7/14 15:22:27
 * @version: 1.0
 */
@WebServlet("/DelCollectionPosition")
public class DelCollectionPosition extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        String jobId = request.getParameter("jobId");
        String userId = request.getParameter("userId");
        String json = "";
        System.out.println("前端传来的参数jobId:"+jobId+"  userId:"+userId);
        String sqlDelCollection = "delete from collection WHERE user_id = "+userId+" and job_id = "+jobId+";";
        int del = MysqlUtil.del(sqlDelCollection);
        if(del ==1){
            json = "{\"code\":\"000\",\"message\":\"success\"}";
        }else {
            json = "{\"code\":\"999\",\"message\":\"failed\"}";
        }
        response.getWriter().write(json);

    }
}
