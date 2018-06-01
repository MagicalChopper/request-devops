package com.yudianbank.request.configuration;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yudianbank.request.dto.Param;
import com.yudianbank.request.executor.RequestExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.net.InetAddress;
import java.net.UnknownHostException;


@Configuration
@ConfigurationProperties(prefix = "request.devops.param")
public class RequestDevOpsAutoConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(RequestDevOpsAutoConfiguration.class);

    //---------向DevOps系统发送http请求的地址-------------
    public static final String REQUEST_DEVOPS_URL = "http://devops.keking.cn:8000/asset/api/app-register/";

    public static boolean flag = false;

    private String i_ip;

    private String health_path ;

    private String app_name ;

    private String instance_id ;

    private String deploy_path ;

    private String app_port ;

    private String commit_id ;

    private String author ;

    private String create_time ;

    private String start_time ;

    private String describe;

    //-----------------初始化默认本机ip-------------------
    public RequestDevOpsAutoConfiguration() throws UnknownHostException {
        i_ip = InetAddress.getLocalHost().getHostAddress();
    }

    /**
     * 向DevOps系统发请求
     * @param obj
     */
    public void doRequest(Object obj) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json;charset=UTF-8");
        httpHeaders.setContentType(type);
        httpHeaders.add("Accept",MediaType.APPLICATION_JSON.toString());
        JSONObject jsonObject = (JSONObject) JSON.toJSON(obj);
        HttpEntity<String> httpEntity = new HttpEntity(jsonObject.toString(), httpHeaders);
        String result = null ;
        try{
            result = restTemplate.postForObject(REQUEST_DEVOPS_URL, httpEntity, String.class);
            flag = true;
        }catch(RuntimeException e){
            logger.info("请求DevOps系统出错,请检查配置信息是否正确.{}",e.getMessage());
        }
        logger.info("请求DevOps系统的返回值:{}", result);
    }


    @Bean(initMethod = "start", destroyMethod = "destroy")
    @ConditionalOnProperty(prefix = "request.devops.param",name = "enabled",havingValue = "true",matchIfMissing = false)
    @ConditionalOnMissingBean
    public RequestExecutor requestDevOpsExecutor() {
        new Thread(() ->{
            logger.info("request-devops config init.");
            try{
                Assert.notNull(i_ip,"请求DevOps系统参数【i_ip】不能为空");
                Assert.notNull(health_path,"请求DevOps系统参数【health_path】不能为空");
                Assert.notNull(app_name,"请求DevOps系统参数【app_name】不能为空");
                Assert.notNull(instance_id,"请求DevOps系统参数【instance_id】不能为空");
                Assert.notNull(deploy_path,"请求DevOps系统参数【deploy_path】不能为空");
                Assert.notNull(app_port,"请求DevOps系统参数【app_port】不能为空");
            }catch (RuntimeException e){
                logger.info("校验参数有误,请检查请求DevOps系统参数.{}",e.getMessage());
                return;
            }
        doRequest(new Param(i_ip,health_path,app_name,instance_id,deploy_path,app_port,commit_id,author,create_time,start_time,describe));
        }).start();
        return new RequestExecutor();
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
