package busan;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

class WebConnection {
	String json;

	WebConnection() throws Exception {
		String address = "http://apis.data.go.kr/6260000/FoodService/getFoodKr?serviceKey=1OOfOaPBVyqHwAq%2B%2BbH3TIBpNvwL6ZN7aDCDq6Mpog0Jx5lkxtk%2B%2F%2B0O8KUtGNx%2BOTq9fy7PEWRnIajviJbNOQ%3D%3D&pageNo=1&numOfRows=1000&resultType=json";
		BufferedReader br;
		URL url;
		HttpURLConnection conn;
		String protocol = "GET";

		url = new URL(address);
		conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod(protocol);
		br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

		json = br.readLine();
	}
}

public class Res2 {
	public static List<Restaurant> getOpneApiData() throws Exception{
		WebConnection wc = new WebConnection();

		String json = wc.json;

		JSONParser parser = new JSONParser();
		JSONObject obj = (JSONObject) parser.parse(json);
		JSONObject channel = (JSONObject) obj.get("getFoodKr");

		JSONArray item = (JSONArray) channel.get("item");

		List<Restaurant> apis = new ArrayList<Restaurant>();
		
		for (int i = 0; i < item.size(); i++) {
			JSONObject tmp = (JSONObject) item.get(i);
			String title = (String) tmp.get("MAIN_TITLE");
			String menu = (String) tmp.get("RPRSNTV_MENU");
			String loca = (String) tmp.get("GUGUN_NM");
			String addr = (String) tmp.get("ADDR1");
			String tel = (String) tmp.get("CNTCT_TEL");
			String time = (String) tmp.get("USAGE_DAY_WEEK_AND_TIME");
			String comment = (String) tmp.get("ITEMCNTNTS");
			String img = (String) tmp.get("MAIN_IMG_NORMAL");
			String thumb = (String) tmp.get("MAIN_IMG_THUMB");
			
			Restaurant api = new Restaurant(title, menu, loca, addr, tel, time, comment, img, thumb);
			
			apis.add(api);
		}
		return apis;
		
	}
}
