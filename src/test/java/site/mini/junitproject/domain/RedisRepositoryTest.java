package site.mini.junitproject.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import site.mini.junitproject.entity.BookEntity;
import site.mini.junitproject.repository.RedisRepository;

@SpringBootTest
public class RedisRepositoryTest {
    
    @Autowired
    private RedisRepository repo;

    @Test
    public void test(){
        BookEntity book = BookEntity.builder()
            .id((long) 123123123)
            .author("kevin")
            .title("test")
            .build();


            repo.save(book);

        int a = (int) repo.count();

        System.out.println(a);
        

    }   

}
