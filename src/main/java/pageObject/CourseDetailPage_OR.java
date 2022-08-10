package pageObject;

import java.util.List;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class CourseDetailPage_OR {

	@AndroidFindBy(id = "tv_offer_title_pack")
	private List<MobileElement> listLableOfferAvailable;

	@AndroidFindBy(id = "btn_buy_one")
	private List<MobileElement> listBtnBuyNow;

	@AndroidFindBy(id = "bt_buy_now_pack")
	private List<MobileElement> listBtnBuyNowPack;

	@AndroidFindBy(id = "tv_offer_name")
	private List<MobileElement> listOfferName;

	@AndroidFindBy(id = "tv_apply_offer")
	private List<MobileElement> listBtnBuyApplyOffer;

	public List<MobileElement> getListLableOfferAvailable() {
		return listLableOfferAvailable;
	}

	public void setListLableOfferAvailable(List<MobileElement> listLableOfferAvailable) {
		this.listLableOfferAvailable = listLableOfferAvailable;
	}

	public List<MobileElement> getListBtnBuyNow() {
		return listBtnBuyNow;
	}

	public void setListBtnBuyNow(List<MobileElement> listBtnBuyNow) {
		this.listBtnBuyNow = listBtnBuyNow;
	}

	public List<MobileElement> getListBtnBuyNowPack() {
		return listBtnBuyNowPack;
	}

	public void setListBtnBuyNowPack(List<MobileElement> listBtnBuyNowPack) {
		this.listBtnBuyNowPack = listBtnBuyNowPack;
	}

	public List<MobileElement> getListOfferName() {
		return listOfferName;
	}

	public void setListOfferName(List<MobileElement> listOfferName) {
		this.listOfferName = listOfferName;
	}

	public List<MobileElement> getListBtnBuyApplyOffer() {
		return listBtnBuyApplyOffer;
	}

	public void setListBtnBuyApplyOffer(List<MobileElement> listBtnBuyApplyOffer) {
		this.listBtnBuyApplyOffer = listBtnBuyApplyOffer;
	}
	
	
	@AndroidFindBy(id = "tv_offer_change_pack")
	private List<MobileElement> listLableChangePack;

	public List<MobileElement> getListLableChangePack() {
		return listLableChangePack;
	}
	
	
	@AndroidFindBy(id = "et_coupon_code")
	private List<MobileElement> listTextCouponCode;

	public List<MobileElement> getListTextCouponCode() {
		return listTextCouponCode;
	}
	
	@AndroidFindBy(id = "tv_apply_input_offer")
	private List<MobileElement> listBtnApplyInputOffer;

	public List<MobileElement> getListBtnApplyInputOffer() {
		return listBtnApplyInputOffer;
	}
	
	
	
	
	

}
