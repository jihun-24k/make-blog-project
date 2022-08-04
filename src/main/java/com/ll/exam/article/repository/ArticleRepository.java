package com.ll.exam.article.repository;

import com.ll.exam.annotation.AutoWired;
import com.ll.exam.annotation.Repository;
import com.ll.exam.article.dto.ArticleDto;
import com.ll.exam.mymap.MyMap;
import com.ll.exam.mymap.SecSql;

import java.util.List;

@Repository
public class ArticleRepository {
    @AutoWired
    private MyMap myMap;

    public List<ArticleDto> getArticles() {
        SecSql sql = myMap.genSecSql();
        sql
                .append("SELECT *")
                .append("FROM article")
                .append("ORDER BY id DESC");
        return sql.selectRows(ArticleDto.class);
    }

    public ArticleDto getArticleById(long id) {
        SecSql sql = myMap.genSecSql();
        sql
                .append("SELECT *")
                .append("FROM article")
                .append("WHERE id = ?",id);

        return sql.selectRow(ArticleDto.class);
    }

    public long getArticlesCount() {
        SecSql sql = myMap.genSecSql();
        sql
                .append("SELECT COUNT(id)")
                .append("FROM article");

        return sql.selectLong();
    }

    public long write(String title, String content, boolean isBlind) {
        SecSql sql = myMap.genSecSql();
        sql
                .append("INSERT INTO article")
                .append("Set title = ?,", title)
                .append("body = ?,", content)
                .append("createdDate = NOW(),")
                .append("modifiedDate = NOW(),")
                .append("isBlind = ?",isBlind);
        return sql.insert();
    }
}
