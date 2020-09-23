package com.wzy.risunking.resource.service.impl;

import com.wzy.risunking.global.config.Config;
import com.wzy.risunking.global.entity.CommandException;
import com.wzy.risunking.global.entity.Response;
import com.wzy.risunking.resource.dao.ArticlesDao;
import com.wzy.risunking.resource.entity.ArticleInfo;
import com.wzy.risunking.resource.entity.ArticleSearchIn;
import com.wzy.risunking.resource.service.ArticlesService;
import com.wzy.risunking.utils.CommonUtils;
import com.wzy.risunking.utils.DataCheck;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * ArticlesServiceImpl
 *
 * @author Wangzy
 * @date 2020/6/24 17:39
 */
@Service
public class ArticlesServiceImpl implements ArticlesService {

    private Logger logger = LoggerFactory.getLogger(ArticlesServiceImpl.class);

    /**
     * 自定义文章文件类型
     */
    private static String articleFileExtension = ".art";

    @Resource
    ArticlesDao articlesDao;

    /**
     * 文章列表
     *
     * @return java.util.List<com.wzy.risunking.resource.entity.ArticleInfo>
     * @author Wangzy
     * @date 2020/6/24 17:33
     */
    @Override
    public List<ArticleInfo> articleList(ArticleSearchIn articleSearchIn) {
        String title = articleSearchIn.getTitle();
        if (!DataCheck.isEmptyString(title)) {
            articleSearchIn.setTitle("%" + title + "%");
        }
        String startDate = articleSearchIn.getStartDate();
        String endDate = articleSearchIn.getEndDate();
        if (!DataCheck.isEmptyString(startDate) && !DataCheck.isEmptyString(endDate)) {
            articleSearchIn.setStartDate(startDate + " 00:00:00");
            articleSearchIn.setEndDate(endDate + " 23:59:59");
        } else {
            articleSearchIn.setEndDate("");
            articleSearchIn.setStartDate("");
        }
        String page = articleSearchIn.getPage();
        String size = articleSearchIn.getSize();
        if (!DataCheck.isEmptyString(page) && !DataCheck.isEmptyString(size)) {
            int ipage = Integer.valueOf(page);
            int isize = Integer.valueOf(size);
            if (ipage <= 0 || isize <= 0) {
                throw new CommandException(Response.PARAM_ERROR, "page或size参数错误");
            }
            int from = (ipage - 1) * isize;
            int to = from + isize;
            articleSearchIn.setFrom(from);
            articleSearchIn.setTo(to);
        }
        return articlesDao.articleList(articleSearchIn);
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
        String url = articleInfo.getUrl();
        String content = articleInfo.getContent();
        if (DataCheck.isEmptyString(url)) {
            articleInfo.setUrl("");
            String saveFileName = UUID.randomUUID().toString();
            String saveFilePath = Config.getFileDirWithFileName(saveFileName);
            String filePath = saveFilePath + saveFileName + articleFileExtension;
            if (!DataCheck.isEmptyString(content)) {
                boolean success = CommonUtils.writeStringToFile(filePath, content);
                if (success) {
                    articleInfo.setPath(saveFileName + articleFileExtension);
                } else {
                    throw new CommandException(Response.INNER_ERROR, "保存文件失败");
                }
            } else {
                throw new CommandException(Response.PARAM_ERROR, "content参数错误");
            }
        } else {
            articleInfo.setContent("");
            articleInfo.setPath("");
        }
        // 插入文章
        int num = articlesDao.articleAdd(articleInfo);
        String articleId = articleInfo.getId();
        String tagCodes = articleInfo.getTagCodes();
        if (!DataCheck.containsEmptyString(tagCodes, articleId)) {
            List<String> tagCodeList = Arrays.asList(tagCodes.split(","));
            // 插入tag与文章关系
            articlesDao.articleToResourceAdd(articleId, tagCodeList);
        }
        return num;
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
        String tagCodes = articleInfo.getTagCodes();
        if (!DataCheck.isEmptyString(tagCodes)) {
            // 删除原tag与文章关系
            articlesDao.articleToResourceDelete(articleInfo);
            // 重新插入tag与文章关系
            List<String> tagCodeList = Arrays.asList(tagCodes.split(","));
            articlesDao.articleToResourceAdd(articleInfo.getId(), tagCodeList);
        }
        return articlesDao.articleUpdate(articleInfo);
    }

    /**
     * 更新文章已读数量
     *
     * @param articleInfo
     * @return int
     * @author Wangzy
     * @date 2020/9/18 23:19
     */
    @Override
    public int articleUpdateReadNum(ArticleInfo articleInfo) {
        return articlesDao.articleUpdateReadNum(articleInfo);
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
        // 删除tag与文章关系
        articlesDao.articleToResourceDelete(articleInfo);
        // 删除文章
        return articlesDao.articleDelete(articleInfo);
    }

    /**
     * 获取文章content
     *
     * @param articleInfo
     * @return java.lang.String
     * @author Wangzy
     * @date 2020/9/23 11:30
     */
    @Override
    public ArticleInfo articleContent(ArticleInfo articleInfo) {
        String fileName = articleInfo.getPath();
        if (!DataCheck.isEmptyString(fileName)) {
            String fileSavePath = Config.getFileDirWithFileName(fileName);
            File file = new File(fileSavePath + fileName);
            if (!file.exists()) {
                logger.error("文件路径：" + fileSavePath + fileName);
                throw new CommandException(Response.PARAM_ERROR, "文件不存在！");
            }
            String content = CommonUtils.readStringFromFile(fileSavePath);
            articleInfo.setContent(content);
        }
        return articleInfo;
    }
}
