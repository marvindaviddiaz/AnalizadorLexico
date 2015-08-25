import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class Lector {

	private final static int BUFFER_SIZE = 1;

	private StringBuilder palabra;
	private String letra;
	private List<StringBuilder> lista;
	byte data[];
	private InputStream is;
	private Utileria util = new Utileria();

	public Lector(InputStream inputStream) {
		is = inputStream;
	}

	public List<StringBuilder> obtenerTokens() throws UnsupportedEncodingException, IOException {
		lista = new ArrayList<StringBuilder>();
		data = new byte[BUFFER_SIZE];
		palabra = new StringBuilder();
		String aux;
		while ((is.read(data)) != -1) {
			letra = new String(data, "UTF-8").trim();
			if (letra.length() > 0) {
				if (util.getSintaxis().containsKey(letra) || letra.equals(":") || letra.equals("=")) {
					agregarPalabra();
					palabra = (new StringBuilder(letra));
					agregarPalabra();
				} else {
					palabra.append(letra);
				}
			} else {
				agregarPalabra();
			}
		}
		return lista;
	}

	private void agregarPalabra() {
		if (palabra.length() > 0) {
			lista.add(palabra);
			palabra = new StringBuilder();
		}
	}
}
