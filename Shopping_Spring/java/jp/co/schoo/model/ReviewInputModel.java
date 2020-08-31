package jp.co.schoo.model;

public class ReviewInputModel {

	private static final long serialVersionUID = 1L;

	private String review_id;		// メッセージ
	private String review_name;		// ID
	private String review_user;// Password
	private String title;	// 名前
	private byte[] image;		// 年齢
	private String Vote;		// 年齢
	public String getReview_id() {
		return review_id;
	}
	public void setReview_id(String review_id) {
		this.review_id = review_id;
	}
	public String getReview_name() {
		return review_name;
	}
	public void setReview_name(String review_name) {
		this.review_name = review_name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getReview_user() {
		return review_user;
	}
	public void setReview_user(String review_user) {
		this.review_user = review_user;
	}
	public String getVote() {
		return Vote;
	}
	public void setVote(String vote) {
		Vote = vote;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
}
