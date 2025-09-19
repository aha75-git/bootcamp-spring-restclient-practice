package de.bootcamp.bootcamp.spring.restclient.controller;

import de.bootcamp.bootcamp.spring.restclient.model.RequestReqresInUser;
import de.bootcamp.bootcamp.spring.restclient.model.NewResponseReqresInUser;
import de.bootcamp.bootcamp.spring.restclient.model.ReqresInUser;
import de.bootcamp.bootcamp.spring.restclient.model.UpdatedResponseReqresInUser;
import de.bootcamp.bootcamp.spring.restclient.service.ReqresInService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class ReqresInController {

    private final ReqresInService service;

    @GetMapping
    public List<ReqresInUser> getUsers() {
        return service.getUsers();
    }

    @PostMapping
    public NewResponseReqresInUser addNewUser(@RequestBody RequestReqresInUser user) {
        return service.addNewUser(user);
    }

    @PutMapping("/{id}")
    public UpdatedResponseReqresInUser updateUser(@PathVariable String id, @RequestBody RequestReqresInUser user) {
        return service.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id) {
        service.deleteUser(id);
    }
}
