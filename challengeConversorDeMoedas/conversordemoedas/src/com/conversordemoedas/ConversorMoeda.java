package com.conversordemoedas;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ConversorMoeda {
    private static final String API_KEY = "06d3be2ac2c34ade47bf0c67";
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";

    public double obterTaxa(Conversao conversao) throws Exception {
        String urlStr = BASE_URL + API_KEY + "/latest/" + conversao.moedaOrigem();
        URL url = new URL(urlStr);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.connect();

        JsonParser jp = new JsonParser();
        JsonObject json = jp.parse(new InputStreamReader((InputStream) request.getContent()))
                .getAsJsonObject();

        if (!json.get("result").getAsString().equals("success")) {
            throw new RuntimeException("Erro na resposta da API.");
        }

        JsonObject rates = json.getAsJsonObject("conversion_rates");
        return rates.get(conversao.moedaDestino()).getAsDouble();
    }
}

