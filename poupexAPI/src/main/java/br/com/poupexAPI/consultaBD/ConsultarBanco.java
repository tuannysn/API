package br.com.poupexAPI.consultaBD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.poupexAPI.core.Poupex_BD;

public class ConsultarBanco {
	
// 	De inicio estava consultando pelo primeiro CPF que aparecia na listagem do banco, porém foi melhor buscar o CPF retornado pelo Login	
//	public String consultaCPFBanco() throws SQLException {
//
//		Poupex_BD.realizaConexao();
//		Connection con = Poupex_BD.con;
//
//		String cpfProposta = null;
//
//
//		try {
//
//			Statement stmt = null;
//			stmt = con.createStatement();
//			ResultSet rs = stmt.executeQuery("select TOP 1 Cpf from dbo.Usuario");
//
//			if (rs.next()) {
//
//				cpfProposta = rs.getString(1);
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return cpfProposta;
//
//	}
	
	public String consultaCPFRetornarLogin(String cpfLogin2) throws SQLException {

		Poupex_BD.realizaConexao();
		Connection con = Poupex_BD.con;

		String cpfLogin = cpfLogin2;
		String email=null;


		try {

			Statement stmt = null;
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select Email from dbo.Usuario where Cpf='"+cpfLogin+"'");

			if (rs.next()) {

				email = rs.getString(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return email;

	}
	
	public void fecharConsulta () throws SQLException {
		Connection con = Poupex_BD.con;
		if(con != null) {
			con.close();
		}
	}

}
