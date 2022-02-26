package ReviewAndRatings.ReviewDB;

public class ReviewInput {
	private String review;
	private Double rating;
	private int bundleNum;
	private int depth;
	private int bpmId;
	public ReviewInput(String review, Double rating, int bundleNum, int depth, int bpmId) {
		super();
		this.review = review;
		this.rating = rating;
		this.bundleNum = bundleNum;
		this.depth = depth;
		this.bpmId = bpmId;
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
	public int getBundleNum() {
		return bundleNum;
	}
	public void setBundleNum(int bundleNum) {
		this.bundleNum = bundleNum;
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
	@Override
	public String toString() {
		return "ReviewInput [review=" + review + ", rating=" + rating + ". bundleNum" + bundleNum + ", depth=" + depth + ", bpmId=" + bpmId + "]";
	}
	
	

}
