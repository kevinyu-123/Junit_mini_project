package site.mini.junitproject.web;


import static org.assertj.core.api.Assertions.*;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import site.mini.junitproject.domain.Book;
import site.mini.junitproject.domain.BookRepository;
import site.mini.junitproject.dto.request.BookSaveRequestDto;
import site.mini.junitproject.service.BookService;

//통합테스트 = 모든레이어를 한번에 테스트
@SpringBootTest(webEnvironment =  WebEnvironment.RANDOM_PORT)
@ActiveProfiles("dev")
public class BookApiControllerTest {
    
    @Autowired
    private BookService service;

    @Autowired
    private BookRepository repository;

    @Autowired
    private TestRestTemplate rt;

    private static ObjectMapper om;

    private static HttpHeaders headers;

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

    @BeforeAll
    public static void init(){
        om = new ObjectMapper();
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

    }

    //책 저장 테스트
    @Test
    public void bookSaveTest() throws JsonProcessingException {

        //given
        BookSaveRequestDto dto = new BookSaveRequestDto(); 
        dto.setTitle("junit project");
        dto.setAuthor("kevin");

        String body = om.writeValueAsString(dto); // json object로 변경        
        
        //when
        HttpEntity<String> request = new HttpEntity<>(body,headers);

        ResponseEntity<String> response = rt.exchange("/api/v1/book", HttpMethod.POST, request, String.class);

        //then
        DocumentContext dc = JsonPath.parse(response.getBody());
        String title = dc.read("$.body.title");
        String author = dc.read("$.body.author");

        assertThat(title).isEqualTo("junit project");
        assertThat(author).isEqualTo("kevin");

    }

    //리스트 보기 테스트
    @Sql("classpath:db/tableInit.sql") // 테이블 초기화
    @Test
    public void getBookListTest(){
        //given

        //when
        HttpEntity<String> request = new HttpEntity<>(null,headers);
        ResponseEntity<String> response = rt.exchange("/api/v1/book", HttpMethod.GET, request, String.class);

        //then
        DocumentContext dc = JsonPath.parse(response.getBody());
        String code = dc.read("$.code");

    }

    //한건보기 테스트
    @Sql("classpath:db/tableInit.sql") // 테이블 초기화
    @Test
    public void getOneBookTest(){
        //given
        Integer id = 1;

        //when
        HttpEntity<String> request = new HttpEntity<>(null,headers);
        ResponseEntity<String> response = rt.exchange("/api/v1/book/1", HttpMethod.GET, request, String.class);

        //then
        DocumentContext dc = JsonPath.parse(response.getBody());
        String code = dc.read("$.code");

        assertThat(code).isEqualTo(1);


    }

    //책삭제 테스트
    @Sql("classpath:db/tableInit.sql")
    @Test
    public void deleteBookTest(){
        //given
        Integer id = 1;

        //when 
        HttpEntity<String> request = new HttpEntity<>(null,headers);
        ResponseEntity<String> response = rt.exchange("/api/v1/book/1", HttpMethod.DELETE, request, String.class);

        //then
        DocumentContext dc = JsonPath.parse(response.getBody());
        Integer code = dc.read("$.code");

        assertThat(code).isEqualTo(1);
    }

    //책 수정 테스트
    @Sql("classpath:db/tableInit.sql")
    @Test
    public void updateBookTest() throws Exception{
        //given 
        Integer id = 1;
        BookSaveRequestDto dto = new BookSaveRequestDto();
        dto.setAuthor("kevinyu");
        dto.setTitle("spring-test");

        String body = om.writeValueAsString(dto); // json object로 변경        

        //when 
        HttpEntity<String> request = new HttpEntity<>(body,headers);
        ResponseEntity<String> response = rt.exchange("/api/v1/book/1", HttpMethod.PUT, request, String.class);

        //then
        DocumentContext dc = JsonPath.parse(response.getBody());
        Integer code = dc.read("$.code");

        assertThat(code).isEqualTo(1);
    }








}
