package edu.du.copy17.service;

import edu.du.copy17.dao.ArticleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;

@Service
public class DeleteArticleService {

	private static DeleteArticleService instance = new DeleteArticleService();

	public static DeleteArticleService getInstance() {
		return instance;
	}

	@Autowired
	ArticleDao articleDao;

	@Autowired
	ArticleCheckHelper articleCheckHelper;

	private DeleteArticleService() {
	}

	public void deleteArticle(DeleteRequest deleteRequest)
			throws ArticleNotFoundException, InvalidPasswordException {
		try {

			articleCheckHelper.checkExistsAndPassword(deleteRequest
					.getArticleId(), deleteRequest.getPassword());

			articleDao.delete(deleteRequest.getArticleId());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (ArticleNotFoundException e) {
			throw e;
		} catch (InvalidPasswordException e) {
			throw e;
		}
	}
}
