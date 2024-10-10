package board;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandHandler;
import mvjsp.chap17.board.model.Article;
import mvjsp.chap17.board.service.IdGenerationFailedException;
import mvjsp.chap17.board.service.WriteArticleService;
import mvjsp.chap17.board.service.WritingRequest;

public class WriteHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse res) {
		try {
			request.setCharacterEncoding("euc-kr");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String writerName = request.getParameter("writerName");
		String title = request.getParameter("title");
		String password = request.getParameter("password");
		String content = request.getParameter("content");
		System.out.println(writerName);
		WritingRequest writingRequest = new WritingRequest(writerName, password, title, content);
		Article postedArticle = null;
		try {
			postedArticle = WriteArticleService.getInstance().write(writingRequest);
		} catch (IdGenerationFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("postedArticle", postedArticle);
		return "/WEB-INF/view/writeResult.jsp";
	}

}
