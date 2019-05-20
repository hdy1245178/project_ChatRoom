package com.hdy.base.service;

import com.hdy.base.entity.Label;
import com.hdy.base.dao.LabelDao;
import com.hdy.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class LabelService {

    @Autowired
    private LabelDao labelDao ;
    @Autowired
    private IdWorker idWorker ;

    //查询全部label数据
    public List<Label> findAll(){
        return labelDao.findAll();//JPA提供的
    }

    public Label findLabelById(String id){
        return  labelDao.findById(id).get() ;
    }

    public void saveLabel(Label label){
        label.setId(idWorker.nextId()+"");//雪花算法
        labelDao.save(label);
    }

    public void updateLabel(Label label){
        labelDao.save(label) ;
    }
    public void deleteLabelById(String id){
        labelDao.deleteById(id);
    }


    //构建查询条件
     public Specification<Label> createSpecification(Map queryMap){//where  ...or... and ...
        return  new Specification<Label>(){
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
              List<Predicate> predicates = new ArrayList<>() ;
                //... where name like '%xxx%' ;
                //..where labelname like ? and  recommend like ?
                //queryMap.set("labelname","旅游")
                //  是空： (queryMap.get("labelname") ==null || queryMap.get("labelname") .equals(""))
                //if(!(queryMap.get("labelname") ==null || queryMap.get("labelname") .equals("")))
               if(queryMap.get("labelname") !=null &&  !queryMap.get("labelname").equals("")){
                   Predicate p1 = criteriaBuilder.like(root.get("labelname").as(String.class)
                           , "%" + (String) queryMap.get("labelname") + "%");
                   predicates.add(p1 ) ;
                }

                if(queryMap.get("recommend") !=null &&  !queryMap.get("recommend").equals("")){
                    Predicate p2 = criteriaBuilder.like(  root.get("recommend").as(String.class)
                            ,"%"+(String)queryMap.get("recommend")+"%")  ;
                    predicates.add(p2 ) ;
                }
                return  criteriaBuilder.and(     predicates.toArray(  new Predicate[predicates.size()] )              );
            }
        };
     }

        //根据条件查询
        public List<Label> findLabels(Map queryMap){
            Specification<Label> specification = createSpecification(queryMap);
            return labelDao.findAll(specification) ;
        }


    //根据条件查询
    public Page findLabels(Map queryMap  ,int start ,int pagesize  ){
        Specification<Label> specification = createSpecification(queryMap);
        PageRequest pageRequest = PageRequest.of(start - 1, pagesize);
        return labelDao.findAll(specification, pageRequest);
    }


}
