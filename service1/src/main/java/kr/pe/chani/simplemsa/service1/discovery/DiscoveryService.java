package kr.pe.chani.simplemsa.service1.discovery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DiscoveryService {

    @Autowired
    private DiscoveryClient discoveryClient;

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

}
