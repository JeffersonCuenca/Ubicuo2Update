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

import pe.edu.upc.entities.Cargo;
import pe.edu.upc.serviceinterface.ICargoService;

@Controller
@RequestMapping("/cargos")
public class CargoController 
{
	@Autowired
	private ICargoService cS;
	
	@GetMapping("/new")
	public String newCargo(Model model) 
	{
		model.addAttribute("cargo", new Cargo());
		return "cargo/cargo";
	}
	
	@GetMapping("/list")
	public String listCargos(Model model) 
	{
		try 
		{
			model.addAttribute("cargo", new Cargo());
			model.addAttribute("listaCargos", cS.list());
		} 
		catch (Exception e) 
		{
			model.addAttribute("error", e.getMessage());
		}
		return "cargo/listCargos";
	}
	
	@PostMapping("/save")
	public String saveMarca(@Valid Cargo cargo, BindingResult result, Model model, SessionStatus status) throws Exception 
	{
		if (result.hasErrors()) 
		{
			return "cargo/cargo";
		} 
		else 
		{
			int rpta = cS.insert(cargo);
			if (rpta > 0) 
			{
				model.addAttribute("mensaje", "Ya existe, ingrese un nuevo cargo");
				return "cargo/cargo";
			} 
			else 
			{
				model.addAttribute("mensaje", "Se guardó correctamente");
				status.setComplete();
			}
		}
		model.addAttribute("cargo", new Cargo());
		return "redirect:/cargos/list";
	}
	
	@RequestMapping("/delete")
	public String deleteCargo(Model model, @RequestParam(value="id") Integer id ) 
	{
		cS.delete(id);
		model.addAttribute("listaCargos", cS.list());
		return "cargo/listCargos";
	}
	
	@RequestMapping("/update/{id}")
	public String irUpdate(@PathVariable int id, Model model, RedirectAttributes objRedirect)
	{
		Optional<Cargo> cargo= cS.listId(id);
		
		if(cargo==null)
		{
			objRedirect.addFlashAttribute("mensaje","Ocurrió un error");
			return "cargo/cargo";
		}
		else
		{
			model.addAttribute("cargo", cargo);
			return "cargo/cargo";
		}
	}
	
	@RequestMapping("/search")
	public String findCargo(@ModelAttribute Cargo cargo, Model model)
	{
		List<Cargo> listacargo;
		listacargo=cS.findByNameCargo(cargo.getNameCargo());
		model.addAttribute("listaCargos", listacargo);
		
		return "cargo/listCargos";
	}
}
