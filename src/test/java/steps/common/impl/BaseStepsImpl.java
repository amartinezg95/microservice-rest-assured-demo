package steps.common.impl;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import steps.common.utils.RequestSpecificationDTO;
import steps.common.utils.RestInteraction;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class BaseStepsImpl {

    @Getter
    protected final String BASE_URL_PROPERTY = "http://";
    @Getter
    protected final String URL_KEY_PROPERTY;
    @Setter
    protected String url;

    protected Logger logger = Logger.getLogger(String.valueOf(this.getClass()));


    public BaseStepsImpl(String gradleUrlKeyProperty, String defaultUrlValue) {

        this.URL_KEY_PROPERTY = gradleUrlKeyProperty;
        this.url = BASE_URL_PROPERTY.concat(defaultUrlValue);
        if (StringUtils.isNotBlank(defaultUrlValue)) {
            logger.warning(String.format("Property %s not found", this.URL_KEY_PROPERTY));
            this.url = defaultUrlValue;
        }
    }

    public RequestSpecification getRequestSpecificationFromDTO(RequestSpecificationDTO requestSpecificationDTO) {
        RequestSpecification requestSpecification = RestAssured.given();

        Map<String, Object> headers = requestSpecificationDTO.getHeaders();
        if (headers != null && !headers.isEmpty()) {
            requestSpecification.headers(headers);
        }

        Map<String, Object> pathParams = requestSpecificationDTO.getPathParams();
        if (pathParams != null && !pathParams.isEmpty()) {
            requestSpecification.pathParams(pathParams);
        }

        Map<String, Object> queryParams = requestSpecificationDTO.getQueryParams();
        if (queryParams != null && !queryParams.isEmpty()) {
            requestSpecification.queryParams(queryParams);
        }

        String body = requestSpecificationDTO.getBody();
        if (StringUtils.isNotBlank(body)) {
            requestSpecification.body(body);
        }

        Map<String, List<?>> multiValuedQueryParams = requestSpecificationDTO.getMultiValuedQueryParams();
        if (multiValuedQueryParams != null && !multiValuedQueryParams.isEmpty()) {
            for (Map.Entry<String, List<?>> multiValueParam : multiValuedQueryParams.entrySet()) {
                requestSpecification.queryParam(multiValueParam.getKey(), multiValueParam.getValue());
            }
        }

        return requestSpecification;
    }


    public RestInteraction launchGetRequestUsingDTO(RequestSpecificationDTO requestSpecificationDTO, final String url) {
        RequestSpecification reqSpec = this.getRequestSpecificationFromDTO(requestSpecificationDTO);

        logger.info(String.format("Endpoint: %s", url));
        Response response = reqSpec.when().get(url);

        // Needed to force to log the body response
        logger.info(String.format("<< Response body: \"%s\"", response.getBody().asString()));

        return new RestInteraction(reqSpec, response);
    }

    public RestInteraction launchGetRequestUsingDTO(RequestSpecificationDTO requestSpecificationDTO) {
        return this.launchGetRequestUsingDTO(requestSpecificationDTO, this.url);
    }

    public RestInteraction launchPostRequestUsingDTO(RequestSpecificationDTO requestSpecificationDTO, final String url) {
        RequestSpecification reqSpec = this.getRequestSpecificationFromDTO(requestSpecificationDTO);
        Response response = reqSpec.when().post(url);

        // Needed to force to log the body response
        logger.info(String.format("<< Response body: \"%s\"", response.getBody().asString()));

        return new RestInteraction(reqSpec, response);
    }

    public RestInteraction launchPostRequestUsingDTO(RequestSpecificationDTO requestSpecificationDTO) {
        return this.launchPostRequestUsingDTO(requestSpecificationDTO, this.url);
    }

    public RestInteraction launchPutRequestUsingDTO(RequestSpecificationDTO requestSpecificationDTO, final String url) {
        RequestSpecification reqSpec = this.getRequestSpecificationFromDTO(requestSpecificationDTO);

        logger.info(String.format("Endpoint: %s", url));
        Response response = reqSpec.when().put(url);

        // Needed to force to log the body response
        logger.info(String.format("<< Response body: \"%s\"", response.getBody().asString()));

        return new RestInteraction(reqSpec, response);
    }

    public RestInteraction launchPutRequestUsingDTO(RequestSpecificationDTO requestSpecificationDTO) {
        return this.launchPutRequestUsingDTO(requestSpecificationDTO, this.url);
    }

    public RestInteraction launchDeleteRequestUsingDTO(RequestSpecificationDTO requestSpecificationDTO, final String url) {
        RequestSpecification reqSpec = this.getRequestSpecificationFromDTO(requestSpecificationDTO);

        logger.info(String.format("Endpoint: %s", url));
        Response response = reqSpec.when().delete(url);

        // Needed to force to log the body response
        logger.info(String.format("<< Response body: \"%s\"", response.getBody().asString()));

        return new RestInteraction(reqSpec, response);
    }

    public RestInteraction launchDeleteRequestUsingDTO(RequestSpecificationDTO requestSpecificationDTO) {
        return this.launchDeleteRequestUsingDTO(requestSpecificationDTO, this.url);
    }

}
