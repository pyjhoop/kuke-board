package kuke.board.articleread.repository;

import kuke.board.articleread.ArticleApplication;
import kuke.board.articleread.etity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = ArticleApplication.class)
class ArticleRepositoryTest {

    @Autowired
    ArticleRepository articleRepository;

    @Test
    void findAllTest() {
        List<Article> articles = articleRepository.findAll(1L, 1499970L, 30L);
        System.out.println("articles size: " + articles.size());
    }

    @Test
    void countTest() {
        Long count = articleRepository.count(1L, 10000L);
        System.out.println("count: " + count);
    }

    @Test
    void findAllInfiniteScrollTest(){
        List<Article> articles = articleRepository.findAllInfiniteScroll(1L, 30L);
        System.out.println("articles size: " + articles.size());

        Long lastArticleId = articles.getLast().getArticleId();

        List<Article> articles2 = articleRepository.findAllInfiniteScroll(1L, 30L, lastArticleId);

        System.out.println("articles size: " + articles2.size());
    }

}