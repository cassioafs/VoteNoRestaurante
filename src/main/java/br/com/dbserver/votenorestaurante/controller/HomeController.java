package br.com.dbserver.votenorestaurante.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.dbserver.votenorestaurante.business.Votacao;
import br.com.dbserver.votenorestaurante.entity.Profissional;
import br.com.dbserver.votenorestaurante.entity.Restaurante;
import br.com.dbserver.votenorestaurante.excepetion.RestauranteNaoPodeSerVotadoException;
import br.com.dbserver.votenorestaurante.repository.ProfissionalRepository;
import br.com.dbserver.votenorestaurante.repository.RestauranteRepository;

@Controller
public class HomeController {

	@Autowired
	private ProfissionalRepository profissionalRepository;
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private Votacao votacao;
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView("home");
		modelAndView.addObject("profissionais", profissionalRepository.findAll());
		modelAndView.addObject("restaurantes", restauranteRepository.findAll());
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/votar", method= RequestMethod.POST)
	public ModelAndView votar(Profissional profissional, Restaurante restaurante, RedirectAttributes redirectAttributes){
		
		try {
			votacao.votar(restauranteRepository.findByNome(restaurante.getNome()), profissionalRepository.findByNome(profissional.getNome()));
			redirectAttributes.addFlashAttribute("sucesso", "Voto realizado com sucesso");
			return new ModelAndView("redirect:/votos");
		} catch (RestauranteNaoPodeSerVotadoException e) {
			redirectAttributes.addFlashAttribute("error", e.getMessage());
			return new ModelAndView("redirect:/");
		}
	}
	
	
	@RequestMapping(value = "/votos", method = RequestMethod.GET)
	public ModelAndView votos() {
		ModelAndView modelAndView = new ModelAndView("listagemVotos");
		modelAndView.addObject("votos", votacao.getVotos());
		return modelAndView;
	}
	
	@RequestMapping(value = "/resultado", method = RequestMethod.GET)
	public ModelAndView resultado() {
		ModelAndView modelAndView = new ModelAndView("resultado");
		modelAndView.addObject("resultado", votacao.getResultado());
		modelAndView.addObject("vencedor", votacao.getRestauranteVencedor());
		return modelAndView;
	}
	

}
