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

		List<Restaurant> apis = new ArrayList<Restaurant>();  // apis = 공공데이터 오픈api로 받아온 자료들을 담아줄 리스트 
		List<String> menuType = new ArrayList<>();
		menuType.add("한식");  // 1
		menuType.add("한식");  // 2
		menuType.add("한식");  // 3
		menuType.add("한식");  // 4
		menuType.add("한식");  // 5
		menuType.add("한식");  // 6
		menuType.add("한식");  // 7
		menuType.add("한식");  // 8
		menuType.add("한식");  // 9
		menuType.add("한식");  // 10
		menuType.add("양식");  // 11
		menuType.add("한식");  // 12
		menuType.add("한식");  // 13
		menuType.add("한식");  // 14
		menuType.add("일식");  // 15
		menuType.add("양식");  // 16
		menuType.add("일식");  // 17
		menuType.add("중식");  // 18
		menuType.add("한식");  // 19
		menuType.add("한식");  // 20
		menuType.add("한식");  // 21
		menuType.add("한식");  // 22
		menuType.add("일식");  // 23
		menuType.add("한식");  // 24
		menuType.add("한식");  // 25
		menuType.add("한식");  // 26
		menuType.add("한식");  // 27
		menuType.add("양식");  // 28
		menuType.add("한식");  // 29
		menuType.add("중식");  // 30
		menuType.add("한식");  // 31
		menuType.add("한식");  // 32
		menuType.add("한식");  // 33
		menuType.add("한식");  // 34
		menuType.add("한식");  // 35
		menuType.add("중식");  // 36
		menuType.add("한식");  // 37
		menuType.add("한식");  // 38
		menuType.add("한식");  // 39
		menuType.add("한식");  // 40
		menuType.add("한식");  // 41
		menuType.add("일식");  // 42
		menuType.add("중식");  // 43
		menuType.add("양식");  // 44
		menuType.add("한식");  // 45
		menuType.add("한식");  // 46
		menuType.add("양식");  // 47
		menuType.add("일식");  // 48
		menuType.add("한식");  // 49
		menuType.add("한식");  // 50
		menuType.add("한식");  // 51
		menuType.add("한식");  // 52
		menuType.add("한식");  // 53
		menuType.add("한식");  // 54
		menuType.add("한식");  // 55
		menuType.add("한식");  // 56
		menuType.add("양식");  // 57
		menuType.add("한식");  // 58
		menuType.add("한식");  // 59
		menuType.add("한식");  // 60
		menuType.add("양식");  // 61
		menuType.add("한식");  // 62
		menuType.add("한식");  // 63
		menuType.add("양식");  // 64
		menuType.add("한식");  // 65
		menuType.add("한식");  // 66
		menuType.add("한식");  // 67
		menuType.add("한식");  // 68
		menuType.add("중식");  // 69
		menuType.add("한식");  // 70
		menuType.add("한식");  // 71
		menuType.add("한식");  // 72
		menuType.add("일식");  // 73
		menuType.add("일식");  // 74
		menuType.add("한식");  // 75
		menuType.add("한식");  // 76
		menuType.add("중식");  // 77
		menuType.add("한식");  // 78
		menuType.add("한식");  // 79
		menuType.add("한식");  // 80
		menuType.add("한식");  // 81
		menuType.add("양식");  // 82
		menuType.add("한식");  // 83
		menuType.add("일식");  // 84
		menuType.add("일식");  // 85
		menuType.add("중식");  // 86
		menuType.add("한식");  // 87
		menuType.add("한식");  // 88
		menuType.add("한식");  // 89
		menuType.add("중식");  // 90
		menuType.add("양식");  // 91
		menuType.add("한식");  // 92
		menuType.add("양식");  // 93
		menuType.add("한식");  // 94
		menuType.add("한식");  // 95
		menuType.add("양식");  // 96
		menuType.add("한식");  // 97
		menuType.add("한식");  // 98
		menuType.add("한식");  // 99
		menuType.add("한식");  // 100
		menuType.add("한식");  // 101
		menuType.add("한식");  // 102
		menuType.add("한식");  // 103
		menuType.add("양식");  // 104
		menuType.add("양식");  // 105
		menuType.add("일식");  // 106
		menuType.add("일식");  // 107
		menuType.add("한식");  // 108
		menuType.add("한식");  // 109
		menuType.add("한식");  // 110
		menuType.add("한식");  // 111
		menuType.add("한식");  // 112
		menuType.add("한식");  // 113
		menuType.add("한식");  // 114
		menuType.add("한식");  // 115
		menuType.add("중식");  // 116
		menuType.add("양식");  // 117
		menuType.add("한식");  // 118
		menuType.add("일식");  // 119
		menuType.add("한식");  // 120
		menuType.add("양식");  // 121
		menuType.add("일식");  // 122
		menuType.add("양식");  // 123
		menuType.add("한식");  // 124
		menuType.add("양식");  // 125
		menuType.add("한식");  // 126
		menuType.add("한식");  // 127
		menuType.add("한식");  // 128
		menuType.add("양식");  // 129
		menuType.add("양식");  // 130
		menuType.add("한식");  // 131
		menuType.add("한식");  // 132
		menuType.add("한식");  // 133
		
		for (int i = 0; i < item.size(); i++) {
			JSONObject tmp = (JSONObject) item.get(i);
			String title = (String) tmp.get("MAIN_TITLE");
			String type = menuType.get(i);
			String menu = (String) tmp.get("RPRSNTV_MENU");
			String loca = (String) tmp.get("GUGUN_NM");
			String addr = (String) tmp.get("ADDR1");
			String tel = (String) tmp.get("CNTCT_TEL");
			String time = (String) tmp.get("USAGE_DAY_WEEK_AND_TIME");
			String comment = (String) tmp.get("ITEMCNTNTS");
			String img = (String) tmp.get("MAIN_IMG_NORMAL");
			String thumb = (String) tmp.get("MAIN_IMG_THUMB");
			
			// api = 오픈api에서 필요한 정보들을 반복문을 통해 하나씩 저장
			Restaurant api = new Restaurant(title, type, menu, loca, addr, tel, time, comment, img, thumb);
			
			// 저장한 api값들을 apis라는 리스트에 추가
			apis.add(api);
		}
		return apis;
		
	}
}