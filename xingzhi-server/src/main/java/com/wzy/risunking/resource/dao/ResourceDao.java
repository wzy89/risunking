package com.wzy.risunking.resource.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

@Repository
public class ResourceDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map> getResourceList(String folderId, String resourceType, String searchKey, int pageSize, int page, String userId) {
        String sql = "SELECT" +
                " * " +
                " FROM" +
                " risunking.user2resource" +
                " LEFT JOIN risunking.resource ON user2resource.resource_id = resource.resource_id" +
                " WHERE" +
                " user2resource.user_id = '" + userId + "'";
        if (folderId != null && !"".equals(folderId)){
            sql = sql + " AND resource.folder_id = '" + folderId + "'";
        }
        if (resourceType != null && !"".equals(resourceType)){
            sql = sql + " AND resource.resource_type = '" + resourceType + "'";
        }
        if (searchKey != null && !"".equals(searchKey)){
            sql = sql + " AND resource.resource_name LIKE '%"+searchKey+"%'";
        }
        if (pageSize > 0 && page > 0){
            int from = pageSize*(page-1);
            sql = sql + " LIMIT " + from + "," + pageSize;
        }
        List resourceList = jdbcTemplate.queryForList(sql);
        return resourceList;
    }

    /**
     * 保存资源
     *
     * @param filePath
     * @param resourceName
     * @param folderId
     * @param description
     * @return
     */
    public int saveResource(String filePath, String resourceName, String folderId,
                            String description, String resourceType, String resourceHref) {
        if (folderId == null || folderId.equals("")){
            folderId = "0";
        }
        if (description == null){
            description = "";
        }
        if (filePath == null){
            filePath = "";
        }
        if (resourceHref == null){
            resourceHref = "";
        }
        String sql = "INSERT INTO risunking.resource ( " +
                " folder_id, " +
                " resource_type, " +
                " resource_name, " +
                " resource_path, " +
                " resource_href, " +
                " resource_desc " +
                ") " +
                "VALUES " +
                " ( " +
                "  '"+ folderId +"', " +
                "  '"+ resourceType +"', " +
                "  '" + resourceName + "', " +
                "  '" + filePath + "', " +
                "  '" + resourceHref + "', " +
                "  '" + description + "'" +
                " );";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        PreparedStatementCreator preparedStatementCreator = con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            return ps;
        };
        jdbcTemplate.update(preparedStatementCreator, keyHolder);
        return keyHolder.getKey().intValue();
    }

    /**
     * 保存用户和资源的关系
     *
     * @param userId
     * @param resourceId
     * @param ownType
     * @return
     */
    public int saveUser2Resource(String userId, String resourceId, String ownType){
        if (userId == null || userId.equals("") || resourceId == null || resourceId.equals("")){
            return 0;
        }
        String realOwnType = "0";
        if (ownType != null && !ownType.equals("")){
            realOwnType = ownType;
        }
        String sql = "INSERT INTO risunking.user2resource (" +
                "user_id," +
                "resource_id," +
                "own_type" +
                ")" +
                " VALUES " +
                "('" + userId + "', '" + resourceId + "', '" + realOwnType + "')";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        PreparedStatementCreator preparedStatementCreator = con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            return ps;
        };
        jdbcTemplate.update(preparedStatementCreator, keyHolder);
        return keyHolder.getKey().intValue();
    }

    /**
     * 更新资源
     *
     * @param filePath
     * @param resourceName
     * @param folderId
     * @param description
     * @param
     * @return
     */
    public int updateResource(String resourceId , String filePath, String resourceName, String folderId,
                              String description, String resourceType, String resourceHref) {
        if (resourceId == null || resourceId.equals("")){
            return 0;
        }
        String sql = "UPDATE  risunking.resource ";
        String whereSql = " WHERE resource_id  = '" + resourceId + "'";
        String setSql = " SET ";
        if (filePath != null && !filePath.equals("")){
            setSql = setSql + " resource_path  = '"+filePath+"',";
        }
        if (resourceName != null && !resourceName.equals("")){
            setSql = setSql + " resource_name  = '"+resourceName+"',";
        }
        if (folderId != null && !folderId.equals("")){
            setSql = setSql + " folder_id  = '"+folderId+"',";
        }
        if (description != null && !description.equals("")){
            setSql = setSql + " resource_desc  = '"+description+"',";
        }
        if (resourceType != null && !resourceType.equals("")){
            setSql = setSql + " resource_type  = '"+resourceType+"',";
        }
        if (resourceHref != null && !resourceHref.equals("")){
            setSql = setSql + " resource_href  = '"+resourceHref+"',";
        }
        if (setSql.length() == 5){
            return 0;
        }
        setSql = setSql.substring(0,setSql.length()-2);
        sql = sql + setSql + whereSql;
        int updateNum = jdbcTemplate.update(sql);
        return updateNum;
    }

    /**
     * 获取文件的所属类型
     *
     * @param resourceId
     * @param userId
     * @return
     */
    public String getResourceOwnType(String resourceId, String userId){
        if (resourceId == null || userId == null || resourceId.equals("") || userId.equals("")){
            return "";
        }
        String sql = "SELECT own_type FROM risunking.user2resource WHERE resource_id='"+resourceId+"' AND user_id='"+userId+"'";
        List<Map<String, Object>> ownTypeList = jdbcTemplate.queryForList(sql);
        if (ownTypeList != null && ownTypeList.get(0) != null && ownTypeList.get(0).get("own_type") != null){
            return ownTypeList.get(0).get("own_type").toString();
        }
        return "";
    }

    /**
     * 删除资源
     *
     * @param resourceId
     * @return
     */
    public int deleteResource(String resourceId) {
        if (resourceId != null && !resourceId.equals("")){
            String sql = "DELETE FROM risunking.resource WHERE resource_id = '" + resourceId + "'";
            int deleteNum = jdbcTemplate.update(sql);
            return deleteNum;
        }
        return 0;
    }


    /**
     * 删除用户资源关系
     *
     * @param resourceId
     * @param userId
     * @return
     */
    public int deleteUser2Resource(String resourceId, String userId) {
        String sql = "DELETE FROM risunking.user2resource where 1=1";
        if (resourceId != null && !resourceId.equals("")){
           sql = sql + " and resource_id = '" + resourceId + "'";
        }
        if (userId != null && !userId.equals("")){
            sql = sql + " and user_id = '" + userId + "'";
        }
        //两个条件都不存在，不执行删除操作
        if (!sql.contains("and")){
            return 0;
        }
        int deleteNum = jdbcTemplate.update(sql);
        return deleteNum;
    }
}
