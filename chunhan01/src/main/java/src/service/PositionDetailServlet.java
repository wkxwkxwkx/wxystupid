package src.service;

import src.utils.MysqlUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/PositionDetailServlet1")
public class PositionDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        String jobId = request.getParameter("jobId");
        System.out.println("前端的入参jobId是:"+jobId);
        String sql = "select j.id,j.job_name,j.salary,j.job_address,j.job_exp,j.edu,j.source,j.uptime,c.company,c.company_logo,c.company_size,c.industry_field,c.finance_stage FROM job j INNER JOIN company c ON j.company_id = c.id WHERE j.id ="+jobId+";";
        String[] colums ={"id","job_name","uptime","salary","job_address","edu","job_exp",
                "company","source","company_logo","company_size","industry_field","finance_stage"};
        String json = MysqlUtil.getJsonBySql(sql,colums);
//        System.out.println(json);
        response.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}
