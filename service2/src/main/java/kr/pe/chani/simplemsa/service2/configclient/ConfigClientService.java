package kr.pe.chani.simplemsa.service2.configclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

@RefreshScope
@Service
public class ConfigClientService {
    @Value("${example.property}")
    private String exampleProperty;

    @Value("${example.password}")
    private String examplePassword;

    public String exampleProperty() {
        return exampleProperty;
    }

    public String examplePassword() {
        return examplePassword;
    }
}
