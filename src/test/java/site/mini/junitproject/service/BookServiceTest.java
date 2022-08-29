package site.mini.junitproject.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import site.mini.junitproject.domain.BookRepository;
import site.mini.junitproject.dto.BookResponseDto;
import site.mini.junitproject.dto.BookSaveRequestDto;
import site.mini.junitproject.util.MailSenderStub;

@DataJpaTest
public class BookServiceTest {

    /*
     * mockito = 가짜 객체 보관을 위한 환경을 조성해줌
     */

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void saveBook(){

        //given
        BookSaveRequestDto dto = new BookSaveRequestDto();
        dto.setAuthor("kevin");
        dto.setTitle("junit test");

        //stub
        MailSenderStub mailSenderStub = new MailSenderStub();

        //when
        BookService service = new BookService(bookRepository,mailSenderStub);
        BookResponseDto responseDto = service.saveBook(dto);

        //then
        assertEquals(dto.getAuthor(),responseDto.getAuthor());
        assertEquals(dto.getTitle(), responseDto.getTitle());
    }
    
}
