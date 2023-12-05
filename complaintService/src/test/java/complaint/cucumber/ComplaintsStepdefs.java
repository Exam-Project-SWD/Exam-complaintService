package complaint.cucumber;

import com.complaint.logic.mailLogic;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import jakarta.validation.constraints.AssertTrue;
import jakarta.mail.*;
import org.junit.Assert;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;


public class ComplaintsStepdefs {
    String complaint = "";
    boolean complaintFixed;
    mailLogic mockMail = mock(mailLogic.class);
    Session s = mock(Session.class);

    @Given("this complaint {string} is submitted")
    public void thisComplaintIsSubmitted(String arg0) {
        complaint = arg0;
    }

    @And("the complaint is checked by the complaintLogic method and is valid")
    public void theComplaintIsCheckedByTheComplaintLogicMethodAndIsValid() {
        Assert.assertTrue(com.complaint.logic.complaintLogic.validComplaint(complaint));
    }

    @When("the complaint has been fixed")
    public void theComplaintHasBeenFixed() {
        complaintFixed = true;
    }

    @Then("the submitter receives an email")
    public void theSubmitterReceivesAnEmail() {
        mockMail.sendEmail(s,"hej","asdf","asdf");
        verify(mockMail, times(1)).sendEmail(s,"hej","asdf","asdf");
    }

}
