package edu.du.sb1024.controller;

import edu.du.sb1024.entity.AnsweredData;
import edu.du.sb1024.entity.Respondent;
import edu.du.sb1024.service.ResponsesService;
import edu.du.sb1024.survey.Question;
import edu.du.sb1024.survey.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class SurveyController {

	@PersistenceUnit
	private EntityManagerFactory emf;

	final SurveyService surveyService;

	final ResponsesService responsesService;

	@GetMapping("/survey.do")
	public String form(Model model) {
		List<Question> questions = createQuestions();
		for (Question question : questions) {
			System.out.println(question);
		}
		model.addAttribute("questions", questions);
		return "/survey/surveyForm";
	}

	private List<Question> createQuestions() {
		Question q1 = new Question("당신의 역할은 무엇입니까?",
				Arrays.asList("서버", "프론트", "풀스택"));
		Question q2 = new Question("많이 사용하는 개발도구는 무엇입니까?",
				Arrays.asList("이클립스", "인텔리J", "서브라임"));
		Question q3 = new Question("하고 싶은 말을 적어주세요.");
		return Arrays.asList(q1, q2, q3);
	}

	@PostMapping("/submitted.do")
	public String submit(@ModelAttribute("ansData") AnsweredData data) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String route = "";
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		//유저 확인 쿼리
		String q = "select count(a) from AnsweredData a where a.uname = :uname";
		TypedQuery<Long> query = em.createQuery(q, Long.class);//count를 사용할 때는 반환 타입을 무조건 Long으로 해야함(고정된 반환값인 듯?)
		query.setParameter("uname", authentication.getName());
		Long a = query.getSingleResult();
		System.out.println(a);
		//이미 설문조사를 진행한 유저인지 확인
		if(a != 1L) {
			surveyService.save(data);
			route = "/survey/submitted";
		}else{
			route = "redirect:/survey/already";
		}
		em.getTransaction().commit();
		return route;
	}

	@GetMapping("/survey/already")
	public String already() {
		return "/survey/confirm";
	}

	@GetMapping("/survey/statistic")
	public String statistic(Model model) {
		List<String> location = new ArrayList<>();
		List<Integer> age = new ArrayList<>();
		List<String> want = new ArrayList<>();
		List<String> first = new ArrayList<>();
		List<String> second = new ArrayList<>();
		List<String> userChoice = new ArrayList<>();

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		want = responsesService.selectThree();
		first = responsesService.selectOne();
		second = responsesService.selectTwo();

		int id = em.createQuery("select a.id from AnsweredData a where a.uname = :uname",Integer.class).setParameter("uname",authentication.getName()).getSingleResult();
		List<Respondent> r = em.createQuery("select r from Respondent r", Respondent.class).getResultList();
		for(Respondent rr : r) {
			location.add(rr.getLocation());
			age.add(rr.getAge());
		}//설문자의 위치와 나이 정보 얻기

		userChoice = responsesService.selectUserChoice(id);
		int count = responsesService.userCount();

		int percentage1 = Collections.frequency(first,userChoice.get(0));
		int percentage2 = Collections.frequency(second,userChoice.get(1));

		double per1 = ((double) percentage1 /count) * 100;
		double per2 = ((double) percentage2 /count) * 100;

		em.getTransaction().commit();
		model.addAttribute("location", location);
		model.addAttribute("age", age);
		model.addAttribute("third", want);
		model.addAttribute("count", count);
		model.addAttribute("per1",per1);
		model.addAttribute("per2",per2);

		return "/survey/statistic";
	}
}
