package br.com.dbserver.votenorestaurante.excepetion;

public class RestauranteJaVisitadoNaSemanaException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return "Esse restaurante já foi visitado nessa semana! Faça uma nova Votação";
	}
	

}
