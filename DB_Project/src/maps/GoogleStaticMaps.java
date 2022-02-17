package maps;

public class GoogleStaticMaps {
	public static String getStaticMapURL(String latitude, String longitude) {
		String imageUrl = null;
		try {
			imageUrl = "https://maps.googleapis.com/maps/api/staticmap?center=" 
						+ latitude 
						+ "," 
						+ longitude
						+ "&zoom=15&size=350x250&key=AIzaSyAerudiODqyJdKXcFjS3k82-7jD9ijSZxE";
					

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return imageUrl;

	}
}