package upc.gmt.model;

public class Reporte {

	private int codigo;
	private String estado;
	private String fecha;
	private String observaciones;
	private int codigoAtencion;
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
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public int getCodigoAtencion() {
		return codigoAtencion;
	}
	public void setCodigoAtencion(int codigoAtencion) {
		this.codigoAtencion = codigoAtencion;
	}
	@Override
	public String toString() {
		return "Reporte [codigo=" + codigo + ", estado=" + estado + ", fecha=" + fecha + ", observaciones="
				+ observaciones + ", codigoAtencion=" + codigoAtencion + "]";
	}
	
}
