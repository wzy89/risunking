package com.wzy.risunking.resource.service;

import com.wzy.risunking.resource.entity.ArticleInfo;
import com.wzy.risunking.resource.entity.ArticleSearchIn;

import java.util.List;

/**
 * ArticlesService
 *
 * @author Wangzy
 * @date 2020/6/24 17:31
 */
public interface ArticlesService {
    /**
     * 文章列表
     *
     * @return java.util.List<com.wzy.risunking.resource.entity.ArticleInfo>
     * @author Wangzy
     * @date 2020/6/24 17:33
     */
    List<ArticleInfo> articleList(ArticleSearchIn articleSearchIn);

    /**
     * 添加文章
     *
     * @param articleInfo
     * @return int
     * @author Wangzy
     * @date 2020/6/24 17:42
     */
    int articleAdd(ArticleInfo articleInfo);

    /**
     * 文章更新
     *
     * @param articleInfo
     * @return int
     * @author Wangzy
     * @date 2020/6/24 17:42
     */
    int articleUpdate(ArticleInfo articleInfo);

    /**
     * 更新文章已读数量
     *
     * @param articleInfo
     * @return int
     * @author Wangzy
     * @date 2020/9/18 23:19
     */
    int articleUpdateReadNum(ArticleInfo articleInfo);

    /**
     * 文章删除
     *
     * @param articleInfo
     * @return int
     * @author Wangzy
     * @date 2020/6/24 17:43
     */
    int articleDelete(ArticleInfo articleInfo);

    /**
     * 获取文章content
     *
     * @param articleInfo
     * @return java.lang.String
     * @author Wangzy
     * @date 2020/9/23 11:30
     */
    ArticleInfo articleContent(ArticleInfo articleInfo);
}
