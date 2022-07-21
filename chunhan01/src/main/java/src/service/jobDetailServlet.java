package src.service;

import src.utils.MysqlUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/jobDetailServlet1")
public class jobDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        String sql = "select j.id,j.job_name,j.job_exp,j.job_address,j.edu,j.uptime,j.salary,j.source,c.company,c.company_logo,c.company_size,c.industry_field,c.finance_stage FROM job j INNER JOIN company c ON j.company_id=c.id WHERE j.company = '哔哩哔哩';";
        String[] colums ={"id","job_name","uptime","salary","job_address","edu","job_exp",
                "company","source","company_logo","company_size","industry_field","finance_stage"};
        String json = MysqlUtil.getJsonBySql(sql, colums);
        response.getWriter().write(json);
    }
}
