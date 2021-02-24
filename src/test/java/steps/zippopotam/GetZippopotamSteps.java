package steps.zippopotam;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import steps.common.context.DeserializationContext;
import steps.common.context.ScenarioContext;
import steps.zippopotam.impl.GetZippopotamImpl;
import steps.zippopotam.json.GetZippopotamJson;

public class GetZippopotamSteps {

	private GetZippopotamImpl getZippopotamImpl;
	private ScenarioContext scenarioContext;
	private DeserializationContext deserializationContext;

	public GetZippopotamSteps(GetZippopotamImpl getZippopotamImpl, ScenarioContext scenarioContext,
							  DeserializationContext deserializationContext) {
		this.getZippopotamImpl = getZippopotamImpl;
		this.scenarioContext = scenarioContext;
		this.deserializationContext = deserializationContext;
	}

	@Given("^the result in case the response is correct will be a list with places$")
	public void theResultInCaseTheResponseIsCorrectWillBeAListWithPlaces() {
		this.deserializationContext.setJsonDeserialize(GetZippopotamJson.class);
	}

	@When("^launch get request (?:\"([^\"]*)\" |)for the country \"([^\"]*)\" y and post code \"([^\"]*)\"$")
	public void launchGetRequestForTheCountryAndPostCode(String key, String country, String postCode) {
		this.scenarioContext.setInteraction(key,
			this.getZippopotamImpl.launchGetRequest(country, postCode));

	}
}
