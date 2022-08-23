package site.mini.junitproject.service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import site.mini.junitproject.domain.Book;
import site.mini.junitproject.domain.BookRepository;
import site.mini.junitproject.dto.BookResponseDto;
import site.mini.junitproject.dto.BookSaveRequestDto;

@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository repository;

    // public BookService( BookRepository repository){
    //     this.repository = repository;
    // }

        // save book info
        @Transactional(rollbackFor = RuntimeException.class)
        public BookResponseDto saveBook(BookSaveRequestDto book){
         Book bookPS = repository.save(book.toEntity());
            return new BookResponseDto().toDto(bookPS);
        }

        // read book list
        public List<BookResponseDto> getList(){
            return repository.findAll()
                        .stream()
                        .map(new BookResponseDto()::toDto)
                        .collect(Collectors.toList());
        }








}
