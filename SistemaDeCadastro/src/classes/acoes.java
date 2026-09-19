package classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

	public class acoes {
		
		private int ID;
		private String Usuario;
		private String Senha;
		
		public acoes(int iD_p) {
			this.ID = iD_p;
		}
	
		public acoes(String us, String senha) {
			this.Senha = senha;
			this.Usuario = us;
		}
	
		public acoes(int iD_p, String us, String senha) {
			this.ID = iD_p;
			this.Usuario = us;
			this.Senha = senha;
		}
		
		//Início do método salvar
		public void salvar() {
			
			try {
				
				Connection con = Conexao.faz_Conexao();
				
				String sql = "Insert into dados_senhas(Usuario, Senha) values (?,?)";
				
				PreparedStatement stmt = con.prepareStatement(sql);
				
				stmt.setString(1, Usuario);
				stmt.setString(2, Senha);
				
				stmt.execute();
				
				stmt.close();
				con.close();
				
				JOptionPane.showMessageDialog(null, "Maravilha, mais um cadastro!");
							
				} catch (SQLException e1) {
				
					e1.printStackTrace();
				}
		}
		//Fim do método salvar
		
		//Início do método atualizar
		public void atualizar () {
			
			try {
				
				Connection con = Conexao.faz_Conexao();
				
				String sql = "update dados_senhas set Usuario=?, Senha=? where id=?";
				
				PreparedStatement stmt = con.prepareStatement(sql);
				
				stmt.setString(1, Usuario);
				stmt.setString(2, Senha);
				stmt.setInt(3, ID);
				
				stmt.execute();
				stmt.close();
				con.close();
				
				JOptionPane.showMessageDialog(null, "Cadastro alterado com sucesso!");
				
													
				} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				}
		
			}
			//Fim do método atualizar
		
		//Início do método excluir
		public void excluir() {
			
			try {
				
				Connection con = Conexao.faz_Conexao();
				
				String sql = "delete from dados_senhas where ID=? ";
						
				PreparedStatement stmt = con.prepareStatement(sql);
				
				stmt.setInt(1, ID);
				
				stmt.execute();
				
				stmt.close();
				con.close();
				
				JOptionPane.showMessageDialog(null, "Cadastro excluído com sucesso!");
							
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			
			
			
			}	
			//Fim do método excluir
		
		
		}
		
		


