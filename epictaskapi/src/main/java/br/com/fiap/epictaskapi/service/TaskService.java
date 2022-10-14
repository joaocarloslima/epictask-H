package br.com.fiap.epictaskapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.fiap.epictaskapi.model.Task;
import br.com.fiap.epictaskapi.repository.TaskRepository;

@Service
public class TaskService {
    
    @Autowired
    TaskRepository repository;

    public Page<Task> listAll(Pageable paginacao){
        return repository.findAll(paginacao);
    }

    public List<Task> listAll(){
        return repository.findAll();
    }

    public List<Task> listDone() {
        return repository.findDone();
    }

    public void save(Task task) {
        repository.save(task);
    }

    public void remove(Long id) {
        repository.deleteById(id);
    }

    public Optional<Task> getById(Long id) {
        return repository.findById(id);
    }



}
