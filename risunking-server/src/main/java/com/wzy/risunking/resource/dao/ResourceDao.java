package com.wzy.risunking.resource.dao;

import com.wzy.risunking.resource.entity.ResourceInfo;
import com.wzy.risunking.resource.entity.ResourceSearchIn;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ResourceDao {

    List<ResourceInfo> getResourceList(ResourceSearchIn searchIn);

    int insertResource(ResourceInfo resourceInfo);

    int updateResource(ResourceInfo resourceInfo);

    int deleteResource(ResourceInfo resourceInfo);

}
