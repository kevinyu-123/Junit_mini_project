package site.mini.junitproject.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import lombok.extern.slf4j.Slf4j;

@DataJpaTest //DB 와 관련된 컴포넌트만 메모리에 로딩
public class BookRepositoryTest {

@Autowired
 private BookRepository repository;

    // 책 등록
    @Test
    public void registerTest(){
        //given (데이터 준비)
        String title = "junit5";
        String author = "kevin";
        
        Book book = Book.builder()
        .title(title)
        .author(author)
        .build();

        //when (테스트 실행)
        Book bookPs = repository.save(book);


        //then (검증)
        assertEquals(title, bookPs.getTitle());
        assertEquals(author, bookPs.getAuthor());
    }















}
