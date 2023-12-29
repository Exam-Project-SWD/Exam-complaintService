package com.complaint.kafka;

import io.github.cdimascio.dotenv.Dotenv;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import com.complaint.entities.Complaint;
import java.time.Duration;
import java.util.*;


public class ComplaintConsumer {
    private static final Logger log = LoggerFactory.getLogger(ComplaintConsumer.class);
    Dotenv dotenv = Dotenv.load();

    public Complaint consumeComplaint(){
        String bootstrapServers = dotenv.get("kafkaBootstrapServer");
        String groupId = "complaint-app";
        String topic = dotenv.get("kafkaTopic");
        Complaint firstComplaint = new Complaint();

        Properties props = new Properties();
        props.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.CLIENT_ID_CONFIG, "ComplaintConsumer");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "com.complaint.logic.ComplaintDeserializer");
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 1);

        KafkaConsumer<String, Complaint> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList(topic));
        ConsumerRecords<String, Complaint> records = consumer.poll(Duration.ofSeconds(1));

        try {
            for (ConsumerRecord<String, Complaint> record : records) {

                firstComplaint.setKundeEmail(record.value().getKundeEmail());
                firstComplaint.setKlage(record.value().getKlage());
                break;
            }
        }catch (Exception e){
            //log.atError().log("No complaint was found in kafka"+e);
            System.out.println(e);
        }

        consumer.close();
        return firstComplaint;
    }
}
