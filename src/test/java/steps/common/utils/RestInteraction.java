package steps.common.utils;

import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class RestInteraction {

    Response response;
    RequestSpecification request;

    public RestInteraction(RequestSpecification request, Response response) {
        this.response = response;
        this.request = request;
    }

    public Map<String, String> getQueryParams() {
        FilterableRequestSpecification reqFilterSpecification = (FilterableRequestSpecification) this.getRequest();
        return reqFilterSpecification.getQueryParams();
    }

    public String getQueryParam(final String key) {
        FilterableRequestSpecification reqFilterSpecification = (FilterableRequestSpecification) this.getRequest();
        Map<String, String> queryParams = reqFilterSpecification.getQueryParams();
        if (queryParams != null) {
            return queryParams.get(key);
        }
        return null;
    }

    public Map<String, String> getPathParams() {
        FilterableRequestSpecification reqFilterSpecification = (FilterableRequestSpecification) this.getRequest();
        return reqFilterSpecification.getPathParams();
    }

    public String getPathParam(final String key) {
        FilterableRequestSpecification reqFilterSpecification = (FilterableRequestSpecification) this.getRequest();
        Map<String, String> pathParams = reqFilterSpecification.getPathParams();
        if (pathParams != null) {
            return pathParams.get(key);
        }
        return null;
    }

    public String getRequestUrl() {
        FilterableRequestSpecification reqFilterSpecification = (FilterableRequestSpecification) this.getRequest();
        return reqFilterSpecification.getURI();
    }

}
