package com.junmo.projectboard.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@ToString
@Table(indexes = {
        @Index(columnList = "title"),
        @Index(columnList = "hashtag"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Setter @Column(nullable = false) private String title; //제목 setter 이 단계에서 설정, notnull 설정
    @Setter @Column(nullable = false, length = 10000) private String content; // 본문

    @Setter private String hashtag; //해시태그 - null 가능한 경우 colum 필요업다.

    //댓글 DB와 연결하는 부분
    @ToString.Exclude //ToString은 일일이 내용들을 다 확인하는데, 본문볼때 댓글 다(댓글 쪽과 연결되어 있으니) 볼필요 없다고 판단.
    @OrderBy("id")
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private final Set<ArticleComment> articleComments = new LinkedHashSet<>();
    //이 article 연동된 articleComments 는 중복을 허용하지 않고 모아서 컬랙션으로 보겠다를 의미

    @CreatedDate @Column(nullable = false) private LocalDateTime createdAt; //생성일시
    @CreatedBy @Column(nullable = false, length = 100) private String createdBy; // 생성자
    @LastModifiedDate @Column(nullable = false) private LocalDateTime modifiedAt; //수정일시
    @LastModifiedBy @Column(nullable = false, length = 100) private String modifiedBy; // 수정자


    //모든 jpa 엔티티는 하이버네이틱 이용, 기본생정자 필요
    protected Article() {}

    //테스트에서는 모든 속성에 대해 하면 좋지만, 실제 할때는 필요한 것들만 여는 것이 좋다
    //팩토리 메소드 형식으로 접근하게 만들 것이다.
    private Article(String title, String content, String hashtag) {
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
    }
    public static Article of(String title, String content, String hashtag) {
        return new Article(title, content, hashtag);
    }

    // 정보를 리스트에 담아서 보낼때 비교 및 전처리 필요 - 동일성 동등성 점검
    // -> 이퀄즈, 해쉬코드 구현(롬복으로 간단히 구현 가능 - @쓰면 전체에 적용 되버려서 여기선 안씀)


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Article article = (Article) o;
        //위의 두 중대신 아래 한 줄이 더 최신이며, 간단한 방법(필요없는 부분을 없앤)
        if (!(o instanceof Article article)) return false;
        // 아직 DB에 영속화 되지 않은 경우 id가 부여되지 않았으므로, 나머지 내용이 같아도 다른 것
        // 만약 id 가 같으면 동등성 검사 통과 한것
        return id != null && id.equals(article.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
