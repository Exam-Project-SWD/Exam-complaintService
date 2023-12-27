package com.complaint.kafka;

import com.complaint.entities.Klage;
import io.github.cdimascio.dotenv.Dotenv;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;


public class ComplaintProducer {
    private static final Logger log = LoggerFactory.getLogger(ComplaintProducer.class);
    Dotenv dotenv = Dotenv.load();

    public void produceComplaint(Klage klage){
        String bootstrapServer = dotenv.get("kafkaBootstrapServer");

        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"com.complaint.logic.KlageSerializer");

        KafkaProducer<String, Klage> producer = new KafkaProducer<>(props);

        ProducerRecord<String, Klage> pr = new ProducerRecord<>("complaint-events", klage);
        producer.send(pr);
        producer.flush();
        producer.close();
    }

}
