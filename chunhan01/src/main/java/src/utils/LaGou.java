package src.utils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import src.entity.Company;
import src.entity.Job;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class LaGou {
    public static String LaGouJobUrl = "";
    static Map<String, String> map = PublicMessage.map;
    static {
         map.put("Cookie", "__lg_stoken__=fb7e1c0b29b1db8f8a6e0c7242b8f41c2f7721f7f6401e8ff8e64a4f379a3d70d9458e8878b5062607ec4bb27c14bda059e30337e7a9906c510e94190d276b4c4810eeaa0e8e;");
    }
    static HttpUtils httpUtils = new HttpUtils();
    static List<Job> jobs = new ArrayList<>();
    static List<Company> companies = new ArrayList<>();
    //before 和after是为了获取json数据
    static final String before = "<script id=\"__NEXT_DATA__\" type=\"application/json\">";
    static final String after = "</script><script nomodule=\"\" src=";
    static final String jsonBefore = "\"result\":";
    static final String jsonAfter = ",\"locationInfo\"";
    // jobUrl = https://www.lagou.com/wn/jobs?kd=Java&city=%E5%8C%97%E4%BA%AC
    //获取网页的json数据
    // 获取一个[{},{},{}]格式的字符串

    public static String getResult(String jobUrl){
       String html = httpUtils.doGetHtml(jobUrl,map);
       String jsonTotal = html.split(before + "|" + after)[1];
       String result = jsonTotal.split(jsonBefore)[1].split(jsonAfter)[0];
       return result;
    }
    /**
     * @param result json格式的字符串 ,根节点是数组
     * result是一个数组 , 里面是一个个的{单个岗位信息}
     * @return jobs  岗位集合
     */
    public static List<Job> getJobs(String result) {
        // 将字符串result转化为JSONArray类型
        JSONArray jsonArray = JSONArray.parseArray(result);
        for (int i = 0; i < jsonArray.size(); i++) {
            Job job = new Job();
            //遍历时将每个JSONArray转为对应的实体jsonObject
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            //岗位名
            job.setJob_name(jsonObject.getString("positionName"));
            //薪水
            job.setSalary(jsonObject.getString("salary"));
            //发布时间
            job.setUptime(jsonObject.getString("createTime"));
            //岗位地点
            job.setJob_address(jsonObject.getString("city")+"市");
            //地址id
            String sqlAreaId = "SELECT id FROM area WHERE `name` = '"+job.getJob_address()+"'";
            job.setAddress_id(MysqlUtil.getId(sqlAreaId));
            //学历
            job.setEdu(jsonObject.getString("education"));
            //工作经验
            job.setJob_exp(jsonObject.getString("workYear"));
            //公司名
            job.setCompany(jsonObject.getString("companyShortName"));
            //公司logo
            job.setCompany_log("https://www.lgstatic.com/thumbnail_120x120/"+jsonObject.getString("companyLogo"));
            //公司id
            // 查询一下这个公司是否在公司表内
            String sqlCompanyId = "select id from company where company = '"+job.getCompany()+"'";
            int companyId = MysqlUtil.getId(sqlCompanyId);
                if(companyId != 0){
                job.setCompany_id(companyId);
            } else {
                //没查询到 , 把这个公司插入到公司表
                String sqlCompanyAdd = "INSERT INTO `company` (`company`, `company_logo`, `company_size`, `industry_field`, `finance_stage`, `hits`) VALUES " +
                        "('"+job.getCompany()+"', '"+job.getCompany_log()+"', '"+jsonObject.getString("companySize")+"', " +
                        "'"+jsonObject.getString("industryField")+"', '"+jsonObject.getString("financeStage")+"', 0);";
                int add = MysqlUtil.add(sqlCompanyAdd);
                //插入成功
                if(add == 1){
                    int companyIdNew = MysqlUtil.getId(sqlCompanyId);
                    if(companyIdNew != 0){
                        //然后把公司id添加到job对象中
                        job.setCompany_id(companyIdNew);
                    }
                }
            }
            //岗位描述
            job.setDescribe(jsonObject.getString("positionDetail"));
            //信息来源地址(岗位详情页)
            job.setSource("https://www.lagou.com/wn/jobs/"+jsonObject.getString("positionId")+".html");
            jobs.add(job);
        }
        return jobs;
    }
    // companyUrl = https://www.lagou.com/gongsi/0-0--0
    public static List<Company> getCompanies(String result){
        JSONArray jsonArray = JSONArray.parseArray(result);
        for (int i = 0; i < jsonArray.size(); i++){
            Company company = new Company();
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            //公司名
            company.setCompany(jsonObject.getString("companyShortName"));
            //公司logo
            company.setCompany_logo("https://www.lgstatic.com/thumbnail_120x120/"+jsonObject.getString("companyLogo"));
            //公司规模
            company.setCompany_size(jsonObject.getString("companySize"));
            //行业领域
            company.setIndustry_field(jsonObject.getString("industryField"));
            //金融阶段
            company.setFinance_stage(jsonObject.getString("financeStage"));
            companies.add(company);
        }
        return companies;
    }
    @SuppressWarnings({"all"})
    public static void main(String[] args) {
        LaGouJobUrl = "https://www.lagou.com/wn/jobs?pn=4";
        String html = httpUtils.doGetHtml("https://www.lagou.com/wn/zhaopin",map);
        System.out.println(html);
//        List<Job> jobs = getJobs(getResult(LaGouJobUrl));
//        Iterator<Job> iterator = jobs.iterator();

//        for (Job job : jobs) {
//            System.out.println(job.toString());
//        }
    }
}
