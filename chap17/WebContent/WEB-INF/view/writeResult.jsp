<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head><title>게시글 작성</title></head>
<body>
게시글을 등록했습니다.
<br/>
<a href="${pageContext.request.contextPath}/list.do">목록보기</a>
<a href="${pageContext.request.contextPath}/read.do?articleId=${postedArticle.id}">게시글 읽기</a>
</body>
</html>