package steps.common.utils;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class HTTPUtils {
    public enum EnumRequestType {
        GET, PUT, POST, DELETE
    }

    public static String AUTHORIZATION = "Authorization";
    public static String JSON_CONTENT_TYPE = "application/json";
    public final static String HEADER_ACCEPT = "Accept";
    public final static String HEADER_ACCEPT_LANGUAGE = "Accept-Language";
    public final static String HEADER_CONTENT_TYPE = "Content-Type";
    public final static String HEADER_MULTIPART = "multipart/form-data";

    public static Response sendTypeRequest(RequestSpecification requestSpecification, EnumRequestType requestType,
                                           String endpoint) {
        switch (requestType) {
            case GET:
                return requestSpecification.get(endpoint);
            case PUT:
                return requestSpecification.put(endpoint);
            case POST:
                return requestSpecification.post(endpoint);
            case DELETE:
                return requestSpecification.delete(endpoint);

        }
        return null;
    }

}
