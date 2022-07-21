package src.utils;
import src.entity.Company;
import src.entity.Job;
import java.util.List;

/**
 * 无论是插入公司还是岗位,入参都是job列表的url
 * https://www.lagou.com/wn/zhaopin?pn=2
 * @author wangkx
 */

public class InsertDateBase {
    public static List<Company> companies = null;
    public static List<Job> jobs = null;
    /**
     * companies = LaGou.getCompanies(LaGou.getResult(jobUrl));
     * List<Job> jobs = LaGou.getJobs(LaGou.getResult(jobUrl));
     */
    public static int insertCompanies(){
        int a = 0;
        for (Company company : companies) {
            //插入之前需要判断
            String sqlSelectCompany = "select  id from `company` where `company` ='"+company.getCompany()+"'";
            int count = MysqlUtil.getCount(sqlSelectCompany);
            if (count==0){
                String sqlInsertCompany = "INSERT INTO `qcby`.`company` (`company`, `company_logo`, `company_size`," +
                        " `industry_field`, `finance_stage`, `hits`) " +
                        "VALUES ('"+company.getCompany()+"', '"+company.getCompany_logo()+"'," +
                        " '"+company.getCompany_size()+"', '"+company.getIndustry_field()+"', '"+company.getFinance_stage()+"', 0);";
                int add = MysqlUtil.add(sqlInsertCompany);
                System.out.println("确实插入了");
                a++;
            }
            else {
                System.out.println("这次不插入");
            }
        }
        return a;
    }
    public static int insertJobs(){
        int a = 0;
        for (Job job : jobs) {
            String sqlGetSource = "select id from `job` where `source` ='"+job.getSource()+"'";
            int jobCount = MysqlUtil.getCount(sqlGetSource);
            if(jobCount == 0){
                String sqlJobInsert = "INSERT INTO `qcby`.`job` (`job_name`, `uptime`, `salary`, `address_id`, `job_address`, `edu`, `job_exp`, " +
                        "`company_id`, `company`, `source`, `describe`, `company_logo`, `hits`) VALUES " +
                        "('"+job.getJob_name()+"', '"+job.getUptime()+"', '"+job.getSalary()+"', "+job.getAddress_id()+"," +
                        " '"+job.getJob_address()+"', '"+job.getEdu()+"', '"+job.getJob_exp()+"', "+job.getCompany_id()+", " +
                        "'"+job.getCompany()+"', '"+job.getSource()+"', '"+job.getDescribe()+"','"+job.getCompany_log()+"', 0);";
                MysqlUtil.add(sqlJobInsert);
                a++;
            }else {
                System.out.println(""+job.getJob_name()+" 岗位已存在,无需更新");
            }
        }
        return a;
    }
    public static void main(String[] args) {
        String result = LaGou.getResult("https://www.lagou.com/wn/zhaopin?pn=3");
        jobs = LaGou.getJobs(result);
        companies = LaGou.getCompanies(result);
        int i = insertJobs();
        System.out.println(i);
    }
}
