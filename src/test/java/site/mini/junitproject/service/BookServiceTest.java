package site.mini.junitproject.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import site.mini.junitproject.domain.Book;
import site.mini.junitproject.domain.BookRepository;
import site.mini.junitproject.dto.request.BookSaveRequestDto;
import site.mini.junitproject.dto.response.BookListResponseDto;
import site.mini.junitproject.dto.response.BookResponseDto;
import site.mini.junitproject.util.MailSender;

@ExtendWith(MockitoExtension.class) //가짜 환경 조성
@ActiveProfiles("dev")
public class BookServiceTest {

    @InjectMocks //BookService가 메모리에 올라감
    private BookService service;

    @Mock
    private BookRepository repository;

    @Mock
    private MailSender mailSender;

    /*
     * mockito = 가짜 객체 보관을 위한 환경을 조성해줌
     *  @injeckMocks = @Mock 어노테이션을 통하여 생성된 익명 클래스를 주입
     */

    @Test
    public void saveBook(){

        //given
        BookSaveRequestDto dto = new BookSaveRequestDto();
        dto.setAuthor("kevin");
        dto.setTitle("junit test");

        //stub
        when(repository.save(any())).thenReturn(dto.toEntity());
        when(mailSender.send()).thenReturn(true);

        //when
        BookResponseDto responseDto = service.saveBook(dto);

        //then
        // assertEquals(dto.getAuthor(),responseDto.getAuthor());
        // assertEquals(dto.getTitle(), responseDto.getTitle());

        assertThat(responseDto.getTitle()).isEqualTo(dto.getTitle());
        assertThat(responseDto.getAuthor()).isEqualTo(dto.getAuthor());
    }

    @Test
    public void getBook(){

        List<Book> books = new ArrayList<>();
        books.add(new Book(1L,"junit","kevin"));
        books.add(new Book(2L, "spring","hyeonjoon"));

        when(repository.findAll()).thenReturn(books);

        BookListResponseDto dto = service.getList();

        assertThat(dto.getBookList().get(0).getTitle()).isEqualTo("spring"); 
    }

    @Test
    public void getOneBook(){
        //given
        Long id = 1L;
     

        //stub
        Book book = new Book(1L,"junit","kevin");
        Optional<Book> bookOP = Optional.of(book);
        when(repository.findById(id)).thenReturn(bookOP);

        //when
        BookResponseDto res = service.getBook(id);

        //then
        assertThat(res.getTitle()).isEqualTo(book.getTitle());
    }
    
    @Test
    public void updateBook(){
        //given
        Long id = 1L;
        BookSaveRequestDto dto = new BookSaveRequestDto();

        dto.setTitle("spring");
        dto.setAuthor("hyeonjoon");

        //stub
        Book book = new Book(1L,"junit","kevin");
        Optional<Book> bookOP = Optional.of(book);
        when(repository.findById(id)).thenReturn(bookOP);

        //when
        BookResponseDto resDto = service.updateBook(id, dto);

        //then
        assertThat(resDto.getTitle()).isEqualTo(dto.getTitle());

    }









}
