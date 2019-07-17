package CriadorDeContas;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;


import javax.net.ssl.HttpsURLConnection;
import javax.swing.JOptionPane;

import org.dreambot.api.methods.MethodProvider;

import CaptchaApi.TwoCaptchaService;



public class SendReq {
    VerificationCode code = new VerificationCode(8);
	
	//private int day, month, year;
	private String email;
	private String password;
    private String proxyN;
    private String port;
    public static int contasCriadas;
    public static int falhas;
	public static String pegaTextDoGui;
    Gui gui = new Gui();
        

	public SendReq(String password, String proxyN, String port){   //this.proxy = proxy; // String proxyN, int port
		this.password = password;
		this.proxyN = proxyN;
        this.port = port;
	}
	
	public SendReq(String password){   //this.proxy = proxy; // String proxyN, int port
		this.password = password;
	}
	
	
	private String getRecaptcha(String apiKey){
		//String googleKey = "6LccFA0TAAAAAHEwUJx_c1TfTBWMTAOIphwTtd1b"; ////6Lcsv3oUAAAAAGFhlKrkRb029OHio098bbeyi_Hv
		String googleKey = "6Lcsv3oUAAAAAGFhlKrkRb029OHio098bbeyi_Hv"; ////
		String pageUrl = "https://secure.runescape.com/m=account-creation/g=oldscape/create_account?trialactive=true";
		TwoCaptchaService service = new TwoCaptchaService(apiKey, googleKey, pageUrl);
		
		try {
			String responseToken = service.solveCaptcha();
			MethodProvider.log("The response token is: " + responseToken);
			if (!responseToken.contains("ERROR")){
			return responseToken;
			}
		} catch (InterruptedException e) {
			MethodProvider.log("Error grabbing key:");
			e.printStackTrace();
		} catch (IOException e) {
			MethodProvider.log("Error grabbing key:");
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean createAccount(String apiKey) throws IOException, InterruptedException {
  		String proxyN = this.proxyN;
        String port = this.port;
		String email = code.toString().toLowerCase()+"@gmail.com";
		String password = this.password;
		String recaptchaResponse = getRecaptcha(apiKey);
		String params =
				"theme="+"oldschool"
				+"&trialactive="+"true"
				+"&email1="+email
				+"&onlyOneEmail="+"1"
				+"&password1="+password
				+"&onlyOnePassword"+"1"
				+"&day="+"1"
				+"&month="+"1"
				+"&year="+"1991"
				+"&create-submit=create"
				+"&g-recaptcha-response="+recaptchaResponse;
				
		MethodProvider.log(recaptchaResponse);
		MethodProvider.log(proxyN);
		MethodProvider.log(""+port);
		
		
		
		Proxy proxy = null;
		switch (gui.getProxySelected()) {
		case "SOCKS5":
			proxy = new Proxy(Proxy.Type.SOCKS, new InetSocketAddress(proxyN,Integer.parseInt(port)));
			break;
		case "HTTPS":
			proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyN,Integer.parseInt(port)));
			break;
		case "HTTP":
			proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyN,Integer.parseInt(port)));
			break;
		default:
			proxy = null;
			break;
		}
		
		
        //Proxy proxy = new Proxy(Proxy.Type.SOCKS, new InetSocketAddress(proxyN,port));
		URL obj = new URL("https://secure.runescape.com/m=account-creation/g=oldscape/create_account");
		
		HttpsURLConnection con;
		if(gui.isCheckSelectProxies() == false) {
			con = (HttpsURLConnection)obj.openConnection();
		}else {
			con = (HttpsURLConnection)obj.openConnection(proxy);
		}
		
		String USER_AGENT = RandomUserAgent.getRandomUserAgent();
		MethodProvider.log("User agent: " + USER_AGENT);
		//add request header
		con.setRequestMethod("POST");
		con.setRequestProperty("Host", "secure.runescape.com");
		con.setRequestProperty("User-Agent",USER_AGENT);
		con.setRequestProperty("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		con.setRequestProperty("Accept-Language", "en-US,en);q=0.5");
		con.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
		con.setRequestProperty("Referer","http://oldschool.runescape.com/");
		//Send post request
		if ( recaptchaResponse != null && !recaptchaResponse.contains("ERROR")){
		MethodProvider.sleep(8000, 10000);
		//Thread.currentThread();
		//Thread.sleep(10000);
		con.setDoOutput(true);
        con.setDoInput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		MethodProvider.log(params);
		wr.writeBytes(params);
		wr.flush();
		wr.close();

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		
		while((inputLine = in.readLine()) != null){
			response.append(inputLine);
		}
		in.close();
                
               String location = con.getURL().toString();
               
              
                    
                if(location.contains("tracker")){
                	
                    MethodProvider.log("Account Created.");
                    setContasCriadas(getContasCriadas() + 1);
                    MethodProvider.log(""+getContasCriadas());
                    
                    String diretorio = System.getProperty("user.dir");
                    File file = new File(diretorio,"accs.txt");
                    if (!file.exists()){
                    	file.createNewFile();
                    }
                    try(FileWriter fw = new FileWriter(file, true);
                    		BufferedWriter bw = new BufferedWriter(fw);
                    		PrintWriter out = new PrintWriter(bw))
                    {
                    		out.println(email + ":" + password);
                    } catch (IOException e) {
                    	MethodProvider.log("Erro escreveer o arquivo");
                    }
                }else{
                    
                    MethodProvider.log("Failed Create Account.");
                    setFalhas(getFalhas() + 1);
                }
                
		
		}else
			MethodProvider.log("Captcha was error or null, not sending post to osrs servers");
			return true;
	}
	

	public String getEmail(){
		return email;
	}
	
	public String getPassword(){
		return password;
	}

         public String getProxyN() {
        return proxyN;
    }

    public void setProxyN(String proxyN) {
        this.proxyN = proxyN;
    }


    public static int getFalhas() {
		return falhas;
	}



	public static void setFalhas(int falhas) {
		SendReq.falhas = falhas;
	}



	public static int getContasCriadas() {
		return contasCriadas;
	}



	public static void setContasCriadas(int contasCriadas) {
		SendReq.contasCriadas = contasCriadas;
	}



	public static String getPegaTextDoGui() {
		return pegaTextDoGui;
	}



	public static void setPegaTextDoGui(String pegaTextDoGui) {
		SendReq.pegaTextDoGui = pegaTextDoGui;
	}


    
    
    void createAccount(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
}