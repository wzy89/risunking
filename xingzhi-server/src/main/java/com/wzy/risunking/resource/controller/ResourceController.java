package com.wzy.risunking.resource.controller;

import com.wzy.risunking.resource.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping(value = "/resource")
public class ResourceController {

    @Autowired
    ResourceService resourceService;

    /**
     * 获取资源列表
     *
     * @param folderId 文件夹id
     * @param resourceType 资源类型
     * @param searchKey 查询关键字
     * @param pageSize 页大小
     * @param page  第几页
     * @param userId 用户id
     * @return
     */
    @RequestMapping(value = "/resourceList",method = RequestMethod.POST)
    public List<Map> resourceList(String folderId, String resourceType, String searchKey, String pageSize, String page, String userId){
        return resourceService.getResourceList(folderId, resourceType, searchKey, pageSize, page, userId);
    }

    /**
     * 更新资源
     * @param resourceId 更新资源id
     * @param oldRealName 旧资源文件保存的名称（用于删除旧文件）
     * @param newRealName 新资源文件保存的名称
     * @param resourceName 资源文件显示名称
     * @param folderId  文件夹id
     * @param description  资源描述
     * @param userId 用户id（用于判断权限）
     * @return
     */
    @RequestMapping(value = "/updateResource",method = RequestMethod.POST)
    public Boolean updateResource(String resourceId ,String oldRealName, String newRealName, String resourceName, String folderId, String description, String resourceHref,String userId){
        return resourceService.updateResource(resourceId , oldRealName, newRealName, resourceName, folderId, description, resourceHref, userId);
    }

    /**
     * 保存资源
     *
     * @param resourceRealName 资源文件保存的名称
     * @param resourceName 资源文件显示名称
     * @param folderId 文件夹id
     * @param description 资源描述
     * @param userId 用户id
     * @return
     */
    @RequestMapping(value = "/saveResource",method = RequestMethod.POST)
    public Boolean saveResource(String resourceRealName, String resourceName, String folderId, String description,String userId){
        return resourceService.saveResource(resourceRealName, resourceName, folderId, description, userId);
    }

    /**
     * 删除资源（同时会删除文件）
     * @param resourceRealName
     * @param resourceId
     * @param userId
     * @return
     */
    @RequestMapping(value = "/deleteResource",method = RequestMethod.POST)
    public Boolean deleteResource(String resourceRealName, String resourceId, String userId){
        return resourceService.deleteResource(resourceRealName, resourceId, userId);
    }

}
