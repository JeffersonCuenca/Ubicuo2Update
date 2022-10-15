package pe.edu.upc.serviceinterface;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import pe.edu.upc.model.ReporteListaTServicioxAlerta;

import net.sf.jasperreports.engine.JRException;

public interface IReporteListaTServicioxAlertaService {
	
	ReporteListaTServicioxAlerta obtenerReporteListaTServicioxAlerta(Map<String, Object> params) throws JRException, IOException, SQLException;

}
