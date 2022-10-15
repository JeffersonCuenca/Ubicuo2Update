package pe.edu.upc.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.sf.jasperreports.engine.JRException;
import pe.edu.upc.entities.TipoUsuario;
import pe.edu.upc.enums.TipoReporteEnum;
import pe.edu.upc.model.ReporteListaTServicioxAlerta;
import pe.edu.upc.serviceinterface.IAlertaService;
import pe.edu.upc.serviceinterface.ICargoService;
import pe.edu.upc.serviceinterface.IContactoService;
import pe.edu.upc.serviceinterface.IReporteListaTServicioxAlertaService;
import pe.edu.upc.serviceinterface.ITipoUsuarioService;

@Controller
@RequestMapping("/reportes")
public class ReportesController {
	
	@Autowired
	private IReporteListaTServicioxAlertaService RListaTServicioxAlerta;
	
	@Autowired
	private IAlertaService aS;
	
	@Autowired
	private ICargoService cS;
	
	@Autowired
	private IContactoService cService;
	
	@Autowired
	private ITipoUsuarioService TUs;
	
	@RequestMapping("/listaReportes")
	public String todosreportes()
	{
		return "/reports/reports";
	}
	
	@RequestMapping("/reporte1")
	public String cargoTopQuantityUsser(Map<String, Object> model)
	{
		model.put("listaCargosReporte",cS.cargoTopQuantityUsser());
		return "/reports/listCargo";
	}
	
	@RequestMapping("/reporteContacto")
	public String contactoXUser(Map<String, Object> model) {
		model.put("listContactXUsr", cService.contactByUser());
		return "contacto/contactoXUser";
	}

	@RequestMapping("/reporte4")
	public String RescatistaAlertas(Map<String, Object> model) {
		model.put("listaRescatistaalertas", aS.RescatistaAlertas());
		return "reports/listAlertas";
	}

	@RequestMapping("/ListaTServicioxAlerta")
	public String TipoServicioAlerta(Map<String, Object> model) {
		model.put("listaTipoServicioalertas", aS.TipoServicioAlerta());
		return "reports/listTipo";
	}
	
	@GetMapping("/ListaTServicioxAlerta/Reporte")
	public ResponseEntity<Resource> ListaTServicioxAlerta(@RequestParam Map<String, Object> params)
			throws JRException, IOException, SQLException {
		ReporteListaTServicioxAlerta dto = RListaTServicioxAlerta.obtenerReporteListaTServicioxAlerta(params);

		InputStreamResource streamResource = new InputStreamResource(dto.getStream());
		MediaType mediaType = null;
		if (params.get("tipo").toString().equalsIgnoreCase(TipoReporteEnum.EXCEL.name())) {
			mediaType = MediaType.APPLICATION_OCTET_STREAM;
		} else {
			mediaType = MediaType.APPLICATION_PDF;
		} 

		return ResponseEntity.ok().header("Content-Disposition", "inline; filename=\"" + dto.getFileName() + "\"")
				.contentLength(dto.getLength()).contentType(mediaType).body(streamResource);
	}
	
	@RequestMapping("/reporte")
	public String reportetipousuario(Model model, Map<String,Object> modeltu ) {
		List<TipoUsuario> lista = TUs.list();
		List<Integer> cantidad = new ArrayList<Integer>();
		List<String> nombres = new ArrayList<String>();
		//permite pasar en el formato indicado para el grafico
		Map<String, Integer> GraphData = new TreeMap<>();
		
		for (int i=0; i < lista.size(); i++) {
			TipoUsuario aux = lista.get(i);
			nombres.add(aux.getRol());
		}
		
		for (int i=0; i < nombres.size(); i++) {
			int c = 0;
			for (int j=0; j < lista.size(); j++) {
				TipoUsuario aux = lista.get(j);
				//en java los strings no se comparan correctamente
				//es un problema de compatibilidad y java para los string usa su propia funcion
				if(nombres.get(i).equals(aux.getRol()))c++;
			}
			cantidad.add(c);
		}
		
		for (int i=0; i < lista.size(); i++) {
			GraphData.put(nombres.get(i), cantidad.get(i));
		}
		
		model.addAttribute("chartData", GraphData);
		
		return "tipousuario/tipousuarioreporte";
	}

}
