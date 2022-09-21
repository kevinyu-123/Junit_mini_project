package site.mini.junitproject.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import site.mini.junitproject.dto.request.BookSaveRequestDto;
import site.mini.junitproject.dto.response.BookListResponseDto;
import site.mini.junitproject.dto.response.BookResponseDto;
import site.mini.junitproject.dto.response.CmResponseDto;
import site.mini.junitproject.service.BookService;

@Slf4j
@RequiredArgsConstructor
@RestController
public class BookApiController { // 컴포지션 = has 관계
    
    private final BookService service;


    @PostMapping("api/v1/book")
    public ResponseEntity<?> saveBook(@RequestBody @Valid BookSaveRequestDto saveDto, BindingResult bindingResult){
        log.error("bindingresult:" + bindingResult.hasErrors());

        //차후 aop 처리
        if(bindingResult.hasErrors()){
            Map<String,String> errorMap = new HashMap<String,String>();
            for(FieldError fe : bindingResult.getFieldErrors()){
                errorMap.put(fe.getField(),fe.getDefaultMessage());
            }
            System.out.println(errorMap.toString());
            log.info("errorMap: "+ errorMap.toString());
             throw new RuntimeException(errorMap.toString());
        }
        BookResponseDto dto = service.saveBook(saveDto);
        return new ResponseEntity<>(CmResponseDto.builder().code(1).msg("save success").body(dto).build(), HttpStatus.CREATED); // 201 = insert, 응답뿐만 아니라 바디데이터를 보내줘야 할 경우도 생김.
    }

    /*
     * v1 = version 1.0
     */ 
    @GetMapping("/api/v1/book")
    public ResponseEntity<?> getBookList(){
        BookListResponseDto bookList = service.getList();
        return new ResponseEntity<>(CmResponseDto.builder()
            .code(1)
            .msg("book lists")
            .body(bookList).build(), HttpStatus.OK);

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
