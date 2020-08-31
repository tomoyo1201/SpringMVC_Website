package jp.co.schoo.model;
/**
 * @author Tomoyo.H
 *
 */

/**
 * Java入門 購入処理画面Modelクラス.
 */
public class PurchaseOutputModel {

	private static final long serialVersionUID = 1L;

	private String itemId;		// 商品ID
	private String itemName;	// 商品名
	private String detail;	// 概要
	private int price;			// 商品価格
	private int quantity;		//在庫数
	private int amount;  		//購入数

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}



	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * コンストラクタ.<br>
	 * メンバ変数の値を初期化します.
	 */
	public PurchaseOutputModel() {
		itemId   = "";
		itemName = "";
		price = 0;
		quantity = 0;
		amount = 0;
	}

	public PurchaseOutputModel(String itemId, int amount) {
		this.itemId = itemId;
		this.amount = amount;

	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}



}
