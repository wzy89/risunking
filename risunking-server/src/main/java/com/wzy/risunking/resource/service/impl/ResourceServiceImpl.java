package com.wzy.risunking.resource.service.impl;

import com.wzy.risunking.resource.dao.ResourceDao;
import com.wzy.risunking.resource.entity.ResourceInfo;
import com.wzy.risunking.resource.entity.ResourceSearchIn;
import com.wzy.risunking.resource.service.ResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    ResourceDao resourceDao;

    Logger logger = LoggerFactory.getLogger(ResourceServiceImpl.class);

    /**
     * 通过类型/folderId/查询key/page 获取资源列表
     *
     * @param searchIn
     * @return
     */
    @Override
    public List<ResourceInfo> getResourceList(ResourceSearchIn searchIn) {
        return null;
    }

    /**
     * 保存资源
     *
     * @param resourceInfo
     * @return
     */
    @Override
    public Boolean saveResource(ResourceInfo resourceInfo) {
        return null;
    }

    /**
     * 更新资源
     *
     * @param resourceInfo
     * @return
     */
    @Override
    public Boolean updateResource(ResourceInfo resourceInfo) {
        return null;
    }

    /**
     * 删除资源
     *
     * @param resourceInfo
     * @return
     */
    @Override
    public Boolean deleteResource(ResourceInfo resourceInfo) {
        return null;
    }
}
