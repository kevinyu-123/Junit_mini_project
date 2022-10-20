package site.mini.junitproject.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import site.mini.junitproject.entity.BookEntity;

@Repository
public interface BookRepository extends JpaRepository<Book,Long>{

    void save(BookEntity book);

    

}
