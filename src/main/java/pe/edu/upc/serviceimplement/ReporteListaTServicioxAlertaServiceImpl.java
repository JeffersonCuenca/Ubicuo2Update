package pe.edu.upc.serviceimplement;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JRException;
import pe.edu.upc.commons.JasperReportManager;
import pe.edu.upc.enums.TipoReporteEnum;
import pe.edu.upc.serviceinterface.IReporteListaTServicioxAlertaService;
import pe.edu.upc.model.ReporteListaTServicioxAlerta;

@Service
public class ReporteListaTServicioxAlertaServiceImpl implements IReporteListaTServicioxAlertaService {

	@Autowired
	private JasperReportManager reportManager;

	@Autowired
	private DataSource dataSource;

	@Override
	public ReporteListaTServicioxAlerta obtenerReporteListaTServicioxAlerta(Map<String, Object> params)
			throws JRException, IOException, SQLException {
		// TODO Auto-generated method stub
		String fileName = "ReportePDF";
		ReporteListaTServicioxAlerta dto = new ReporteListaTServicioxAlerta();
		String extension = params.get("tipo").toString().equalsIgnoreCase(TipoReporteEnum.EXCEL.name()) ? ".xlsx"
				: ".pdf";
		dto.setFileName(fileName + extension);

		ByteArrayOutputStream stream = reportManager.export(fileName, params.get("tipo").toString(), params,
				dataSource.getConnection());

		byte[] bs = stream.toByteArray();
		dto.setStream(new ByteArrayInputStream(bs));
		dto.setLength(bs.length);

		return dto;
	}

}
