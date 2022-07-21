package src.service;


import src.utils.MysqlUtil;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebServlet("/AdviceWorkServlet1")
public class AdviceWorkServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       response.setContentType("text/json;charset=utf-8");
       response.setCharacterEncoding("utf-8");
       String sql = "SELECT `id`,`job_name`,`uptime`,`salary`,`job_address`,`edu`,`job_exp`,`company`,`company_logo`,`describe`,`source` FROM job ";
        String[] colums ={"id","job_name","uptime","salary","job_address","edu","job_exp","company","source","company_logo"};
        String json = MysqlUtil.getJsonBySql(sql, colums);
//        System.out.println(json);
        HttpSession session = request.getSession();
        System.out.println("session:"+session.getAttribute("userId"));
        response.getWriter().append(json);
    }
}
