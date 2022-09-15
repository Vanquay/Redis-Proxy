package Controller;

import java.util.AbstractMap.SimpleEntry;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import Models.SoccerPlayer;

@RestController
@RequestMapping("/api/soccer")

public class SoccerController {
    @Autowired

    private RedisTemplate<String, SoccerPlayer> proxy;

    @GetMapping("/player/{key}")
    public Map.Entry<String, SoccerPlayer> getSoccerPlayer(@PathVariable("key") String key){
        SoccerPlayer value =  (SoccerPlayer) proxy.opsForValue().get(key);

        if(value == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Key not found");
        }
        
        return new SimpleEntry<String, SoccerPlayer>(key, value);
    }
    
}


