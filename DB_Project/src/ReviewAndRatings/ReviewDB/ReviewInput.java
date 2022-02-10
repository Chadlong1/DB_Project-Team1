package ReviewAndRatings.ReviewDB;
// 코멘트 Class
public class ReviewInput {
	private String review;
	private Double rating;
	
	public ReviewInput() {}
	public ReviewInput(String review, Double rating) {
		super();
		this.review = review;
		this.rating = rating;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	public Double getRating() {
		return rating;
	}
	public void setRating(Double rating) {
		this.rating = rating;
	}
	@Override
	public String toString() {
		return "reviewtInput [review=" + review + ", rating=" + rating + "]";
	}
	
	
}
