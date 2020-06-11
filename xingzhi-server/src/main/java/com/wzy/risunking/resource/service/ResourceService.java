package com.wzy.risunking.resource.service;

import java.util.List;
import java.util.Map;

public interface ResourceService {

    /**
     * 通过类型/folderId/查询key/page 获取资源列表
     *
     * @param folderId
     * @param userId
     * @return
     */
    List<Map> getResourceList(String folderId, String resourceType, String searchKey, String pageSize, String page, String userId);

    /**
     * 保存资源
     *
     * @param resourceRealName
     * @param resourceName
     * @param folderId
     * @param description
     * @param userId
     * @return
     */
    Boolean saveResource(String resourceRealName, String resourceName, String folderId, String description,String userId);

    /**
     * 更新资源
     *
     * @param oldRealName
     * @param newRealName
     * @param resourceName
     * @param folderId
     * @param description
     * @param userId
     * @return
     */
    Boolean updateResource(String resourceId ,String oldRealName, String newRealName, String resourceName, String folderId, String description, String resourceHref,String userId);

    /**
     * 删除资源
     *
     * @param resourceRealName
     * @param resourceId
     * @param userId
     * @return
     */
    Boolean deleteResource(String resourceRealName, String resourceId, String userId);

}
