package projectChatRMI.id1212.model;

import java.util.concurrent.TimeUnit;

public class ChatFormat {
	private TimeUnit time;
	private final String COLUMNS = "%-10s %-10s %-10s\n";
	private final String BIG_LINE = "-----------------------------------\n";
	private final String SMALL_LINE = "-----------------\n";
	private String userOutput;
	private String helpPrint;
	private String onlineUsers;
	private String welcomePrint;
	
	public ChatFormat() {
		this.helpPrint = BIG_LINE+""
				+ "/HELP - Displays commands\n"
				+ "/QUIT - Logout and quit\n"
				+ "/ONLINE - Display online users\n"
				+ "/ADD username - Add a user to your friend list\n"
				+ "/W or /WHSIPER - whisper to an online user"+BIG_LINE;
		this.welcomePrint = BIG_LINE+"WELCOME TO CHATROOM 1212";
	}
	
	public String outputFormat(String output) {
		userOutput = "["+time.toHours(System.currentTimeMillis())+":"+time.toMinutes(System.currentTimeMillis())+"]"+output;
		return userOutput;
		
	}
	
	public String getHelp() {
		return helpPrint;
	}
	
	public String getWelcome() {
		return welcomePrint;
	}
	
	public String getOnlineUsers() {
		return null;
	}

}
