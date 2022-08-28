package site.mini.junitproject.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import site.mini.junitproject.domain.Book;
import site.mini.junitproject.domain.BookRepository;
import site.mini.junitproject.dto.BookResponseDto;
import site.mini.junitproject.dto.BookSaveRequestDto;
import site.mini.junitproject.util.MailSender;

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
            return new BookResponseDto().toDto(bookPS);
        }

        // read book list
        public List<BookResponseDto> getList(){
            return repository.findAll()
                        .stream()
                        .map(new BookResponseDto()::toDto)
                        .collect(Collectors.toList());
        }

        // get book
        public BookResponseDto getBook(Long id){
           Optional<Book> book = repository.findById(id);
            if(book.isPresent()){
                return new BookResponseDto().toDto(book.get());
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
        public void updateBook(Long id,BookSaveRequestDto saveDto){
            Optional<Book> book = repository.findById(id);
            if(book.isPresent()){
                Book bookPs = book.get();
                bookPs.update(saveDto.getTitle(), saveDto.getAuthor());
            }else{
                throw new RuntimeException("해당 아이디가 없습니다.");
            }
        }


}
