package br.com.robsonviana.pesquisaCepWebservice.test;

import java.util.Scanner;

import br.com.robsonviana.pesquisaCepWebservice.consumer.ConsumerWebCep;
import br.com.robsonviana.pesquisaCepWebservice.pojo.Webservicecep;

public class PesquisaCepByScanner {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		testPesquisaCep();

	}

	/**
	 * realiza testes para a classe PesquisaCeps com entrada de dados pelo
	 * usuário
	 */
	public static void testPesquisaCep() {
		ConsumerWebCep cwc = new ConsumerWebCep();

		Scanner scanner = new Scanner(System.in);

		System.out.println("Informe o CEP desejado:");
		String sCep = scanner.next();
		Webservicecep wCep = cwc.getEndereco(sCep);

		if ("1".equals(wCep.getResultado())) {
			cwc.showCepValues(wCep);
			cwc.convertJavaToXml(wCep, sCep, false);
		} else {
			System.out.println("CEP [" + sCep + "] informado não encontrado!");
		}
	}

}
