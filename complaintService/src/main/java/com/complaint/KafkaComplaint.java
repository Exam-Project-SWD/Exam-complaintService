package com.complaint;

import com.complaint.entities.Complaint;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import com.complaint.kafka.ComplaintConsumer;

@Component
public class KafkaComplaint implements JavaDelegate {
    ComplaintConsumer cc = new ComplaintConsumer();
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Complaint complaint = cc.consumeComplaint();
        try {
            delegateExecution.setVariable("klage", complaint.getKlage());
            delegateExecution.setVariable("email", complaint.getKundeEmail());
        }catch (NullPointerException np){
            System.out.println(np);
        }

    }
}
