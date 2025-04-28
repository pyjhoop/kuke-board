package kuke.board.article.api;

import kuke.board.articleread.service.response.ArticleResponse;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestClient;

public class ArticleApiTest {
    RestClient restClient = RestClient.create("http://localhost:9000");

    @Test
    void createTest() {
        ArticleResponse response = create(new ArticleCreateRequest(
                "hi", "my content",1L, 1L
        ));
        System.out.println("response = "+ response);
    }

    ArticleResponse create(ArticleCreateRequest request) {
        return restClient.post()
                .uri("/v1/articles")
                .body(request)
                .retrieve()
                .body(ArticleResponse.class);
    }

    @Test
    void readTest() {
        ArticleResponse response = read(175221653231497216L);
        System.out.println("response = "+ response);
    }

    ArticleResponse read(Long articleId) {
        return restClient.get()
                .uri("/v1/articles/{articleId}", articleId)
                .retrieve()
                .body(ArticleResponse.class);
    }

    @Test
    void updateTest() {
        update(175221653231497216L);
        ArticleResponse response = read(175221653231497216L);
        System.out.println("response = "+ response);
    }

    void update(Long articleId) {
        restClient.put()
                .uri("/v1/articles/{articleId}", articleId)
                .body(new ArticleUpdateRequest("hi 2", "my content 2"))
                .retrieve();

    }

    @Test
    void deleteTest() {
        restClient.delete()
                .uri("/v1/articles/{articleId}", 175221653231497216L)
                .retrieve();
    }



    static class ArticleCreateRequest {
        private String title;
        private String content;
        private Long writerId;
        private Long boardId;

        public ArticleCreateRequest(String title, String content, Long writerId, Long boardId) {
            this.title = title;
            this.content = content;
            this.writerId = writerId;
            this.boardId = boardId;
        }

        public String getTitle() {
            return title;
        }

        public String getContent() {
            return content;
        }

        public Long getWriterId() {
            return writerId;
        }

        public Long getBoardId() {
            return boardId;
        }
    }


    static class ArticleUpdateRequest {
        private String title;
        private String content;

        public ArticleUpdateRequest(String title, String content) {
            this.title = title;
            this.content = content;
        }

        public String getTitle() {
            return title;
        }

        public String getContent() {
            return content;
        }
    }

}

