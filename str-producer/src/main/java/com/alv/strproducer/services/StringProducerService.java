package com.alv.strproducer.services;

import org.springframework.kafka.core.KafkaProducerException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Service
public class StringProducerService {
    
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message){

        try{
            SendResult<String,String> future = kafkaTemplate.send("str-topic",message).get();
            log.info("Send message with success {}", message);
            log.info("Partition {}, Offset {}", 
                    future.getRecordMetadata().partition(), 
                    future.getRecordMetadata().offset() );
        } catch (Exception e) {
            log.error("Error send message");
        }
    }
}
