package site.mini.junitproject.dto.request;

import lombok.Data;
import lombok.Setter;
import site.mini.junitproject.domain.Book;

@Data
public class BookSaveRequestDto {
    
    private String title;
    
    private String author;

    public Book toEntity(){
        return Book.builder().title(title).author(author).build();
    }








    
}
