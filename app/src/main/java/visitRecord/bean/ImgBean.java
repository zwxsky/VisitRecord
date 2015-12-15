package visitRecord.bean;

public class ImgBean {
	private String path;// 图片的路径
	private String value;// 图片的描述

	public ImgBean() {
	}

	public ImgBean(String path, String value) {
		this.path = path;
		this.value = value;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
