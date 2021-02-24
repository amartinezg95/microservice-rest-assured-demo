package steps.common.utils;

import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class RequestSpecificationDTO {
    private Map<String, Object> headers;
    private Map<String, Object> pathParams;
    private Map<String, Object> queryParams;
    private Map<String, Object> formParam;
    private Map<String, List<?>> multiValuedQueryParams;
    private String body;
    private Map<String,Object> multiPart;

    public final static String PAGE_NUMBER_QUERY_PARAM = "page";
    public final static String PAGE_RESULTS_QUERY_PARAM = "limit";
    public final static String SORT_FIELD_QUERY_PARAM = "sort";
    public final static String SORT_DIR_QUERY_PARAM = "dir";

    public RequestSpecificationDTO() {
        headers = new HashMap<>();
        pathParams = new HashMap<>();
        queryParams = new HashMap<>();
        multiValuedQueryParams = new HashMap<>();
        multiPart = new HashMap<>();
        formParam = new HashMap<>();
        body = new String();
    }


    static Map<String, Integer> getPaginationQueryParams(Integer pageNumber, Integer pageSize) {
        HashMap<String, Integer> queryPaginationParams = new HashMap<>();
        if (pageNumber != null) {
            queryPaginationParams.put(PAGE_NUMBER_QUERY_PARAM, pageNumber);
        }

        if (pageSize != null) {
            queryPaginationParams.put(PAGE_RESULTS_QUERY_PARAM, pageSize);
        }

        return queryPaginationParams;

    }

    public void addBody(String value) {
        this.body = value;
    }

    public void addHeader(String key, Object value) {
        this.headers.put(key, value);
    }

    public void addQueryParam(String key, Object value) {
        this.queryParams.put(key, value);
    }

    public void addPathParam(String key, Object value) {
        this.pathParams.put(key, value);
    }

    public void addFormParam(String key, Object value) { this.formParam.put(key, value); }

    public void addMultiValuedQueryParam(String key, List<?> values) {
        this.multiValuedQueryParams.put(key, values);
    }

    public void addMultiPart(String key, Object value) { this.multiPart.put(key, value); }

    public void fillPagination(Integer pageNumber, Integer pageSize) {
        Map<String, Integer> paginationQueryParams = RequestSpecificationDTO.getPaginationQueryParams(pageNumber, pageSize);
        this.queryParams.putAll(paginationQueryParams);
    }

    public void fillAuthorization(String token) {
        if (token != null)
            this.headers.put(HTTPUtils.AUTHORIZATION,"Bearer "+token);
    }

    public void fillHeaderAccept(String acceptType) {
        if (acceptType != null)
            this.headers.put(HTTPUtils.HEADER_ACCEPT, acceptType);
    }

    public void fillHeaderContentType(String contentType) {
        if (contentType != null)
            this.headers.put(HTTPUtils.HEADER_CONTENT_TYPE, contentType);
    }

    public void setPageNumber(Integer pageNumber) {
        if (pageNumber != null)
            this.queryParams.put(RequestSpecificationDTO.PAGE_NUMBER_QUERY_PARAM, pageNumber);
    }
}
