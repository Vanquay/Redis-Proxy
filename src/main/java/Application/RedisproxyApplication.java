package Application;

import java.time.Duration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootApplication
@EnableCaching
public class RedisproxyApplication {
	//Cache manager for Redis with TTL config
	@Bean
	public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
	  	RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig() //
		  .prefixCacheNameWith(this.getClass().getPackageName() + ".") //
		  .entryTtl(Duration.ofSeconds(7)) //
		  .disableCachingNullValues();
		
		return RedisCacheManager.builder(connectionFactory) //
		  .cacheDefaults(config) //
		  .build();
	}

	//Thread safe bridge between Java Spring and Redis
	@Bean
	public RedisTemplate<?, ?> redisTemplate(RedisConnectionFactory connectionFactory) {
  		RedisTemplate<?, ?> template = new RedisTemplate<>();
  		template.setConnectionFactory(connectionFactory);

  		return template;
}

	public static void main(String[] args) {
		SpringApplication.run(RedisproxyApplication.class, args);
	}

}
