package com.wzy.risunking.global.cache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.CacheConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @description: 缓存管理类
 * @author: Wangzy
 * @create: 2018-10-26 10:54
 **/
public final class ECacheManager {

    public enum CacheTimeType{
        Default(0),
        LongTime(1),
        Forever(4);

        private int value = 0;

        CacheTimeType(int value) {
            this.value = value;
        }

        public String value(){
            return String.valueOf(this.value);
        }
    }

    Logger logger = LoggerFactory.getLogger(ECacheManager.class);

    public static CacheManager cacheManager;
    private static ECacheManager eCacheManager;

    static {
        eCacheManager = new ECacheManager();
        cacheManager = CacheManager.create(ECacheManager.class.getResourceAsStream("/config/ehcache.xml"));
    }

    private ECacheManager() {

    }

    /**
     * 获取ECacheManager单例
     * @param
     * @return com.wzy.risunking.global.cache.ECacheManager
     * @Create Wangzy 2018/10/26 11:29
     * @Update Wangzy 2018/10/26 11:29
     */
    public static ECacheManager getInstance() {
        return eCacheManager;
    }

    /**
     * 添加一组缓存
     * @param cacheName
     * @param cacheTimeType
     * @return void
     * @Create Wangzy 2018/10/26 16:05
     * @Update Wangzy 2018/10/26 16:05
     */
    public void addCache(String cacheName,CacheTimeType cacheTimeType){
        if (cacheName == null || cacheName.equals("")){
            logger.error("ECacheManager:addCache失败,cacheName为空！");
            return;
        }
        if (cacheManager.getCache(cacheName) == null){
            if (cacheTimeType == null){
                cacheTimeType = CacheTimeType.Default;
            }
            Cache newCache = new Cache(getCacheConfiguration(cacheName,cacheTimeType));
            cacheManager.addCache(newCache);
        }
    }

    /**
     * 更新缓存中的数据
     * @param cacheName 存储的命名空间
     * @param cacheKey 存储的键名称
     * @param value 存储的值
     * @return void
     * @Create Wangzy 2018/10/26 15:06
     * @Update Wangzy 2018/10/26 15:06
     */
    public void updateCacheValue(String cacheName,String cacheKey, Object value, CacheTimeType cacheTimeType){
        if (cacheName== null || "".equals(cacheName)){
            logger.error("ECacheManager:updateCacheValue,cacheName为空");
            return;
        }
        if (cacheKey==null || "".equals(cacheKey)){
            logger.error("ECacheManager:updateCacheValue,cacheKey为空");
            return;
        }
        //添加缓存组
        addCache(cacheName,cacheTimeType);
        Cache cache = cacheManager.getCache(cacheName);
        if (cache.get(cacheKey)!=null){
            cache.remove(cacheKey);
        }
        Element element = new Element(cacheKey,value);
        cache.put(element);
    }

    /**
     * 清除缓存组中的某个缓存数据
     * @param cacheName
     * @param cacheKey
     * @return void
     * @Create Wangzy 2018/10/26 16:32
     * @Update Wangzy 2018/10/26 16:32
     */
    public void clearCacheValue(String cacheName,String cacheKey) {
        if (cacheName== null || "".equals(cacheName)){
            logger.error("ECacheManager:clearCacheValue,cacheName为空");
            return;
        }
        if (cacheKey==null || "".equals(cacheKey)){
            logger.error("ECacheManager:clearCacheValue,cacheKey为空");
            return;
        }
        Cache cache = cacheManager.getCache(cacheName);
        if (cache != null){
            cache.remove(cacheKey);
        }
    }

    /**
     * 清除缓存组
     * @param cacheName
     * @return void
     * @Create Wangzy 2018/10/26 16:34
     * @Update Wangzy 2018/10/26 16:34
     */
    public void clearCache(String cacheName){
        if (cacheName== null || "".equals(cacheName)){
            logger.error("ECacheManager:clearCache,cacheName 为空");
            return;
        }
        cacheManager.removeCache(cacheName);
    }

    /**
     * 清空所有缓存
     * @param
     * @return void
     * @Create Wangzy 2018/10/26 16:53
     * @Update Wangzy 2018/10/26 16:53
     */
    public void clearAllCache(){
        cacheManager.removeAllCaches();
    }

    public Object getCacheValue(String cacheName,String cacheKey){
        if (cacheName== null || "".equals(cacheName)){
            logger.error("ECacheManager:getCacheValue,cacheName为空");
            return null;
        }
        if (cacheKey==null || "".equals(cacheKey)){
            logger.error("ECacheManager:getCacheValue,cacheKey为空");
            return null;
        }
        Cache cache = cacheManager.getCache(cacheName);
        if (cache == null){
            return null;
        }
        Element element = cache.get(cacheKey);
        if (element == null){
            return null;
        }
        return element.getObjectValue();
    }

    /**
     * 获取默认缓存设置(存储30分钟失效 && 连续10分钟不被访问则失效)
     * @param name 缓存唯一标识
     * @return net.sf.ehcache.config.CacheConfiguration
     * @Create Wangzy 2018/10/26 13:58
     * @Update Wangzy 2018/10/26 13:58
     */
    private CacheConfiguration getCacheConfiguration(String name,CacheTimeType type){
        switch (type){
            case Default:{
                return makeCacheConfiguration(name,false,600,1800,500,300);
            }
            case Forever:{
                return makeCacheConfiguration(name,true,0,0,100,100);
            }
            case LongTime:{
                return makeCacheConfiguration(name,false,86400,86400,300,100);
            }
            default:{
                logger.error("ECacheManager:getCacheConfiguration错误的设置类型");
                return null;
            }
        }
    }

    /**
     * 自定义 缓存设置
     * @param name 唯一标志
     * @param eternal 是否永久有效
     * @param timeToIdleSeconds 最小的访问间隔
     * @param timeToLiveSeconds 最大的存储时间
     * @param maxEntriesLocalHeap 内存中允许存放的最大个数
     * @param maxEntriesLocalDisk 硬盘中允许存放的最大个数
     * @return net.sf.ehcache.config.CacheConfiguration
     * @Create Wangzy 2018/10/26 13:55
     * @Update Wangzy 2018/10/26 13:55
     */
    public CacheConfiguration makeCacheConfiguration(String name, boolean eternal, int timeToIdleSeconds, int timeToLiveSeconds, int maxEntriesLocalHeap, int maxEntriesLocalDisk){
        CacheConfiguration configuration = new CacheConfiguration();
        if (name == null || name.equals("")){
            logger.error("ECacheManager:makeCacheConfiguration参数错误！CacheConfiguration将不生效！");
            return configuration;
        }
        configuration.setName(name);
        configuration.setEternal(eternal);
        configuration.setTimeToIdleSeconds(timeToIdleSeconds);
        configuration.setTimeToLiveSeconds(timeToLiveSeconds);
        configuration.setOverflowToDisk(true);
        configuration.setMaxEntriesLocalHeap(maxEntriesLocalHeap);
        configuration.setMaxEntriesLocalDisk(maxEntriesLocalDisk);
        return configuration;
    }

}
