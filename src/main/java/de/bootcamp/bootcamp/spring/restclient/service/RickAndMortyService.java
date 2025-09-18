package de.bootcamp.bootcamp.spring.restclient.service;

import de.bootcamp.bootcamp.spring.restclient.model.Info;
import de.bootcamp.bootcamp.spring.restclient.model.MultiCharacter;
import de.bootcamp.bootcamp.spring.restclient.model.RickAndMortyCharacter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class RickAndMortyService {

    private final RestClient restClient;

    public RickAndMortyService(RestClient.Builder restClientBuilder) {
        this.restClient = restClientBuilder
                .baseUrl("https://rickandmortyapi.com/api")
                .build();
    }

    public RickAndMortyCharacter getRickAndMortyCharacterById(Integer id) {
        return restClient.get()
                .uri("/character/" + id)
                .retrieve()
                .body(RickAndMortyCharacter.class);
    }

    public List<RickAndMortyCharacter> getRickAndMortyCharacters() {
        List<RickAndMortyCharacter> rickAndMortyCharacters = restClient.get()
                .uri("/character")
                .retrieve()
                .body(MultiCharacter.class)
                .results();

        return rickAndMortyCharacters;
    }

    public List<RickAndMortyCharacter> getRickAndMortyCharactersByStatus(String status) {
        return restClient.get()
                .uri("/character/?status=" + status)
                .retrieve()
                .onStatus(httpStatusCode -> httpStatusCode.getStatusCode().is4xxClientError())
                .onStatus(httpStatusCode -> httpStatusCode.getStatusCode().is5xxServerError())
                .body(MultiCharacter.class)
                .results();
    }

    public Info getRickAndMortyCharactersSpeciesStatistic(String species) {
        return restClient.get()
                .uri("/character/?species=" + species)
                .retrieve()
                .onStatus(httpStatusCode -> httpStatusCode.getStatusCode().is4xxClientError())
                .onStatus(httpStatusCode -> httpStatusCode.getStatusCode().is5xxServerError())
                .body(MultiCharacter.class)
                .info();
    }
}
