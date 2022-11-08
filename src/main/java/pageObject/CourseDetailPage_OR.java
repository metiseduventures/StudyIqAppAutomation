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

	@AndroidFindBy(id = "coupon_code_added_done")
	private MobileElement applyCodeAfterInputBtnElement;

	public MobileElement applyCodeAfterInputBtn() {
		return applyCodeAfterInputBtnElement;
	}

	@AndroidFindBy(id = "coupon_code_added_cancel")
	private MobileElement cancelCodeAfterInputBtnElement;

	public MobileElement cancelCodeAfterInputBtn() {
		return cancelCodeAfterInputBtnElement;
	}

	@AndroidFindBy(id = "tv_offer_change_pack")
	private List<MobileElement> listLableChangePack;

	public List<MobileElement> getListLableChangePack() {
		return listLableChangePack;
	}

	@AndroidFindBy(id = "tv_available_offer_label")
	private MobileElement availableOfferLabelBox;

	public MobileElement availOfferLabelBox() {
		return availableOfferLabelBox;
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

	@AndroidFindBy(id = "tv_pack_title")
	private MobileElement packTitleElement;

	public MobileElement packTitle() {
		return packTitleElement;
	}

	@AndroidFindBy(id = "tv_selling_price_pack")
	private MobileElement packSellingPriceElement;

	public MobileElement packSellingPrice() {
		return packSellingPriceElement;
	}

	@AndroidFindBy(id = "tv_offer_discount_pack")
	private MobileElement packDiscountPriceElement;

	public MobileElement packDiscountPrice() {
		return packDiscountPriceElement;
	}

	@AndroidFindBy(id = "view_details_ps")
	private MobileElement viewDetailsBtnElement;

	public MobileElement viewDetailsBtn() {
		return viewDetailsBtnElement;
	}

	@AndroidFindBy(id = "tv_base_price_psd")
	private MobileElement viewDetailsCoursePriceElement;

	public MobileElement viewDetailsCoursePrice() {
		return viewDetailsCoursePriceElement;
	}

	@AndroidFindBy(id = "tv_discount_psd")
	private MobileElement viewDetailsDiscountPriceElement;

	public MobileElement viewDetailsDiscountPrice() {
		return viewDetailsDiscountPriceElement;
	}

	@AndroidFindBy(id = "tv_selling_price_psd")
	private MobileElement viewDetailsTotalPriceElement;

	public MobileElement viewDetailsTotalPrice() {
		return viewDetailsTotalPriceElement;
	}

	@AndroidFindBy(id = "ivClose")
	private MobileElement viewDetailsCloseBtnElement;

	public MobileElement viewDetailsCloseBtn() {
		return viewDetailsCloseBtnElement;
	}

	@AndroidFindBy(id = "tv_emi_title_pack")
	private MobileElement emiOptionTitleElement;

	public MobileElement emiOptionTitle() {
		return emiOptionTitleElement;
	}

	@AndroidFindBy(id = "tv_emi_desc_pack")
	private MobileElement emiDescElement;

	public MobileElement emiDesc() {
		return emiDescElement;
	}

	@AndroidFindBy(id = "iv_emi_dialog")
	private MobileElement emiDialogBtnElement;

	public MobileElement emiDialogBtn() {
		return emiDialogBtnElement;
	}

	@AndroidFindBy(id = "iv_check_blank_pack")
	private MobileElement emiCheckBtnElement;

	public MobileElement emiCheckedBtn() {
		return emiCheckBtnElement;
	}

	@AndroidFindBy(id = "tv_emi_title")
	private List<MobileElement> emiTitlesOfInstallment;

	public List<MobileElement> getListEmiTitlesOfInstallment() {
		return emiTitlesOfInstallment;
	}

	@AndroidFindBy(id = "tv_emi_validity")
	private List<MobileElement> emiValidityOfInstallments;

	public List<MobileElement> getListEmiValidityOfInstallments() {
		return emiValidityOfInstallments;
	}

	@AndroidFindBy(id = "tv_emi_amount")
	private List<MobileElement> emiAmountOfInstallments;

	public List<MobileElement> getListEmiAmountOfInstallments() {
		return emiAmountOfInstallments;
	}

	@AndroidFindBy(xpath = "(//android.widget.Toast)[1]")
	private MobileElement toastInvalidCouponElement;

	public MobileElement toastInvalidCoupon() {
		return toastInvalidCouponElement;
	}

	@AndroidFindBy(id = "tv_offer_title_pack")
	private MobileElement noOfOffersAvailElement;

	public MobileElement noOfOffersAvail() {
		return noOfOffersAvailElement;
	}

	@AndroidFindBy(id = "tv_payment_status_pc")
	private MobileElement paymentStatusElement;

	public MobileElement paymentStatus() {
		return paymentStatusElement;
	}

	@AndroidFindBy(id = "tv_payment_desc_pc")
	private MobileElement paymentStatusDescElement;

	public MobileElement paymentStatusDesc() {
		return paymentStatusDescElement;
	}

	@AndroidFindBy(id = "bt_action_pc")
	private MobileElement goToLibraryBtnElement;

	public MobileElement goToLibraryBtn() {
		return goToLibraryBtnElement;
	}

	@AndroidFindBy(id = "et_name")
	private List<MobileElement> formName;

	public List<MobileElement> getListBookingFormName() {
		return formName;
	}

	@AndroidFindBy(id = "et_mobile")
	private List<MobileElement> formPhoneNo;

	public List<MobileElement> getListBookingFormPhoneNo() {
		return formPhoneNo;
	}

	@AndroidFindBy(id = "et_addresss")
	private List<MobileElement> formAddress;

	public List<MobileElement> getListBookingFormAddress() {
		return formAddress;
	}

	@AndroidFindBy(id = "et_email")
	private List<MobileElement> formEmail;

	public List<MobileElement> getListBookingFormEmail() {
		return formEmail;
	}

	@AndroidFindBy(id = "et_pin_code")
	private List<MobileElement> formPincode;

	public List<MobileElement> getListBookingFormPinCode() {
		return formPincode;
	}

	@AndroidFindBy(id = "et_city")
	private List<MobileElement> formCity;

	public List<MobileElement> getListBookingFormCity() {
		return formCity;
	}

	@AndroidFindBy(id = "et_state")
	private List<MobileElement> formState;

	public List<MobileElement> getListBookingFormState() {
		return formState;
	}

	@AndroidFindBy(id = "bt_buy_now_pack")
	private MobileElement buyNowElement;

	public MobileElement buyNowBtn() {
		return buyNowElement;
	}

}
