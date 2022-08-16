package com.psoft.scrumboard.repository.observer;

import java.util.Collection;
import java.util.HashSet;

import com.psoft.scrumboard.model.UserStory;
import com.psoft.scrumboard.model.event.UserStoryEvent;
import com.psoft.scrumboard.model.listener.UserStoryListener;

public class UserStorySource {
	
	private Collection<UserStoryListener> listeners = new HashSet<UserStoryListener>();
	
	public void mudouDescricao(Integer projectKey, UserStory userStory) {
		UserStoryEvent userStoryEvent = new UserStoryEvent(this, projectKey, userStory);
		this.disparaMudouDescricao(userStoryEvent);
	}
	
	public void mudouEstagio(Integer projectKey, UserStory userStory) {
		UserStoryEvent userStoryEvent = new UserStoryEvent(this, projectKey, userStory);
		this.disparaMudouEstagio(userStoryEvent);
	}
	
	public synchronized void addListener(UserStoryListener listener) {
		this.listeners.add(listener);
	}
	
	public synchronized void removeListener(UserStoryListener listener) {
		this.listeners.remove(listener);
	}
	
	private void disparaMudouDescricao(UserStoryEvent userStoryEvent) {
		Collection<UserStoryListener> listenersCopy;
		synchronized (this) {
			listenersCopy = (Collection) ((HashSet<UserStoryListener>) listeners).clone();
		}
		
		for (UserStoryListener userStoryListener : listenersCopy) {
			userStoryListener.mudouDescricao(userStoryEvent);
		}
		
	}
	
	private void disparaMudouEstagio(UserStoryEvent userStoryEvent) {
		Collection<UserStoryListener> listenersCopy;
		synchronized (this) {
			listenersCopy = (Collection) ((HashSet<UserStoryListener>) listeners).clone();
		}
		
		for (UserStoryListener userStoryListener : listenersCopy) {
			userStoryListener.mudouEstagio(userStoryEvent);
		}
		
	}

}
