package com.wzy.risunking.resource.controller;

import com.wzy.risunking.resource.entity.ArticleInfo;
import com.wzy.risunking.resource.entity.ArticleSearchIn;
import com.wzy.risunking.resource.service.ArticlesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ArticlesController
 *
 * @author Wangzy
 * @date 2020/6/24 17:27
 */
@RestController
@CrossOrigin
@RequestMapping(value = "web/resource/articles")
public class ArticlesController {

    @Autowired
    ArticlesService articlesService;

    /**
     * 查询文章列表
     *
     * @param articleSearchIn
     * @return java.util.List<com.wzy.risunking.resource.entity.ArticleInfo>
     * @author Wangzy
     * @date 2020/6/24 17:45
     */
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public List<ArticleInfo> articleList(@RequestBody ArticleSearchIn articleSearchIn){
        return articlesService.articleList(articleSearchIn);
    }

    /**
     * 添加文章
     *
     * @param articleInfo
     * @return int
     * @author Wangzy
     * @date 2020/6/24 17:42
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    int articleAdd(ArticleInfo articleInfo){
        return articlesService.articleAdd(articleInfo);
    }

    /**
     * 文章更新
     *
     * @param articleInfo
     * @return int
     * @author Wangzy
     * @date 2020/6/24 17:42
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    int articleUpdate(ArticleInfo articleInfo){
        return articlesService.articleUpdate(articleInfo);
    }

    /**
     * 文章删除
     *
     * @param articleInfo
     * @return int
     * @author Wangzy
     * @date 2020/6/24 17:43
     */
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    int articleDelete(ArticleInfo articleInfo){
        return articlesService.articleDelete(articleInfo);
    }
}
