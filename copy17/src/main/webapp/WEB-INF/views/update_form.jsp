<%@ page contentType="text/html; charset=euc-kr" %>
<%@ page import="edu.du.copy17.service.ReadArticleService" %>
<%@ page import="edu.du.copy17.model.Article" %>
<%@ page import="edu.du.copy17.service.ArticleNotFoundException" %>
<%
	String viewPage = null;
	try {
		int articleId = Integer.parseInt(request.getParameter("articleId"));
		Article article = ReadArticleService.getInsteance().getArticle(articleId);
		viewPage = "/update_form_view.jsp";
		request.setAttribute("article", article);
	} catch (ArticleNotFoundException ex) {
		viewPage = "/article_not_found.jsp";
	}
%>
<jsp:forward page="<%= viewPage %>" />
