package jp.co.schoo.model;
/**
 * @author Tomoyo.H
 *
 */

/**
 * Java入門 購入処理画面Modelクラス.
 */
public class PurchaseInputModel {

	private static final long serialVersionUID = 1L;

	private String itemId;		// 商品ID
	private String itemName;	// 商品名
	private int price;			// 商品価格
	private int quantity;		// 商品数

	/**
	 * コンストラクタ.<br>
	 * メンバ変数の値を初期化します.
	 */
	public PurchaseInputModel() {
		itemId   = "";
		itemName = "";
		price = 0;
		quantity = 0;
	}

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

}
