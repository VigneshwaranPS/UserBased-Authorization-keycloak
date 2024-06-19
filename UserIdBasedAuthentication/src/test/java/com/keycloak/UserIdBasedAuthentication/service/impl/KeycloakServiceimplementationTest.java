package com.keycloak.UserIdBasedAuthentication.service.impl;

import com.keycloak.UserIdBasedAuthentication.customException.AdminTokenException;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

class KeycloakServiceimplementationTest {

    @Test
    void getAdminToken() {
        String url = "http://127.0.0.1:8081/realms/DemoRealm/protocol/openid-connect/token";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("client_id", "Demo");
        requestBody.add("client_secret", "o3BC7QW6gJtC8LY9XQza5bkhnS6Y3BN8");
        requestBody.add("grant_type", "password");
        requestBody.add("username", "viki");
        requestBody.add("password", "123");

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<AccessTokenResponse> response;

        response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, AccessTokenResponse.class);

        System.out.println("AccessToken Response : "+response.getBody().getToken());
    }

    @Test
    void callImpersonationAPI() {

        String impersonationApiUrl = String.format("http://127.0.0.1:8081/admin/realms/DemoRealm/users/%s/impersonation", "4be42f68-75af-42f3-9829-37038f43f540");

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth("eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICI1b1NBUm16ejljMlFjc2txa2hQcWdILUlxR0xMaHg3VDhsSzNid3pKT1o0In0.eyJleHAiOjE3MTg0Mjg5MTIsImlhdCI6MTcxODQyODYxMiwianRpIjoiZjkyM2U2NmItNTBjYS00MGZmLTk3N2EtNmE2ZGE4ZjkyZGJjIiwiaXNzIjoiaHR0cDovLzEyNy4wLjAuMTo4MDgxL3JlYWxtcy9EZW1vUmVhbG0iLCJhdWQiOlsicmVhbG0tbWFuYWdlbWVudCIsImJyb2tlciIsImFjY291bnQiXSwic3ViIjoiMmQ1YWM5MWQtZWIxNC00ZTQwLTg2NGEtN2E5ZTNkNmM3ZDllIiwidHlwIjoiQmVhcmVyIiwiYXpwIjoiRGVtbyIsInNlc3Npb25fc3RhdGUiOiI4MjMyNmE0ZC02YjVkLTQ5MzItYTJmZS04NzA3ZGM5OTVlOTAiLCJhY3IiOiIxIiwiYWxsb3dlZC1vcmlnaW5zIjpbIioiXSwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbIm9mZmxpbmVfYWNjZXNzIiwiZGVmYXVsdC1yb2xlcy1kZW1vcmVhbG0iLCJ1bWFfYXV0aG9yaXphdGlvbiJdfSwicmVzb3VyY2VfYWNjZXNzIjp7InJlYWxtLW1hbmFnZW1lbnQiOnsicm9sZXMiOlsidmlldy1yZWFsbSIsInZpZXctaWRlbnRpdHktcHJvdmlkZXJzIiwibWFuYWdlLWlkZW50aXR5LXByb3ZpZGVycyIsImltcGVyc29uYXRpb24iLCJyZWFsbS1hZG1pbiIsImNyZWF0ZS1jbGllbnQiLCJtYW5hZ2UtdXNlcnMiLCJxdWVyeS1yZWFsbXMiLCJ2aWV3LWF1dGhvcml6YXRpb24iLCJxdWVyeS1jbGllbnRzIiwicXVlcnktdXNlcnMiLCJtYW5hZ2UtZXZlbnRzIiwibWFuYWdlLXJlYWxtIiwidmlldy1ldmVudHMiLCJ2aWV3LXVzZXJzIiwidmlldy1jbGllbnRzIiwibWFuYWdlLWF1dGhvcml6YXRpb24iLCJtYW5hZ2UtY2xpZW50cyIsInF1ZXJ5LWdyb3VwcyJdfSwiRGVtbyI6eyJyb2xlcyI6WyJ1bWFfcHJvdGVjdGlvbiJdfSwiYnJva2VyIjp7InJvbGVzIjpbInJlYWQtdG9rZW4iXX0sImFjY291bnQiOnsicm9sZXMiOlsibWFuYWdlLWFjY291bnQiLCJ2aWV3LWFwcGxpY2F0aW9ucyIsInZpZXctY29uc2VudCIsInZpZXctZ3JvdXBzIiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJkZWxldGUtYWNjb3VudCIsIm1hbmFnZS1jb25zZW50Iiwidmlldy1wcm9maWxlIl19fSwic2NvcGUiOiJwcm9maWxlIGVtYWlsIiwic2lkIjoiODIzMjZhNGQtNmI1ZC00OTMyLWEyZmUtODcwN2RjOTk1ZTkwIiwiYWRkcmVzcyI6e30sImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJyb2xlcyI6WyJvZmZsaW5lX2FjY2VzcyIsImRlZmF1bHQtcm9sZXMtZGVtb3JlYWxtIiwidW1hX2F1dGhvcml6YXRpb24iXSwibmFtZSI6InZpa2kgcHMiLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiJ2aWtpIiwiZ2l2ZW5fbmFtZSI6InZpa2kiLCJmYW1pbHlfbmFtZSI6InBzIiwiZW1haWwiOiJ2aWtpQGdtYWlsLmNvbSJ9.Aaah5SHNRcyQKYuDI94fFn4M0wkqMLKVmEMDUgRM4GfowKlQpcwDlIDTaxMlvQiM4twmdWcM90Ic8XSkPXisR0qMOaZoYbvDFrXpd5-69cTkMbK738V5TsenYbFTEfvOuF1TnpuBSVnUgca6W52LLlBGhmA5DYDBmVY5QEHOk1mCxyXCBTSYb-umDZ67zkYVy3jr4KZZqKEc-puFb6Ybes5aAge41oERz1sYgD1qxCwvcIwNyd87cvJ2wfa8_xLMZfAoecRZQfg28WuerrxtKeAbQ4UGzAAjso9WgdcBRW8B53UeF9HzX9Am1WfhTyhn-sv6EENX-iydTNBNVBysOA");

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> impersonationResponse = restTemplate.exchange(impersonationApiUrl, HttpMethod.POST, requestEntity, String.class);
        HttpHeaders responseHeaders = impersonationResponse.getHeaders();
        List<String> cookies = responseHeaders.get(HttpHeaders.SET_COOKIE);
        System.out.println("Cookies : "+cookies);
    }

    @Test
    void useKeycloakIdentity() {
    }
}