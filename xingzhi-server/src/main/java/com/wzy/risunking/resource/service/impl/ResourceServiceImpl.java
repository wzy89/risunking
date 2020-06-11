package com.wzy.risunking.resource.service.impl;

import com.wzy.risunking.global.config.Config;
import com.wzy.risunking.global.entity.CommandException;
import com.wzy.risunking.global.entity.Response;
import com.wzy.risunking.resource.dao.ResourceDao;
import com.wzy.risunking.resource.dao.TagDao;
import com.wzy.risunking.resource.service.ResourceService;
import com.wzy.risunking.utils.DataCheck;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.List;
import java.util.Map;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    ResourceDao resourceDao;
    @Autowired
    TagDao tagDao;

    Logger logger = LoggerFactory.getLogger(ResourceServiceImpl.class);

    /**
     * 通过类型/folderId/查询key/page 获取资源列表
     *
     * @param folderId
     * @param userId
     * @return
     */
    @Override
    public List<Map> getResourceList(String folderId, String resourceType, String searchKey, String pageSize, String page, String userId) {
        // 请求参数是否错误
        if (DataCheck.containsEmptyString(userId)){
            throw new CommandException(Response.PARAM_ERROR, "请求参数错误");
        }
        int intPage = 0;
        int intPageSize = 0;
        if (!DataCheck.containsEmptyString(page,pageSize)){
            intPage = Integer.valueOf(page);
            intPageSize = Integer.valueOf(pageSize);
        }
        List<Map> resultList = resourceDao.getResourceList(folderId,resourceType,searchKey,intPageSize,intPage,userId);
        return resultList;
    }

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
    @Override
    @Transactional
    public Boolean saveResource(String resourceRealName, String resourceName, String folderId, String description, String userId) {
        // 请求参数是否错误
        if (DataCheck.containsEmptyString(resourceRealName,resourceName,userId)){
            throw new CommandException(Response.PARAM_ERROR, "请求参数错误");
        }
        String filePath = Config.getFileDirWithFileName(resourceRealName);
        String resourceType = resourceTypeByName(resourceName);
        int saveResourceId = resourceDao.saveResource(filePath,resourceName,folderId,description,userId,resourceType);
        if (saveResourceId > 0){
            int saveUser2ResrouceId = resourceDao.saveUser2Resource(userId,String.valueOf(saveResourceId),null);
            if (saveUser2ResrouceId>0){
                return true;
            }
        }
        return false;
    }

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
    @Override
    public Boolean updateResource(String resourceId ,String oldRealName, String newRealName, String resourceName, String folderId, String description,String resourceHref,String userId) {
        // 请求参数是否错误
        if (DataCheck.containsEmptyString(userId,resourceId)){
            throw new CommandException(Response.PARAM_ERROR, "请求参数错误");
        }
        // 查询文件所属权限
        String ownType = resourceDao.getResourceOwnType(resourceId,userId);
        if (ownType.equals("0")){
            // 如果文件更新了
            String filePath = null;
            if (oldRealName != null && newRealName != null && !oldRealName.equals(newRealName)){
                this.deleteFile(oldRealName);//删除原来文件
                filePath = Config.getFileDirWithFileName(newRealName);
            }
            String resourceType = resourceTypeByName(resourceName);
            int updatedNum = resourceDao.updateResource(resourceId,filePath,resourceName,folderId,description,resourceType,resourceHref);
            if (updatedNum >0){
                return true;
            }
            return false;
        } else {
            throw new CommandException(Response.UNAUTHORIZED_ACCESS, "您没有更改该文件的权限");
        }
    }

    /**
     * 删除资源
     *
     * @param resourceRealName
     * @param resourceId
     * @param userId
     * @return
     */
    @Override
    @Transactional
    public Boolean deleteResource(String resourceRealName, String resourceId, String userId) {
        // 请求参数是否错误
        if (DataCheck.containsEmptyString(userId,resourceId,resourceRealName)){
            throw new CommandException(Response.PARAM_ERROR, "请求参数错误");
        }
        // 查询文件所属权限
        String ownType = resourceDao.getResourceOwnType(resourceId,userId);
        if (ownType.equals("0")){
            //删除数据库存储(因为文件不存在了，所以删除文件相关的所有用户和tag信息，文件拥有者可以绝对控制文件)
            resourceDao.deleteResource(resourceId);
            resourceDao.deleteUser2Resource(resourceId, null);
            tagDao.deleteUser2Resource2Tag(resourceId,null,null);
            //删除文件
            this.deleteFile(resourceRealName);
            return true;
        }else {
            throw new CommandException(Response.UNAUTHORIZED_ACCESS, "您没有删除该文件的权限");
        }
    }

    /**
     * 删除文件
     *
     * @param fileName
     */
    private Boolean deleteFile(String fileName) {
        String filePath = Config.getFileDirWithFileName(fileName);
        File file = new File(filePath);
        if (file.exists()) {
            if (file.delete()) {
                return true;
            } else {
                logger.error("删除文件失败");
                return false;
            }
        }else {
            logger.error("想要删除的文件不存在,不删除,但返回成功");
            return true;
        }
    }

    private String resourceTypeByName(String resourceName){
        if (resourceName == null || resourceName.equals("")){
            return "-1";
        }
        int dot = resourceName.lastIndexOf(46);
        if (dot > -1 && dot < resourceName.length() - 1) {
            String ext = resourceName.substring(dot + 1).toLowerCase();
            switch (ext){
                case "txt":
                case "h":
                case "m":
                case "c":
                case "cpp":
                case "js":
                case "css":
                case "xml":
                case "sql":
                case "vue":
                case "java":
                case "properties":
                case "plist":
                case "sh":
                case "bat":
                case "yml":{
                    return "0";//文本
                }
                case "gif":
                case "jpg":
                case "jpeg":
                case "png":{
                    return "1";//图片
                }
                case "wps":
                case "doc":
                case "ppt":
                case "xls":
                case "xlsx":
                case "pptx":
                case "docx": {
                    return "2";//office
                }
                case "avi":
                case "rm":
                case "mov":
                case "rmvb":
                case "flv":
                case "mp4":
                case "3gp":{
                    return "3";//视频
                }
                case "wav":
                case "wma":
                case "aac":
                case "mp3":{
                    return "4";//音频
                }
                case "pdf":{
                    return "5";//pdf
                }
                default:{
                    return "-1";//未知
                }
            }
        }
        return "-1";//未知
    }
}
