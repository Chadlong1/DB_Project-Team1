package busan;

import java.util.List;

import CommentAndRatings.CommentDB.CommentRepository;

public class Main {
	public static void main(String[] args) {
		Repository repo = new Repository();
//		repo.dropTable();
		repo.createTable();

		CommentRepository crepo = new CommentRepository();
		crepo.createCommentTable();

		try {
			List<Restaurant> apis = Res2.getOpneApiData();
			repo.insertAll(apis);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
