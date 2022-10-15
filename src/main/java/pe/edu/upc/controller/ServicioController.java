package pe.edu.upc.controller;

import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.entities.Servicio;
import pe.edu.upc.serviceinterface.IDistritoService;
import pe.edu.upc.serviceinterface.IServicioService;
import pe.edu.upc.serviceinterface.ITipoServicioService;
import pe.edu.upc.serviceinterface.IUploadFileService;


@Controller
@RequestMapping("/servicios")
public class ServicioController {

	@Autowired
	private IServicioService sService;
	
	@Autowired
	private IDistritoService dService;
	
	@Autowired
	private ITipoServicioService tService;
	@Autowired
	private IUploadFileService uploadFileService;

	
	@GetMapping("/new")
	public String newServicio(Model model) {
		model.addAttribute("servicio", new Servicio());
		model.addAttribute("listaDistritos", dService.list());
		model.addAttribute("listaTipoServicios", tService.list());
		model.addAttribute("servicio", new Servicio());
		return "servicio/servicio";
	}
	@GetMapping("/list")
	public String listServicios(Model model) {
		try {
			model.addAttribute("servicio", new Servicio());
			model.addAttribute("listaServicios", sService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "servicio/listServicios";
	}
	@RequestMapping("/save")
	public String insertProduct(@Valid Servicio servicio, BindingResult binRes, Model model,
			 SessionStatus status)
			throws ParseException {
		if (binRes.hasErrors()) {
			model.addAttribute("listaDistritos", dService.list());
			model.addAttribute("listaTipoServicios", tService.list());
			return "servicio/servicio";
		} else {

			int rpta = sService.insert(servicio);
			if (rpta > 0) {
				model.addAttribute("mensaje", "Ya existe, Ingrese nuevo Nombre de Servicio");
				model.addAttribute("listaDistritos", dService.list());
				model.addAttribute("listaTipoServicios", tService.list());
				return "servicio/servicio";
			} else {
				model.addAttribute("mensaje", "Se guardo correctamente");
				status.setComplete();
			}
		}

		model.addAttribute("servicio", new Servicio());
		return "redirect:/servicios/list";
		
	}

	@GetMapping(value = "/uploads/{filename:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String filename) {

		Resource recurso = null;

		try {
			recurso = uploadFileService.load(filename);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"")
				.body(recurso);
	}
	@GetMapping(value = "/view/{id}")
	public String view(@PathVariable(value = "id") int id, Map<String, Object> model, RedirectAttributes flash) {

		Servicio servicio = sService.listarId(id);

		if (servicio == null) {
			flash.addFlashAttribute("error", "El Servicio no existe en la base de datos");
			return "servicio/listServicios";
		}

		model.put("servicio", servicio);
		model.put("titulo", "Detalle de producto: " + servicio.getNameServicio());

		return "servicio/ver";
	}
	@RequestMapping("/list")
	public String listServicios(Map<String, Object> model) {
		model.put("listaServicios", sService.list());
		return "servicio/listServicios";

	}
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Servicio pro) {
		sService.listarId(pro.getIdServicio());
		return "servicio/listServicios";

	}
	@RequestMapping("/delete")
	public String deleteServicio(Model model, @RequestParam(value = "id") Integer id, Servicio servicio) {
		sService.delete(id);
		model.addAttribute("servicio",servicio);
		model.addAttribute("listaServicios", sService.list());
		return "servicio/servicio";
	}
	@RequestMapping("/update/{id}")
	public String update(@PathVariable int id, Model model, RedirectAttributes objRedir) {

		Servicio servicio = sService.listarId(id);
		if (servicio == null) {
			objRedir.addFlashAttribute("mensaje", "OcurriÃ³ un error");
			return "redirect:/servicios/list";
		} else {
			model.addAttribute("listaDistritos", dService.list());
			model.addAttribute("listaTipoServicios", tService.list());
			model.addAttribute("servicio", servicio);
			return "servicio/servicio";
		}
	}
}
