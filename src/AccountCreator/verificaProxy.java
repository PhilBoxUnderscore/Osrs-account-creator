package AccountCreator;

import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.dreambot.api.methods.MethodProvider;

import com.esotericsoftware.minlog.Log;


public class verificaProxy {

	
private String status;
private String proxyip;
private String port;
private String state;
Proxy proxy = null;


	public String testaConexao(String proxyip, String port,String state) throws MalformedURLException{
		this.proxyip = proxyip;
		this.port = port;
		this.state = state;
		
		
		switch (getState()) {
		case "SOCKS5":
			proxy = new Proxy(Proxy.Type.SOCKS, new InetSocketAddress(proxyip,Integer.parseInt(port)));
			executarProxy(proxy);
			break;
		case "HTTPS":
			proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyip,Integer.parseInt(port)));
			executarProxy(proxy);
			break;
		case "HTTP":
			proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyip,Integer.parseInt(port)));
			executarProxy(proxy);
			break;
		default:
			proxy = null;
			executarProxy();
			break;
		}
				
		
		return status;

	}

	
	public String executarProxy(Proxy proxy) throws MalformedURLException {
		URL obj = new URL("https://secure.runescape.com/m=account-creation/g=oldscape/create_account");
		
		try {
			HttpsURLConnection con = (HttpsURLConnection)obj.openConnection(proxy);
			String USER_AGENT = RandomUserAgent.getRandomUserAgent();
			con.setRequestMethod("POST");
			con.setRequestProperty("Host", "secure.runescape.com");
			con.setRequestProperty("User-Agent",USER_AGENT);
			con.setRequestProperty("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
			con.setRequestProperty("Accept-Language", "en-US,en);q=0.5");
			con.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
			con.setRequestProperty("Referer","http://oldschool.runescape.com/");
			status = ""+con.getResponseCode();
			con.disconnect();
			return status;
		} catch (Exception e) {
			status = "erro";
			
		}
		return status;
	}
	
	public String executarProxy() throws MalformedURLException {
		URL obj = new URL("https://secure.runescape.com/m=account-creation/g=oldscape/create_account");
		
		try {
			HttpsURLConnection con = (HttpsURLConnection)obj.openConnection();
			String USER_AGENT = RandomUserAgent.getRandomUserAgent();
			con.setRequestMethod("POST");
			con.setRequestProperty("Host", "secure.runescape.com");
			con.setRequestProperty("User-Agent",USER_AGENT);
			con.setRequestProperty("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
			con.setRequestProperty("Accept-Language", "en-US,en);q=0.5");
			con.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
			con.setRequestProperty("Referer","http://oldschool.runescape.com/");
			status = ""+con.getResponseCode();
			con.disconnect();
			return status;
		} catch (Exception e) {
			status = "erro";
			
		}
		return status;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}
	
	
	
	
}
