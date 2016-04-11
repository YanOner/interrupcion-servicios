package upc.gmt.servicios;

import java.util.List;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mysql.jdbc.StringUtils;

import upc.gmt.dao.BaseDAO;
import upc.gmt.model.Atencion;
import upc.gmt.model.Cuadrilla;
import upc.gmt.model.Interrupcion;
import upc.gmt.model.Usuario;

@RestController
public class ServicioController {

	BaseDAO dao = new BaseDAO();
	
	@RequestMapping("/WValidarUsuario/{usuario}/{clave}")
    public String WValidarUsuario(@PathVariable String usuario,@PathVariable String clave) {
		System.out.println("WValidarUsuario");
		Usuario obj = dao.validarUsuario(usuario, clave);
		
		if(!StringUtils.isEmptyOrWhitespaceOnly(obj.getCodigo())){
			obj.setValido(true);
		}
		if(obj.getMensaje() == null){
			obj.setMensaje("Credenciales Incorrectas");
		}
		
		System.out.println(obj.toString());
		JSONObject json = new JSONObject();
		json.put("codigo", obj.getCodigo());
		json.put("nombre", obj.getNombre());
		json.put("valido", obj.getValido());
		json.put("mensaje", obj.getMensaje());
		System.out.println(json.toString());
		return json.toString();
    }
	
	@RequestMapping("/WInterrupcionLista/{usuario}/{estado}")
    public String WInterrupcionLista(
    		@PathVariable String usuario,
    		@PathVariable String estado) {
		System.out.println("WInterrupcionLista");
		List<Interrupcion> lista = dao.listarInterrupciones(usuario,estado);
		
		JSONObject json = new JSONObject();
		json.put("lista",lista);
		return json.getJSONArray("lista").toString();
    }
	
	@RequestMapping("/WOrdenAtencionLista/{usuario}/{estado}")
    public String WOrdenAtencionLista(
    		@PathVariable String usuario,
    		@PathVariable String estado) {
		System.out.println("WOrdenAtencionLista");
		List<Atencion> lista = dao.listarOrdenesAtencion(usuario,estado);
		
		JSONObject json = new JSONObject();
		json.put("lista", lista);
		return json.getJSONArray("lista").toString();
    }
	
	@RequestMapping("/WOrdenAtencionInterrupcion/{usuario}")
    public String WOrdenAtencionInterrupcion(
    		@PathVariable String usuario
    	) {
		System.out.println("WOrdenAtencionInterrupcion");
		List<Atencion> lista = dao.listarOrdenesAtencionAtendidas(usuario);
		
		JSONObject json = new JSONObject();
		json.put("lista", lista);
		return json.getJSONArray("lista").toString();
    }
	
	@RequestMapping("/WCuadrillaLista/{usuario}/{estado}")
    public String WCuadrillaLista(
    		@PathVariable String usuario,
    		@PathVariable String estado) {
		System.out.println("WCuadrillaLista");
		List<Cuadrilla> lista = dao.listarCuadrillas(usuario,estado);
		
		JSONObject json = new JSONObject();
		json.put("lista", lista);
		return json.getJSONArray("lista").toString();
    }
	
	@RequestMapping("/WAsignarCuadrilla/{codigoInterrupcion}/{codigoCuadrilla}")
    public String WAsignarCuadrilla(
    		@PathVariable String codigoInterrupcion,
    		@PathVariable String codigoCuadrilla) {
		System.out.println("WAsignarCuadrilla");
		boolean resultado = dao.asignarCuadrilla(codigoInterrupcion, codigoCuadrilla);		
		JSONObject json = new JSONObject();
		json.put("exito", resultado);
		return json.toString();
    }
	
	@RequestMapping("/WAtenderOrden/{codigoOrden}/{codigoInterrupcion}/{codigoCuadrilla}")
    public String WAtenderOrden(
    		@PathVariable String codigoOrden,
    		@PathVariable String codigoInterrupcion,
    		@PathVariable String codigoCuadrilla) {
		System.out.println("WAtenderOrden");
		boolean resultado = dao.atenderOrden(codigoOrden, codigoInterrupcion, codigoCuadrilla);		
		JSONObject json = new JSONObject();
		json.put("exito", resultado);
		return json.toString();
    }
	
	@RequestMapping("/WEnviarOrden/{codigoOrden}/{codigoInterrupcion}/{codigoCuadrilla}/{observacion}")
    public String WEnviarOrden(
    		@PathVariable String codigoOrden,
    		@PathVariable String codigoInterrupcion,
    		@PathVariable String codigoCuadrilla,
    		@PathVariable String observacion) {
		System.out.println("WAtenderOrden");
		boolean resultado = dao.enviarOrden(codigoOrden, codigoInterrupcion, codigoCuadrilla, observacion);		
		JSONObject json = new JSONObject();
		json.put("exito", resultado);
		return json.toString();
    }
}
