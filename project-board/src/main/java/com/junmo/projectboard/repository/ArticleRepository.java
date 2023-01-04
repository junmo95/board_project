package com.junmo.projectboard.repository;

import com.junmo.projectboard.domain.Article;
import com.junmo.projectboard.domain.QArticle;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ArticleRepository extends
        JpaRepository<Article, Long>,
        QuerydslPredicateExecutor<Article>,  // 이 엔티티(Article)에 있는 모느 필드에 대한 기본 검색기능 추가해줌
        QuerydslBinderCustomizer<QArticle> {

    //검색에 대한 세부적인 조건 구현
    @Override
    default void customize(QuerydslBindings bindings, QArticle root) {
        bindings.excludeUnlistedProperties(true); //현제 이기능에 의해서 모든 검색이 열려있으니 선택적인 부분만 가능하게 제한
        bindings.including(root.title, root.content, root.hashtag, root.createdAt, root.createdBy); //원하는 필드 추가
//        bindings.bind(root.title).first(StringExpression::likeIgnoreCase); // 이걸로 하면 검색시 lie '${}'
        bindings.bind(root.title).first(StringExpression::containsIgnoreCase); // 검색 파라미터 하나, 대소문자 무시, 검색시 like '%${}%'
        bindings.bind(root.content).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.hashtag).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.createdAt).first(DateTimeExpression::eq); // 시간 형식
        bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase);
    }

}