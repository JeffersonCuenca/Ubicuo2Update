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

import pe.edu.upc.entities.Contacto;
import pe.edu.upc.serviceinterface.IContactoService;
import pe.edu.upc.serviceinterface.IUploadFileService;
import pe.edu.upc.serviceinterface.IUsuarioService;

@Controller
@RequestMapping("/contactos")
public class ContactoController {
	@Autowired
	private IContactoService cService;
	@Autowired
	private IUsuarioService uService;
	@Autowired
	private IUploadFileService uploadFileService;

	@GetMapping("/new")
	public String newContacto(Model model) {
		model.addAttribute("contacto", new Contacto());
		model.addAttribute("listaUsuarios", uService.list());
		model.addAttribute("contacto", new Contacto());
		return "contacto/contacto";
	}

	@GetMapping("/list")
	public String listContactos(Model model) {
		try {
			model.addAttribute("contacto", new Contacto());
			model.addAttribute("listaContactos", cService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "contacto/listContactos";
	}

	@RequestMapping("/save")
	public String insertContacto(@Valid Contacto contacto, BindingResult binRes, Model model,
			 SessionStatus status)
			throws ParseException {
		if (binRes.hasErrors()) {
			model.addAttribute("listaUsuarios", uService.list());
			return "contacto/contacto";
		} else {
			int rpta = cService.insert(contacto);
			if (rpta > 0) {
				model.addAttribute("mensaje", "Ya existe, ingrese un nuevo contacto");
				return "contacto/contacto";
			} else {
				model.addAttribute("mensaje", "Se guardó correctamente");
				status.setComplete();
			}
		}
		model.addAttribute("contacto", new Contacto());
		return "redirect:/contactos/list";
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

		Contacto contacto = cService.listId(id);

		if (contacto == null) {
			flash.addFlashAttribute("error", "El contacto no existe en la base de datos");
			return "contacto/listContactos";
		}

		model.put("contacto", contacto);
		model.put("titulo", "Detalle del contacto: " + contacto.getNameContacto());

		return "contacto/ver";
	}

	@RequestMapping("/list")
	public String listContactos(Map<String, Object> model) {
		model.put("listaContactos", cService.list());
		return "contacto/listContactos";

	}
	
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Contacto pro) {
		cService.listId(pro.getIdContacto());
		return "contacto/listContactos";

	}

	@RequestMapping("/delete")
	public String deleteContacto(Model model, @RequestParam(value = "id") Integer id, Contacto contacto) {
		cService.delete(id);
		model.addAttribute("contacto", contacto);
		model.addAttribute("listaContactos", cService.list());
		return "contacto/listContactos";
	}

	@RequestMapping("/update/{id}")
	public String update(@PathVariable int id, Model model, RedirectAttributes objRedir) {

		Contacto contacto = cService.listId(id);
		if (contacto == null) {
			objRedir.addFlashAttribute("mensaje", "OcurriÃ³ un error");
			return "redirect:/contactos/list";
		} else {
			model.addAttribute("listaUsuarios", uService.list());
			model.addAttribute("contacto", contacto);
			return "contacto/contacto";
		}
	}
}
