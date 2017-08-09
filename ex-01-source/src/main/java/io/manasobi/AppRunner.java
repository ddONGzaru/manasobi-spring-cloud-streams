package io.manasobi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@SpringBootApplication
@EnableBinding(Source.class)
@RestController
public class AppRunner {

    @Autowired
    Source source; // [2]

    @PostMapping("/msg")
    public String greet(String msg) { // [3]
        source.output().send(MessageBuilder.withPayload(msg).build()); // [4]
        return "ok";
    }

    @Component
    public static class Greeter {
        @InboundChannelAdapter(value = Source.OUTPUT, poller = @Poller(fixedRate = "1000", maxMessagesPerPoll = "1"))
        public String greet() {
            return "Hello-" + UUID.randomUUID().toString();
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(AppRunner.class, args);
    }
}
