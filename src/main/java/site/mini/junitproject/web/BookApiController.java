package site.mini.junitproject.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import site.mini.junitproject.dto.BookSaveRequestDto;
import site.mini.junitproject.service.BookService;

@RequiredArgsConstructor
@RestController
public class BookApiController { // 컴포지션 = has 관계
    
    private final BookService service;


    public ResponseEntity<?> saveBook(@RequestBody BookSaveRequestDto saveDto){
        service.saveBook(saveDto);

        return new ResponseEntity<>(HttpStatus.CREATED); // 201 = insert

    }

    public ResponseEntity<?> getBookList(){

        return null;

    }

    public ResponseEntity<?> getBookOne(){

        return null;

    }

    public ResponseEntity<?> deleteBook(){

        return null;

    }

    public ResponseEntity<?> updateBook(){

        return null;

    }


}
