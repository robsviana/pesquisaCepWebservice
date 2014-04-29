package br.com.robsonviana.pesquisaCepWebservice.consumer;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import br.com.robsonviana.pesquisaCepWebservice.pojo.Webservicecep;

/**
 * Classe para comsumir webservice de CEP e converções de objeto java para
 * arquivo xml e vice versa com uso de JAX-B
 * 
 * @author ROBSON_VIANA
 * 
 */
public class ConsumerWebCep {

	/**
	 * Método que consome um serviço web de ceps
	 * 
	 * @param cep
	 * @return
	 */
	public Webservicecep getEndereco(String cep) {
		Webservicecep wCep = null;

		try {
			System.out.println("Consumindo Webservice de CEP...........");
			JAXBContext context = JAXBContext.newInstance(Webservicecep.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			URL url = new URL(
					"http://cep.republicavirtual.com.br/web_cep.php?cep=" + cep
							+ "&formato=xml");
			wCep = (Webservicecep) unmarshaller.unmarshal(url);

		} catch (JAXBException e) {
			System.out.println("Erro ao tenter consumir serviço de CEP");
			e.printStackTrace();
		} catch (MalformedURLException e) {
			System.out.println("Erro ao tenter acessar a URL do webservice");
			e.printStackTrace();
		}
		return wCep;
	}

	/**
	 * Exibe as informações do cep pesquisado
	 * 
	 * @param wCep
	 */
	public void showCepValues(Webservicecep wCep) {
		String sResult = "=============================================\n"
				+ "Resultado: " + wCep.getResultado() + "\nResultado txt"
				+ wCep.getResultado_txt() + "\nUF: " + wCep.getUf()
				+ "\nCidade: " + wCep.getCidade() + "\nBairro: "
				+ wCep.getBairro() + "\nTipo_logradouro: "
				+ wCep.getTipo_logradouro() + "\nLogradouro: "
				+ wCep.getLogradouro()
				+ "\n=============================================";
		System.out.println(sResult);
	}

	/**
	 * Converte o objeto cepObject pesquisado para um arquivo xml
	 * 
	 * @param CepObj
	 * @param sCep
	 * @param local
	 */
	public void convertJavaToXml(Webservicecep CepObj, String sCep,
			boolean createArqXml) {
		final String localArqXml = "../pesquisaCepWebservice/arqsXml/";
		try {
			JAXBContext context = JAXBContext.newInstance(Webservicecep.class);
			Marshaller marshaller = context.createMarshaller();

			// local e nome do arquivo xml para ser criado
			String arquivoXml = localArqXml + sCep + ".xml";
			// Formata a sáida do output
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
					Boolean.TRUE);
			// Exibe a saída no console
			marshaller.marshal(CepObj, System.out);
			if (createArqXml == true) {
				// Cria o arquivo xml baseado no objeto Java
				marshaller.marshal(CepObj, new File(arquivoXml));
				System.out.println("Arquivo xml criado com scucesso!");
			}
		} catch (JAXBException e) {
			System.out.println("Erro ao criar contexto do JAX-B");
			e.printStackTrace();
		}
	}

}
