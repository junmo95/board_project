package com.junmo.projectboard.repository;

import com.junmo.projectboard.domain.Article;
import com.junmo.projectboard.domain.ArticleComment;
import com.junmo.projectboard.domain.QArticle;
import com.junmo.projectboard.domain.QArticleComment;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ArticleCommentRepository extends
        JpaRepository<ArticleComment, Long>,
        QuerydslPredicateExecutor<ArticleComment>,
        QuerydslBinderCustomizer<QArticleComment> {
    @Override
    default void customize(QuerydslBindings bindings, QArticleComment root) {
        bindings.excludeUnlistedProperties(true); //현제 이기능에 의해서 모든 검색이 열려있으니 선택적인 부분만 가능하게 제한
        bindings.including(root.content, root.createdAt, root.createdBy); //원하는 필드 추가
        bindings.bind(root.content).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.createdAt).first(DateTimeExpression::eq); // 시간 형식
        bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase);
    }
}
