package kr.pe.chani.simplemsa.service1.rest;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("service2")
public interface FeignClientCommunicator {
    @RequestMapping(
            method= RequestMethod.GET,
            value="/name/{id}",
            consumes="application/json")
    /** 넷플릭스 Feign 클라이언트를 사용해서 리본을 통해 서비스 호출 */
    String getName(@PathVariable("id") String id);
}
