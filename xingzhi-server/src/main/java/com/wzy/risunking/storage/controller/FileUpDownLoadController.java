package com.wzy.risunking.storage.controller;

import com.wzy.risunking.storage.service.FileUpDownLoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin
@RequestMapping(value = "/storage")
public class FileUpDownLoadController {
    @Autowired
    FileUpDownLoadService fileUpDownLoadService;

    @RequestMapping(value = "/downloadFile")
    public void downloadFile(HttpServletRequest req, HttpServletResponse resp){
        fileUpDownLoadService.download(req,resp);
    }

    @RequestMapping(value = "/uploadFile")
    public void uploadFile(HttpServletRequest req, HttpServletResponse resp){
        fileUpDownLoadService.saveUploadFile(req,resp);
    }
}
