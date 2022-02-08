package CommentAndRatings.CommentDB;
// 코멘트 Class
public class CommentInput {
	private String comment;
	private Double rating;
	
	public CommentInput() {}
	public CommentInput(String comment, Double rating) {
		super();
		this.comment = comment;
		this.rating = rating;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Double getRating() {
		return rating;
	}
	public void setRating(Double rating) {
		this.rating = rating;
	}
	@Override
	public String toString() {
		return "commentInput [comment=" + comment + ", rating=" + rating + "]";
	}
	
	
}
