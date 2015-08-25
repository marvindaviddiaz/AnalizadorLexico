import java.util.ArrayList;
import java.util.List;

public class Analizador {

	private List<StringBuilder> lista;
	private Utileria util = new Utileria();

	public Analizador(List<StringBuilder> parametros) {
		lista = parametros;
	}

	public List<StringBuilder> analizar() throws Exception {
		List<StringBuilder> retorno = new ArrayList<StringBuilder>();
		String palabra;
		StringBuilder ret;
		for (StringBuilder s : lista) {
			palabra = new String(s);
			if (util.getSintaxis().containsKey(palabra)) {
				retorno.add(new StringBuilder(util.getSintaxis().get(palabra)));
			} else if (palabra.equals("=")) {
				retorno.add(new StringBuilder("TOKEN_RESERV_IGUAL"));
			} else if (palabra.equals(palabra.toLowerCase())) {
				retorno.add(new StringBuilder("TOKEN_ID<" + palabra + ">"));
			} else if (palabra.equals(palabra.toUpperCase())) {
				throw new Exception("Palabra no aceptada " + palabra);
			} else {
				throw new Exception("Palabra no aceptada " + palabra);
			}

		}
		return retorno;

	}
}
