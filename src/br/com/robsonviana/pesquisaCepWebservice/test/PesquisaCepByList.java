package br.com.robsonviana.pesquisaCepWebservice.test;

import br.com.robsonviana.pesquisaCepWebservice.consumer.ConsumerWebCep;
import br.com.robsonviana.pesquisaCepWebservice.pojo.Webservicecep;

public class PesquisaCepByList {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		testPesquisaCep();

	}

	/**
	 * realiza testes para a classe PesquisaCeps com uma lista de ceps
	 * 
	 * @param local
	 */
	public static void testPesquisaCep() {
		
		ConsumerWebCep cwc = new ConsumerWebCep();
		String[] listCeps = { "04571010", "04547000", "07859220", "04585221",
				"01311300", "05606100", "07785200" };

		for (String cep : listCeps) {
			System.out.println("\nConsultando CEP: " + cep.toString());
			Webservicecep wCep = cwc.getEndereco(cep);

			if ("1".equals(wCep.getResultado())) {
				cwc.showCepValues(wCep);
				cwc.convertJavaToXml(wCep, cep, false);
			} else {
				System.out.println("CEP [" + cep
						+ "] informado não encontrado!");
			}
		}
	}

}
