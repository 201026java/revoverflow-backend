package com.revature.entities;

import java.sql.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "answers")
public class Answer {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name = "user_id")
	private int userId;

	@Column(name = "question_id")
	private int questionId;

	@NotBlank(message = "Content must have a string value")
	private String content;

	// add the not null check in the service layer
	private Timestamp creationDate;


	private Timestamp editDate;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public int getQuestionId() {
		return questionId;
	}


	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public Timestamp getCreationDate() {
		return creationDate;
	}


	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}


	public Timestamp getEditDate() {
		return editDate;
	}


	public void setEditDate(Timestamp editDate) {
		this.editDate = editDate;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + ((editDate == null) ? 0 : editDate.hashCode());
		result = prime * result + id;
		result = prime * result + questionId;
		result = prime * result + userId;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Answer other = (Answer) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (creationDate == null) {
			if (other.creationDate != null)
				return false;
		} else if (!creationDate.equals(other.creationDate))
			return false;
		if (editDate == null) {
			if (other.editDate != null)
				return false;
		} else if (!editDate.equals(other.editDate))
			return false;
		if (id != other.id)
			return false;
		if (questionId != other.questionId)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Answer [id=" + id + ", userId=" + userId + ", questionId=" + questionId + ", content=" + content
				+ ", creationDate=" + creationDate + ", editDate=" + editDate + "]";
	}


	public Answer(int id, int userId, int questionId,
			@NotBlank(message = "Content must have a string value") String content, Timestamp creationDate,
			Timestamp editDate) {
		super();
		this.id = id;
		this.userId = userId;
		this.questionId = questionId;
		this.content = content;
		this.creationDate = creationDate;
		this.editDate = editDate;
	}


	public Answer() {
		super();
		// TODO Auto-generated constructor stub
	}


}
