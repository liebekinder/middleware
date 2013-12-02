package fr.alma.middleware1314.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Tokens {
	
	/**
	 * Timeout in seconds
	 */
	protected static final int timeout = 10*60;
	/**
	 * Check time in seconds
	 */
	protected static final int checkTime = 60;

	protected static Map<String,UserBean> correlation = new HashMap<String,UserBean>();
	protected static Map<String,Long> timeRemaining = new HashMap<String,Long>();
	private static TokenCleaner cleaner = new TokenCleaner();
	
	
	public static String requestNewToken(UserBean user) {
		if(correlation.size()==0) launchCleaner();
		UUID uuid = UUID.randomUUID();
		String token = uuid.toString();
		correlation.put(token, user);
		timeRemaining.put(token, new Date().getTime());
		return token;
	}

	private static void launchCleaner() {
		Executors.newScheduledThreadPool(1).scheduleAtFixedRate(cleaner, 0, timeout, TimeUnit.SECONDS);
	}

	public static UserBean getUserFromToken(String token) {
		if(correlation.containsKey(token)) return correlation.get(token);
		return null;
	}

	private static class TokenCleaner implements Runnable {

		@Override
		public void run() {
			List<String> markedForRemove = new ArrayList<String>();
			for(String token : timeRemaining.keySet()) {
				if(timeRemaining.get(token)+timeout*1000>new Date().getTime()) markedForRemove.add(token);
			}
			for(String token : markedForRemove) {
				timeRemaining.remove(token);
				correlation.remove(token);
			}
		}
		
	}
}
