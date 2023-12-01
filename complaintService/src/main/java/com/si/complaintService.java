package com.si;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class complaintService implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        // Hent complaint-værdien fra processens variabler
        String complaint = (String) execution.getVariable("klage");

        // Check om complaint er null eller ej
        if (complaint.length() > 10) {
            // Lige nu er en "god" klage, som kan løses, over 10 karakterer lang. Burde nok være mere indviklet
            // logik til at bestemme hvordan klager skal håndteres, men det har vi ikke fået lavet.

            System.out.println("Succesfuld behandling (false)");
            // Her kan du tilføje yderligere logik eller handlinger, der skal udføres
            execution.setVariable("isSuccessful", false);
        } else {

            System.out.println("Mislykket behandling (true)");
            // Her kan du tilføje yderligere logik eller handlinger, der skal udføres i tilfælde af mislykket behandling
            execution.setVariable("isSuccessful", true);
        }
    }
}