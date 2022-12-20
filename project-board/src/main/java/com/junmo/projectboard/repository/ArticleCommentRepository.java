package com.junmo.projectboard.repository;

import com.junmo.projectboard.domain.Article;
import com.junmo.projectboard.domain.ArticleComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleCommentRepository extends JpaRepository<ArticleComment, Long> {
}
