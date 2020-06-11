package com.wzy.risunking.resource.service.impl;

import com.wzy.risunking.global.entity.CommandException;
import com.wzy.risunking.global.entity.Response;
import com.wzy.risunking.resource.dao.FolderDao;
import com.wzy.risunking.resource.service.FolderService;
import com.wzy.risunking.utils.DataCheck;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class FolderServiceImpl implements FolderService {

    @Autowired
    FolderDao folderDao;

    Logger logger = LoggerFactory.getLogger(FolderServiceImpl.class);

    /**
     * 获取文件结构树(folderId为空时，获取整棵文件夹树)
     *
     * @param userId 资源id
     * @param folderId 父级文件夹id
     *
     * @return 资源详情信息
     */
    @Override
    public Map getFolderTree(String folderId, String userId) {
        // 请求参数是否错误
        if (DataCheck.containsEmptyString(userId)){
            throw new CommandException(Response.PARAM_ERROR, "请求参数错误");
        }

        return null;
    }

    /**
     * 添加子目录
     *
     * @param folderName
     * @param userId
     * @param fatherFolderId
     * @return
     */
    @Override
    public Boolean addFolder(String folderName, String fatherFolderId, String userId) {
        return null;
    }

    /**
     * 删除目录结(当目录下有子目录或者保护资源的时候将删除失败)
     *
     * @param folderId
     * @param userId
     * @return
     */
    @Override
    public Boolean deleteFolder(String folderId, String userId) {
        return null;
    }

    /**
     * 更新folder的名称
     *
     * @param newFolderName
     * @param folderId
     * @return
     */
    @Override
    public Boolean updateFolderName(String newFolderName, String folderId, String userId) {
        return null;
    }
}
