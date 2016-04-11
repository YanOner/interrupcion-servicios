package upc.gmt.model;

public class Atencion {

	private int codigo;
	private String descripcion;
	private String fecha;
	private String estado;
	private String comentario;
	private int codigoInterrupcion;
	private int codigoCuadrilla;
	private int codigoUsuario;
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public int getCodigoCuadrilla() {
		return codigoCuadrilla;
	}
	public void setCodigoCuadrilla(int codigoCuadrilla) {
		this.codigoCuadrilla = codigoCuadrilla;
	}
	public int getCodigoUsuario() {
		return codigoUsuario;
	}
	public void setCodigoUsuario(int codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public int getCodigoInterrupcion() {
		return codigoInterrupcion;
	}
	public void setCodigoInterrupcion(int codigoInterrupcion) {
		this.codigoInterrupcion = codigoInterrupcion;
	}
	@Override
	public String toString() {
		return "Atencion [codigo=" + codigo + ", descripcion=" + descripcion + ", fecha=" + fecha + ", estado=" + estado
				+ ", comentario=" + comentario + ", codigoInterrupcion=" + codigoInterrupcion + ", codigoCuadrilla="
				+ codigoCuadrilla + ", codigoUsuario=" + codigoUsuario + "]";
	}
	
}
