package busan;

import java.util.List;

import ReviewAndRatings.ReviewDB.ReviewRepository;

public class Main {
	public static void main(String[] args) {
		Repository repo = new Repository();
		repo.createTable();
		System.out.println("테이블 생성");

		ReviewRepository reviewRepo = new ReviewRepository();
		reviewRepo.createReviewTable();
		reviewRepo.CreateReCommentTable();
		
		while (repo.discriminant()) {
			try {
				List<Restaurant> apis = Res2.getOpneApiData();
				repo.insertAll(apis);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
