package com.tensquare.base.pojo;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_lable")
public class Lable {

    @Id
    private String id;
    //标签名称
    private String lablename;
    //状态
    private String state;
    //使用数量
    private Long count;
    //关注数量
    private Long fans;
    //是否推荐
    private String recommend;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLablename() {
        return lablename;
    }

    public void setLablename(String lablename) {
        this.lablename = lablename;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Long getFans() {
        return fans;
    }

    public void setFans(Long fans) {
        this.fans = fans;
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }
}
