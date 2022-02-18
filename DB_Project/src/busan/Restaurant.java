package busan;

public class Restaurant {
	private String title;
	private String type;
	private String menu;
	private String loca;
	private String addr;
	private String tel;
	private String time;
	private String comment;
	private String img;
	private String thumb;
	private String rating;

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public Restaurant(String title, String type, String menu, String loca, String addr, String tel, String time,
			String comment, String img, String thumb) {
		super();
		this.title = title;
		this.type = type;
		this.menu = menu;
		this.loca = loca;
		this.addr = addr;
		this.tel = tel;
		this.time = time;
		this.comment = comment;
		this.img = img;
		this.thumb = thumb;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public String getLoca() {
		return loca;
	}

	public void setLoca(String loca) {
		this.loca = loca;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getThumb() {
		return thumb;
	}

	public void setThumb(String thumb) {
		this.thumb = thumb;
	}

	@Override
	public String toString() {
		return "Restaurant [title=" + title + ", menu=" + menu + ", loca=" + loca + ", addr=" + addr + ", tel=" + tel
				+ ", time=" + time + ", comment=" + comment + ", img=" + img + ", thumb=" + thumb + "]";
	}

}