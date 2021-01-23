package com.example.restalfabank;

import com.example.restalfabank.service.WriteDbService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestAlfaBankApplication implements ApplicationRunner {

    @Value("${xml.input.file}")
    private String file;

    @Value("${xml.input.classpath}")
    private String classpath;

    @Value("${xml.input.url}")
    private String url;

    private final WriteDbService writeServices;

    public RestAlfaBankApplication(WriteDbService writeServices) {
        this.writeServices = writeServices;
    }

    public static void main(String[] args) {
        SpringApplication.run(RestAlfaBankApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        writeServices.writeXmlToDb(file, classpath, url);
    }
}
