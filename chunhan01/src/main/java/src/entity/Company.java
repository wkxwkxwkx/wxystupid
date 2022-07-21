package src.entity;

import lombok.Data;

@Data
public class Company {
    private int id;
    private String company;
    private int hits;
    private String company_logo;
    // 公司规模
    private String company_size;
    //公司所属行业领域
    private String industry_field;
    // 金额阶段
    private String finance_stage;

}
