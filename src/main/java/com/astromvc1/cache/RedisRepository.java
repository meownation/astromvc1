package com.astromvc1.cache;


import com.astromvc1.daily.DailyHoroscope;
import com.astromvc1.paragraph.Paragraph;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

@Repository
public class RedisRepository {

    private final StringRedisTemplate template;
    private final ValueOperations<String, String> valueOps;

    //private RedisList<String> users;

    public RedisRepository(StringRedisTemplate template) {
        this.template = template;
        valueOps = template.opsForValue();

        //users = new DefaultRedisList<String>("user", template);
    }

    public void saveDailyResult(DailyHoroscope dailyHoroscope){
        String test1=dailyHoroscope.getDate()+":"+dailyHoroscope.getSign();

       // valueOps.set(test1,test1);
       // template.opsForValue().set(test1,test1);
        dailyHoroscope.getParagraphs()
                        .stream()
                        .map(Paragraph::getTopic)
                        .forEach(p->template.opsForList().leftPush(test1,p));

        dailyHoroscope.getParagraphs()
                .stream()
                .map(Paragraph::getText)
                .forEach(p->template.opsForList().leftPush(test1,p));
//        template.opsForList().leftPush(test1,

    }



}
