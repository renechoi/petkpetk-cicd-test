package com.petkpetk.service.domain.community.fixture;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.petkpetk.service.domain.community.constatnt.CategoryType;
import com.petkpetk.service.domain.community.entity.Article;
import com.petkpetk.service.domain.community.entity.ArticleComment;
import com.petkpetk.service.domain.community.entity.ArticleImage;
import com.petkpetk.service.domain.community.entity.Hashtag;
import com.petkpetk.service.domain.user.entity.UserAccount;

public class ArticleEntityFixture {

    public static Article createArticle(UserAccount userAccount, CategoryType categoryType) {
        Article article = new Article();
        article.setUserAccount(userAccount);
        article.setTitle("Test Article Title");
        article.setContent("Test Article Content");
        article.setHit(0L);
        article.setCategoryType(categoryType);

        Set<Hashtag> hashtags = new HashSet<>();
        hashtags.add(new Hashtag("TestHashtag1"));
        hashtags.add(new Hashtag("TestHashtag2"));
        article.setHashtags(hashtags);

        List<ArticleImage> images = List.of(
                ArticleImage.asRepresentative(createMockMultipartFile()),
                ArticleImage.from(createMockMultipartFile())
        );
        article.addImages(images);

        ArticleComment comment1 = new ArticleComment();
        comment1.setUserAccount(userAccount);
        comment1.setContent("Test Comment 1");
        comment1.setParentCommentId(null);

        ArticleComment comment2 = new ArticleComment();
        comment2.setUserAccount(userAccount);
        comment2.setContent("Test Comment 2");
        comment2.setParentCommentId(null);

        comment1.getChildComments().add(comment2);
        article.getArticleComments().add(comment1);

        return article;
    }

    public static MultipartFile createMockMultipartFile() {
        byte[] mockBytes = UUID.randomUUID().toString().getBytes();
        return new MockMultipartFile("test.jpg", "testImage.jpg", "JPG", mockBytes);
    }

}
