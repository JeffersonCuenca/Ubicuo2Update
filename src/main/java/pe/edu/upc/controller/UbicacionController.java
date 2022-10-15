package pe.edu.upc.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import pe.edu.upc.entities.Ubicacion;
import pe.edu.upc.serviceinterface.IUbicacionService;

@Controller
@RequestMapping("/ubicaciones")
public class UbicacionController {

	@Autowired
	private IUbicacionService uS;

	@GetMapping("/new")
	public String newUbicacion(Model model) {
		model.addAttribute("ubicacion", new Ubicacion());
		return "ubicacion/ubicacion";
	}
	@GetMapping("/list")
	public String listUbicaciones(Model model) {
		try {
			model.addAttribute("ubicacion", new Ubicacion());
			model.addAttribute("listaUbicaciones", uS.list());
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("error", e.getMessage());
		}
		return "ubicacion/listUbicaciones";
	}
	@PostMapping("/save")
	public String saveMarca(@Valid Ubicacion ubicacion, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			return "ubicacion/ubicacion";
		} else {
			model.addAttribute("mensaje", "Se guardó correctamente");
				status.setComplete();
			
		}
		model.addAttribute("ubicacion", new Ubicacion());
		return "redirect:/ubicaciones/list";
	}
	@RequestMapping("/delete")
	public String deleteUbicacion(Model model, @RequestParam(value = "id") Integer id, Ubicacion ubicacion) {
		uS.delete(id);
		model.addAttribute("ubicacion",ubicacion);
		model.addAttribute("listaUbicaciones", uS.list());
		return "ubicacion/listUbicaciones";
	}

	@RequestMapping("/update/{id}")
	public String updateUbicacion(@PathVariable int id, Model model, RedirectAttributes objRedirect) {
		Optional<Ubicacion> ubicacion = uS.listId(id);
		if (ubicacion == null) {
			objRedirect.addFlashAttribute("mensaje", "Ocurrio un error");
			return "ubicacion/ubicacion";
		} else {
			model.addAttribute("ubicacion", ubicacion);
			return "ubicacion/ubicacion";
		}
	}
}
