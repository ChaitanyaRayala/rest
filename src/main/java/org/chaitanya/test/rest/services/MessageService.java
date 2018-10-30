package org.chaitanya.test.rest.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.chaitanya.test.rest.databases.DatabaseClass;
import org.chaitanya.test.rest.exceptions.DataNotFoundException;
import org.chaitanya.test.rest.models.Message;

public class MessageService {

	private Map<Long, Message> messages = DatabaseClass.getMessages();
	
	public MessageService() {
		messages.put(1L, new Message(1, "Hello world!", "Chaitanya"));
		messages.put(2L,  new Message(2, "Hello Jersey!", "Chaitanya"));
	}
	
	public List<Message> getAllMessages(){
		
		return new ArrayList<Message>(messages.values());
	}
	
	public List<Message> getAllMessagesByYear(int year){
		
			List<Message> messagesOfYear = new ArrayList<Message>();
			Calendar cal = Calendar.getInstance();
			
			for(Message message:messages.values()) {
				cal.setTime(message.getCreated());
				if(cal.get(Calendar.YEAR) == year) {
					messagesOfYear.add(message);
				}
			}
			return messagesOfYear;
	}
	
	public List<Message> getAllMessagesByPagination(int startPoint, int size){
		
		List<Message> messagesPaginated = new ArrayList<Message>(messages.values());
		if(startPoint+size > messagesPaginated.size()) return new ArrayList<Message>();
		return messagesPaginated.subList(startPoint, startPoint+size);
	} 
	
	public Message getMessage(long id) {
		
		Message message = messages.get(id);
		if(message == null) {
			throw new DataNotFoundException("Message with id "+id+" not found");
		}
		return message;
	}
	
	public Message addMessage(Message msg) {
			
		msg.setId(messages.size()+1);
		msg.setCreated(new Date());
		messages.put(msg.getId(), msg);
		return msg;
	}
	
	public Message updateMessage(Message msg) {
		
		if(msg.getId() <= 0) {
			return null;
		}		
		msg.setCreated(new Date());
		messages.put(msg.getId(), msg);
		return msg;
	}
	
	public void removeMessage(long id) {
		
		messages.remove(id);
	}
}
