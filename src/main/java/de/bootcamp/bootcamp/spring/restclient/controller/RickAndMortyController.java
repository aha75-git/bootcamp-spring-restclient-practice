package de.bootcamp.bootcamp.spring.restclient.controller;

import de.bootcamp.bootcamp.spring.restclient.model.RickAndMortyCharacter;
import de.bootcamp.bootcamp.spring.restclient.service.RickAndMortyService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class RickAndMortyController {
    private final RickAndMortyService service;

    @GetMapping
    public List<RickAndMortyCharacter> getRickAndMortyCharacters() {
        return service.getRickAndMortyCharacters();
    }

    @GetMapping("/characters/{id}")
    public ResponseEntity<RickAndMortyCharacter> getRickAndMortyCharacterById(@PathVariable Integer id) {
        var character = service.getRickAndMortyCharacterById(id);
        if (character == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new  ResponseEntity<>(character, HttpStatus.OK);
    }

    @GetMapping("/characters/")
    public ResponseEntity<List<RickAndMortyCharacter>> getRickAndMortyCharactersByStatus(@RequestParam String status) {
        var characters = service.getRickAndMortyCharactersByStatus(status);
        if (characters == null || characters.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(characters, HttpStatus.OK);
    }

    @GetMapping("/species-statistic/")
    public ResponseEntity<Integer> getRickAndMortyCharactersSpeciesStatistic(@RequestParam String species) {
        var speciesStatistic = service.getRickAndMortyCharactersSpeciesStatistic(species);
        if (speciesStatistic == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(speciesStatistic.count(), HttpStatus.OK);
    }
}
