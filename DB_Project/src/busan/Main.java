package busan;

import java.util.List;

import ReviewAndRatings.ReviewDB.ReviewRepository;

public class Main {
	public static void main(String[] args) {
		Repository repo = new Repository();
//		repo.dropTable();
		repo.createTable();

		ReviewRepository reviewRepo= new ReviewRepository();
		reviewRepo.createReviewTable();
		
		try {
			List<Restaurant> apis = Res2.getOpneApiData();
			repo.insertAll(apis);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
