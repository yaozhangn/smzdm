package com.smzdm.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


//Jedis底层操作，目的是在Jedis连接池中获取一个Jedis连接，同时实现与Redis数据的get，set，remove等操作。
// 这里运用的set数据结构

@Service
public class JedisAdapter implements InitializingBean{

    private  static final Logger logger = LoggerFactory.getLogger(JedisAdapter.class);

    private Jedis jedis = null;
    private JedisPool jedisPool = null;


    @Override
    public void afterPropertiesSet() throws Exception {
        //初始化
        jedisPool = new JedisPool("localhost",6379);
    }

    //获取一个Jedis
    private Jedis getJedis(){
        try {
            jedis = jedisPool.getResource();
        }catch (Exception e){
            logger.error("获取jedis失败！" +e.getMessage());
        }finally {
            if (jedis != null){
                jedis.close();
            }
        }
        return jedis;
    }

    /**
     * 获取Redis中集合中某个key值
     * @param key
     * @return
     */
    public String get(String key){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.get(key);
        }catch (Exception e){
            logger.error("Jedis get 发生异常 " + e.getMessage());
            return null;
        }finally {
            if(jedis != null){
                jedis.close();
            }
        }
    }

    /**
     * 给redis中set集合的某个key设值
     * @param key
     * @param value
     */
    public void set(String key,String value){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.set(key,value);
        }catch (Exception e){
            logger.error("Jedus set 异常" + e.getMessage());
        }finally {
            if (jedis != null){
                jedis.close();
            }
        }
    }

    /**
     * 往set集合中增加一个或多个元素：点赞
     * @param key
     * @param value
     * @return 返回增加的数据条数
     */
    public long sadd(String key,String value){

        Jedis jedis = null;
        Long sadd = null;

        try {
            jedis = jedisPool.getResource();
           sadd = jedis.sadd(key, value);
        }catch (Exception e){
            logger.error("Jedis sadd 异常：" +e.getMessage());
            return 0;
        }finally {
            if (jedis != null){
                jedis.close();
            }
            return sadd;
        }
    }

    /**
     * 从set集合中移除数据
     * @param key
     * @param value
     * @return 返回移除的数据条数
     */
    public long srem(String key,String value){

        Jedis jedis = null;
        Long srem = null;

        try {
            jedis = jedisPool.getResource();
            srem = jedis.srem(key, value);
        }catch (Exception e){
            logger.error("Jedis srem 异常："+ e.getMessage());
            return 0;
        }finally {
            if (jedis != null){
                jedis.close();
            }
        }
        return srem;
    }

    /**
     * 判断value是否存在set集合key中
     * @param key  set集合的名称
     * @param value
     * @return
     */
    public boolean sismember(String key,String value){

        Jedis jedis = null;

        try {
            jedis = jedisPool.getResource();
            return jedis.sismember(key, value);
        }catch (Exception e){
            logger.error("Jedis sismember 异常："+e.getMessage());
            return false;
        }finally {
            if (jedis != null){
                try {
                    jedis.close();
                }catch (Exception e){
                    logger.error("Jedis 关闭异常："+e.getMessage());
                }
            }
        }
    }

    /**
     * 获取set集合key的大小
     * @param key
     * @return
     */
    public long scard(String key){

        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.scard(key);
        }catch (Exception e){
            logger.error("Jedis scard 异常 ：" +e.getMessage());
            return 0;
        }finally {
            if (jedis != null){
                jedis.close();
            }
        }
    }
}
