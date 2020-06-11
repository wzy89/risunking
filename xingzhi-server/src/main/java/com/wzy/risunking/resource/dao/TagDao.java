package com.wzy.risunking.resource.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TagDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 删除用户资源标签关系
     *
     * @param resourceId
     * @param userId
     * @param tagId
     * @return
     */
    public int deleteUser2Resource2Tag(String resourceId, String userId, String tagId) {
        String sql = "DELETE FROM risunking.user2resource2tag WHERE 1=1";
        if (resourceId != null && !resourceId.equals("")){
            sql = sql + " and resource_id = '" + resourceId + "'";
        }
        if (userId != null && !userId.equals("")){
            sql = sql + " and user_id = '" + userId + "'";
        }
        if (tagId != null && !tagId.equals("")){
            sql = sql + " and tag_id = '" + tagId + "'";
        }
        //三个条件都不存在，不执行删除操作
        if (!sql.contains("and")){
            return 0;
        }
        int deleteNum = jdbcTemplate.update(sql);
        return deleteNum;
    }

    public int deleteTag(String userId, String tagId) {
        return 0;
    }
}
