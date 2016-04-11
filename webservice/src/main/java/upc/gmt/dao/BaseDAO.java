package upc.gmt.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import upc.gmt.model.Atencion;
import upc.gmt.model.Cuadrilla;
import upc.gmt.model.Interrupcion;
import upc.gmt.model.Usuario;

public class BaseDAO implements InterrupcionDAO{
	
	String url = "jdbc:mysql://localhost:3306/interrupciones?autoReconnect=true&useSSL=false";
	String usuario = "root";
	String clave = "root";
	
	Connection connection = null;
	{
		try {
		    Class.forName("com.mysql.jdbc.Driver");
		    System.out.println("Driver loaded!");
		} catch (ClassNotFoundException e) {
		    throw new IllegalStateException("Cannot find the driver in the classpath!", e);
		}
	}
	public BaseDAO(){
		try {
			if(null==connection){
				connection = DriverManager.getConnection(url,usuario,clave);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Usuario validarUsuario(String usuarioLogin, String contraseña) {
		Usuario usuario = new Usuario();
		try {
			Statement stmt = connection.createStatement();
			String sql = " select codigo,nombre from usuario where usuario='"+usuarioLogin.toLowerCase()+
							"' and clave='"+contraseña.toLowerCase()+"'";
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()){
				usuario.setCodigo(rs.getString("codigo"));
				usuario.setNombre(rs.getString("nombre"));
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			usuario.setMensaje("Se produjo un error en la Base de Datos, comunicarse con el DBA.");
		}
		return usuario;
	}

	@Override
	public List<Cuadrilla> listarCuadrillas(String usuario, String estado) {
		List<Cuadrilla> lista = new ArrayList<Cuadrilla>();
		try {
			Statement stmt = connection.createStatement();
			String sql = "select * from cuadrilla where estado='"+estado+"'";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				Cuadrilla cua = new Cuadrilla();
				cua.setCodigo(rs.getInt("codigo"));
				cua.setDescripcion(rs.getString("descripcion"));
				cua.setEstado(rs.getString("estado"));				
				lista.add(cua);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public List<Interrupcion> listarInterrupciones(String usuario, String estado) {
		List<Interrupcion> lista = new ArrayList<Interrupcion>();
		try {
			Statement stmt = connection.createStatement();
			String sql = "select * from interrupcion where codigoUsuario='"+usuario+"'"+
							" and estado='"+estado+"'";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				Interrupcion inter = new Interrupcion();
				inter.setCodigo(rs.getInt("codigo"));
				inter.setDescripcion(rs.getString("descripcion"));
				inter.setEstado(rs.getString("estado"));
				inter.setFecha(rs.getString("fecha"));
				lista.add(inter);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public List<Atencion> listarOrdenesAtencion(String usuario, String estado) {
		List<Atencion> lista = new ArrayList<Atencion>();
		try {
			Statement stmt = connection.createStatement();
			String sql = "select * from atencion where codigoUsuario='"+usuario+"'"+
							" and estado='"+estado+"'";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				Atencion aten = new Atencion();
				aten.setCodigo(rs.getInt("codigo"));
				aten.setEstado(rs.getString("estado"));
				aten.setFecha(rs.getString("fecha"));
				aten.setDescripcion(rs.getString("descripcion"));
				aten.setComentario(rs.getString("comentario"));
				aten.setCodigoInterrupcion(rs.getInt("codigoInterrupcion"));
				aten.setCodigoCuadrilla(rs.getInt("codigoCuadrilla"));
				aten.setCodigoUsuario(rs.getInt("codigoUsuario"));
				lista.add(aten);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public boolean asignarCuadrilla(String codigoInterrupcion, String codigoCuadrilla) {
		boolean resultado=true;
		try {
			Statement stmt = connection.createStatement();
			String sql = "insert into atencion (descripcion,estado,comentario,fecha,codigoInterrupcion,codigoCuadrilla,codigoUsuario)" +
							" values ('OPERADORA','P','APLICACIÓN','"+new SimpleDateFormat("dd/MM/yyyy").format(new Date())+"'," +
							"'"+codigoInterrupcion+"','"+codigoCuadrilla+"','"+codigoCuadrilla+"')";
			int exito = stmt.executeUpdate(sql);
			if(exito==0){
				throw new Exception();
			}
			stmt.close();
		} catch (Exception e) {
			resultado=false;
		}
		return resultado;
	}

	@Override
	public boolean atenderOrden(String codigoOrden, String codigoInterrupcion, String codigoCuadrilla) {
		boolean resultado=true;
		try {
			Statement stmt = connection.createStatement();
			stmt.getConnection().setAutoCommit(false);
			String sql = "update atencion set estado='A' where codigo='"+codigoOrden+"'";// A = ACTIVO ; P = PENDIENTE
			int exito = stmt.executeUpdate(sql);
			if(exito==0){
				stmt.getConnection().rollback();
				throw new Exception();
			}
			
			String sql2 = "update interrupcion set estado='A' where codigo='"+codigoInterrupcion+"'";// A = ATENDIDO ; D = DISPONIBLE
			int exito2 = stmt.executeUpdate(sql2);
			if(exito2==0){
				stmt.getConnection().rollback();
				throw new Exception();
			}
			
			String sql3 = "update cuadrilla set estado='A' where codigo='"+codigoCuadrilla+"'";// A = ATENDIDO ; D = DISPONIBLE
			int exito3 = stmt.executeUpdate(sql3);
			if(exito3==0){
				stmt.getConnection().rollback();
				throw new Exception();
			}
			stmt.getConnection().commit();
			stmt.close();
		} catch (Exception e) {
			resultado=false;
		}
		return resultado;
	}

	@Override
	public List<Atencion> listarOrdenesAtencionAtendidas(String usuario) {
		return this.listarOrdenesAtencion(usuario, "A");// A = ATENDIDO
	}
	
	@Override
	public boolean enviarOrden(String codigoOrden, String codigoInterrupcion, String codigoCuadrilla, String observacion) {
		boolean resultado=true;
		try {
			Statement stmt = connection.createStatement();
			stmt.getConnection().setAutoCommit(false);
			String sql = "update atencion set estado='T',observacion='"+observacion+"' where codigo='"+codigoOrden+"'";// A = ACTIVO ; P = PENDIENTE ; T = TERMINADO
			int exito = stmt.executeUpdate(sql);
			if(exito==0){
				stmt.getConnection().rollback();
				throw new Exception();
			}
			
			String sql2 = "update interrupcion set estado='T' where codigo='"+codigoInterrupcion+"'";// A = ATENDIDO ; D = DISPONIBLE ; T = TERMINADO
			int exito2 = stmt.executeUpdate(sql2);
			if(exito2==0){
				stmt.getConnection().rollback();
				throw new Exception();
			}
			
			String sql3 = "update cuadrilla set estado='D' where codigo='"+codigoCuadrilla+"'";// A = ATENDIDO ; D = DISPONIBLE
			int exito3 = stmt.executeUpdate(sql3);
			if(exito3==0){
				stmt.getConnection().rollback();
				throw new Exception();
			}
			stmt.getConnection().commit();
			stmt.close();
		} catch (Exception e) {
			resultado=false;
		}
		return resultado;
	}	
	
}
