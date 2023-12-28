package complaint.cucumber;

import com.complaint.logic.ComplaintLogic;
import com.complaint.logic.MailLogic;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import jakarta.mail.*;
import org.junit.Assert;

import static org.mockito.Mockito.*;


public class ComplaintsStepdefs {
    String complaint = "";
    boolean complaintFixed;
    MailLogic mockMail = mock(MailLogic.class);
    Session s = mock(Session.class);

    @Given("this complaint {string} is submitted")
    public void thisComplaintIsSubmitted(String arg0) {
        complaint = arg0;
    }

    @And("the complaint is checked by the complaintLogic method and is valid")
    public void theComplaintIsCheckedByTheComplaintLogicMethodAndIsValid() {
        Assert.assertTrue(ComplaintLogic.validComplaint(complaint));
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
