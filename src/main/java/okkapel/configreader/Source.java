package okkapel.configreader;

public class Source {
	private String location;
	private boolean isRemote; //Is this source on local disk or on a remote server
	
	public Source(String location, boolean remote) {
		this.location = location;
		this.isRemote = remote;
	}
	
	public String getLocation() {
		return this.location;
	}
	
	public boolean isRemote() {
		return this.isRemote;
	}
	
	public boolean isHTTP() {
		return this.isRemote && location.startsWith("http://");
	}
	
	public boolean isHTTPS() {
		return this.isRemote && location.startsWith("https://");
	}
}
