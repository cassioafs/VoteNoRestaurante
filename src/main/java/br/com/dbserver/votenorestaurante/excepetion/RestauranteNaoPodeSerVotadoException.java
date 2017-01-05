package br.com.dbserver.votenorestaurante.excepetion;

public class RestauranteNaoPodeSerVotadoException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return "Esse Profissional Faminto já fez sua escolha do dia!";
	}
	

}
