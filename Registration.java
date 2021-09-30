import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class Registration extends JFrame {

	private JPanel contentPane;
	private JTextField user_field;
	private JTextField phonenumber;
	private JTextField emailid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registration frame = new Registration();
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
	public Registration() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Bookman Old Style", Font.BOLD, 13));
		lblNewLabel.setBounds(63, 57, 121, 20);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("PhoneNumber");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Bookman Old Style", Font.BOLD, 13));
		lblNewLabel_2.setBounds(63, 102, 121, 18);
		contentPane.add(lblNewLabel_2);
		
		JButton submitbtn = new JButton("Submit");
		submitbtn.setForeground(Color.BLACK);
		submitbtn.setBackground(Color.WHITE);
		submitbtn.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
			
				try 
				{
					
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/crvote", "root", "");
					String sql = "insert  into  signup values(?,?,?)";
					PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
					pst.setString(1,user_field.getText());
					pst.setString(2,phonenumber.getText());
					pst.setString(3,emailid.getText());
					pst.executeUpdate();
					if(pst.executeUpdate()==0)
					{
						JOptionPane.showMessageDialog(null, "values not inserted ");
						user_field.setText("");
						
						//pass_field.setText("");
					}
					else
					{
						if(user_field.getText().trim().isEmpty() && phonenumber.getText().trim().isEmpty() && emailid.getText().trim().isEmpty())
						{
							JOptionPane.showMessageDialog(null, "fields are empty");
						}
						else if(user_field.getText().trim().isEmpty())
						{
							JOptionPane.showMessageDialog(null, "username field is empty");
						}
						else if(phonenumber.getText().trim().isEmpty())
						{
							JOptionPane.showMessageDialog(null, "phone number field is requried");
						}
						else if(emailid.getText().trim().isEmpty())
						{
							JOptionPane.showMessageDialog(null, "emailid field is requried");
						}
						else
						{
						JOptionPane.showMessageDialog(null, "Signup done sucessfully");
						Login lg =new Login();
						lg.setVisible(true);
						dispose();
						}
					}
					
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null, ex);
				}
				
			}
		});
		submitbtn.setFont(new Font("Bookman Old Style", Font.BOLD, 13));
		submitbtn.setBounds(127, 207, 89, 23);
		contentPane.add(submitbtn);
		
		user_field = new JTextField();
		user_field.setBounds(180, 58, 121, 20);
		contentPane.add(user_field);
		user_field.setColumns(10);
		
		phonenumber = new JTextField();
		phonenumber.setBounds(180, 102, 121, 20);
		contentPane.add(phonenumber);
		phonenumber.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Emailid");
		lblNewLabel_1.setFont(new Font("Bookman Old Style", Font.BOLD, 13));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(64, 142, 89, 18);
		contentPane.add(lblNewLabel_1);
		
		emailid = new JTextField();
		emailid.setBounds(180, 142, 121, 20);
		contentPane.add(emailid);
		emailid.setColumns(10);
	}
}
