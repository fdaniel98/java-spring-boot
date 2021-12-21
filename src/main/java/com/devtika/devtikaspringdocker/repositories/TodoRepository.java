package com.devtika.devtikaspringdocker.repositories;

import com.devtika.devtikaspringdocker.entities.Todo;
import org.springframework.data.repository.CrudRepository;

public interface TodoRepository extends CrudRepository<Todo, Long> {
}
