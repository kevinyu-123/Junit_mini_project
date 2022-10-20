package site.mini.junitproject.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@RedisHash(value = "book")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookEntity {

    @Id
    @Indexed
    private Long id;

    private String title;

    private String author;    
}

/*
 * @Id : Id가 키 값이 된다.

    @Indexed : 값으로 검색을 할 시에 추가한다.

    @TimeToLive : 만료시간을 설정

   - RedishHash 어노테이션에 설정해도 된다.

   - 값은 초 단위로 설정된다.
 */