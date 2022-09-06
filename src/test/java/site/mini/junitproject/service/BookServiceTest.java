package site.mini.junitproject.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import site.mini.junitproject.domain.Book;
import site.mini.junitproject.domain.BookRepository;
import site.mini.junitproject.dto.BookResponseDto;
import site.mini.junitproject.dto.BookSaveRequestDto;
import site.mini.junitproject.util.MailSender;
import site.mini.junitproject.util.MailSenderStub;

@ExtendWith(MockitoExtension.class) //가짜 환경 조성
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
        books.add(new Book(1L,"juint","kevin"));
        books.add(new Book(2L, "spring","hyeonjoon"));

        when(repository.findAll()).thenReturn(books);

        List<BookResponseDto> dto = service.getList();

        assertThat(dto.get(0).getTitle()).isEqualTo("junit"); 
    }

    
}
