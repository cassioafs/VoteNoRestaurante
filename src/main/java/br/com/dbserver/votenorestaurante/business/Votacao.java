package br.com.dbserver.votenorestaurante.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.dbserver.votenorestaurante.entity.Almoco;
import br.com.dbserver.votenorestaurante.entity.Profissional;
import br.com.dbserver.votenorestaurante.entity.Restaurante;
import br.com.dbserver.votenorestaurante.entity.Voto;
import br.com.dbserver.votenorestaurante.excepetion.RestauranteNaoPodeSerVotadoException;
import br.com.dbserver.votenorestaurante.repository.AlmocoRepository;
import br.com.dbserver.votenorestaurante.repository.VotoRepository;
import br.com.dbserver.votenorestaurante.util.DateUtil;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multiset.Entry;
import com.google.common.collect.Multisets;

@Component
public class Votacao {

	private Restaurante restauranteEscolhido;
	private Restaurante restauranteVencedor;
	
	@Autowired
	private VotoRepository votoRepository;
	
	@Autowired
	private AlmocoRepository almocoRepository;
	
	public Votacao(VotoRepository votoRepository, AlmocoRepository almocoRepository) {
		this.votoRepository = votoRepository;
		this.almocoRepository = almocoRepository;
	}
	
	public Votacao() {
		// TODO Auto-generated constructor stub
	}
	
	
	private List<Voto> votos = new ArrayList<Voto>();

	
	public Restaurante getRestauranteEscolhido() {
		return restauranteEscolhido;
	}
	
	public Voto votar(Restaurante restaurante, Profissional profissional) {
		this.restauranteEscolhido = restaurante;
		Voto voto = new Voto();
		
		voto.setRestaurante(restaurante);
		voto.setProfissional(profissional);
		
		adicionarVoto(voto);
		
		return voto;
	}

	private void adicionarVoto(Voto voto) {
		
		Almoco almoco = almocoRepository.findByDatesBetween(DateUtil.getPrimeiroDiaDaSemana(), DateUtil.getUltimoDiaDaSemana(), voto.getRestaurante());

		if(almoco != null)
			throw new RestauranteNaoPodeSerVotadoException();
		
		if(votoRepository.findByProfissional(voto.getProfissional())==null){
			votoRepository.save(voto);
			votos.add(voto);
		}else {
			throw new RestauranteNaoPodeSerVotadoException();
		}
	}
	
	public List<Voto> getVotos() {
		return  !votos.isEmpty() ? votos : (List<Voto>) votoRepository.findAll();
	}

	public int getQuantidadeVotos() {
		return votos.size();
		
	}
	
	
	public Map<Restaurante, Integer> getResultado() {
		
		Multiset<Restaurante> multiset = HashMultiset.create();
		
		List<Voto> listaVotos = getVotos();
		if(listaVotos.isEmpty()){
			return null;
		}
		
		Map<Restaurante, Integer> mapResultado = new HashMap<Restaurante, Integer>();
		
		for (Voto voto : getVotos()) {
			Integer quantidadeVotos = 1;
			
			if(mapResultado.containsKey(voto.getRestaurante())){
				quantidadeVotos += mapResultado.get(voto.getRestaurante());
			}
			
			mapResultado.put(voto.getRestaurante(), quantidadeVotos);
			multiset.add(voto.getRestaurante());
		}
		
		Entry<Restaurante> vencedor = Multisets.copyHighestCountFirst(multiset).entrySet().iterator().next();
		
		restauranteVencedor = vencedor.getElement();
		
		return mapResultado;
	}


	public Restaurante getRestauranteVencedor() {
		
		return restauranteVencedor;
	}
	
	public void setRestauranteVencedor(Restaurante restauranteVencedor) {
		this.restauranteVencedor = restauranteVencedor;
	}
}
