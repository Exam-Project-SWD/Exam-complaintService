package com.complaint.logic;

import com.complaint.entities.Complaint;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;


public class ComplaintDeserializer implements Deserializer<Complaint> {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public Complaint deserialize(String topic, byte[] data) {
        try {
            if (data == null){
                System.out.println("Null received at deserializing");
                return null;
            }
            System.out.println("Deserializing...");
            return objectMapper.readValue(new String(data, "UTF-8"), Complaint.class);
        } catch (Exception e) {
            throw new SerializationException("Error when deserializing byte[] to complaint");
        }
    }

    @Override
    public void close() {
    }
}
