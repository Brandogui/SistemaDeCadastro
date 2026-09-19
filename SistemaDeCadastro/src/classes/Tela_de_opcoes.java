package classes;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Tela_de_opcoes extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfID;
	private JTextField tfUsuario;
	private JTextField tfSenha;
	private JTextField tfBusca;
	private JTable tbDados;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela_de_opcoes frame = new Tela_de_opcoes();
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
	public Tela_de_opcoes() {
		setResizable(false);
		setTitle("Tela de Opções");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 456, 399);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(22, 10, 28, 12);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Usuário");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(10, 32, 44, 20);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Senha");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1_1.setBounds(10, 62, 52, 20);
		contentPane.add(lblNewLabel_1_1);
		
		tfID = new JTextField();
		tfID.setEditable(false);
		tfID.setBounds(60, 8, 117, 20);
		contentPane.add(tfID);
		tfID.setColumns(10);
		
		tfUsuario = new JTextField();
		tfUsuario.setColumns(10);
		tfUsuario.setBounds(59, 34, 201, 20);
		contentPane.add(tfUsuario);
		
		tfSenha = new JTextField();
		tfSenha.setColumns(10);
		tfSenha.setBounds(59, 62, 201, 20);
		contentPane.add(tfSenha);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("ToolTip.border"), "A\u00E7\u00F5es", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(10, 196, 416, 72);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Salvar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (tfUsuario.getText().equals("") || tfSenha.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe um usuário e uma senha");
				}
					else {
				
						acoes ac = new acoes(tfUsuario.getText(), tfSenha.getText());
						ac.salvar();
								
						tfUsuario.setText("");
						tfSenha.setText("");
										
					}					
				}
		});
		btnNewButton.setBounds(21, 20, 84, 20);
		panel.add(btnNewButton);
		
		JButton btnNewButton_3 = new JButton("Atualizar");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(tfID.getText().equals("")) {
					JOptionPane.showMessageDialog(null,	"Digite um valor de ID");
					
				} else {
					
					acoes ac = new acoes(Integer.parseInt(tfID.getText()), tfUsuario.getText(), tfSenha.getText());
					
					ac.atualizar();
					
					tfUsuario.setText("");
					tfSenha.setText("");
					tfID.setText("");
				
				}
			}
		});
		btnNewButton_3.setBounds(155, 20, 84, 20);
		panel.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Excluir");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(tfID.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Entre com um ID válido");
					
				} else {
				
					acoes ac = new acoes (Integer.parseInt(tfID.getText()));
					
					ac.excluir();
					
					tfID.setText("");
					tfUsuario.setText("");
					tfSenha.setText("");
					
				}
				
			}
		});
		btnNewButton_4.setBounds(295, 20, 84, 20);
		panel.add(btnNewButton_4);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Abrir dados", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBackground(new Color(181, 217, 179));
		panel_1.setBounds(10, 289, 416, 63);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnNewButton_1 = new JButton("Abrir");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (tfBusca.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe o ID");
				}
				else {
				
					try {
					
						Connection con = Conexao.faz_Conexao();
					
						String sql = "select *from dados_senhas where id like ?";
					
						PreparedStatement stmt = con.prepareStatement(sql);
					
						stmt.setString(1, "%" + tfBusca.getText());
									
						ResultSet rs = stmt.executeQuery();
					
						while (rs.next()) {
							
							tfID.setText(rs.getString("ID"));
							tfUsuario.setText(rs.getString("Usuario"));
							tfSenha.setText(rs.getString("Senha"));
						}
					
						rs.close();
						con.close();
										
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}	
				
				}
			}
		});
		btnNewButton_1.setBounds(10, 20, 75, 21);
		panel_1.add(btnNewButton_1);
		
		tfBusca = new JTextField();
		tfBusca.setBounds(90, 21, 193, 18);
		panel_1.add(tfBusca);
		tfBusca.setColumns(10);
		
		JButton btnNewButton_2 = new JButton("listar dados");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					Connection con = Conexao.faz_Conexao();
					
					String sql = "Select *from dados_senhas";
					
					PreparedStatement stmt = con.prepareStatement(sql);
					
					ResultSet rs = stmt.executeQuery();
					
					DefaultTableModel modelo = (DefaultTableModel) tbDados.getModel();
					
					modelo.setNumRows(0);
					
					while (rs.next()) {
						
						modelo.addRow(new Object [] {rs.getString("ID"), rs.getString("Usuario"), rs.getString("Senha")});
						
					}
					
					rs.close();
					con.close();
					
					
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnNewButton_2.setBounds(307, 20, 99, 21);
		panel_1.add(btnNewButton_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 101, 416, 85);
		contentPane.add(scrollPane);
		
		tbDados = new JTable();
		tbDados.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
			},
			new String[] {
				"ID", "Usuario", "Senha"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tbDados.getColumnModel().getColumn(0).setResizable(false);
		tbDados.getColumnModel().getColumn(1).setResizable(false);
		tbDados.getColumnModel().getColumn(2).setResizable(false);
		scrollPane.setViewportView(tbDados);

	}
}
