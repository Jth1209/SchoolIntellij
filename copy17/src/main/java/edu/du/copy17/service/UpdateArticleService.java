package edu.du.copy17.service;

import java.sql.Connection;
import java.sql.SQLException;

import edu.du.copy17.dao.ArticleDao;
import edu.du.copy17.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateArticleService {

	private static UpdateArticleService instance = new UpdateArticleService();

	public static UpdateArticleService getInstance() {
		return instance;
	}

	@Autowired
	ArticleDao articleDao;

	@Autowired
	ArticleCheckHelper articleCheckHelper;

	private UpdateArticleService() {
	}

	public Article update(UpdateRequest updateRequest)
			throws ArticleNotFoundException, InvalidPasswordException {

		try {
			articleCheckHelper.checkExistsAndPassword(updateRequest
					.getArticleId(), updateRequest.getPassword());

			Article updatedArticle = new Article();
			updatedArticle.setId(updateRequest.getArticleId());
			updatedArticle.setTitle(updateRequest.getTitle());
			updatedArticle.setContent(updateRequest.getContent());

			int updateCount = articleDao.update(updatedArticle);
			if (updateCount == 0) {
				throw new ArticleNotFoundException(
				"�Խñ��� �������� ����: " + updateRequest.getArticleId());
			}

			Article article = articleDao.selectById(updateRequest
					.getArticleId());

			return article;
		} catch (SQLException e) {

			throw new RuntimeException("DB ����: " + e.getMessage(), e);
		} catch (ArticleNotFoundException e) {

			throw e;
		} catch (InvalidPasswordException e) {

			throw e;
		}
	}

}
