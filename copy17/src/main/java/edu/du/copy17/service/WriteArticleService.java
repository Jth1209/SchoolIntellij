package edu.du.copy17.service;

import java.text.DecimalFormat;
import java.util.Date;

import edu.du.copy17.dao.ArticleDao;
import edu.du.copy17.dao.IdGenerator;
import edu.du.copy17.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WriteArticleService {
	@Autowired
	ArticleDao articleDao;

	@Autowired
	IdGenerator idGenerator;

	private static WriteArticleService instance = new WriteArticleService();
	public static WriteArticleService getInstance() {
		return instance;
	}

	private WriteArticleService() {
	}

	public Article write(WritingRequest writingRequest)
			throws IdGenerationFailedException {

		int groupId = idGenerator.generateNextId("article");

		Article article = writingRequest.toArticle();

		article.setGroupId(groupId);
		article.setPostingDate(new Date());
		DecimalFormat decimalFormat = new DecimalFormat("0000000000");
		article.setSequenceNumber(decimalFormat.format(groupId) + "999999");

		int articleId = articleDao.insert(article);

		article.setId(articleId);
		return article;
	}
}
