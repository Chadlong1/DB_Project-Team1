package maps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Geocoding {

	public static ArrayList<String> getGeocoding(String fullADDR) {
		String addr = null;
		ArrayList<String> list = new ArrayList<String>();

		try {
			addr = URLEncoder.encode(fullADDR, "utf-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		String api = "https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode?query=" + addr;
		StringBuffer sb = new StringBuffer();

		try {
			URL url = new URL(api);
			HttpsURLConnection http = (HttpsURLConnection) url.openConnection();
			http.setRequestProperty("Content-Type", "application/json");
			http.setRequestProperty("X-NCP-APIGW-API-KEY-ID", "mfqze4rjsj");
			http.setRequestProperty("X-NCP-APIGW-API-KEY", "tnrrMYU3Pf1qnuABR7BSvqbCs45V5gw121e38Qtx");
			http.setRequestMethod("GET");
			http.connect();

			InputStreamReader in = new InputStreamReader(http.getInputStream(), "utf-8");
			BufferedReader br = new BufferedReader(in);

			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line).append("\n");
			}

			JSONParser parser = new JSONParser();
			JSONObject jsonObject = null;
			JSONObject jsonObject2;
			JSONArray jsonArray;
			String x = "";
			String y = "";

			try {
				jsonObject = (JSONObject) parser.parse(sb.toString());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			jsonArray = (JSONArray) jsonObject.get("addresses");
			for (int i = 0; i < jsonArray.size(); i++) {
				jsonObject2 = (JSONObject) jsonArray.get(i);
				if (null != jsonObject2.get("x")) {
					x = (String) jsonObject2.get("x").toString();
				}
				if (null != jsonObject2.get("y")) {
					y = (String) jsonObject2.get("y").toString();
				}
			}
			list.add(y);
			list.add(x);

			br.close();
			in.close();
			http.disconnect();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		return list;
	}
}
