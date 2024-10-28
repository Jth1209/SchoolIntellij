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
		Long a = em.createQuery(q, Long.class).setParameter("uname",authentication.getName()).getSingleResult();//count를 사용할 때는 반환 타입을 무조건 Long으로 해야함(고정된 반환값인 듯?)
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
		List<String> want = responsesService.selectThree();
		List<String> first = responsesService.selectOne();
		List<String> second = responsesService.selectTwo();
		int sum = 0;

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		int id = em.createQuery("select a.id from AnsweredData a where a.uname = :uname",Integer.class).setParameter("uname",authentication.getName()).getSingleResult();
		List<Respondent> r = em.createQuery("select r from Respondent r", Respondent.class).getResultList();
		for(Respondent rr : r) {
			location.add(rr.getLocation());
			age.add(rr.getAge());
		}//설문자의 위치와 나이 정보 얻기

		List<String> userChoice = responsesService.selectUserChoice(id);
		int count = responsesService.userCount();

		for(int ages : age){
			sum += ages;
		}

		int percentage1 = Collections.frequency(first,userChoice.get(0));
		int percentage2 = Collections.frequency(second,userChoice.get(1));

		double per1 = Math.floor(((double) percentage1 /count) * 100);
		double per2 = Math.floor(((double) percentage2 /count) * 100);
		double totalAge = Math.floor(((double) sum/count));


		em.getTransaction().commit();
		model.addAttribute("location", location);//설문 조사에 참여한 인원들의 거주 지역
		model.addAttribute("totalAge", totalAge);//설문조사에 참여한 참가자들의 평균 연령대
		model.addAttribute("third", want);//하고싶은 말
		model.addAttribute("count", count);// 설문조사에 참여한 모든 인원
		model.addAttribute("per1",per1);//현재 사용자의 1번째 답변이 차지하는 퍼센테이지
		model.addAttribute("per2",per2);//현재 사용자의 2번째 답변이 차지하는 퍼센테이지
		model.addAttribute("userChoice1",userChoice.get(0));//1번 답
		model.addAttribute("userChoice2",userChoice.get(1));//2번 답
		model.addAttribute("username",authentication.getName());

		return "/survey/statistic";
	}
}