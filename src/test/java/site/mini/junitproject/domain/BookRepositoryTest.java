package site.mini.junitproject.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import apple.laf.JRSUIState.TitleBarHeightState;
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
    } // 테스트 후  트렌젝션 종료

    @Test
    public void getBookList(){
        String title = "junit5";
        String author = "kevin";
        
        Book book = Book.builder()
        .title(title)
        .author(author)
        .build();

         repository.save(book);
        List<Book> list = repository.findAll();

        assertEquals(title, list.get(0).getTitle());
        assertEquals(author, list.get(0).getAuthor());
    }

    @Test
    public void getOneBook(){

        String title = "junit5";
        String author = "kevin";
        
        Book book = Book.builder()
        .title(title)
        .author(author)
        .build();

        repository.save(book);

        Book bookPs = repository.findById(1L).get();

        assertEquals(title, bookPs.getTitle());
        assertEquals(author, bookPs.getAuthor()); 


    }











}
