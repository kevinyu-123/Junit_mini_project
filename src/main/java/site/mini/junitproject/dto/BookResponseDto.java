package site.mini.junitproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import site.mini.junitproject.domain.Book;

@Data
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class BookResponseDto {

    private Long id;

    private String author;

    private String title;


    // public BookResponseDto toDto(Book bookPS){
    //     this.id = bookPS.getId();
    //     this.title = bookPS.getTitle();
    //     this.author = bookPS.getAuthor();

    //     return this;
    // }
    
}
