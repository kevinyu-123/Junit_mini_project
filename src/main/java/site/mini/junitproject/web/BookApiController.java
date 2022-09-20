package site.mini.junitproject.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import site.mini.junitproject.dto.request.BookSaveRequestDto;
import site.mini.junitproject.dto.response.BookResponseDto;
import site.mini.junitproject.dto.response.CmResponseDto;
import site.mini.junitproject.service.BookService;

@RequiredArgsConstructor
@RestController
public class BookApiController { // 컴포지션 = has 관계
    
    private final BookService service;


    @GetMapping("api/v1/book")
    public ResponseEntity<?> saveBook(@RequestBody BookSaveRequestDto saveDto){
        BookResponseDto dto = service.saveBook(saveDto);
        CmResponseDto<?> cmDto = CmResponseDto.builder().code(1).msg("save success").body(dto).build();

        return new ResponseEntity<>(cmDto, HttpStatus.CREATED); // 201 = insert, 응답뿐만 아니라 바디데이터를 보내줘야 할 경우도 생김.

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
