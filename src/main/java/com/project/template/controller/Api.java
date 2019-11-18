package com.project.template.controller;

import com.project.template.db.model.Test;
import com.project.template.service.IApiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Api Controller
 *
 * @author suibin.wu
 */
@RestController
public class Api {

    @Resource
    IApiService apiService;


    @GetMapping("/hello/{name}")
    @ResponseBody
    public Test index(@PathVariable String name) {
        return apiService.findByName(name);
    }


    @GetMapping("/queryOperator/{mobile}")
    @ResponseBody
    public String queryOperator(@PathVariable String mobile) {
        apiService.queryOperator(mobile);
        return "success";
    }
}
