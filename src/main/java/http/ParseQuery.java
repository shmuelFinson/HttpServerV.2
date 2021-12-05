package http;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ParseQuery {
    public static void parseQuery(String query, Map<String,
                Object> parameters) throws UnsupportedEncodingException {

        if (query != null) {
            String pairs[] = query.split("[&]");
            for (String pair : pairs) {
                String param[] = pair.split("[=]");
                String key = null;
                String value = null;
                if (param.length > 0) {
                    key = URLDecoder.decode(param[0],
                            System.getProperty("file.encoding"));
                }

                if (param.length > 1) {
                    value = URLDecoder.decode(param[1],
                            System.getProperty("file.encoding"));
                }

                if (parameters.containsKey(key)) {
                    Object obj = parameters.get(key);
                    if (obj instanceof List<?>) {
                        List<String> values = (List<String>) obj;
                        values.add(value);

                    } else if (obj instanceof String) {
                        List<String> values = new ArrayList<String>();
                        values.add((String) obj);
                        values.add(value);
                        parameters.put(key, values);
                    }
                } else {
                    parameters.put(key, value);
                }
            }
        }
    }


    public static boolean properFormatForKV(Map<String, Object> parameters){
        if((parameters.containsKey("Key") || parameters.containsKey("key") )&& (parameters.containsKey("Value") || parameters.containsKey("value")))
            return true;
        else
            return false;
    }
    public static boolean properFormatForK(Map<String, Object> parameters){
        if(parameters.containsKey("Key") || parameters.containsKey("key"))
            return true;
        else return false;
    }
}
