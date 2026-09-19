package classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
		
	private String caminho = "jdbc:mysql://localhost/db_senhas";
	private String usuario = "root";
	private String senha = "@Gui313301313";
	
		public static Connection faz_Conexao() throws SQLException {
			
			try {
				
				Class.forName("com.mysql.jdbc.Driver");
				return DriverManager.getConnection("jdbc:mysql://localhost/db_senhas","root","@Gui313301313");
				
			} catch (ClassNotFoundException e) {
				
				throw new SQLException(e.getException());
			}
			
		}

}
