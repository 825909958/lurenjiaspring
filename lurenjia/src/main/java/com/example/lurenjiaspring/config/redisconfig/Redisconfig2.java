package com.example.lurenjiaspring.config.redisconfig;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;

@Configuration
public class Redisconfig2 {

    @Resource
    ApplicationContext context;
    ///**
    // * 配置redisTemplate 覆盖默认配置 ，设置key/value 序列化规则
    // * @param factory
    // * @param builder
    // * @return
    // */
    //@Bean
    //public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory factory, Jackson2ObjectMapperBuilder builder){
    //    RedisTemplate<String,Object> redisTemplate = new RedisTemplate<String,Object>();
    //    redisTemplate.setConnectionFactory(factory);
    //
    //    //使用Jackson2JsonRedisSerializer 替换默认序列化
    //    Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(String.class);
    //    ObjectMapper objectMapper = builder.createXmlMapper(false).build();
    //    objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
    //    objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
    //    jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
    //
    //    /**
    //     * Jackson2JsonRedisSerializer  这个替换默认的JdkSerializationRedisSerializer  序列化，
    //     * 有个坑，存入对象，或者map的时候，一定要转换成json字符串，（例:redisUtil.setString(username, JSON.toJSONString(user)）
    //     * 否则就无法反序列化，因为保存的redis值默认包含包名，类名这些
    //     */
    //
    //
    //    //设置Key value 的序列化规则     jdk默认序列化会乱码
    //    redisTemplate.setKeySerializer(new StringRedisSerializer());
    //    redisTemplate.setHashKeySerializer(jackson2JsonRedisSerializer);
    //
    //    redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
    //    redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
    //    redisTemplate.afterPropertiesSet();
    //
    //    return redisTemplate;
    //}

    @Bean
    @SuppressWarnings(value = { "unchecked", "rawtypes" })
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory connectionFactory)
    {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        FastJson2JsonRedisSerializer serializer = new FastJson2JsonRedisSerializer(Object.class);

        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        mapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
        serializer.setObjectMapper(mapper);

        template.setValueSerializer(serializer);
        // 使用StringRedisSerializer来序列化和反序列化redis的key值
        template.setKeySerializer(new StringRedisSerializer());
        template.afterPropertiesSet();
        return template;
    }
//在configuration 这样写为什么会出现循环引用？？？？？？？？
    //@PostConstruct
    //public void a() {
    //    Object redisTemplate = context.getBean("redisTemplate");
    //    System.out.println("redisTemplate222222"+ redisTemplate);
    //    System.out.println("redisTemplate222222hashcode"+redisTemplate.hashCode());
    //}
}
