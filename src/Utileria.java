import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 
 * @author marvin
 *
 */
public class Utileria extends Object {

	private static Map<StringBuilder, StringBuilder> separadores;

	public Map<StringBuilder, StringBuilder> getSintaxis() throws IOException {
		if (separadores == null) {
			Properties props = new Properties();
			props.load(getClass().getResourceAsStream("sintaxis.properties"));
			separadores = new HashMap<StringBuilder, StringBuilder>((Map) props);
		}
		return separadores;
	}
}
