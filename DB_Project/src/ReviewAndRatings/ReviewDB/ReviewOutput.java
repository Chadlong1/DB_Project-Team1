package ReviewAndRatings.ReviewDB;

import java.sql.Timestamp;
import java.time.LocalDateTime;

// 코멘트 Class
public class ReviewOutput {
	private int reviewId;
	private String review;
	private Double rating;
	private int depth;
	private int bpmId;
	private Timestamp writingTime;
	public ReviewOutput(int reviewId, String review, Double rating, int depth, int bpmId, Timestamp writingTime){
		super();
		this.reviewId = reviewId;
		this.review = review;
		this.rating = rating;
		this.depth = depth;
		this.bpmId = bpmId;
		this.writingTime = writingTime;
	}
	
	public int getReviewId() {
		return reviewId;
	}
	public void setReveiwId(int reviewId) {
		this.reviewId = reviewId;
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
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public int getBpmId() {
		return bpmId;
	}
	public void setBpmId(int bpmId) {
		this.bpmId = bpmId;
	}
	public Timestamp getTimestamp() {
		return writingTime;
	}
	public void setTimestamp(Timestamp writingTime) {
		this.writingTime = writingTime;
	}
	@Override
	public String toString() {
		return "ReviewInput [reviewId=" + reviewId + ", review=" + review + ", rating=" + rating + ", depth=" + depth + ", bpmId=" + bpmId + ", writingTime=" + writingTime + "]";
	}
	
	
	
}
