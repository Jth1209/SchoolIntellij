package edu.du.copy17.service;

import edu.du.copy17.dao.ArticleDao;
import edu.du.copy17.model.Article;
import edu.du.copy17.model.ArticleListModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListArticleService {
    @Autowired
    ArticleDao articleDao;

    public static final int COUNT_PER_PAGE = 5;

    public ArticleListModel getArticleList(int requestPageNumber) {
        if (requestPageNumber < 0) {
            throw new IllegalArgumentException("page number < 0 : "
                    + requestPageNumber);
        }

        int totalArticleCount = articleDao.selectCount();

        if (totalArticleCount == 0) {
            return new ArticleListModel();
        }

        int totalPageCount = calculateTotalPageCount(totalArticleCount);

        int firstRow = (requestPageNumber - 1) * COUNT_PER_PAGE;
        int endRow = firstRow + COUNT_PER_PAGE;

        if (endRow > totalArticleCount) {
            endRow = totalArticleCount;
        }
        List<Article> articleList = articleDao.select(firstRow,
                endRow);

        ArticleListModel articleListView = new ArticleListModel(
                articleList, requestPageNumber, totalPageCount, firstRow+1,
                endRow);
        return articleListView;

    }

    private int calculateTotalPageCount(int totalArticleCount) {
        if (totalArticleCount == 0) {
            return 0;
        }
        int pageCount = totalArticleCount / COUNT_PER_PAGE;
        if (totalArticleCount % COUNT_PER_PAGE > 0) {
            pageCount++;
        }
        return pageCount;
    }
}
