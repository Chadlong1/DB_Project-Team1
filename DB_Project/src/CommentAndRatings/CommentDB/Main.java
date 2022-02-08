package CommentAndRatings.CommentDB;

public class Main {
	public static void main(String[] args) {
		CommentRepository repo = new CommentRepository();
		
		repo.createCommentTable();
	}
}
