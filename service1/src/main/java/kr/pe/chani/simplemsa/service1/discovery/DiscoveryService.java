package kr.pe.chani.simplemsa.service1.discovery;

import kr.pe.chani.simplemsa.service1.rest.FeignClientCommunicator;
import kr.pe.chani.simplemsa.service1.rest.RestTemplateClientCommunicator;
import kr.pe.chani.simplemsa.service1.rest.RibbonClientCommunicator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class DiscoveryService {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    RestTemplateClientCommunicator restTemplateClientCommunicator;

    @Autowired
    RibbonClientCommunicator ribbonClientCommunicator;

    @Autowired
    FeignClientCommunicator feignClientCommunicator;

    public List getServices(){
        List<String> services = new ArrayList<String>();

        /** 람다스트림 표현 */
        discoveryClient.getServices().forEach(serviceName -> {
            discoveryClient.getInstances(serviceName).forEach(instance->{
                services.add( String.format("%s:%s",serviceName,instance.getUri()));
            });
        });

        /** 일반 표현 */
//        List<String> serviceNames = discoveryClient.getServices();
//        for(String serviceName : serviceNames) {
//            List<ServiceInstance> serviceInstances = discoveryClient.getInstances(serviceName);
//            for(ServiceInstance instance : serviceInstances) {
//                services.add(String.format("%s:%s", serviceName, instance.getUri()));
//            }
//        }

        return services;
    }

    public String resttemplate(String id) {
        log.info("Communicating by RestTemplateClientCommunicator.");
        return restTemplateClientCommunicator.getName(id);
    }

    public String ribbon(String id) {
        log.info("Communicating by DiscoveryClientCommunicator.");
        return ribbonClientCommunicator.getName(id);
    }

    public String feign(String id) {
        log.info("Communicating by FeignClientCommunicator.");
        return id + " is " + feignClientCommunicator.getName(id);
    }

}
