package com.ergon.register;

import com.ergon.register.model.Client;
import com.ergon.register.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RegisterApplication {
    @Bean
    public CommandLineRunner commandLineRunner(@Autowired ClientRepository clientRepository){
        return args -> {
            Client client = Client.builder().cpf("12345678911").name("testandoName").build();
            clientRepository.save(client);
        };
    }
    public static void main(String[] args) {
        SpringApplication.run(RegisterApplication.class, args);
    }

}
