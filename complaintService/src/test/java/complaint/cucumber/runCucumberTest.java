package complaint.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"classpath:features/complaint.feature"},
        glue = {"complaint.cucumber"}
)
public class runCucumberTest {
}
