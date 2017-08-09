package io.manasobi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.integration.annotation.Transformer;
import org.springframework.messaging.Message;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@SpringBootApplication
@EnableBinding(Processor.class)
public class AppRunner {

    @Transformer(inputChannel = Processor.INPUT, outputChannel = Processor.OUTPUT)
    public String decorate(String payload) throws IOException {

        /*Map<String, Object> parsed = objectMapper.readValue(payload, Map.class);

        String name = (String) parsed.get("name");
        String decoratedName = "★" + name + "★";

        Map<String, Object> transformed = new LinkedHashMap<>(parsed);
        transformed.put("name", decoratedName);
        return transformed;*/

        String decoratedMsg = "스프링 클라우드 스트림즈 ::" + payload;

        log.info(decoratedMsg);

        return decoratedMsg;
    }

    public static void main(String[] args) {
        SpringApplication.run(AppRunner.class, args);
    }
}
