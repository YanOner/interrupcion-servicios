package upc.gmt.model;

public class Interrupcion {

	private int codigo;
	private String descripcion;
	private String estado;
	private String fecha;
	private String codigoUsuario;
	
	public Interrupcion(int codigo, String descripcion, String estado, String fecha, String codigoUsuario) {
		super();
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.estado = estado;
		this.fecha = fecha;
		this.codigoUsuario = codigoUsuario;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigoInterripcion) {
		codigo = codigoInterripcion;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
	@Override
	public String toString() {
		return "Interrupcion [CodigoInterripcion=" + codigo + ", Descripcion=" + descripcion + ", Estado="
				+ estado + ", Fecha=" + fecha + ", CodigoUsuario=" + codigoUsuario + "]";
	}
	public Interrupcion() {
		super();
	}
	
}
