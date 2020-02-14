package com.tensquare.recruit.controller;

import com.tensquare.recruit.pojo.Recruit;
import com.tensquare.recruit.service.RecruitService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/recruit")
@RestController
@CrossOrigin
public class RecruitController {

    @Autowired
    private RecruitService recruitService;

    /**
     * 添加职位
     * @param recruit
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result addRecruit(@RequestBody Recruit recruit){
        recruitService.addRecruit(recruit);
        return new Result(true, StatusCode.OK,"添加成功");
    }

    /**
     * 查询推荐职位
     * @return
     */
    @RequestMapping(value = "/search/recommend", method = RequestMethod.GET)
    public Result recommend(){
        return new Result(true,StatusCode.OK,"查询成功",recruitService.findTop4ByStateOrderByCreatetimeDesc("2"));
    }

    /**
     * 查询最新职位
     * @return
     */
    @RequestMapping(value = "/search/newlist", method = RequestMethod.GET)
    public Result newList(){
        return new Result(true,StatusCode.OK,"查询成功",recruitService.findTop12ByStateNotOrderByCreatetimeDesc("0"));
    }
}
