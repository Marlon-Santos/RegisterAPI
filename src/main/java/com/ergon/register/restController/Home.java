package com.ergon.register.restController;

import com.ergon.register.model.Client;
import com.ergon.register.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/home")
public class Home {
    @Autowired
    private ClientRepository clientRepository;

    @GetMapping
    public List<Client> listClients() {
        return clientRepository.findAll();
    }
}
