package com.mycompany.ethereumapi.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

@Slf4j
@Configuration
public class Web3jConfig {

    @Bean
    Web3j web3j() {
        return Web3j.build(new HttpService());
    }

}
