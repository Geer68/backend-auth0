package com.example.backend_auth0.domain.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.net.URLEncoder;
import org.springframework.http.HttpHeaders;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@Service
public class Auth0ManagementService {

    @Value("${auth0.domain}")
    private String domain;

    @Value("${auth0.mgmt.clientId}")
    private String mgmtClientId;

    @Value("${auth0.mgmt.clientSecret}")
    private String mgmtClientSecret;

    private RestTemplate rest = new RestTemplate();


    private String fetchMgmtApiToken() {
        String url = "https://" + domain + "/oauth/token";

        Map<String, String> body = Map.of(
                "grant_type", "client_credentials",
                "client_id", mgmtClientId,
                "client_secret", mgmtClientSecret,
                "audience", "https://" + domain + "/api/v2/");

        @SuppressWarnings("unchecked")
        Map<String, Object> resp = rest.postForObject(url, body, Map.class);
        return (String) resp.get("access_token");
    }


    public void assignRolesToUser(String auth0UserId, List<String> roleIds) {
        String token = fetchMgmtApiToken();

        String url = "https://" + domain + "/api/v2/users/{userId}/roles";

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        Map<String, List<String>> body = Map.of("roles", roleIds);

        HttpEntity<Map<String, List<String>>> req = new HttpEntity<>(body, headers);

        rest.exchange(
                url,
                HttpMethod.POST,
                req,
                Void.class,
                auth0UserId
        );
    }
}
