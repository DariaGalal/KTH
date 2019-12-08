package client;

import java.rmi.RemoteException;
import java.util.Scanner;
import model.Catalogue;

public class View {
	private Catalogue stub;
	private String username = "";
	private Scanner sc = new Scanner(System.in);
	private ResponseInterface obs;
	
	public View(Catalogue stub, String username, ResponseInterface obs) {
		this.stub = stub;
		this.obs = obs;
		this.username = username;
	}
	
	public void handle() throws RemoteException {
		System.out.println("Type 'List' to list all available files\nType 'Download' to download a file\nType 'Upload' to upload a file.");
	    	while(sc.hasNext()) {
	    		stub.getUpdates(obs);
	    		String input = sc.nextLine();
	    		if(input.equalsIgnoreCase("logout")) {
	    			stub.logout(username, obs);
	    			break;
	    		}
	    		if(input.equalsIgnoreCase("List")) {
	    			stub.getAllFiles(obs);
	    		}
	    		if(input.equalsIgnoreCase("Download")) {
	    			System.out.println("Enter the name of the file you want to download!");
	    			input = sc.nextLine();
	    			if(input.equalsIgnoreCase("cancel")) {
	    				System.out.println("Operation canceled!");
	    				continue;
	    			}
	    			try {
						stub.downloadFile(input, username, obs);
					} catch (RemoteException e) {
						System.out.println("There is no such file with that name");
						continue;
					}
	    		}
	    		if(input.equalsIgnoreCase("Upload")) {
	    			System.out.println("Enter a name for your file");
	    			String name = sc.nextLine();
	    			if(input.equalsIgnoreCase("cancel")) {
	    				System.out.println("Operation canceled!");
	    				continue;
	    			}
	    			stub.uploadFile(name, username, (int)(20.0 * Math.random()), obs);
	    		}
	    	}
	    }
}
