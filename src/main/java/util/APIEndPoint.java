package util;

public class APIEndPoint {
	static String strLang;

	static {
		strLang = System.getProperty("lang");
		if (strLang == null || strLang.isEmpty() || strLang.equalsIgnoreCase("null")) {
			strLang = "en";
			System.out.println("strLang: " + strLang);
		}

	}

	public static final String MASTER_BUISNESS_ITEM = "account/master/business-items";

	// Profile

	public static final String SELF_PROFILE_DETAILS = "account/self/profile/details?lang="
			+ System.getProperty("lang");;

	public static final String UPDATE_PROFILE_DETAILS = "account/profile/edit";

	public static final String SUBMIT_PROFILE = "v2/account/app/submitfeedback";

	public static final String ACCOUNT_UPDATE_RATING = "account/update/rating";
	// Location

	public static final String GET_LOCATION_SEARCH = "v2/account/search/cities?";

	public static final String GET_AUTO_DETECT_LOCATION = "v2/account/auto-detect/cities?";

	public static final String GET_DISTRICT_FILTER = "v2/account/search/district?";

	// Loader/ Buyer

	public static final String LOADER_LIST = "account/filter/la?page=1";

	public static final String BUYER_LIST = "account/filter/ca?page=1";

	public static final String ADD_LOADER = "account/add/party?role=";

	// EBijak

	public static final String EBIJAK_CONFIG = "v2/account/ebijak/config?lang=" + System.getProperty("lang");

	public static final String CREATE_EBIJAK = "v2/order/create/ebijak?lang=" + System.getProperty("lang");

	// ORDER

	public static final String ADD_ORDER = "v2/order/create/new?lang=" + strLang;

	public static final String ORDER_LIST = "v2/order/paginated/list?role=";

	public static final String ORDER_DETAILS = "order/details/";

	public static final String ORDER_UPDATE = "order/payment/record/create?lang=" + System.getProperty("lang");

	public static final String ADD_LOGISTIC = "v2/order/create/logistic/";

	public static final String ORDER_CANCEL = "v2/order/cancel/order/";

	// Mandi Rate

	public static final String MANDI_RATE_COMMODITY = "commodity/mandilist";

	public static final String MANDI_DETAILS = "commodity/mandidetail";

	public static final String MANDI_COMMODITY_TREND = "mandi/commoditypricetrend";

	public static final String USER_DISTINCT_COMMODITY = "commodity/distinctlist";

	// bank accounts

	public static final String GET_BANK_ACCOUNT_LIST = "account/la/bank/list/";

	public static final String ADD_BANK_ACCOUNT = "account/la/bank/list/add/";

	public static final String DELETE_BANK_ACCOUNT = "account/bank/delete/";

	public static final String PRIMARY_BANK_ACCOUNT = "account/bank/primary/";

	// DS FEED

	public static final String GET_LIST_SUPPLY = "list/supply";

	public static final String GET_QUANTITY_UNITS = "fetch/quanity/units";

	public static final String CREATE_DEMAND = "v2/create/demand";

	public static final String APPROVE_DEMAND = "approve_demand";

	public static final String CREATE_SUPPLY = "v2/create/supply";

	public static final String APPROVE_SUPPLY = "approve_supply";

	// LA CA Filter
	public static final String GET_GLOBAL_SEARCH = "account/filter/global/";

	// Cash back

	public static final String GET_CASHBACK_CONFIG = "v2/account/";

	// user create
	public static final String USER_CREATE = "account/user/form";

	// Referral

	public static final String GET_USER_REFERRAL_CODE = "refer/code/";

	public static final String GET_USER_REFERRAL_COUNT = "refer/count/";

	public static final String GET_USER_REFERRAL_DETAIL = "refer/details/";

	public static final String COMMODITY_SEARCH_V2 = "v2/account/search/commodity";

	// Payment
	public static final String GET_PAYMENT_LIST = "v2/order/paginated/payment/list";

	public static final String RECORD_PAYMENT = "order/payment/record/create";

	public static final String INSTANT_PAYMENT_ADJUST = "account/instant/pay-adjust/";

	public static final String CREDIT_CHECK = "order/payment/pay/later/credit/verify";

	public static final String PAYMENT_DETAILS = "order/payment/details/";

	public static final String GET_Order_LIST = "v2/order/paginated/list";

	public static final String BIJAK_CRDIT_PAYMENT = "order/payment/pay/later/create?lang="
			+ System.getProperty("lang");

	public static final String GET_SELF_CREDIT_CHECK = "v2/account/self/credit/details?lang="
			+ System.getProperty("lang");

	public static final String PAYMENT_REMINDER = "communication/initiate?lang=" + System.getProperty("lang");

	// KYC
	public static final String KYC_UPDATE = "account/kyc/update";

	public static final String KYC_STATUS = "account/kyc/details";

	// App Config
	public static final String GET_APP_CONFIG = "v2/account/app/config?lang=" + System.getProperty("lang");

}
