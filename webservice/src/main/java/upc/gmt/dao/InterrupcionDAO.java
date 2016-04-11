package upc.gmt.dao;

import java.util.List;

import upc.gmt.model.Atencion;
import upc.gmt.model.Cuadrilla;
import upc.gmt.model.Interrupcion;
import upc.gmt.model.Usuario;

public interface InterrupcionDAO {
	
	Usuario validarUsuario(String usuario, String contraseña);
	List<Cuadrilla> listarCuadrillas(String usuario, String estado);
	List<Interrupcion> listarInterrupciones(String usuario, String estado);
	List<Atencion> listarOrdenesAtencion(String usuario, String estado);
	List<Atencion> listarOrdenesAtencionAtendidas(String usuario);
	boolean asignarCuadrilla(String codigoInterrupcion, String codigoCuadrilla);
	boolean atenderOrden(String codigoOrden, String codigoInterrupcion, String codigoCuadrilla);
	boolean enviarOrden(String codigoOrden, String codigoInterrupcion, String codigoCuadrilla, String observacion);
	
}
