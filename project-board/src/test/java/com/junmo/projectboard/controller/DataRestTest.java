package com.junmo.projectboard.controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Disabled("Spring Data Rest 통합테스트는 불필요하므로 제외시킴")
@DisplayName("Data Rest - API테스트")
@Transactional
@AutoConfigureMockMvc
@SpringBootTest
public class DataRestTest {

    private final MockMvc mvc;

    public DataRestTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }


    @DisplayName("[api] 게시글 리스트 조회")
    @Test
    void giveNothing_whenRequestingArticles_thenReturnsArticlesJsonResponse() throws Exception {
        //Given

        //When & Then
        mvc.perform(get("/api/articles")) // MockMvcRequestBuilders.get 을 이용한거다. alt + enter로 정적으로 가져옴
                .andExpect(status().isOk()) // 존재여부 확인
//                .andExpect(content.contentType(MediaType.valueOf("application/hal+json")))
                ;
    }

    @DisplayName("[api] 게시글 단건 조회")
    @Test
    void giveNothing_whenRequestingArticle_thenReturnsArticlesJsonResponse() throws Exception {
        //Given

        //When & Then
        mvc.perform(get("/api/articles/1")) // MockMvcRequestBuilders.get 을 이용한거다. alt + enter로 정적으로 가져옴
                .andExpect(status().isOk()) // 존재여부 확인
//                .andExpect(content.contentType(MediaType.valueOf("application/hal+json")))
        ;
    }

    @DisplayName("[api] 게시글 -> 댓글 조회")
    @Test
    void giveNothing_whenRequestingArticleCommentsFromArticle_thenReturnsArticlesJsonResponse() throws Exception {
        //Given

        //When & Then
        mvc.perform(get("/api/articles/1/articleComments")) // MockMvcRequestBuilders.get 을 이용한거다. alt + enter로 정적으로 가져옴
                .andExpect(status().isOk()) // 존재여부 확인
//                .andExpect(content.contentType(MediaType.valueOf("application/hal+json")))
        ;
    }

    @DisplayName("[api] 댓글 리스트 조회")
    @Test
    void giveNothing_whenRequestingArticleComments_thenReturnsArticlesJsonResponse() throws Exception {
        //Given

        //When & Then
        mvc.perform(get("/api/articleComments")) // MockMvcRequestBuilders.get 을 이용한거다. alt + enter로 정적으로 가져옴
                .andExpect(status().isOk()) // 존재여부 확인
//                .andExpect(content.contentType(MediaType.valueOf("application/hal+json")))
        ;
    }

    @DisplayName("[api] 댓글 단건 조회")
    @Test
    void giveNothing_whenRequestingArticleComment_thenReturnsArticlesJsonResponse() throws Exception {
        //Given

        //When & Then
        mvc.perform(get("/api/articleComments/1")) // MockMvcRequestBuilders.get 을 이용한거다. alt + enter로 정적으로 가져옴
                .andExpect(status().isOk()) // 존재여부 확인
//                .andExpect(content.contentType(MediaType.valueOf("application/hal+json")))
        ;
    }

}
