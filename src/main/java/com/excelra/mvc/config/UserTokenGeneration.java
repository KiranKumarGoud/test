package com.excelra.mvc.config;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * To Generate User token to access Microservices
 *
 * @author venkateswarlu.s
 */
@Component
public class UserTokenGeneration {

    @Value("${microservices.url}")
    private String microServiceUrl;

    @Value("${microservice.usertoken.generation}")
    private String microServiceTokenGeneration;

    public String getUserTokenFromService(String userName, String password) {

        String urlString = microServiceUrl + microServiceTokenGeneration;
        String token = StringUtils.EMPTY;

        // create request body
        JSONObject request = new JSONObject();
        request.put("username", userName);
        request.put("password", password);

        // set headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(request.toString(), headers);

        // send request and parse result
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> loginResponse = restTemplate
                .exchange(urlString, HttpMethod.POST, entity, String.class);
        if (loginResponse.getStatusCode() == HttpStatus.OK) {
            JSONObject userJson = new JSONObject(loginResponse.getBody());
            token = userJson.getJSONObject("result").get("token").toString();
        } else if (loginResponse.getStatusCode() == HttpStatus.UNAUTHORIZED) {
            token = StringUtils.EMPTY;
        }

        return token;
    }

    /**
     * Result object in Response object.
     */
    private class Result {
        private String token;
        private String username;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }

    /**
     * Response Object
     */
    private class ResponseObj {

        private int status;
        private String message;
        private Result result;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public Result getResult() {
            return result;
        }

        public void setResult(Result result) {
            this.result = result;
        }
    }
}
