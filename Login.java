import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.Driver;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Random;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField phonenumber;
	private JTextField otpfield;
	public int otp=0;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					Login frame = new Login();
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
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setFont(new Font("Bookman Old Style", Font.BOLD, 13));
		lblNewLabel.setBounds(42, 65, 97, 16);
		contentPane.add(lblNewLabel);
		
		JTextField userfield = new JTextField();
		userfield.setBounds(164, 64, 114, 20);
		contentPane.add(userfield);
		userfield.setColumns(10);
		
		
		JButton otpbtn = new JButton("Get OTP");
		otpbtn.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				
				
			try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn =  (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/crvote","root", "");
					String sql = "select * from signup where username=? and password=?";
					PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql);
					pst.setString(1,userfield.getText());
					pst.setString(2,passfield.getText());
					ResultSet rs = pst.executeQuery();
					if(rs.next())
					{
						JOptionPane.showMessageDialog(null, "Username and password are correct");
						if(userfield.getText().trim().isEmpty() && passfield.getText().trim().isEmpty())
						{
							JOptionPane.showMessageDialog(null, "fields are empty");
						}
						else if(userfield.getText().trim().isEmpty())
						{
							JOptionPane.showMessageDialog(null, "username field is empty");
						}
						else if(passfield.getText().trim().isEmpty())
						{
							JOptionPane.showMessageDialog(null, "password field is requried");
						}
						else
						{
							Vote vobj = new Vote();
							vobj.setVisible(true);
							dispose();
						}
						
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Username or password are does not exist");
						userfield.setText("");
						passfield.setText("");
					}
				}
				catch(Exception ex)
				{
					
				}
				
		/*
				try {
					// Construct data
					String apiKey = "apikey=" + "TlVv+YraM5U-RUg9QQnvxIxw3JPd24OoqrpPI6H822";
					Random rad = new Random();
					otp = rad.nextInt(9999);
					String name = userfield.getText();
					String message = "&message=" + "Hello" + name + " your OTP is" + otp;
					String sender = "&sender=" + "CRVOTE";
					String numbers = "&numbers=" + phonenumber.getText();
					
					// Send data
					HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
					String data = apiKey + numbers + message + sender;
					conn.setDoOutput(true);
					conn.setRequestMethod("POST");
					conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
					conn.getOutputStream().write(data.getBytes("UTF-8"));
					final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
					final StringBuffer stringBuffer = new StringBuffer();
					String line;
					while ((line = rd.readLine()) != null) {
						stringBuffer.append(line);
					}
					rd.close();
					JOptionPane.showConfirmDialog(null, "OTP sent sucessfully");
					
					//return stringBuffer.toString();
					
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Error SMS");
					JOptionPane.showMessageDialog(null, "");
					//return "Error "+e;
				}
			}
		});
		otpbtn.setFont(new Font("Bookman Old Style", Font.BOLD, 14));
		otpbtn.setBounds(113, 139, 104, 23);
		contentPane.add(otpbtn);
		
		JLabel lblNewLabel_1 = new JLabel("PhoneNumber");
		lblNewLabel_1.setFont(new Font("Bookman Old Style", Font.BOLD, 13));
		lblNewLabel_1.setBounds(42, 92, 126, 26);
		contentPane.add(lblNewLabel_1);
		
		phonenumber = new JTextField();
		phonenumber.setBounds(164, 95, 114, 20);
		contentPane.add(phonenumber);
		phonenumber.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Enter OTP");
		lblNewLabel_2.setFont(new Font("Bookman Old Style", Font.BOLD, 13));
		lblNewLabel_2.setBounds(42, 176, 97, 17);
		contentPane.add(lblNewLabel_2);
		
		otpfield = new JTextField();
		otpfield.setBounds(164, 173, 114, 20);
		contentPane.add(otpfield);
		otpfield.setColumns(10);
		*/
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Integer.parseInt(otpfield.getText())==otp)
				{
					JOptionPane.showMessageDialog(null, "Login sucessfully");
					Vote vobj = new Vote();
					vobj.setVisible(true);
					dispose();
				}
				else
				{
					JOptionPane.showMessageDialog(null,"login failed");
				}
			}
		});
		btnNewButton.setFont(new Font("Bookman Old Style", Font.BOLD, 13));
		btnNewButton.setBounds(113, 213, 89, 23);
		contentPane.add(btnNewButton);
	}
}
