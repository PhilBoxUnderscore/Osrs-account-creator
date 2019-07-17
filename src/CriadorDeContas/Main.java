package CriadorDeContas;

import java.awt.Graphics;

import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;

@ScriptManifest(author = "henrique190", name = "Hrq190 Account Creator", version = 1.0, category = Category.UTILITY)
public class Main extends AbstractScript{
	Boolean contasCriadas = false;
	Gui gui = new Gui();
	//verificaProxy vp = new verificaProxy();
	int badproxy = 0;
	int i = 0;
	//String[] proxy = ProxyList.proxy1;
	//String[] port = ProxyList.port1;
	ProxyList proxyList;
	
	@Override
	public void onStart() {
		if(gui.isScriptRunning() == false) {
			gui.setVisible(true);
		}
	}

	@Override
	public int onLoop() {
		if(gui.isScriptRunning() == true) {
			gui.setVisible(false);
		}
		

		if(SendReq.getContasCriadas() < gui.getPegaQtd() ) {

				try {	
					
					verificaProxy vp = new verificaProxy();
					if(vp.testaConexao(proxyList.proxy.get(i), proxyList.port.get(i), gui.getProxySelected()) != "erro") {
						MethodProvider.log("Criando a conta.");
						SendReq red1 = new SendReq("03111991Hr", proxyList.proxy.get(i), proxyList.port.get(i));
						MethodProvider.log(proxyList.proxy.get(i) + " " + proxyList.port.get(i));
						red1.createAccount(gui.getApiCaptcha());
					}
					else {
						MethodProvider.log("Proxy com defeito.");
						badproxy ++;
					}
					
					
				
//				 if(gui.isCheckSelectProxies() == false) {
//				 log("Without using proxies, creating account"); SendReq4 red1 = new
//				 SendReq4("03111991Hr"); red1.createAccount(gui.getApiCaptcha()); }else {
//				 
//				 }
				
					
					

		
		 }catch (Exception e) { 
			 System.out.println("Erro!!!"+e); 
			 } 
				i++; 
				}
		else if(SendReq.getContasCriadas() == gui.getPegaQtd() &&
		 SendReq.getContasCriadas() != 0) { log("Accounts created, done");
		 }
		 

		

		

		// TODO Auto-generated method stub
		return 2000;
	}

	@Override
	public void onPaint(Graphics g) {
		// TODO Auto-generated method stub
		g.drawString("Accounts Created: "+SendReq.getContasCriadas(), 30, 240);
		g.drawString("Accounts failed : "+SendReq.getFalhas(), 30, 260);
		g.drawString("Bad Proxys : "+badproxy, 30, 280);
	}


}
