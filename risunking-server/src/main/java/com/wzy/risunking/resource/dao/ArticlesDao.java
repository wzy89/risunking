package com.wzy.risunking.resource.dao;

import com.wzy.risunking.resource.entity.ArticleInfo;
import com.wzy.risunking.resource.entity.ArticleSearchIn;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

/**
 * ArticlesDao
 *
 * @author Wangzy
 * @date 2020/9/15 9:45
 */
public interface ArticlesDao {
    /**
     * 查询文章
     *
     * @return java.util.List<com.wzy.risunking.resource.entity.ArticleInfo>
     * @author Wangzy
     * @date 2020/6/24 17:33
     */
    List<ArticleInfo> articleList(@Param("articleSearchIn") ArticleSearchIn articleSearchIn);
    int articleListCount(@Param("articleSearchIn") ArticleSearchIn articleSearchIn);

    /**
     * 添加文章
     *
     * @param articleInfo
     * @return int
     * @author Wangzy
     * @date 2020/6/24 17:42
     */
    int articleAdd(@Param("articleInfo") ArticleInfo articleInfo);

    /**
     * 更新文章已读数量
     *
     * @param articleInfo
     * @return int
     * @author Wangzy
     * @date 2020/9/18 23:19
     */
    int articleUpdateReadNum(@Param("articleInfo") ArticleInfo articleInfo);

    /**
     * 更新文章
     *
     * @param articleInfo
     * @return int
     * @author Wangzy
     * @date 2020/6/24 17:42
     */
    int articleUpdate(@Param("articleInfo") ArticleInfo articleInfo);

    /**
     * 删除文章
     *
     * @param articleInfo
     * @return int
     * @author Wangzy
     * @date 2020/6/24 17:43
     */
    int articleDelete(@Param("articleInfo") ArticleInfo articleInfo);

    /**
     * 插入文章和标签的关系表
     *
     * @param articleId
     * @param tagCodes
     * @return
     */
    int articleToResourceAdd(@Param("articleId") String articleId, @Param("tagCodes") List<String> tagCodes);

    /**
     * 删除文章和标签的关系表
     *
     * @param articleInfo
     * @return int
     * @author Wangzy
     * @date 2020/9/23 10:59
     */
    int articleToResourceDelete(@Param("articleInfo") ArticleInfo articleInfo);

    /**
     * 获取文章详情
     *
     * @param articleInfo
     * @return java.lang.String
     * @author Wangzy
     * @date 2020/9/23 11:30
     */
    ArticleInfo articleDetail(@Param("articleInfo") ArticleInfo articleInfo);
}
