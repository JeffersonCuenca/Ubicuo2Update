package pe.edu.upc.controller;

import java.util.List;
import java.util.Optional;

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

import pe.edu.upc.entities.TipoServicio;
import pe.edu.upc.serviceinterface.ITipoServicioService;

@Controller
@RequestMapping("/tiposervicios")
public class TipoServicioController {

	@Autowired
	private ITipoServicioService tS;
	
	@GetMapping("/new")
	public String newTipoServicio(Model model) {
		model.addAttribute("tiposervicio", new TipoServicio());
		return "tiposervicio/tiposervicio";
	}
	
	@GetMapping("/list")
	public String listTipoServicios(Model model) {
		try {
			model.addAttribute("tiposervicio", new TipoServicio());
			model.addAttribute("listaTipoServicios", tS.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "tiposervicio/listTipoServicios";
	}
	
	@PostMapping("/save")
	public String saveTipoServicio(@Valid TipoServicio tiposervicio, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			model.addAttribute("tiposervicio",tiposervicio);
			model.addAttribute("mensaje", "El nombre del Tipo de Servicio no puede contener un número o caracter");
			return "tiposervicio/tiposervicio";
		} else {
			int rpta = tS.insert(tiposervicio);
			if (rpta > 0) {
				model.addAttribute("tiposervicio",tiposervicio);
				model.addAttribute("mensaje", "Ya existe, ingrese un nuevo tipo de servicio");
				return "tiposervicio/tiposervicio";
			} else {
				model.addAttribute("mensaje", "Se guardó correctamente");
				status.setComplete();
			}
		}
		model.addAttribute("tiposervicio", new TipoServicio());
		return "redirect:/tiposervicios/list";
	}
	
	@RequestMapping("/delete")
	public String deleteTiposervicio(Model model, @RequestParam(value = "id") Integer id, TipoServicio tiposervicio) {
		tS.delete(id);
		model.addAttribute("tiposervicio",tiposervicio);
		model.addAttribute("listaTipoServicios", tS.list());
		return "tiposervicio/listTipoServicios";
	}

	@RequestMapping("/update/{id}")
	public String updateTiposervicio(@PathVariable int id, Model model, RedirectAttributes objRedirect) {
		Optional<TipoServicio> tiposervicio = tS.listId(id);
		if (tiposervicio == null) {
			objRedirect.addFlashAttribute("mensaje", "Ocurrio un error");
			return "tiposervicio/tiposervicio";
		} else {
			model.addAttribute("tiposervicio", tiposervicio);
			return "tiposervicio/tiposervicio";
		}
	}
	
	@RequestMapping("/search")
	public String findTiposervicio(@ModelAttribute TipoServicio tiposervicio, Model model) {
		
		List<TipoServicio> listaTipoServicios;
		listaTipoServicios = tS.findByNameTiposervicio(tiposervicio.getNameTiposervicio());
		model.addAttribute("tiposervicio",tiposervicio);
		model.addAttribute("listaTipoServicios",listaTipoServicios);
		return "tiposervicio/listTipoServicios";
		
	}
}
