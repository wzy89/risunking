package com.wzy.risunking.resource.service;

import com.wzy.risunking.resource.entity.ResourceInfo;
import com.wzy.risunking.resource.entity.ResourceSearchIn;
import java.util.List;
import java.util.Map;

public interface ResourceService {

    /**
     * 通过类型/folderId/查询key/page 获取资源列表
     *
     * @return
     */
    List<ResourceInfo> getResourceList(ResourceSearchIn searchIn);

    /**
     * 保存资源
     *
     * @return
     */
    Boolean saveResource(ResourceInfo resourceInfo);

    /**
     * 更新资源
     *
     * @return
     */
    Boolean updateResource(ResourceInfo resourceInfo);

    /**
     * 删除资源
     *
     * @return
     */
    Boolean deleteResource(ResourceInfo resourceInfo);

}
