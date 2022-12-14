package com.ll.exam.article.service;

import com.ll.exam.annotation.AutoWired;
import com.ll.exam.annotation.Service;
import com.ll.exam.article.dto.ArticleDto;
import com.ll.exam.article.repository.ArticleRepository;

import java.util.List;

@Service
public class ArticleService {
    @AutoWired
    ArticleRepository articleRepository;

    public List<ArticleDto> getArticles() {
        return articleRepository.getArticles();
    }

    public ArticleDto getArticleById(long id) {
        return articleRepository.getArticleById(id);
    }

    public long getArticlesCount() {
        return articleRepository.getArticlesCount();
    }

    public long write(String title, String body) {
        return write(title, body, false);
    }

    public long write(String title, String content, boolean isBlind) {
        return articleRepository.write(title,content,isBlind);
    }

    public void modify(long id, String title, String content, boolean isBlind) {
        articleRepository.modify(id, title, content, isBlind);
    }

    public void delete(long id) {
        articleRepository.delete(id);
    }

    public ArticleDto getPreArticle(long id) {
        return articleRepository.getPrevArticle(id);
    }

    public ArticleDto getNextArticle(long id) {
        return articleRepository.getNextArticle(id);
    }
}
