package com.revature.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.entities.Question;
import com.revature.services.QuestionService;


@RestController
@RequestMapping("/questions")
@CrossOrigin(
		origins = { "http://localhost:3000" }, 
		methods = { RequestMethod.GET, RequestMethod.PUT, 
					RequestMethod.PATCH, RequestMethod.POST },
		allowedHeaders = { "content-type" }
	)
public class QuestionController {
	
	@Autowired
	QuestionService questionService;
	
	/**	 *@author ken 
	 * get all the questions*/
	@GetMapping
	@PreAuthorize("hasAuthority('user')")
	public Page<Question> getAllQuestions(Pageable pageable)
	{
		return questionService.getAllQuestions(pageable);
	}

	/**
	 * @param status = true/false
	 * get all the questions by the status of the question
	 */
	/**@author ken*/
	@GetMapping("/status/{status}")
	@PreAuthorize("hasAuthority('admin')")
	public Page<Question> getAllQuestionsByStatus(Pageable pageable, @PathVariable boolean status)
	{
		return questionService.getAllQuestionsByStatus(pageable, status);
	}

	/**@author ken*/
	/** get all the questions by user id
	 * @param pageable
	 * @param id = the id of the user
	 * @return
	 */
	@GetMapping("/user/{id}")
	@PreAuthorize("hasAuthority('user')")
	public Page<Question> getAllQuestionsByUserId(Pageable pageable, @PathVariable int id)
	{
		return questionService.getAllQuestionsByUserId(pageable, id);
	}

	/** @Author James Walls */
	/** Adds new questions and updates existing ones. */
	@PostMapping
	@PreAuthorize("hasAuthority('user')")
	public Question saveQuestion(@Valid @RequestBody Question question) {
		return questionService.save(question);
	}

	/** 
	 * @author Hugh Thornhill 
	 * @return This is the updateQuestionAcceptedAnswerId endpoint which updates the
	 * acceptedId to the answer that is deemed the most acceptable.
	 */
	@PutMapping
	@PreAuthorize("hasAuthority('user')")
	public Question updateQuestionAcceptedAnswerId(@RequestBody Question question) {
		return questionService.updateQuestionAcceptedAnswerId(question);
	}

	/** 
	 * @author Hugh Thornhill 
	 * @return This is the updateStatus endpoint which updates the question status and 
	 * awards 20 points to the user who answered the question.
	 */
	@PutMapping("/status")
	@PreAuthorize("hasAuthority('admin')")
	public Question updateStatus(@RequestBody Question question) {
		return questionService.updateQuestionStatus(question, 20);
	}
	
	/** @Author Natasha Poser 
	 * @return the is the GetQuestionById end-point. It retrieves a question by it's ID*/
	@GetMapping("/id/{id}")
	@PreAuthorize("hasAuthority('user')")
	public Question getQuestionById(@PathVariable int id) {
		return questionService.findById(id);
	}
	
		
}
