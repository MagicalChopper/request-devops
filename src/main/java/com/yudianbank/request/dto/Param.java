package com.yudianbank.request.dto;

import java.time.LocalDateTime;

/**
 * 请求DevOps系统的参数封装类
 */
public class Param {
    /**
     * 应用所在服务器的内网ip
     */
    private String i_ip ;
    /**
     * 健康检查地址，要求：带ip地址，不要填域名
     */
    private String health_path ;
    /**
     * 应用名称，应用名称和实例id用来确认应用的唯一性
     */
    private String app_name ;
    /**
     * 应用的实例id，如果应用采用负载均衡部署多台服务器，则每台服务器都要求有唯一的实例id
     */
    private String instance_id ;
    /**
     * 部署路径，应用部署在服务器的路径
     */
    private String deploy_path ;
    /**
     * 访问端口，访问应用的端口
     */
    private String app_port ;
    /**
     * 发布git的版本号
     */
    private String commit_id ;
    /**
     * 发布者的名字
     */
    private String author ;
    /**
     * 创建时间，格式为 YYYY-MM-DDThh:mm:ssZ
     */
    private String create_time ;
    /**
     * 最后启动的时间，格式为 YYYY-MM-DDThh:mm:ssZ
     */
    private String start_time ;
    /**
     * 对应用的描述
     */
    private String describe;

    public Param(String i_ip, String health_path, String app_name, String instance_id, String deploy_path, String app_port, String commit_id, String author, String create_time, String start_time, String describe) {
        this.i_ip = i_ip;
        this.health_path = health_path;
        this.app_name = app_name;
        this.instance_id = instance_id;
        this.deploy_path = deploy_path;
        this.app_port = app_port;
        this.commit_id = commit_id;
        this.author = author;
        this.create_time = create_time;
        this.start_time = start_time;
        this.describe = describe;
        if(commit_id==null){
            this.commit_id = "";
        }
        if(author==null){
            this.author = "";
        }
        if(create_time==null){
            this.create_time = String.valueOf(LocalDateTime.now());
        }
        if(start_time==null){
            this.start_time = String.valueOf(LocalDateTime.now());
        }
        if(describe==null){
            this.describe = "";
        }
    }

    public String getI_ip() {
        return i_ip;
    }

    public void setI_ip(String i_ip) {
        this.i_ip = i_ip;
    }

    public String getHealth_path() {
        return health_path;
    }

    public void setHealth_path(String health_path) {
        this.health_path = health_path;
    }

    public String getApp_name() {
        return app_name;
    }

    public void setApp_name(String app_name) {
        this.app_name = app_name;
    }

    public String getInstance_id() {
        return instance_id;
    }

    public void setInstance_id(String instance_id) {
        this.instance_id = instance_id;
    }

    public String getDeploy_path() {
        return deploy_path;
    }

    public void setDeploy_path(String deploy_path) {
        this.deploy_path = deploy_path;
    }

    public String getApp_port() {
        return app_port;
    }

    public void setApp_port(String app_port) {
        this.app_port = app_port;
    }

    public String getCommit_id() {
        return commit_id;
    }

    public void setCommit_id(String commit_id) {
        this.commit_id = commit_id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
