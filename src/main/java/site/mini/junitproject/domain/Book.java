package site.mini.junitproject.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.mini.junitproject.dto.response.BookResponseDto;

@NoArgsConstructor
@Entity
@Data
@Builder
@AllArgsConstructor
public class Book {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(length = 20,nullable = false)
    private String author;    
    
    public void update(String title,String author){
        this.author = author;
        this.title = title;
    }

    public BookResponseDto toDto(){

        return BookResponseDto.builder().id(id).title(title).author(author).build();
    }


}
