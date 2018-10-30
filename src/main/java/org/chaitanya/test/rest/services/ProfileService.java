package org.chaitanya.test.rest.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.chaitanya.test.rest.databases.DatabaseClass;
import org.chaitanya.test.rest.models.Profile;

public class ProfileService {
	
	private Map<String, Profile> profilesMap = DatabaseClass.getProfiles();
	
	public ProfileService() {
		
		profilesMap.put("Chaitanya-Rayala", new Profile(1, "Chaitanya-Rayala", "Chaitanya", "Rayala"));
	}
	
	public Profile addProfile(Profile profile) {
		
		profile.setCreated(new Date());
		profile.setId(profilesMap.size()+1);
		System.out.println(profile.getProfileName());
		profilesMap.put(profile.getProfileName(), profile);
		return profile;
	}
	
	public Profile updateProfile(Profile profile) {
		
		if(profile.getId() <= 0) {
			return null;
		}
		if(profilesMap.keySet().contains(profile.getProfileName())) {
			profile.setCreated(new Date());
			profilesMap.put(profile.getProfileName(), profile);
			return profile;
		}else {
			return null;
		}
	}
	
	public List<Profile> getProfiles(){
		
		return new ArrayList<Profile>(profilesMap.values());
	}
	
	public Profile getProfile(String profileName) {
	
		if(profilesMap.keySet().contains(profileName)) {
			
			return profilesMap.get(profileName);
		}else {
			
			return null;
		}
	}
	
	public Profile removeProfile(String profileName) {
		
		if(profilesMap.keySet().contains(profileName)) {
			
			return profilesMap.remove(profileName);
		}else {
			return null;
		}
	}
}
