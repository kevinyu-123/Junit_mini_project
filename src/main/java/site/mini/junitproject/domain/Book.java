package site.mini.junitproject.domain;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Data
@Builder
@AllArgsConstructor
public class Book {
    private Long id;
    private String title;
    private String author;    
}
