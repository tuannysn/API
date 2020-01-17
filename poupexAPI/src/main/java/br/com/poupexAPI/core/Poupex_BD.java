package br.com.poupexAPI.core;

import java.sql.*;

public class Poupex_BD {
	
	static String usuario = "USR_DESENV";
	static String senha = "161120170939";
	static String serverName = "MIBHIFR005H";
	static String port = "1433";
	static String database= "databaseName=DE_DB_ORIGINACAO_SEGURANCA";
	static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	
	static String url = "jdbc:sqlserver://" + serverName + ":" + port + ";"+ database;
	
	public static Connection con;
	
//	public static void main(String[] args) throws SQLException {
//		realizaConexao();
//	}

	public static void realizaConexao() throws SQLException {

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, usuario, senha);
			con.setAutoCommit(false);
			
			System.out.println("Conexão realizada com sucesso!");

		} catch (ClassNotFoundException e) {
			// Erro caso o driver JDBC não constar instalado
			System.out.println("O Driver JDBC não consta instalado");
			e.printStackTrace();

		} catch (SQLException e) {
			// Erro caso haja problemas para se conectar ao banco de dados
			e.printStackTrace();
			System.out.println("Erro caso haja problemas para se conectar ao banco de dados");
		}
	}
	
	public static void fechaConexao() {
		
		try {
			con.close();
			System.out.println("Fim de conexão realizada com sucesso!");
		}catch(Exception e) {
			System.out.println("Falha ao finalizar acesso ao banco");
		}
	}


}
