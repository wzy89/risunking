package com.wzy.risunking.resource.service.impl;

import com.wzy.risunking.resource.entity.ArticleInfo;
import com.wzy.risunking.resource.entity.ArticleSearchIn;
import com.wzy.risunking.resource.service.ArticlesService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ArticlesServiceImpl
 *
 * @author Wangzy
 * @date 2020/6/24 17:39
 */
@Service
public class ArticlesServiceImpl implements ArticlesService {

    /**
     * 文章列表
     *
     * @return java.util.List<com.wzy.risunking.resource.entity.ArticleInfo>
     * @author Wangzy
     * @date 2020/6/24 17:33
     */
    @Override
    public List<ArticleInfo> articleList(ArticleSearchIn articleSearchIn) {
        return null;
    }

    /**
     * 添加文章
     *
     * @param articleInfo
     * @return int
     * @author Wangzy
     * @date 2020/6/24 17:42
     */
    @Override
    public int articleAdd(ArticleInfo articleInfo) {
        return 0;
    }

    /**
     * 文章更新
     *
     * @param articleInfo
     * @return int
     * @author Wangzy
     * @date 2020/6/24 17:42
     */
    @Override
    public int articleUpdate(ArticleInfo articleInfo) {
        return 0;
    }

    /**
     * 文章删除
     *
     * @param articleInfo
     * @return int
     * @author Wangzy
     * @date 2020/6/24 17:43
     */
    @Override
    public int articleDelete(ArticleInfo articleInfo) {
        return 0;
    }
}
