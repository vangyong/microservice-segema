package cn.segema.cloud.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.segema.cloud.demo.domain.DemoUser;
import cn.segema.cloud.demo.vo.DemoUserVO;

@RestController
@RequestMapping(value = "/redis2/test")
public class Redis2TestController {
  @Autowired
  private DiscoveryClient discoveryClient;
  
  @Resource
  private StringRedisTemplate stringRedisTemplate;  //使用的是StringRedisSerializer

  @Resource
  private RedisTemplate redisTemplate; //使用的是JdkSerializationRedisSerializer
  
  @GetMapping("/redis")
	public List<DemoUserVO> redis(DemoUser user, Model model) {
	  	DemoUserVO user1 = new DemoUserVO();
	  	user1.setUserId("1");
	  	user1.setUserName("a1");
	  	DemoUserVO user2 = new DemoUserVO();
	  	user2.setUserId("2");
	  	user2.setUserName("a2");
		List<DemoUserVO> userList = new ArrayList<DemoUserVO>();
		userList.add(user1);
		userList.add(user2);
		
		redisTemplate.opsForValue().set("key1a", "key1avalue");
		stringRedisTemplate.opsForValue().set("key1b", "key1bvalue");
		 
		System.out.println(redisTemplate.opsForValue().get("key1a"));
		System.out.println(redisTemplate.opsForValue().get("key1b"));
  
		System.out.println(stringRedisTemplate.opsForValue().get("key1a"));
		System.out.println(stringRedisTemplate.opsForValue().get("key1b"));

		//添加一个 key 
        ValueOperations<String, Object> value = redisTemplate.opsForValue();
        value.set("lp", "hello word");
        //获取 这个 key 的值
        System.out.println(value.get("lp"));
        //添加 一个 hash集合
        HashOperations<String, Object, Object>  hash = redisTemplate.opsForHash();
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("name", "lp");
        map.put("age", "26");
        hash.putAll("lpMap", map);
        //获取 map
        System.out.println(hash.entries("lpMap"));
        //添加 一个 list 列表
        ListOperations<String, Object> list = redisTemplate.opsForList();
        list.rightPush("lpList", "lp");
        list.rightPush("lpList", "26");
        //输出 list
        System.out.println(list.range("lpList", 0, 1));
        //添加 一个 set 集合
        SetOperations<String, Object> set = redisTemplate.opsForSet();
        set.add("lpSet", "lp");
        set.add("lpSet", "26");
        set.add("lpSet", "178cm");
        //输出 set 集合
        System.out.println(set.members("lpSet"));
        //添加有序的 set 集合
        ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
        zset.add("lpZset", "lp", 0);
        zset.add("lpZset", "26", 1);
        zset.add("lpZset", "178cm", 2);
        //输出有序 set 集合
        System.out.println(zset.rangeByScore("lpZset", 0, 2));
		
		return userList;
	}
 

  /**
   * 本地服务实例的信息
   * @return
   */
  @GetMapping("/instance-info")
  public ServiceInstance showInfo() {
    ServiceInstance localServiceInstance = this.discoveryClient.getLocalServiceInstance();
    return localServiceInstance;
  }
}
