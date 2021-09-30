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
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.Color;

public class Vote extends JFrame {

	private JPanel contentPane;
	private JTextField id;
	private JTextField namefield;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vote frame = new Vote();
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
	public Vote() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 579, 335);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Select your member:");
		lblNewLabel.setFont(new Font("Bookman Old Style", Font.BOLD, 20));
		lblNewLabel.setBounds(24, 11, 360, 38);
		contentPane.add(lblNewLabel);
		//ButtonGroup g =new ButtonGroup();
		
		
		JRadioButton boy1 = new JRadioButton("Dhoni");
		boy1.setBounds(32, 125, 109, 23);
		contentPane.add(boy1);
		
		JRadioButton boy2 = new JRadioButton("Raina");
		boy2.setBounds(32, 176, 109, 23);
		contentPane.add(boy2);
		
		JRadioButton boy3 = new JRadioButton("Rohit");
		boy3.setBounds(32, 150, 109, 23);
		contentPane.add(boy3);
		
		JRadioButton girl1 = new JRadioButton("RamaLakshmi");
		girl1.setBounds(428, 125, 109, 23);
		contentPane.add(girl1);
		
		JRadioButton girl2 = new JRadioButton("MahaLakshmi");
		girl2.setBounds(428, 150, 109, 23);
		contentPane.add(girl2);
		
		JRadioButton girl3 = new JRadioButton("Lakshmi");
		girl3.setBounds(428, 176, 109, 23);
		contentPane.add(girl3);
		
		
		
		JLabel boy = new JLabel("Boys");
		boy.setFont(new Font("Arial Black", Font.BOLD, 14));
		boy.setBounds(27, 97, 95, 21);
		contentPane.add(boy);
		
		JLabel girl = new JLabel("Girls");
		girl.setFont(new Font("Arial Black", Font.BOLD, 14));
		girl.setBounds(428, 97, 77, 21);
		contentPane.add(girl);
		
		ButtonGroup justboy =new ButtonGroup();
		justboy.add(boy1);
		justboy.add(boy2);
		justboy.add(boy3);
		ButtonGroup justgirl = new ButtonGroup();
		justgirl.add(girl1);
		justgirl.add(girl2);
		justgirl.add(girl3);
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					String boys=null,girls=null;
					Class.forName("com.mysql.jdbc.Driver");
					Connection c = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/crvote", "root", "");
					String sql = "insert  into  result values(?,?,?,?)";
					PreparedStatement pst = (PreparedStatement) c.prepareStatement(sql);
					pst.setString(1,id.getText());
					pst.setString(2,namefield.getText());
					if(boy1.isSelected())
					{
						boys="Dhoni";
					}
					if(boy2.isSelected())
					{
						boys="Raina";
					}
					if(boy3.isSelected())
					{
						boys="Rohit";
					}
					if(girl1.isSelected())
					{
						girls="RamaLakshmi";
					}
					if(girl2.isSelected())
					{
						girls="MahaLakshmi";
					}
					if(girl3.isSelected())
					{
						girls="Lakshmi";
					}
					pst.setString(3, boys);
					pst.setString(4, girls);
					pst.executeUpdate();
					if(pst.executeUpdate()==0)
					{
						JOptionPane.showMessageDialog(null, "vote not submitted");
					}
					else
					{
						if(id.getText().trim().isEmpty() && namefield.getText().trim().isEmpty())
						{
							JOptionPane.showMessageDialog(null, "fields are empty");
						}
						else if(id.getText().trim().isEmpty())
						{
							JOptionPane.showMessageDialog(null, "id field is empty");
						}
						else if(namefield.getText().trim().isEmpty())
						{
							JOptionPane.showMessageDialog(null, "name field is requried");
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Vote submitted sucessfully");
							dispose();
						}
						
					}
					
				}
				catch(Exception exp)
				{
					JOptionPane.showMessageDialog(null, exp);
				}
				
				
			}
		});
		btnNewButton.setFont(new Font("Bookman Old Style", Font.BOLD, 14));
		btnNewButton.setBounds(217, 197, 89, 23);
		contentPane.add(btnNewButton);

		JLabel roll = new JLabel("Enter your ID:");
		roll.setFont(new Font("Calibri", Font.BOLD, 15));
		roll.setBounds(24, 60, 94, 26);
		contentPane.add(roll);
		
		id = new JTextField();
		id.setBounds(116, 61, 155, 20);
		contentPane.add(id);
		id.setColumns(10);
		
		JLabel scroll_view = new JLabel("Enter Your  Id i.e roll number and Name before submiting  the  vote");
		scroll_view.setBounds(20, 231, 516, 26);
		contentPane.add(scroll_view);
		scroll_view.setForeground(Color.BLACK);
		scroll_view.setBackground(Color.BLACK);
		scroll_view.setFont(new Font("Verdana", Font.PLAIN, 15));
		
		JLabel lblNewLabel_2 = new JLabel("Enter Your Name:");
		lblNewLabel_2.setFont(new Font("Calibri", Font.BOLD, 15));
		lblNewLabel_2.setBounds(293, 60, 129, 26);
		contentPane.add(lblNewLabel_2);
		
		namefield = new JTextField();
		namefield.setBounds(409, 61, 144, 20);
		contentPane.add(namefield);
		namefield.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Select one member from boys and one member from girl");
		lblNewLabel_3.setFont(new Font("Verdana", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(20, 268, 504, 26);
		contentPane.add(lblNewLabel_3);
	}
}
