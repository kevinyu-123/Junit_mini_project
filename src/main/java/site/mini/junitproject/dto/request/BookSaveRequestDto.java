package site.mini.junitproject.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.Setter;
import site.mini.junitproject.domain.Book;

@Data
public class BookSaveRequestDto {
    
    @NotBlank
    @Size(min = 1, max = 50)
    private String title;
    
    @NotBlank
    @Size(min = 3, max = 20)
    private String author;

    public Book toEntity(){
        return Book.builder()
            .title(title)
            .author(author)
            .build();
    }








    
}
