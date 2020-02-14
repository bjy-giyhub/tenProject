package com.tensquare.base.service;

import com.tensquare.base.dao.LableDao;
import com.tensquare.base.pojo.Lable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class LableService {

    @Autowired
    private LableDao lableDao;

    @Autowired
    private IdWorker idWorker;

    /**
     * 查询所有
     * @return
     */
    public List<Lable> findAll(){
        return lableDao.findAll();
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    public Lable findById(String id){
        return lableDao.findById(id).get();
    }

    /**
     * 新增标签
     * @param lable
     */
    public void add(Lable lable){
        lable.setId(idWorker.nextId()+"");
        lableDao.save(lable);
    }

    /**
     * 更新标签
     * @param lable
     */
    public void update(Lable lable){
        lableDao.save(lable);
    }

    /**
     * 根据id删除标签
     * @param id
     */
    public void deleteById(String id){
        lableDao.deleteById(id);
    }

    /**
     * 构建查询条件
     * @param searchMap
     * @return
     */
    private Specification<Lable> createSpecification(Map searchMap){
        return new Specification<Lable>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<Lable> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<>();
                if(searchMap.get("lablename") != null && !"".equals(searchMap.get("lablename"))){
                    predicateList.add(criteriaBuilder.like(root.get("lablename").as(String.class), "%" + (String)searchMap.get("lablename") + "%"));
                }
                if(searchMap.get("state") != null && !"".equals(searchMap.get("state"))){
                    predicateList.add(criteriaBuilder.equal(root.get("state").as(String.class), (String)searchMap.get("state")));
                }
                if(searchMap.get("recommend") != null && !"".equals(searchMap.get("recommend"))){
                    predicateList.add(criteriaBuilder.equal(root.get("recommend").as(String.class), (String)searchMap.get("recommend")));
                }
                return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };
    }

    /**
     * 条件查询
     * @param searchMap
     * @return
     */
    public List<Lable> findSearch(Map searchMap){
        Specification<Lable> specification = createSpecification(searchMap);
        return lableDao.findAll(specification);
    }

    public Page<Lable> findSearch(Map searchMap, int page, int size){
        Specification<Lable> specification = createSpecification(searchMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return lableDao.findAll(specification, pageRequest);
    }
}
