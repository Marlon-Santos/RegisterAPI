package com.ergon.register.rest;

import com.ergon.register.model.Client;
import com.ergon.register.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/home")
public class ClientController {
    @Autowired
    private ClientRepository clientRepository;

    @GetMapping
    public ResponseEntity<List<Client>> listClients() {
        return ResponseEntity.ok(clientRepository.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        return optionalClient.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Transactional
    @PostMapping
    public ResponseEntity<Client> saveClient(@RequestBody @Valid Client client, UriComponentsBuilder uriBuilder) {
        clientRepository.save(client);
        URI uri = uriBuilder.path("{id}").buildAndExpand(client.getId()).toUri();
        return ResponseEntity.created(uri).body(client);
    }

    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void deleteClient(@PathVariable Long id) {
        clientRepository.findById(id).map((client) -> {
            clientRepository.delete(client);
            return Void.TYPE;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente Não Encontrado"));
    }

    @Transactional
    @PutMapping("{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @Valid @RequestBody Client clientUpdate) {
        return clientRepository.findById(id).map((client) -> {
            client.setCpf(clientUpdate.getCpf());
            client.setName(clientUpdate.getName());
            clientRepository.save(client);
            return ResponseEntity.ok(client);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente Não Encontrado"));
    }
}
