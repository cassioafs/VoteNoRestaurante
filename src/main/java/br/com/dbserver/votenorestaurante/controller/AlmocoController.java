package br.com.dbserver.votenorestaurante.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.dbserver.votenorestaurante.entity.Restaurante;
import br.com.dbserver.votenorestaurante.repository.AlmocoRepository;

@Controller
public class AlmocoController {

	@Autowired
	private AlmocoRepository almocoRepository;
	
	
	@RequestMapping(value = "/confirmar", method= RequestMethod.POST)
	public ModelAndView votar(Restaurante restaurante, RedirectAttributes redirectAttributes){
		
		almocoRepository.save(restaurante);
		redirectAttributes.addFlashAttribute("sucesso", "Restaurante Escolhido =)");
		
		return new ModelAndView("redirect:/votos");
	}
	
	
}
