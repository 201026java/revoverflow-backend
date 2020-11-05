package com.revature.messaging;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.revature.repositories.UserRepository;

@Service
public class MessageService {
	
	private static Set<Integer> eventCache = new HashSet<>();
	
	@Autowired
	private UserRepository userDao;

	@Autowired
	private KafkaTemplate<String, MessageEvent> kt;
	
	public void triggerFlashcardEvent(MessageEvent event) {
		eventCache.add(event.hashCode());
		
		if(event.getOperation() == Operation.DELETE) {
			kt.send("quiz-flashcard", event);
		}
		
		kt.send("flashcard", event);
	}
	
	@KafkaListener(topics = "answers")
	public void processMessageEvent(MessageEvent event) {
		if(eventCache.contains(event.hashCode())) {
			eventCache.remove(event.hashCode());
			return;
		}
		
		//Flashcard card = event.getFlashcard();
		
		switch(event.getOperation()) {
		case CREATE:
			//this.userDao.save(card);
			break;
		case UPDATE:
			//this.userDao.save(card);
			break;
		case DELETE:
			//this.userDao.deleteById(card.getId());
			break;
		}
	}
}
