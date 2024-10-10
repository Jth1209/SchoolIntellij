package edu.du.copy17;

import edu.du.copy17.model.Article;
import edu.du.copy17.model.ArticleListModel;
import edu.du.copy17.service.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.UnsupportedEncodingException;

@Controller
public class MyController {

    @Autowired
    ListArticleService listSerivce;

    @Autowired
    ReadArticleService readSerivce;

    @Autowired
    WriteArticleService writeSerivce;

    @Autowired
    UpdateArticleService updateSerivce;

    @Autowired
    DeleteArticleService deleteSerivce;

    @Autowired
    ReplyArticleService replySerivce;

    @GetMapping("/")
    public String index() {
        return "redirect:list";
    }

    @GetMapping("/list")
    public String list(Model model, HttpServletRequest request) {
        String pageNumberString = request.getParameter("p");
        int pageNumber = 1;
        if (pageNumberString != null && pageNumberString.length() > 0) {
            pageNumber = Integer.parseInt(pageNumberString);
        }
        ArticleListModel articleListModel = listSerivce.getArticleList(pageNumber);
        System.out.println(articleListModel.getArticleList().size());
        model.addAttribute("listModel", articleListModel);

        if (articleListModel.getTotalPageCount() > 0) {
            int beginPageNumber =
                    (articleListModel.getRequestPage() - 1) / 10 * 10 + 1;
            int endPageNumber = beginPageNumber + 9;
            if (endPageNumber > articleListModel.getTotalPageCount()) {
                endPageNumber = articleListModel.getTotalPageCount();
            }
            model.addAttribute("beginPage", beginPageNumber);
            model.addAttribute("endPage", endPageNumber);
        }
        return "list_view";
    }

    @GetMapping("/read")
    public String read(Model model, HttpServletRequest request) {
        int articleId = Integer.parseInt(request.getParameter("articleId"));
        String viewPage = null;
        try {
            Article article = readSerivce.readArticle(articleId);
            model.addAttribute("article", article);
            viewPage = "/read_view.jsp";
        } catch (ArticleNotFoundException ex) {
            viewPage = "/article_not_found.jsp";
        }
        return "read_view";
    }

    @GetMapping("/writeForm")
    public String writeForm() {
        return "writeForm";
    }

    @PostMapping("/write")
    public String write(WritingRequest writingRequest) throws IdGenerationFailedException {
        System.out.println(writingRequest);
        writeSerivce.write(writingRequest);
        return "redirect:list";
    }

    @GetMapping("/update_form")
    public String updateForm(Model model, HttpServletRequest request) {
        try {
            int articleId = Integer.parseInt(request.getParameter("articleId"));
            int pageNumber = Integer.parseInt(request.getParameter("p"));
            Article article = readSerivce.getArticle(articleId);
            model.addAttribute("article", article);
            model.addAttribute("param",pageNumber);
            return "update_form_view";
        } catch (ArticleNotFoundException ex) {
            return "article_not_found";
        }
    }

    @PostMapping("/update")
    public String update(Model model , HttpServletRequest request) throws UnsupportedEncodingException, ArticleNotFoundException, InvalidPasswordException {

        request.setCharacterEncoding("euc-kr");
        int articleId = Integer.parseInt(request.getParameter("articleId"));
        System.out.println(articleId);
        String title = request.getParameter("title");
        String password = request.getParameter("password");
        System.out.println(password);
        String content = request.getParameter("content");
        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.setArticleId(articleId);
        updateRequest.setContent(content);;
        updateRequest.setPassword(password);;
        updateRequest.setTitle(title);;
        Article article = updateSerivce.update(updateRequest);
        model.addAttribute("updatedArticle", article);
        return  "redirect:list";

    }

    @GetMapping("/delete_form")
    public String deleteForm(Model model, HttpServletRequest request) {
        int articleId = Integer.parseInt(request.getParameter("articleId"));
        System.out.println(articleId);
        model.addAttribute("param", articleId);
        return "delete_form";
    }

    @PostMapping("/delete")
    public String delete(HttpServletRequest request) throws ArticleNotFoundException, InvalidPasswordException, UnsupportedEncodingException {
        request.setCharacterEncoding("euc-kr");

        int articleId = Integer.parseInt(request.getParameter("articleId"));
        System.out.println(articleId);
        String password = request.getParameter("password");
        System.out.println(password);

        DeleteRequest deleteRequest = new DeleteRequest();
        deleteRequest.setArticleId(articleId);
        deleteRequest.setPassword(password);

        deleteSerivce.deleteArticle(deleteRequest);
        return "redirect:list";

    }

    @GetMapping("/reply_form")
    public String replyForm(Model model, HttpServletRequest request) {
        int parentId = Integer.parseInt(request.getParameter("parentId"));
        int pageNumber = Integer.parseInt(request.getParameter("p"));
        model.addAttribute("param",parentId);
        model.addAttribute("param",pageNumber);
        return "reply_form";
    }

    @PostMapping("/reply")
    public String reply(Model model, HttpServletRequest request) throws UnsupportedEncodingException, CannotReplyArticleException, ArticleNotFoundException, LastChildAleadyExistsException {
        request.setCharacterEncoding("euc-kr");

        int parentArticledId = Integer.parseInt(request.getParameter("parentArticleId"));//댓글을 달고자 하는 작성자의 아이디
        System.out.println(parentArticledId);
        String writerName = request.getParameter("writerName");
        System.out.println(writerName);
        String title = request.getParameter("title");
        String password = request.getParameter("password");
        String content = request.getParameter("content");

        ReplyingRequest replyingRequest = new ReplyingRequest(writerName,title,password,content);
        replyingRequest.setParentArticleId(parentArticledId);
        replySerivce.reply(replyingRequest);
        return "redirect:list";
    }
}