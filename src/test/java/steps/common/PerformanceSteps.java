package steps.common;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import steps.common.context.PerformanceContext;
import steps.common.context.ScenarioContext;
import steps.common.utils.RestInteraction;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class PerformanceSteps {

    private PerformanceContext performanceContext;
    private ScenarioContext scenarioContext;

    public PerformanceSteps(ScenarioContext scenarioContext, PerformanceContext performanceContext) {
        this.scenarioContext = scenarioContext;
        this.performanceContext = performanceContext;
    }

    private void assertResponseTimeIsLessThan(RestInteraction interaction) {
        TimeUnit timeUnit = this.performanceContext.getResponseTimeoutUnits();

        Long obtainedTimeValue = interaction.getResponse().getTimeIn(timeUnit);

        assertThat(obtainedTimeValue).isLessThan(this.performanceContext.getResponseTimeoutValue());
    }


    @Given("^the request time must be lower than (\\d+) \"([^\"]*)\"$")
    public void theRequestTimeMustBeLowerThan(Long responseTimeoutValue, TimeUnit responseTimeoutUnits) {
        this.performanceContext.setResponseTimeoutUnits(responseTimeoutUnits);
        this.performanceContext.setResponseTimeoutValue(responseTimeoutValue);
    }

    @Then("^the results? (?:\"([^\"]*)\" |)meets? the requirements?$")
    public void losResultadosCumplenRequisitosDeRendimiento(String key) {
        for (RestInteraction interaction : this.scenarioContext.getInteractions(key)) {
            this.assertResponseTimeIsLessThan(interaction);
        }
    }


}
