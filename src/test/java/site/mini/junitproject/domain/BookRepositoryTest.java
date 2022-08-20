package site.mini.junitproject.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


@DataJpaTest //DB 와 관련된 컴포넌트만 메모리에 로딩
public class BookRepositoryTest {

@Autowired
 private BookRepository repository;

    /*
        @BeforeEach
        [dataPrep -> getBookList],[dataPrep -> getOneBook]
        
    */   

  //  @BeforeAll // 테스트 시작 전 한번만 실행
    @BeforeEach //각 테스트 시작 전 한번씩 실행
    public void dataPrep(){
        System.out.println("============================");
        String title = "junit";
        String author = "hyeonjoon";
        
        Book book = Book.builder()
        .title(title)
        .author(author)
        .build();

        repository.save(book);
    }

    // 책 등록
    @Test
    public void registerTest(){
        //given (데이터 준비)
        String title = "junit";
        String author = "hyeonjoon";
        
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
        String title = "junit";
        String author = "hyeonjoon";
        
        List<Book> list = repository.findAll();

        assertEquals(title, list.get(0).getTitle());
        assertEquals(author, list.get(0).getAuthor());
    }

    @Test
    public void getOneBook(){

        String title = "junit";
        String author = "hyeonjoon";

        Book bookPs = repository.findById(1L).get();

        assertEquals(title, bookPs.getTitle());
        assertEquals(author, bookPs.getAuthor()); 

    }

    //책 삭제
    @Test
    public void delBook(){
        Long id = 1L;

        repository.deleteById(id);

        assertFalse(repository.findById(id).isPresent());

    }










}
