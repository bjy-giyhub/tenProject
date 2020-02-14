package com.tensquare.recruit.service;

import com.tensquare.recruit.dao.RecruitDao;
import com.tensquare.recruit.pojo.Recruit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.IdWorker;

import java.util.List;

@Service
public class RecruitService {

    @Autowired
    private RecruitDao recruitDao;

    @Autowired
    private IdWorker idWorker;

    public void addRecruit(Recruit recruit){
        recruit.setId(idWorker.nextId()+"");
        recruitDao.save(recruit);
    }

    /**
     * 查询推荐职位
     * @param state
     * @return
     */
    public List<Recruit> findTop4ByStateOrderByCreatetimeDesc(String state){
        return recruitDao.findTop4ByStateOrderByCreatetimeDesc(state);
    }

    public List<Recruit> findTop12ByStateNotOrderByCreatetimeDesc(String state){
        return recruitDao.findTop12ByStateNotOrderByCreatetimeDesc(state);
    }
}
