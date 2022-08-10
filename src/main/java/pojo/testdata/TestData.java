package pojo.testdata;

public class TestData {

	private String courseName;
	private String courseId;
	private String paymentMethod;
	private String couponCode;
	private String paymentType;
	private boolean isGuestUser;
	public boolean isGuestUser() {
		return isGuestUser;
	}
	public void setGuestUser(boolean isGuestUser) {
		this.isGuestUser = isGuestUser;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public String getCouponCode() {
		return couponCode;
	}
	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

}
