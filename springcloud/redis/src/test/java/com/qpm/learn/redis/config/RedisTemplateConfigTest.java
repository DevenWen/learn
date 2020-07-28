package com.qpm.learn.redis.config;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/** 
* RedisTemplateConfig Tester. 
* 
* @author <Authors name> 
* @since <pre>7月 27, 2020</pre> 
* @version 1.0 
*/
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTemplateConfigTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception {
    redisTemplate.delete(Key);
} 

@Autowired
private RedisTemplate<String, Long> redisTemplate;

    private final String Key = "KEY_LONG";
    private final String HKey = "Hash_KEY_Long";

/** 
* 测试 redis 做统计器的潜力！
 *
* Method: longRedisTemplate(RedisConnectionFactory redisConnectionFactory) 
* 
*/ 
@Test
public void testLongRedisTemplate() throws Exception {
    redisTemplate.opsForHash().increment(Key, HKey, 1);
    long result = redisTemplate.<String, Long>opsForHash().get(Key, HKey);
    Assert.assertEquals(1, result);
}



} 
