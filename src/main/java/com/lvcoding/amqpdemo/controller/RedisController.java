package com.lvcoding.amqpdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * @description 描述
 * @date   2022-02-11 下午3:18
 * @author  wuyanshen
 */
@RestController
@RequestMapping("redis")
public class RedisController {

    @Autowired
    private RedisTemplate redisTemplate;

    // 点赞
    @PutMapping("user/liked/{id}")
    public String liked(@PathVariable("id")Integer id) {
        // 获取当前用户信息
        String userId = "2";
        // 记录当前视频点赞总数
        redisTemplate.opsForValue().increment("VIDEO_LIKED_COUNT_"+id);
        // 记录当前用户是否对该视频进行点赞
        redisTemplate.opsForSet().add("USER_LIKED_" + userId, id);
        // 判断用户之前是否不喜欢该视频
        if(redisTemplate.opsForSet().isMember("USER_DISLIKED_" + userId, id)) {
            redisTemplate.opsForSet().remove("USER_DISLIKED_" + userId, id);
        }
        return "点赞";
    }

    // 取消点赞
    @DeleteMapping("user/liked/{id}")
    public String unLiked(@PathVariable("id")Integer id) {
        // 获取当前用户信息
        String userId = "2";
        // 当前视频点赞次数-1
        redisTemplate.opsForValue().decrement("VIDEO_LIKED_COUNT_"+id);
        // 从用户点赞列表中移除该视频
        redisTemplate.opsForSet().remove("USER_LIKED_" + userId, id);

        return "取消点赞";
    }

    // 点击不喜欢
    @PutMapping("user/disliked/{id}")
    public String disliked(@PathVariable("id")Integer id) {
        String userId = "2";
        // 将视频放到不喜欢列表
        redisTemplate.opsForSet().add("USER_DISLIKED_"+userId, id);
        // 判断用户之前是否喜欢该视频
        if(redisTemplate.opsForSet().isMember("USER_LIKED_"+userId, id)) {
            redisTemplate.opsForSet().remove("USER_LIKED_"+userId, id);
            redisTemplate.opsForValue().decrement("VIDEO_LIKED_COUNT_"+id);
        }
        return "点击不喜欢";
    }

    // 取消点击不喜欢
    @DeleteMapping("user/disliked/{id}")
    public String unDisliked(@PathVariable("id")Integer id) {
        // 获取用户信息
        String userId = "2";
        // 将视频从不喜欢列表移除
        if(redisTemplate.opsForSet().isMember("USER_DISLIKED_"+userId, id)) {
            redisTemplate.opsForSet().remove("USER_DISLIKED_"+userId, id);
        }
        return "取消点击不喜欢";
    }
}
