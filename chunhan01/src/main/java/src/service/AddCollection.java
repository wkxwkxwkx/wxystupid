package src.service;

import src.utils.MysqlUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/AddCollection1")
public class AddCollection extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        String jobId = request.getParameter("jobId");
        String userId =request.getParameter("userId");
        String json = "";
        String sql1 = "select account from user where id ="+ userId;
        String[] columns1 = {"account"};
        ArrayList<String[]> arrayList1 = MysqlUtil.showUtil(sql1, columns1);
        String sql2 ="select `job_name` , `company_id` , `company` , `address_id`, `job_address`, `salary`, `source` from job where id="+jobId;
        String[] columns2 = {"job_name","company_id","company","address_id","job_address","salary","source"};
        ArrayList<String[]> arrayList2 = MysqlUtil.showUtil(sql2, columns2);
        String sql3 = "select id from collection where user_id ="+userId+" and job_id = "+jobId;
        int count = MysqlUtil.getCount(sql3);
        if (count ==0){
            String sql4 = "INSERT INTO `qcby`.`collection` (`user_id`, `user_name`, `job_id`, `job_name`, `company_id`, `company`, `address_id`, `job_address`, `salary`, `source`)" +
                    " VALUES ("+userId+", '"+arrayList1.get(0)[0]+"', "+jobId+", '"+arrayList2.get(0)[0]+"', "+arrayList2.get(0)[1]+", '"+arrayList2.get(0)[2]+"', "+arrayList2.get(0)[3]+", '"+arrayList2.get(0)[4]+"', '"+arrayList2.get(0)[5]+"', '"+arrayList2.get(0)[6]+"');";
            MysqlUtil.add(sql4);
            json = "{\"code\":\"000\",\"message\":\"success\"}";
        }else {
            json = "{\"code\":\"999\",\"message\":\"failed\"}";
        }
        response.getWriter().append(json);
    }
}
