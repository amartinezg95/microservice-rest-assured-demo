package steps.common.context;

import io.restassured.response.Response;
import lombok.Getter;
import lombok.Setter;
import steps.common.json.AbstractBaseJson;
import steps.common.json.ICollectionJson;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class DeserializationContext {
    private Class<?> jsonDeserialize = null;

    public List<AbstractBaseJson> deserializeCollection(Response response) {
        Object deserialized = response.as(this.jsonDeserialize);
        List<AbstractBaseJson> list;
        if (deserialized.getClass().isArray()) {
            list = Arrays.asList((AbstractBaseJson[]) deserialized);
        } else {
            list = ((ICollectionJson) deserialized).getList();
        }
        return list;
    }
}
