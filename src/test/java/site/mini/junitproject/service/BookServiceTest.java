package site.mini.junitproject.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import site.mini.junitproject.domain.BookRepository;
import site.mini.junitproject.dto.BookResponseDto;
import site.mini.junitproject.dto.BookSaveRequestDto;
import site.mini.junitproject.util.MailSender;
import site.mini.junitproject.util.MailSenderStub;

@ExtendWith(MockitoExtension.class) //가짜 환경 조성
public class BookServiceTest {

    
    private BookService service;

    @Mock
    private BookRepository repository;

    @Mock
    private MailSender mailSender;

    /*
     * mockito = 가짜 객체 보관을 위한 환경을 조성해줌
     */

    @Test
    public void saveBook(){

        //given
        BookSaveRequestDto dto = new BookSaveRequestDto();
        dto.setAuthor("kevin");
        dto.setTitle("junit test");

        //stub

        //when
        BookResponseDto responseDto = service.saveBook(dto);

        //then
        assertEquals(dto.getAuthor(),responseDto.getAuthor());
        assertEquals(dto.getTitle(), responseDto.getTitle());
    }
    
}
