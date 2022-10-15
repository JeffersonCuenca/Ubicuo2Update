package pe.edu.upc.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.entities.Usuario;
import pe.edu.upc.serviceinterface.ICargoService;
import pe.edu.upc.serviceinterface.IServicioService;
import pe.edu.upc.serviceinterface.ITipoUsuarioService;
import pe.edu.upc.serviceinterface.IUbicacionService;
import pe.edu.upc.serviceinterface.IUsuarioService;

@Controller
@RequestMapping("/register")
public class RegisterController 
{
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private IUsuarioService usS;
	
	@Autowired
	private ITipoUsuarioService iS;
	
	@Autowired
	private IUbicacionService ubS;
	
	@Autowired
	private ICargoService cS;
	
	@Autowired
	private IServicioService sS;
	
	@Autowired
	private pe.edu.upc.serviceinterface.IUploadFileService uploadFileService;
	
	@GetMapping("/new")
	public String register(Model model) 
	{
		model.addAttribute("usuario", new Usuario());
		model.addAttribute("listatipousuarios", iS.list());
		model.addAttribute("listaubicacion", ubS.list());
		model.addAttribute("listacargo", cS.list());
		model.addAttribute("listaservicio", sS.list());
		model.addAttribute("usuario", new Usuario());
		return "register";
	}
	
	/*@PostMapping("/save")
	public String saveUser(@Valid Usuario usuario, BindingResult result, Model model, SessionStatus status) throws Exception 
	{
		if (result.hasErrors()) 
		{
			return "register";
		} 
		else 
		{
			String bcryptPassword = passwordEncoder.encode(usuario.getPassword());
			usuario.setPassword(bcryptPassword);
			usuario.setEnabled(true);
			usuario.setIdUsuario(usuario.getIdUsuario());
			
			int rpta = usS.insert(usuario);
			
			
			if (rpta > 0) 
			{
				model.addAttribute("mensaje", "Ya existe");
				return "register";
			} 
			else 
			{
				model.addAttribute("mensaje", "Se guardó correctamente");
				status.setComplete();
			}
		}
		
		model.addAttribute("listaUsuarios", usS.list());

		return "login";
	}*/
	
	@RequestMapping("/save")
	public String saveMarca(@Valid Usuario usuario, BindingResult binRes, Model model, @RequestParam("file") MultipartFile foto, RedirectAttributes flash, SessionStatus status) throws ParseException	
	{
		if (binRes.hasErrors()) 
		{
			model.addAttribute("listaTipoUsuarios",iS.list());
			model.addAttribute("listaUbicacion",ubS.list());
			model.addAttribute("listaCargo",cS.list());
			model.addAttribute("listaServicio",sS.list());
			return "register";
		} 
		else 
		{
			String bcryptPassword = passwordEncoder.encode(usuario.getPassword());
			usuario.setPassword(bcryptPassword);
			usuario.setEnabled(true);
			usuario.setIdUsuario(usuario.getIdUsuario());
			
			if (!foto.isEmpty()) 
			{

				if (usuario.getIdUsuario() > 0 && usuario.getPhotoUsuario()!= null && usuario.getPhotoUsuario().length() > 0) 
				{
					uploadFileService.delete(usuario.getPhotoUsuario());
				}

				String uniqueFilename = null;
				try 
				{
					uniqueFilename = uploadFileService.copy(foto);
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}

				flash.addFlashAttribute("info", "Has subido correctamente '" + uniqueFilename + "'");
				usuario.setPhotoUsuario(uniqueFilename);
			}
			
			int rpta = usS.insert(usuario);
			if (rpta > 0) 
			{
				model.addAttribute("mensaje", "Ya existe, ingrese un Nuevo Nombre");
				model.addAttribute("listatipousuarios", iS.list());
				model.addAttribute("listaubicacion", ubS.list());
				model.addAttribute("listacargo", cS.list());
				model.addAttribute("listaservicio", sS.list());
				return "register";
			} 
			else 
			{
				model.addAttribute("mensaje", "Se guardo correctamente");
				status.setComplete();
			}
		}
		model.addAttribute("usuario", new Usuario());
		return "login";
		
	}
	
	@GetMapping(value = "/uploads/{filename:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String filename) 
	{
		Resource recurso = null;

		try 
		{
			recurso = uploadFileService.load(filename);
		} 
		catch (MalformedURLException e) 
		{
			e.printStackTrace();
		}
		
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"").body(recurso);
	}
	
	@RequestMapping("/list")
	public String listUsuarios(Map<String, Object> model) 
	{
		model.put("listaUsuarios", usS.list());
		return "usuario/listUsuarios";
	}
	
	@RequestMapping("/delete")
	public String deleteUsuario(Model model, @RequestParam(value="id") int id)
	{
		usS.delete(id);
		model.addAttribute("listaUsuarios",usS.list());
		return "usuario/listUsuarios";
	}

}
