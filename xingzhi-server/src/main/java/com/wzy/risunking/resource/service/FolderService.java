package com.wzy.risunking.resource.service;

import java.util.Map;

public interface FolderService {

    /**
     * 获取文件结构树(folderId为空时，获取整棵文件夹树)
     *
     * @param userId 资源id
     * @param folderId 父级文件夹id
     *
     * @return 资源详情信息
     */
    Map getFolderTree(String folderId, String userId);

    /**
     * 添加子目录
     *
     * @param folderName
     * @param userId
     * @param fatherFolderId
     * @return
     */
    Boolean addFolder(String folderName, String fatherFolderId, String userId);

    /**
     * 删除目录结(当目录下有子目录或者保护资源的时候将删除失败)
     *
     * @param folderId
     * @param userId
     * @return
     */
    Boolean deleteFolder(String folderId, String userId);

    /**
     * 更新folder的名称
     *
     * @param newFolderName
     * @param folderId
     * @return
     */
    Boolean updateFolderName(String newFolderName, String folderId, String userId);
}
