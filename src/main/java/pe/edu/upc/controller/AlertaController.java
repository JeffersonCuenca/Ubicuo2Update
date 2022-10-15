package pe.edu.upc.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lowagie.text.DocumentException;

import pe.edu.upc.entities.Alerta;
import pe.edu.upc.serviceinterface.IAlertaService;
import pe.edu.upc.serviceinterface.IEstadosService;
import pe.edu.upc.serviceinterface.IUsuarioService;
import pe.edu.upc.util.AlertaExporterPdf;

@Controller
@RequestMapping("/alertas")
public class AlertaController {

	@Autowired
	private IAlertaService aS;

	@Autowired
	private IEstadosService eS;

	@Autowired
	private IUsuarioService uSaux;

	@Autowired
	private IUsuarioService uSres;

	/*
	 * @InitBinder protected void initBinder(HttpServletRequest request,
	 * ServletRequestDataBinder binder) { SimpleDateFormat dateFormat = new
	 * SimpleDateFormat("yyyy-MM-dd"); dateFormat.setLenient(false);
	 * binder.registerCustomEditor(Date.class, null, new
	 * CustomDateEditor(dateFormat, true)); }
	 */
	
	@GetMapping("/ListaAlertasPDF")
	public void ListaAlertasPDF(HttpServletResponse response) throws DocumentException, IOException {

		response.setContentType("application/pdf");

		DateFormat dateFormatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
		String fechaActual = dateFormatter.format(new Date());

		String cabecera = "Content-Disposition";
		String valor = "inline; filename=ListaAlertas_" + fechaActual + ".pdf";

		response.setHeader(cabecera, valor);

		List<Alerta> alertas = aS.list();

		AlertaExporterPdf exporter = new AlertaExporterPdf(alertas);
		exporter.exportar(response);

	}

	@GetMapping("/new")
	public String newAlerta(Model model) {
		model.addAttribute("alerta", new Alerta());
		model.addAttribute("listaAlertas", aS.list());
		model.addAttribute("listaEstados", eS.list());
		model.addAttribute("listaUsuariosAux", uSaux.list());
		model.addAttribute("listaUsuariosRes", uSres.list());
		model.addAttribute("alerta", new Alerta());
		return "alerta/alerta";
	}

	@GetMapping("/list")
	public String listAlertas(Model model) {
		try {
			model.addAttribute("alerta", new Alerta());
			model.addAttribute("listaAlertas", aS.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "alerta/listAlertas";
	}

	@PostMapping("/save")
	public String saveAlerta(@ModelAttribute("alerta") @Valid Alerta alerta, BindingResult result, Model model,
			SessionStatus status) throws Exception {
		if (result.hasErrors()) {
			model.addAttribute("listaEstados", eS.list());
			model.addAttribute("listaUsuariosAux", uSaux.list());
			model.addAttribute("listaUsuariosRes", uSres.list());
			return "alerta/alerta";
		} else {
			int rpta = aS.insert(alerta);
			if (rpta > 0) {
				model.addAttribute("mensaje", "Usuario Rescatista no disponible");
				model.addAttribute("listaEstados", eS.list());
				model.addAttribute("listaUsuariosAux", uSaux.list());
				model.addAttribute("listaUsuariosRes", uSres.list());
				return "alerta/alerta";
			} else {
				model.addAttribute("mensaje", "Se guardó correctamente");
				status.setComplete();
			}
		}
		model.addAttribute("alerta", new Alerta());
		return "redirect:/alertas/list";
	}

	@PostMapping("/saveUpdate")
	public String saveAlertaUpdate(@ModelAttribute("alerta") @Valid Alerta alerta, BindingResult result, Model model,
			SessionStatus status) throws Exception {
		if (result.hasErrors()) {
			model.addAttribute("listaEstados", eS.list());
			model.addAttribute("listaUsuariosAux", uSaux.list());
			model.addAttribute("listaUsuariosRes", uSres.list());
			return "alerta/alertaUpdate";
		} else {
			aS.insertUpdate(alerta);
			model.addAttribute("mensaje", "Se guardó correctamente");
			status.setComplete();
		}
		model.addAttribute("alerta", new Alerta());
		return "redirect:/alertas/list";
	}

	@RequestMapping("/delete")
	public String deleteAlerta(Model model, @RequestParam(value = "id") Integer id, Alerta alerta) {
		aS.delete(id);
		model.addAttribute("alerta", alerta);
		model.addAttribute("listaAlertas", aS.list());
		return "alerta/listAlertas";
	}

	@RequestMapping("/update/{id}")
	public String updateAlerta(@PathVariable int id, Model model, RedirectAttributes objRedirect) {
		Optional<Alerta> alerta = aS.listId(id);
		if (alerta == null) {
			objRedirect.addFlashAttribute("mensaje", "Ocurrio un error");
			return "alerta/alertaUpdate";
		} else {
			model.addAttribute("listaEstados", eS.list());
			model.addAttribute("listaUsuariosAux", uSaux.list());
			model.addAttribute("listaUsuariosRes", uSres.list());
			model.addAttribute("alerta", alerta);
			return "alerta/alertaUpdate";
		}
	}

}
