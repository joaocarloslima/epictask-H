package br.com.fiap.epictaskapi.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.fiap.epictaskapi.model.Task;
import br.com.fiap.epictaskapi.repository.TaskRepository;

@Configuration
public class DatabaseSeed implements CommandLineRunner {

    @Autowired
    TaskRepository repository;

    @Override
    public void run(String... args) throws Exception {
        repository.saveAll(List.of( 
            new Task("BD", "Modelar as tabelas", 150, 0),
            new Task("Prototipo", "Prototipar as telas do app", 250, 0)
        ));
    }
    
}
