package com.wzy.risunking.resource.controller;

import com.wzy.risunking.resource.service.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping(value = "/folder")
public class FolderController {

    @Autowired
    FolderService folderService;

    /**
     * 获取文件夹树
     * @param userId
     * @param folderId
     * @return
     */
    @RequestMapping(value = "/folderTree",method = RequestMethod.POST)
    public Map folderTree(String userId, String folderId){
        return folderService.getFolderTree(userId, folderId);
    }

    /**
     * 增加一个文件夹
     * @param folderName
     * @param fatherFolderId
     * @param userId
     * @return
     */
    @RequestMapping(value = "/addFolder",method = RequestMethod.POST)
    public Boolean addFolder(String folderName, String fatherFolderId, String userId){
        return folderService.addFolder(folderName, fatherFolderId, userId);
    }

    /**
     * 删除一个文件夹
     * @param folderId
     * @param userId
     * @return
     */
    @RequestMapping(value = "/deleteFolder",method = RequestMethod.POST)
    public Boolean deleteFolder(String folderId, String userId){
        return folderService.deleteFolder(folderId, userId);
    }

    /**
     * 更新一个文件夹（名字）
     * @param newFolderName
     * @param folderId
     * @param userId
     * @return
     */
    @RequestMapping(value = "/updateFolderName",method = RequestMethod.POST)
    public Boolean updateFolderName(String newFolderName, String folderId, String userId){
        return folderService.updateFolderName(newFolderName, folderId, userId);
    }

}
