package com.revature.controller;

import java.time.LocalDate;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.entities.Question;
import com.revature.services.QuestionService;

@RestController
@RequestMapping("/questions")
public class QuestionController {

	@ModelAttribute
	LocalDate creationDate() {
		return LocalDate.now();
	}
	
	@Autowired
	QuestionService questionService;
	
	/**	 *@author ken */
	@GetMapping
	public Page<Question> getAllQuestions(Pageable pageable)
	{
		return questionService.getAllQuestions(pageable);
	}

	// issue Ambiguous handler method
	/**@author ken*/
	@GetMapping("/status/{status}")
	public Page<Question> getAllQuestionsByStatus(Pageable pageable, @PathVariable boolean status)
	{
		return questionService.getAllQuestionsByStatus(pageable, status);
	}

	/**@author ken*/
	@GetMapping("/user/{id}")
	public Page<Question> getAllQuestionsByUserId(Pageable pageable, @PathVariable int id)
	{
		return questionService.getAllQuestionsByUserId(pageable, id);
	}

	/** @Author James Walls */
	@PostMapping
	public Question saveQuestion(@Valid @RequestBody Question question) {
		return questionService.save(question);
	}

	/**@author Hugh Thornhill*/
	@PutMapping
	public Question updateQuestionAcceptedAnswerId(@RequestBody Question question) {
		return questionService.updateQuestionAcceptedAnswerId(question);
	}

	/**@author Hugh Thornhill*/
	@PutMapping("/status")
	public Question updateStatus(@RequestBody Question question) {
		return questionService.updateQuestionStatus(question, 20);
	}
	
	/** @Author Natasha Poser */
	@GetMapping("/id/{id}")
	public Question getQuestionByQuestionId(@PathVariable int id) {
		return questionService.findById(id);
	}
	
	@GetMapping("/recent")
	public Page<Question> findAllByOrderByCreationDateDesc(Pageable creationDatePageable){
//		System.out.println(creationDate);
		return questionService.findAllByOrderByCreationDateDesc(creationDatePageable);
	}
}