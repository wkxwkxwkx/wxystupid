package src.entity;

import lombok.Data;

@Data
public class Job {
    private int id;
    private String job_name;
    private String uptime;
    private String salary;
    private int address_id;
    private String job_address;
    private String edu;
    private String job_exp;
    private int company_id;
    private String company;
    private String source;
    private String describe;
    private String company_log;
    private int hits;
}