package com.complaint.controller;

import com.complaint.entities.Klage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import com.complaint.kafka.ComplaintProducer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class KlageController {
    ComplaintProducer crateComplaint = new ComplaintProducer();

    @PostMapping("/klage")
    public Klage makeComplaint(@RequestBody String complaint) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        Klage klage = om.readValue(complaint, Klage.class);

        crateComplaint.produceComplaint(klage);
        return klage;
    }
}
