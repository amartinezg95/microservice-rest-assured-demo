package steps.common.context;

import io.cucumber.java.Scenario;
import lombok.Getter;
import lombok.Setter;
import steps.common.utils.RestInteraction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Getter
@Setter
public class ScenarioContext {

    private String token = null;

    private final String MAIN_KEY = "MAIN_KEY";

    private HashMap<String, List<RestInteraction>> restInteractions;

    private Scenario scenario;

    public ScenarioContext() {
        restInteractions = new HashMap<>();
    }

    private void addInteractionToMap(String key, RestInteraction restInteraction) {
        List<RestInteraction> list = new ArrayList<>();
        list.add(restInteraction);
        this.restInteractions.put(key, list);
    }

    private RestInteraction getInteractionFromMap(String key) {
        List<RestInteraction> listInteractions = this.restInteractions.get(key);
        if (listInteractions != null) {
            return listInteractions.get(0);
        }
        return null;
    }

    private List<RestInteraction> getInteractionsFromMap(String key) {
        List<RestInteraction> listInteractions = this.restInteractions.get(key);
        return listInteractions;
    }

    public void setInteraction(String key, RestInteraction restInteraction) {
        key = (key == null) ? this.MAIN_KEY : key;
        this.addInteractionToMap(key, restInteraction);
    }

    public RestInteraction getInteraction(String key) {
        key = (key == null) ? this.MAIN_KEY : key;
        return this.getInteractionFromMap(key);
    }


    public List<RestInteraction> getInteractions(String key) {
        key = (key == null) ? this.MAIN_KEY : key;
        return this.getInteractionsFromMap(key);
    }
}