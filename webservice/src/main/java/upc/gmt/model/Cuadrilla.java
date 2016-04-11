package upc.gmt.model;

public class Cuadrilla {

	private int codigo;
	private String descripcion;
	private String estado;
	private String codigoAtencion;
	
	@Override
	public String toString() {
		return "Cuadrilla [codigo=" + codigo + ", descripcion=" + descripcion + ", estado=" + estado + ", codigoAtencion="
				+ codigoAtencion + "]";
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

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getCodigoAtencion() {
		return codigoAtencion;
	}

	public void setCodigoAtencion(String codigoAtencion) {
		this.codigoAtencion = codigoAtencion;
	}
	
}
