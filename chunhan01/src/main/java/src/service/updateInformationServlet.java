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
 * @date: 2022/7/14 17:26:15
 * @version: 1.0
 */
@WebServlet("/updateInformationServlet")
public class updateInformationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        String userId =request.getParameter("userId");
        String school = request.getParameter("school");
        String address = request.getParameter("address");
        String edu = request.getParameter("edu");
        String job_exp = request.getParameter("job_exp");
        String json="";
        System.out.println("前端传过来的参数school是:"+school+" 地址是:"+address+" 学历是:"+edu+" 工作经验是:"+job_exp);
        String sql="UPDATE `qcby`.`user` SET  `school` = '"+school+"', `address` = '"+address+"', `edu` = '"+edu+"', `job_exp` = '"+job_exp+"' WHERE `id` ="+userId;
        int count = MysqlUtil.update(sql);
        if(count ==1){
            json = "{\"code\":\"000\",\"message\":\"success\"}";
        }else {
            json = "{\"code\":\"999\",\"message\":\"failed\"}";
        }
        response.getWriter().write(json);
    }
}
