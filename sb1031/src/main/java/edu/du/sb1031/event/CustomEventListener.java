package edu.du.sb1031.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Configuration
@Slf4j
public class CustomEventListener {
    @EventListener
    public void handleContextStart(CustomEvent event) {
        log.info("Custom event listener called : {}",event.getMessage());
    }

}
