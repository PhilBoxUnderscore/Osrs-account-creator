package CriadorDeContas;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.AbstractButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;

public class Gui extends JFrame {
	
	/**
	 * 
	 */
	private boolean tickedProxies;
	private String proxyType;
	private static final long serialVersionUID = 1L;
	private boolean scriptRunning = false;
	public int pegaQtd = 0;
	private String apiCaptcha;
	private JPanel contentPane;
	private JTextField txtTwoCaptcha;
	private JTextField txtAccountsToMake;
	ProxyList proxyList = new ProxyList();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui frame = new Gui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Gui() {
		setTitle("Osrs account creator");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(5, 5, 459, 252);
		contentPane.add(panel);
		
		JLabel lblcaptchaApi = new JLabel("2Captcha api");
		lblcaptchaApi.setBounds(5, 9, 63, 14);
		
		txtTwoCaptcha = new JTextField();
		txtTwoCaptcha.setText("2e544d93615fa0b58a0029a7646749f1");
		txtTwoCaptcha.setBounds(103, 6, 86, 20);
		txtTwoCaptcha.setColumns(10);
		
		JLabel lblAccountsToMake = new JLabel("Accounts to make");
		lblAccountsToMake.setBounds(5, 34, 85, 14);
		
		txtAccountsToMake = new JTextField();
		txtAccountsToMake.setBounds(103, 37, 86, 20);
		txtAccountsToMake.setColumns(10);
		
		JButton btnNewButton = new JButton("Start Script");
		btnNewButton.setBounds(103, 118, 89, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pegaQtd = Integer.parseInt(txtAccountsToMake.getText());
				apiCaptcha = txtTwoCaptcha.getText();
				scriptRunning = true;

			}
		});
		panel.setLayout(null);
		panel.add(lblcaptchaApi);
		
		JLabel label = new JLabel("");
		label.setBounds(73, 16, 0, 0);
		panel.add(label);
		
		JLabel label_1 = new JLabel("");
		label_1.setBounds(78, 16, 0, 0);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("");
		label_2.setBounds(83, 16, 0, 0);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("");
		label_3.setBounds(88, 16, 0, 0);
		panel.add(label_3);
		panel.add(txtTwoCaptcha);
		panel.add(lblAccountsToMake);
		panel.add(txtAccountsToMake);
		panel.add(btnNewButton);
		
		JButton btnProxy = new JButton("Open Proxies");
		btnProxy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					proxyList.lerArquivo();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnProxy.setBounds(5, 118, 89, 23);
		panel.add(btnProxy);
		
		JComboBox comboBoxProxiesType = new JComboBox();
		comboBoxProxiesType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				proxyType = (String) comboBoxProxiesType.getSelectedItem();
			}
		});
		comboBoxProxiesType.setModel(new DefaultComboBoxModel(new String[] {"", "SOCKS5", "HTTP", "HTTPS"}));
		comboBoxProxiesType.setSelectedIndex(0);
		comboBoxProxiesType.setBounds(103, 72, 86, 20);
		panel.add(comboBoxProxiesType);
		
		JCheckBox chckbxUseProxies = new JCheckBox("Use proxies");
		chckbxUseProxies.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tickedProxies = chckbxUseProxies.isSelected();
			}
		});
		chckbxUseProxies.setBounds(-3, 71, 97, 23);
		panel.add(chckbxUseProxies);
	}

	public int getPegaQtd() {
		return pegaQtd;
	}

	public void setPegaQtd(int pegaQtd) {
		this.pegaQtd = pegaQtd;
	}

	public String getApiCaptcha() {
		return apiCaptcha;
	}

	public void setApiCaptcha(String apiCaptcha) {
		this.apiCaptcha = apiCaptcha;
	}

	public boolean isScriptRunning() {
		return scriptRunning;
	}

	public void setScriptRunning(boolean scriptRunning) {
		this.scriptRunning = scriptRunning;
	}

	public String getProxySelected() {
		return proxyType;
	}

	public void setProxySelected(String proxySelected) {
		this.proxyType = proxySelected;
	}

	public boolean isCheckSelectProxies() {
		return tickedProxies;
	}

	public void setCheckSelectProxies(boolean checkSelectProxies) {
		this.tickedProxies = checkSelectProxies;
	}
	
	
}
