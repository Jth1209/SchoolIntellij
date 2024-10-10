package board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandHandler;
import mvjsp.chap17.board.model.ArticleListModel;
import mvjsp.chap17.board.service.ListArticleService;

public class ListHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse res) {
		String pageNumberString = request.getParameter("p");
		int pageNumber = 1;
		if (pageNumberString != null && pageNumberString.length() > 0) {
			pageNumber = Integer.parseInt(pageNumberString);
		}
		ListArticleService listSerivce = ListArticleService.getInstance();
		ArticleListModel articleListModel = listSerivce.getArticleList(pageNumber);
		request.setAttribute("listModel", articleListModel);
		
		if (articleListModel.getTotalPageCount() > 0) {
			int beginPageNumber = 
				(articleListModel.getRequestPage() - 1) / 10 * 10 + 1;
			int endPageNumber = beginPageNumber + 9;
			if (endPageNumber > articleListModel.getTotalPageCount()) {
				endPageNumber = articleListModel.getTotalPageCount();
			}
			request.setAttribute("beginPage", beginPageNumber);
			request.setAttribute("endPage", endPageNumber);
		}
		return "/WEB-INF/view/list_view.jsp";
	}

}
