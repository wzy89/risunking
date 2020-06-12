package com.wzy.risunking.resource.controller;

import com.wzy.risunking.resource.entity.ResourceInfo;
import com.wzy.risunking.resource.entity.ResourceSearchIn;
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
@RequestMapping(value = "web/resource")
public class ResourceController {

    @Autowired
    ResourceService resourceService;

    /**
     * 获取资源列表
     *
     * @return
     */
    @RequestMapping(value = "/resourceList",method = RequestMethod.POST)
    public List<ResourceInfo> resourceList(ResourceSearchIn searchIn){
        return resourceService.getResourceList(searchIn);
    }

    /**
     * 更新资源
     *
     * @return
     */
    @RequestMapping(value = "/updateResource",method = RequestMethod.POST)
    public Boolean updateResource(ResourceInfo resourceInfo){
        return resourceService.updateResource(resourceInfo);
    }

    /**
     * 保存资源
     *
     * @return
     */
    @RequestMapping(value = "/insertResource",method = RequestMethod.POST)
    public Boolean insertResource(ResourceInfo resourceInfo){
        return resourceService.saveResource(resourceInfo);
    }

    /**
     * 删除资源（同时会删除文件）
     *
     * @return
     */
    @RequestMapping(value = "/deleteResource",method = RequestMethod.POST)
    public Boolean deleteResource(ResourceInfo resourceInfo){
        return resourceService.deleteResource(resourceInfo);
    }
}
