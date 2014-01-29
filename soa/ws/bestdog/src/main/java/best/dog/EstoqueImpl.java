package best.dog;

import javax.jws.WebService;

@WebService(endpointInterface = "best.dog.Estoque")
public class EstoqueImpl implements Estoque {

	@Override
	public String calculaEstoque(String codItem) {
		if (buscaItem(codItem)) {
			String result = codItem + " disponivel";
			System.out.println(result);
			return result;
		}
		// estimar melhor o estoque da proxima vez
		throw new UnsupportedOperationException(codItem + "indisponivel!");
	}

	private boolean buscaItem(String codItem) {
		// busca no BD de estoques
		return codItem != null && !codItem.isEmpty();
	}

}
