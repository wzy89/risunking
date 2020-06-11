package com.wzy.risunking.resource.dao;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class FolderDao {

    /**
     * 根据userId和父floderid查询低一级的floder（只低一级）
     * @param userId 用户id
     * @param pid 文件夹的父ID（当传入空的时候，查询该用户所有的floder）
     * @return
     */
    public List<Map<String,String>> foldersByUserIdAndPFloderId(String userId, String pid) {
        return null;
    }

    /**
     * 更新floder
     * @param userId  用户id
     * @param floderId  需要更新的floder
     * @return
     */
    public int updateFloderByUserIdAndFolderId(String userId, String floderId, String floderName, String pid) {
        return 0;
    }

    /**
     * 批量删除folder
     * @param userId
     * @param floderId
     * @return
     */
    public int deleteFloderByUserIdAndFolderId(String userId, String floderId){
        return 0;
    }

    /**
     * 查询直接子文件夹数量
     * @param userId
     * @param floderId
     * @return
     */
    public int childsCountForFolder(String userId, String floderId){
        return 0;
    }
}
