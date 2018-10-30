package org.chaitanya.test.rest.databases;

import java.util.HashMap;
import java.util.Map;

import org.chaitanya.test.rest.models.Message;
import org.chaitanya.test.rest.models.Profile;

public class DatabaseClass {

	private static Map<Long, Message> messagesMap = new HashMap<Long, Message>();
	private static Map<String, Profile> profilesMap = new HashMap<String, Profile>();

	public static Map<Long, Message> getMessages() {
		return messagesMap;
	}

	public static Map<String, Profile> getProfiles() {
		return profilesMap;
	}
}