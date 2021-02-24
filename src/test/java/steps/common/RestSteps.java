package steps.common;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import steps.common.context.DeserializationContext;
import steps.common.context.ScenarioContext;
import steps.common.json.AbstractBaseJson;
import steps.common.utils.RestInteraction;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


public class RestSteps {
    private ScenarioContext scenarioContext;
    private DeserializationContext deserializationContext;

    public RestSteps(ScenarioContext scenarioContext, DeserializationContext deserializationContext) {
        this.scenarioContext = scenarioContext;
        this.deserializationContext = deserializationContext;
    }

    @Then("^the http results? (?:\"([^\"]*)\" |)is (\\d+)$")
    public void thHttpResultIs(String key, int httpCode) {
        for (RestInteraction interaction : this.scenarioContext.getInteractions(key)) {
            Integer statusCode = interaction.getResponse().getStatusCode();
            assertThat(statusCode).as(String.format("Request URL \"%s\"", interaction.getRequestUrl())).isEqualTo(httpCode);
        }
    }

    @Then("^the value returned (?:\"([^\"]*)\" |)in the json path \"([^\"]*)\" is \"([^\"]*)\"$")
    public void theValueReturnedInTheJsonPathIs(String key, String jsonPath, String value) {
        Response response = this.scenarioContext.getInteraction(key).getResponse();
        assertThat(response.getBody().jsonPath().getString(jsonPath)).isEqualTo(value);
    }

    @Then("^the value of the list (?:\"([^\"]*)\" |)in the json path \"([^\"]*)\" is \"([^\"]*)\"$")
    public void theValueOfTheListInTheJsonPathIs(
        String key, String path, String value) {
        Response response = this.scenarioContext.getInteraction(key).getResponse();
        List<Object> list = response.getBody().jsonPath().getList(path);
        List<String> listOneDimension = new ArrayList<>();
        this.convertToOneSingleDimensionList(list, listOneDimension);
        listOneDimension.forEach(x -> assertThat(x).isEqualTo(value));
    }

    @Then("^El resultado (?:\"([^\"]*)\" |)tiene informados los campos$")
    public void elResultadoTieneInformadosLosCampos(String key, DataTable tableFields) throws Throwable {
        List<String> fieldRows = tableFields.asList(String.class);
        RestInteraction restInteraction = this.scenarioContext.getInteraction(key);
        AbstractBaseJson baseJson = (AbstractBaseJson) restInteraction.getResponse().as(this.deserializationContext.getJsonDeserialize());
        for (String fRow : fieldRows) {
            String[] fRowList = fRow.split(",");
            for (String field : fRowList) {
                assertThat(baseJson.getFieldValue(field)).withFailMessage(String.format("Field: %s", field)).isNotBlank();
            }
        }
    }

    private void convertToOneSingleDimensionList(Object objectToAnalyze, List<String> list_one_dimension) {
        if (objectToAnalyze != null) {
            if (objectToAnalyze instanceof ArrayList) {
                for (Object item : ((ArrayList) objectToAnalyze).toArray()) {
                    this.convertToOneSingleDimensionList(item, list_one_dimension);
                }
            } else list_one_dimension.add(objectToAnalyze.toString());
        }
    }

}
