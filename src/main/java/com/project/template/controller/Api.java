package com.project.template.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.project.template.db.model.Test;
import com.project.template.model.ResultModel;
import com.project.template.pojo.req.RequestVO;
import com.project.template.service.IApiService;
import com.project.template.service.IRetryService;
import com.project.template.utils.ValidateMsgUtil;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * Api Controller
 *
 * @author suibin.wu
 */
@RestController
@RequestMapping("/api")
public class Api {

    @Resource
    IApiService apiService;

    @Resource
    IRetryService retryService;


    /**
     * 系统跑通测试：mysql+redis
     *
     * @param name 姓名
     * @return Test
     */
    @GetMapping("/hello/{name}")
    @ResponseBody
    public Test index(@PathVariable String name) {
        return apiService.findByName(name);
    }


    /**
     * 使用线程池+@Async注解测试异步任务
     *
     * @param mobile 手机号
     * @return String
     */
    @GetMapping("/queryOperator/{mobile}")
    @ResponseBody
    public String queryOperator(@PathVariable String mobile) {
        apiService.queryOperator(mobile);
        return "success";
    }


    /**
     * 测试Mybatis分页插件
     * <p>
     * 注：https://github.com/pagehelper/Mybatis-PageHelper/blob/master/wikis/zh/HowToUse.md 使用方式介绍
     * https://blog.csdn.net/gnail_oug/article/details/80229542 案例
     * 貌似更推荐：PageHelper.startPage(1, 10).doSelectPageInfo()方法
     *
     * @param name     姓名
     * @param pageNo   第几页
     * @param pageSize 每页大小
     * @return PageInfo<Test>
     */
    @GetMapping("/pageTest/{name}")
    @ResponseBody
    public PageInfo<Test> pageTest(@PathVariable String name, @RequestParam(defaultValue = "1") int pageNo,
                                   @RequestParam(defaultValue = "10") int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        return new PageInfo<>(apiService.getUsers(name));
    }


    /**
     * 请求参数的验证
     *
     * @param params        参数
     * @param bindingResult 验证结果
     * @return ResultModel<Object>
     */
    @PostMapping("/verify")
    @ResponseBody
    public ResultModel<Object> paramsVerify(@RequestBody @Valid RequestVO params, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ValidateMsgUtil.invalidResult(bindingResult);
        }
        return apiService.paramsVerify(params);
    }


    /**
     * 邮件功能测试
     */
    @GetMapping("/sendEmail")
    @ResponseBody
    public void sendEmail() {
        apiService.sendEmail();
    }


    /**
     * 多数据源测试
     *
     * @param name 姓名
     * @return Test
     */
    @GetMapping("/dataSourceTest/{name}")
    @ResponseBody
    public Test dataSourceTest(@PathVariable String name) {
        return apiService.dataSourceTest(name);
    }


    /**
     * spring-retry：重试框架测试
     */
    @GetMapping("/retry")
    @ResponseBody
    public void reTryTest() {
        retryService.testRetry();
    }

}
