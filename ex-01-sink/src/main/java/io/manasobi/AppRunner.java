package io.manasobi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@SpringBootApplication
@EnableBinding(Sink.class)
@RestController
public class AppRunner {

    @StreamListener(Sink.INPUT) // [10]
    public void log(Message<String> message) { // [11]
        log.info("received :: {}", message);
    }

    public static void main(String[] args) {
        SpringApplication.run(AppRunner.class, args);
    }
}
