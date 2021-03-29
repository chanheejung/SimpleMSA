package kr.pe.chani.simplemsa.service2.name;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Slf4j
@RefreshScope
@Service
public class NameService {

    @Autowired
    Environment environment;

    public String getName(String id) {

        log.info("Server2 Proccess getName() Port : {}", environment.getProperty("local.server.port"));

        switch (id) {
            case "1":
                return "Jesse";
            case "2":
                return "Jimmy";
            default:
                return "UnKnown";
        }
    }
}
