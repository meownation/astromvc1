package com.astromvc1.cache;


import com.astromvc1.daily.DailyHoroscope;
import com.astromvc1.daily.DailyHoroscopeDao;
import com.astromvc1.model.AstroSign;
import com.astromvc1.paragraph.Paragraph;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository("redis")
public class RedisRepository implements DailyHoroscopeDao {

    private final StringRedisTemplate template;
    private final ValueOperations<String, String> valueOps;

    //private RedisList<String> users;

    public RedisRepository(StringRedisTemplate template) {
        this.template = template;
        valueOps = template.opsForValue();

        //users = new DefaultRedisList<String>("user", template);
    }

    public DailyHoroscope save(DailyHoroscope dailyHoroscope){
        String key=dailyHoroscope.getDate()+":"+dailyHoroscope.getSign();

       // valueOps.set(test1,test1);
       // template.opsForValue().set(test1,test1);
        dailyHoroscope.getParagraphs()
                        .stream()
                        .map(Paragraph::getTopic)
                        .forEach(p->template.opsForList().rightPush(key,p));

        dailyHoroscope.getParagraphs()
                .stream()
                .map(Paragraph::getText)
                .forEach(p->template.opsForList().rightPush(key,p));

        return dailyHoroscope;
    }

    @Override
    public Optional<DailyHoroscope> readDailyHoroscope(Date date, AstroSign sign) {
        String key=date.toString()+":"+sign.toString();

        List<String> topics=template.opsForList().range(key,0,2).stream().collect(Collectors.toList());
        if(topics.isEmpty()) return Optional.empty();
        List<String> texts=template.opsForList().range(key,3,5).stream().collect(Collectors.toList());

        List<Paragraph> paragraphs=new ArrayList<>();
        for(int i=0;i<texts.size();i++){
            paragraphs.add(new Paragraph(0L,topics.get(i),texts.get(i)));
        }

        return Optional.of(new DailyHoroscope(date,sign,paragraphs));





    }

    @Override
    public void delete(LocalDate date, AstroSign astroSign) {
        Date sqldate = Date.valueOf(LocalDate.now());
        String key = date.toString() + ":" + astroSign.toString();

        template.delete(key);
    }
}
