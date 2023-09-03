package com.zwyue.service.impl;

import com.zwyue.annotation.PagingQuery;
import com.zwyue.dao.ResourceDao;
import com.zwyue.entity.Resource;
import com.zwyue.enums.CategoryEnum;
import com.zwyue.service.ResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * RecourceServiceImpl class
 *
 * @author zwy
 * @date 2018/11/28 15:46
 */
@Repository
public class ResourceServiceImpl implements ResourceService {

    private static final Logger logger = LoggerFactory.getLogger(ResourceServiceImpl.class);

    @Autowired
    private ResourceDao resourceDao ;

    @Override
    public Integer saveResouce(Resource resource) {
        return resourceDao.insert(resource);
    }

    /**
     * 查询资源建设列表
     *
     * @author zwy
     * @date 2018/11/28 17:10
     */
    @Override
    @PagingQuery
    public List<Resource> queryResourceList() {
        List<Resource> list = resourceDao.queryResourceList();
        list.forEach(li->li.setCategoryTxt(CategoryEnum.returnEnumByCode(li.getCategory()).desc));
        return list;
    }

    @Override
    public Resource queryResourceById(Integer id) {
        Resource resource = resourceDao.selectByPrimaryKey(id) ;
        resource.setCategoryTxt(CategoryEnum.returnEnumByCode(resource.getCategory()).desc);
        return resource;
    }

    @Override
    public Integer updateResource(Resource resource) {
        return resourceDao.updateByPrimaryKeySelective(resource);
    }

    @Override
    public Integer deleteResouece(List<Integer> idList) {
        return resourceDao.deleteResourceByIds(idList);
    }

    @Override
    @PagingQuery
    public List<Resource> getTitleList(String title) {
        return resourceDao.getTitleList(title);
    }
}
