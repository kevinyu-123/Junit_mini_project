package site.mini.junitproject.repository;

import org.springframework.data.repository.CrudRepository;

import site.mini.junitproject.entity.BookEntity;

public interface RedisRepository extends CrudRepository<BookEntity,String> {
    
}
