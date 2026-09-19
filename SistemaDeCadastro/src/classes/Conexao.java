package classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
		
	private String caminho = "jdbc:mysql://localhost/db_senhas";
	private String usuario = "root";
	
	//Caso você queira testar esse código terá que criar uma tabela no MySQL, lá você terá um usuário root
	//Nele você poderá ou não colocar uma senha, caso opte por colocar, digite-a no campo (suaSenha entre aspas)
	//Caso deixe a senha em branco use aspas SEM ESPAÇO, caso contrário ele interpreta que sua senha é espaco.
	private String senha = "suaSenha";
	
		public static Connection faz_Conexao() throws SQLException {
			
			try {
				//Caso você queira testar esse código terá que criar uma tabela no MySQL, lá você terá um usuário root
				//Nele você poderá ou não colocar uma senha, caso opte por colocar, digite-a no campo (suaSenha entre aspas)
				//Caso deixe a senha em branco use aspas SEM ESPAÇO, caso contrário ele interpreta que sua senha é espaco.
				Class.forName("com.mysql.jdbc.Driver");
				return DriverManager.getConnection("jdbc:mysql://localhost/db_senhas","root","suaSenha");
				
			} catch (ClassNotFoundException e) {
				
				throw new SQLException(e.getException());
			}
			
		}

}
