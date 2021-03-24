package kr.pe.chani.simplemsa.service1.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RibbonClientCommunicator {
    @Autowired
    RestTemplate restTemplate;

    /** 향상된 스프링 RestTemplate을 사용해 리본 기반의 서비스를 호출 */
    public String getName(String id){

        ResponseEntity<String> restExchange =
                /** Url : http://{applicationId}/v1/ ~~ */
                restTemplate.exchange(
                        "http://service2/name/{id}",
                        HttpMethod.GET,
                        null, String.class, id);

        return id + " is " + restExchange.getBody();
    }
}
