package kr.pe.chani.simplemsa.service1.discovery;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class DiscoveryController {

    @Autowired
    DiscoveryService discoveryService;

    @GetMapping(value = "/services")
    public List<String> services() {
        return discoveryService.getServices();
    }

    @GetMapping(value = "/resttemplate/{id}")
    public String resttemplate(@PathVariable("id") String id) {
        return discoveryService.resttemplate(id);
    }

    @GetMapping(value = "/ribbon/{id}")
    public String ribbon(@PathVariable("id") String id) {
        return discoveryService.ribbon(id);
    }

    @GetMapping(value = "/feign/{id}")
    public String feign(@PathVariable("id") String id) {
        return discoveryService.feign(id);
    }
}
