package busan;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream.GetField;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

public class Main {
	public static void main(String[] args) {
		Repository repo = new Repository();
		repo.createTable();
	

		try {
			List<Restaurant> apis = Res2.getOpneApiData();
			repo.insertAll(apis);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
