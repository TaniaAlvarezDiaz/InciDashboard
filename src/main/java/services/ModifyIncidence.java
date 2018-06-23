package services;

import model.Incidence;
import model.Incidence.Estado;

public interface ModifyIncidence {

	public void modificarEstado(Long id, Estado state);
	public void modificarComentarios(Long id, String comments);
	public void modificarIncidencia(Incidence i);
}
