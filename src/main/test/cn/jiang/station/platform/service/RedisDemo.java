package cn.jiang.station.platform.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Set;

/**
 * @Description redis工具
 * @Created jiang
 */
public class RedisDemo {
    @Autowired
    private RedisTemplate redisTemplate;

    public static void main(String[] args) {

    }

    //删除相同键名前缀
    @Test
    public void test1() {
        String keys = "key*";
        Set keysSet = redisTemplate.keys(keys);
        redisTemplate.delete(keysSet);

    }
}
