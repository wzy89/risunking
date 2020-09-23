package com.wzy.risunking.resource.controller;

import com.wzy.risunking.global.entity.CommandException;
import com.wzy.risunking.global.entity.Response;
import com.wzy.risunking.resource.entity.ArticleInfo;
import com.wzy.risunking.resource.entity.ArticleSearchIn;
import com.wzy.risunking.resource.service.ArticlesService;
import com.wzy.risunking.utils.DataCheck;
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
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public List<ArticleInfo> articleList(@RequestBody ArticleSearchIn articleSearchIn) {
        String page = articleSearchIn.getPage();
        String size = articleSearchIn.getSize();
        if (DataCheck.containsEmptyString(page, size)) {
            throw new CommandException(Response.PARAM_ERROR, "参数错误");
        }
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
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    Integer articleAdd(ArticleInfo articleInfo) {
        String title = articleInfo.getTitle();
        String desc = articleInfo.getDesc();
        if (DataCheck.containsEmptyString(title, desc)) {
            throw new CommandException(Response.PARAM_ERROR, "参数错误");
        }
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
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    Integer articleUpdate(ArticleInfo articleInfo) {
        String articleId = articleInfo.getId();
        if (DataCheck.isEmptyString(articleId)) {
            throw new CommandException(1, "参数错误");
        }
        return articlesService.articleUpdate(articleInfo);
    }

    /**
     * 文章阅读数量更新
     *
     * @param articleInfo
     * @return int
     * @author Wangzy
     * @date 2020/6/24 17:42
     */
    @RequestMapping(value = "/addReadNum", method = RequestMethod.POST)
    Integer articleUpdateReadNum(ArticleInfo articleInfo) {
        String articleId = articleInfo.getId();
        if (DataCheck.isEmptyString(articleId)) {
            throw new CommandException(1, "参数错误");
        }
        return articlesService.articleUpdateReadNum(articleInfo);
    }

    /**
     * 文章删除
     *
     * @param articleInfo
     * @return int
     * @author Wangzy
     * @date 2020/6/24 17:43
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    Integer articleDelete(ArticleInfo articleInfo) {
        String articleId = articleInfo.getId();
        if (DataCheck.isEmptyString(articleId)) {
            throw new CommandException(1, "参数错误");
        }
        return articlesService.articleDelete(articleInfo);
    }

    /**
     * 获取文章content
     *
     * @param articleInfo
     * @return java.lang.String
     * @author Wangzy
     * @date 2020/9/23 11:30
     */
    ArticleInfo articleContent(ArticleInfo articleInfo) {
        return articlesService.articleContent(articleInfo);
    }
}
