package com.complaint;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import com.complaint.kafka.ComplaintConsumer;

@Component
public class KafkaComplaint implements JavaDelegate {
    ComplaintConsumer cc = new ComplaintConsumer();
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        cc.consumeComplaint();
    }
}
