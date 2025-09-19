package de.bootcamp.bootcamp.spring.restclient.service;

import de.bootcamp.bootcamp.spring.restclient.model.*;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class ReqresInService {

    private final RestClient restClient;

    public ReqresInService(RestClient.Builder restClientBuilder) {
        this.restClient = restClientBuilder
                .baseUrl("https://reqres.in/api")
                .build();
    }

    public List<ReqresInUser> getUsers() {
        return restClient.get()
                .uri("/users")
                .header("x-api-key", "reqres-free-v1")
                .retrieve()
                .body(MultiUser.class)
                .data();
    }

    public NewResponseReqresInUser addNewUser(RequestReqresInUser user) {
        return restClient.post()
                .uri("/users")
                .header("x-api-key", "reqres-free-v1")
                .contentType(MediaType.APPLICATION_JSON)
                .body(user)
                .retrieve()
                .body(NewResponseReqresInUser.class);
    }

    public UpdatedResponseReqresInUser updateUser(String id, RequestReqresInUser user) {
        return restClient.put()
                .uri("/users/{id}", id)
                .header("x-api-key", "reqres-free-v1")
                .contentType(MediaType.APPLICATION_JSON)
                .body(user)
                .retrieve()
                .body(UpdatedResponseReqresInUser.class);
    }

    public void deleteUser(String id) {
         restClient.delete()
                .uri("/users/{id}", id)
                .header("x-api-key", "reqres-free-v1")
                .retrieve()
                .body(Void.class);
    }
}
