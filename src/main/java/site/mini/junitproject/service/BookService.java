package site.mini.junitproject.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import site.mini.junitproject.domain.Book;
import site.mini.junitproject.domain.BookRepository;
import site.mini.junitproject.dto.request.BookSaveRequestDto;
import site.mini.junitproject.dto.response.BookListResponseDto;
import site.mini.junitproject.dto.response.BookResponseDto;
import site.mini.junitproject.util.MailSender;

@Slf4j
@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository repository;
    
    private final MailSender mailSender;

    // public BookService( BookRepository repository){
    //     this.repository = repository;
    // }

        // save book info
        @Transactional(rollbackFor = RuntimeException.class)
        public BookResponseDto saveBook(BookSaveRequestDto book){
         Book bookPS = repository.save(book.toEntity());

         if(bookPS != null){
             if(!mailSender.send()){
                 throw new RuntimeException("메일 전송 실패");
             }
         }
            return bookPS.toDto();
        }

        // read book list
        public BookListResponseDto getList(){

            List<BookResponseDto> dtos = repository.findAll().stream()
            // .map(new BookResponseDto()::toDto) // 한번 받은 값을 toDto를 두번 돌림
             .map(Book::toDto) 
             .collect(Collectors.toList());

            BookListResponseDto result = BookListResponseDto.builder().bookList(dtos).build();

            return result;
        }

        // get book
        public BookResponseDto getBook(Long id){
           Optional<Book> book = repository.findById(id);
            if(book.isPresent()){
                Book bookPs = book.get();
                return bookPs.toDto();
                
            }else{
                throw new RuntimeException("해당 아이디가 없습니다.");
            }
        }

        //delete book
        @Transactional(rollbackFor = RuntimeException.class)
        public void deleteBook(Long id){

            repository.deleteById(id);
        }

        //update book info
        @Transactional(rollbackFor = RuntimeException.class)
        public BookResponseDto updateBook(Long id,BookSaveRequestDto saveDto){
            Optional<Book> book = repository.findById(id);
            if(book.isPresent()){
                Book bookPs = book.get();
                bookPs.update(saveDto.getTitle(), saveDto.getAuthor());
                return bookPs.toDto();
            }else{
                throw new RuntimeException("해당 아이디가 없습니다.");
            }
        }


}
