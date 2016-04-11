package upc.gmt.model;

public class DetalleAtencion {

	private int codigo;
	private String estado;
	private String fecha;
	private String codigoUsuario;
	private String codigoInterrupcion;
	private String codigoCuadrilla;
	@Override
	public String toString() {
		return "DetalleAtencion [codigo=" + codigo + ", estado=" + estado + ", fecha=" + fecha + ", codigoUsuario="
				+ codigoUsuario + ", codigoInterrupcion=" + codigoInterrupcion + ", codigoCuadrilla=" + codigoCuadrilla
				+ "]";
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getCodigoUsuario() {
		return codigoUsuario;
	}
	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}
	public String getCodigoInterrupcion() {
		return codigoInterrupcion;
	}
	public void setCodigoInterrupcion(String codigoInterrupcion) {
		this.codigoInterrupcion = codigoInterrupcion;
	}
	public String getCodigoCuadrilla() {
		return codigoCuadrilla;
	}
	public void setCodigoCuadrilla(String codigoCuadrilla) {
		this.codigoCuadrilla = codigoCuadrilla;
	}
	
}
