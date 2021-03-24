package kr.pe.chani.simplemsa.service1.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class RestTemplateClientCommunicator {

    @Autowired
    private DiscoveryClient discoveryClient;

    /** 디스커버리 클라이언트와 표준 스프링 RestTemplate 클래스를 사용 (리본 기반 X, 직접 클라이언트 선택 필요 */
    public String getName(String id) {

        RestTemplate restTemplate = new RestTemplate();
        List<ServiceInstance> instances = discoveryClient.getInstances("service2");

        if (instances.size()==0) return null;
        /** 인스턴스들 중 0번째 클라이언트에 요청 */
        String serviceUri = String.format("%s/name/%s",instances.get(0).getUri().toString(), id);
    
        ResponseEntity<String> restExchange =
                restTemplate.exchange(
                        serviceUri,
                        HttpMethod.GET,
                        null, String.class, id);

        return  id + " is " + restExchange.getBody();
    }
}
