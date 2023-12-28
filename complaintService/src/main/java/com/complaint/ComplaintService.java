package com.complaint;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import com.complaint.logic.ComplaintLogic;

@Component
public class ComplaintService implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        // Hent complaint-værdien fra processens variabler
        String complaint = (String) execution.getVariable("klage");

        // Check om complaint er null eller ej
        if (ComplaintLogic.validComplaint(complaint)) {
            // Lige nu er en "god" klage, som kan løses, over 10 karakterer lang og ikke indeholder "dårlige" ord.

            // Grunden til at en valid klage bliver sat til false er den måde vi har håndteret
            // det på inde i camunda, det har noget at gøre med at hvis en klage bliver false, så er der IKKE
            // brug for at en manager kigger på klagen. Så isSuccessful burde nok hedde noget a la "needManager"
            execution.setVariable("isSuccessful", false);
        } else {

            // Se kommentar ovenfor ^^
            execution.setVariable("isSuccessful", true);
        }
    }
}