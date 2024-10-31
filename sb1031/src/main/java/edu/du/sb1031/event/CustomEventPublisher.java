package edu.du.sb1031.event;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class CustomEventPublisher {

    final private ApplicationEventPublisher publisher;

    public void doStuffAndPublishAnEvent(final String message){
        System.out.println("Publishing custom event.");
        CustomEvent customEvent = new CustomEvent(this, message);
        publisher.publishEvent(customEvent);//이벤트를 보내는 일.
    }
}
