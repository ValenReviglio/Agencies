import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import static spark.Spark.*;

public class AgenciesService {
    public static void main(String[] args) {
        get("/agencias", (req, res) -> {
            try {
                res.type("application/json");

                String siteId = req.queryParams("site_id");
                String paymentMethod = req.queryParams("payment_method_id");
                String nearTo = req.queryParams("near_to");
                String order = req.queryParams("order");

                JsonArray data = readUrl("https://api.mercadolibre.com/sites/" + siteId + "/payment_methods/" + paymentMethod + "/agencies?near_to=" + nearTo);
                TypeToken<ArrayList<Agency>> token = new TypeToken<ArrayList<Agency>>() {};
                ArrayList<Agency> agencias = new Gson().fromJson(data, token.getType());
                
                if (order != null) {
                    setCriterio(order);
                    Operador.ordenarArreglo(agencias);
                }
                for (int i = 0; i < agencias.size(); i++) {
                    System.out.println(agencias.get(i));
                }
                return "SUCCESS";
            } catch(IOException e) {
                e.printStackTrace();
            }
        });

        System.out.println();
    }

    public static JsonArray readUrl(String urlString) throws IOException {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            URLConnection connection = url.openConnection();
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            char[] chars = new char[1024];
            StringBuffer buffer = new StringBuffer();
            int read = 0;
            while ((read = reader.read(chars)) != -1) {
                buffer.append(chars, 0, read);
            }
            JsonParser parser = new JsonParser();
            JsonObject resultObj = parser.parse(buffer.toString()).getAsJsonObject();
            JsonArray resultString = (JsonArray) resultObj.get("results");
            return resultString;
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }

    public static void setCriterio(String order) {
        System.out.println(order);
        if (order.toLowerCase().equals("distancia")) {
            Agency.criterio = Agency.Criterio.DISTANCE;
            return;
        }
        if (order.toLowerCase().equals("address_line")) {
            System.out.println("Entras");
            Agency.criterio = Agency.Criterio.ADRESS_LINE;
            return;
        }
        Agency.criterio = Agency.Criterio.AGENCY_CODE;

    }
}
