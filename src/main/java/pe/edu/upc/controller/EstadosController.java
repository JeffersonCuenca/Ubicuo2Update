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

import pe.edu.upc.entities.Estados;
import pe.edu.upc.serviceinterface.IEstadosService;

@Controller
@RequestMapping("/estadoss")
public class EstadosController {
	@Autowired
	private IEstadosService cService;

	@GetMapping("/new")
	public String newEstados(Model model) {
		model.addAttribute("estados", new Estados());
		return "estados/estados";
	}

	@GetMapping("/list")
	public String listEstados(Model model) {
		try {
			model.addAttribute("estados", new Estados());
			model.addAttribute("listaEstados", cService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "estados/listEstados";
	}

	@PostMapping("/save")
	public String saveMarca(@Valid Estados estados, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			return "estados/estados";
		} else {
			int rpta = cService.insert(estados);
			if (rpta > 0) {
				model.addAttribute("mensaje", "Ya existe, ingrese un nuevo estado");
				return "estados/estados";
			} else {
				model.addAttribute("mensaje", "Se guardó correctamente");
				status.setComplete();
			}
		}
		model.addAttribute("estados", new Estados());
		return "redirect:/estadoss/list";
	}

	@RequestMapping("/delete")
	public String deleteEstados(Model model, @RequestParam(value = "id") int id, Estados estados) {
		cService.delete(id);
		model.addAttribute("estados", estados);
		model.addAttribute("listaEstados", cService.list());
		return "estados/listEstados";
	}

	@RequestMapping("/update/{id}")
	public String updateEstados(@PathVariable int id, Model model, RedirectAttributes objRedirect) {
		Optional<Estados> estados = cService.listId(id);
		if (estados == null) {
			objRedirect.addFlashAttribute("mensaje", "Ocurrio un error");
			return "estados/estados";
		} else {
			model.addAttribute("estados", estados);
			return "estados/estados";
		}
	}
}
