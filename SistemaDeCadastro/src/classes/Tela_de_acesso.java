package classes;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Window.Type;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Tela_de_acesso extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfUsuario;
	private JPasswordField tfSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela_de_acesso frame = new Tela_de_acesso();
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
	public Tela_de_acesso() {
		setResizable(false);
		setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		setTitle("Tela de Acesso ao Sistema");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 371, 224);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("Usuário");
		label.setForeground(new Color(0, 128, 0));
		label.setFont(new Font("Tahoma", Font.BOLD, 16));
		label.setBounds(14, 29, 62, 29);
		contentPane.add(label);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setForeground(new Color(0, 128, 0));
		lblSenha.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblSenha.setBounds(20, 86, 56, 29);
		contentPane.add(lblSenha);
		
		tfUsuario = new JTextField();
		tfUsuario.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tfUsuario.setBounds(80, 29, 246, 28);
		contentPane.add(tfUsuario);
		tfUsuario.setColumns(10);
		
		tfSenha = new JPasswordField();
		tfSenha.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tfSenha.setBounds(80, 86, 246, 29);
		contentPane.add(tfSenha);
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.setForeground(new Color(0, 128, 255));
		btnEntrar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Connection con = Conexao.faz_Conexao();
					
					String sql = "select *from dados_Senhas where Usuario=? and Senha=?";
					
					PreparedStatement stmt = con.prepareStatement(sql);
					
					stmt.setString(1, tfUsuario.getText());
					stmt.setString(2, new String (tfSenha.getPassword()));
					
					ResultSet rs = stmt.executeQuery();
					
					if (rs.next()) {
						
						Tela_de_opcoes exibir = new Tela_de_opcoes();
						exibir.setVisible(true);
						
						setVisible(false);
						
					}
						else {
							JOptionPane.showMessageDialog(null, "Usuário ou senha incorreta");
							
						}
					
					stmt.close();
					con.close();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btnEntrar.setBounds(136, 140, 92, 29);
		contentPane.add(btnEntrar);

	}
}
