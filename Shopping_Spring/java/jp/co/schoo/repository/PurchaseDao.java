package jp.co.schoo.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mysql.jdbc.PreparedStatement;

import jp.co.schoo.model.PurchaseInputModel;
import jp.co.schoo.model.PurchaseOutputModel;
/**
 * Java入門 購入機能DAOクラス.
 */
@Repository
public class PurchaseDao {

	@Autowired
	JdbcTemplate data;

	private ResultSet  rs  = null;
	private PreparedStatement ps = null;


	private final String PURCHASE_QUERY =
			"select item.item_id, item.item_name, item.price, stock.quantity "
					+ "from item inner join stock on item.item_id = stock.item_id limit 5 offset 0";
	private final String SELECT_ITEM_QUERY =
			"select item.item_id, item.item_name, item.price, stock.quantity from item inner join stock on item.item_id = stock.item_id where item.item_id = ?";

	private final String UPDATE_ITEM_QUERY =
			"update stock set quantity = quantity - ? where item_id = ? ";

	private final String UPDATE_HISTORY_QUERY =
			"insert into history (id, item_id, quantity) values (?, ?, ?)";
	private final String SELECT_HISTORY_QUERY =
			"select history.item_id, item.item_name, history.quantity from history inner join item on history.id = ? and history.item_id = item.item_id";
	private final String SELECT_ITEMNAME_QUERY =
			"select item.item_id, item.item_name, item.price, stock.quantity from item inner join stock on item.item_name = stock.item_name where item.item_name like ?";
	private final String PAGENATION_QUERY =
			"select item.item_id, item.item_name, item.price, stock.quantity "
					+ "from item inner join stock on item.item_id = stock.item_id limit ? offset ?";
	private final String DETAIL_ITEM_QUERY = "select item.item_id, item.item_name, item.price, stock.quantity, item.item_detail from item inner join stock on item.item_id = stock.item_id where item.item_id = ?";
//	limit ? offset (?-1)"

	/**
	 * 購入画面に表示する情報を取得するメソッド
	 */
	public List<Map<String, Object>> itemList(){

		List<Map<String, Object>> itemList = null;	// DB検索結果を格納するリスト
		PurchaseOutputModel outputModel = null;


		try {
			// ユーザ検索処理を実行
			itemList = data.queryForList(PURCHASE_QUERY);
			System.out.println("sqlの結果：" + itemList);

			// 検索結果が空でないことをチェック
			if(!itemList.isEmpty()) {

				// Modelクラスのインスタンスを生成して検索結果の情報をセット
				outputModel = new PurchaseOutputModel();

				for(Map<String, Object> map : itemList) {

					outputModel.setItemId((String)map.get("item_id"));
					outputModel.setItemName((String)map.get("item_name"));
					outputModel.setPrice((Integer)map.get("price"));
					outputModel.setQuantity((Integer)map.get("quantity"));
				}
			}

		} catch(DataAccessException dae) {
			// 例外が発生した場合はModelクラスを空にして処理を終了させる
			itemList = null;
		}

		//		return outputModel;
		return itemList;
	}

	/**
	 * 購入ボタンが押された商品idを取得するメソッド
	 */
	public List<Map<String, Object>> selectItem(String itemId) throws SQLException {

		List<Map<String, Object>> itemList = null;
		PurchaseInputModel inputModel = null;

		try {

			// SQL文を生成
			itemList = data.queryForList(SELECT_ITEM_QUERY, new Object[]{itemId});



			if(!itemList.isEmpty()) {

				// Modelクラスのインスタンスを生成して検索結果の情報をセット
				inputModel = new PurchaseInputModel();

				for(Map<String, Object> map : itemList) {

					inputModel.setItemId((String)map.get("item_id"));
					inputModel.setItemName((String)map.get("item_name"));
					inputModel.setPrice((Integer)map.get("price"));
					inputModel.setQuantity((Integer)map.get("quantity"));
//					inputModel.setAmount((Integer)map.get("item_id"+"list"));
				}
			}
		}
			catch(DataAccessException ce) {

				// JDBCドライバが見つからなかった場合
				itemList = null;
				ce.printStackTrace();
		}


		return itemList;
	}

	/**
	 * 保持クラス
	 */

	public List<Map<String, Object>> selectItem(List<Map<String, Object>> itemList) throws SQLException {

		List<Map<String, Object>> cartList = null;
		PurchaseInputModel inputModel = null;

		try {

			// SQL文を生成
			cartList = data.queryForList(SELECT_ITEM_QUERY, new Object[]{itemList});

			if(!cartList.isEmpty()) {

				// Modelクラスのインスタンスを生成して検索結果の情報をセット
				inputModel = new PurchaseInputModel();

				for(Map<String, Object> map : cartList) {

					inputModel.setItemId((String)map.get("item_id"));
					inputModel.setItemName((String)map.get("item_name"));
					inputModel.setPrice((Integer)map.get("price"));
					inputModel.setQuantity((Integer)map.get("quantity"));
				}
			}
		}
			catch(DataAccessException ce) {

				// JDBCドライバが見つからなかった場合
				cartList = null;
				ce.printStackTrace();
		}


		return cartList;
	}

	/**
	 * 商品名であいまい検索
	 */
	public List<Map<String, Object>> selectName(String itemName) throws SQLException {
		List<Map<String, Object>> itemList = null;
		PurchaseInputModel inputModel = null;

		try {

			// SQL文を生成
			itemList = data.queryForList(SELECT_ITEMNAME_QUERY, new Object[]{ "%" + itemName + "%"});

			if(!itemList.isEmpty()) {

				// Modelクラスのインスタンスを生成して検索結果の情報をセット
				inputModel = new PurchaseInputModel();

				for(Map<String, Object> map : itemList) {

					inputModel.setItemId((String)map.get("item_id"));
					inputModel.setItemName((String)map.get("item_name"));
					inputModel.setPrice((Integer)map.get("price"));
					inputModel.setQuantity((Integer)map.get("quantity"));
				}
			}
		}
			catch(DataAccessException ce) {

				// JDBCドライバが見つからなかった場合
				itemList = null;
				ce.printStackTrace();
		}


		return itemList;
	}




	/**
	 * 在庫数をアップデートするメソッド
	 */
	public void updateItem(String itemId, int qunatity) throws SQLException {


		try {

			// SQL文を生成
			data.update(UPDATE_ITEM_QUERY, new Object[]{qunatity, itemId});


		}
			catch(DataAccessException ce) {

				ce.printStackTrace();
		}

//
//		return itemList;
	}
	/**
	 * 在庫数をアップデートするメソッド
	 */
	public void updateHistory(String id, String itemId, int quantity) throws SQLException {

		try {

			// SQL文を生成
			data.update(UPDATE_HISTORY_QUERY, new Object[]{id, itemId, quantity});


		}
			catch(DataAccessException ce) {


				ce.printStackTrace();
		}

	}



	/**
	 * ユーザの購入履歴を取得します.
	 * @return	購入履歴（ResultSet）
	 * @throws SQLException
	 */
	public List<Map<String, Object>> selectHistory(String id) throws SQLException {

		List<Map<String, Object>> historyList = null;
		PurchaseInputModel historyModel = null;

		try {


			// SQL文を生成
			historyList  = data.queryForList(SELECT_HISTORY_QUERY, new Object[]{id});

			if(!historyList.isEmpty()) {


				// Modelクラスのインスタンスを生成して検索結果の情報をセット
				historyModel = new PurchaseInputModel();

				for(Map<String, Object> map : historyList) {

					historyModel.setItemId((String)map.get("item_id"));
					historyModel.setItemName((String)map.get("item_name"));
					historyModel.setQuantity((Integer)map.get("quantity"));
				}
			}

		} catch(DataAccessException ce) {

			// JDBCドライバが見つからなかった場合
			historyList = null;
			ce.printStackTrace();
		}

		return historyList;
	}

	/**
	 * page取得
	 */

//	public void setTemplate(JdbcTemplate data) {
//		this.data = data;
//	}


	public List<Map<String, Object>> getItemByPage(int total,int pageid)  throws SQLException{

		List<Map<String, Object>> pageList = null;
		PurchaseOutputModel outputModel = null;
		//	    String sql=	"select item.item_id, item.item_name, item.price, stock.quantity "
		//				+ "from item inner join stock on item.item_id = stock.item_id limit"+(pageid-1)+","+total;
		try {

			pageList  = data.queryForList(PAGENATION_QUERY, new Object[]{total, pageid-1});
			System.out.println("sqlの結果：" + pageList);

			if(!pageList.isEmpty()) {

				// Modelクラスのインスタンスを生成して検索結果の情報をセット
				outputModel = new PurchaseOutputModel();

				for(Map<String, Object> map : pageList) {

					outputModel.setItemId((String)map.get("item_id"));
					outputModel.setItemName((String)map.get("item_name"));
					outputModel.setPrice((Integer)map.get("price"));
					outputModel.setQuantity((Integer)map.get("quantity"));
				}

			}}catch(DataAccessException ce) {
				pageList  = null;
			}


			return pageList;
		}

//	private final String DETAIL_ITEM_QUERY = "select item.item_id, item.item_name, item.price, stock.quantity from user where item.item_id = ?";


	public PurchaseOutputModel detailItem(String item_id){

		List<Map<String, Object>> list = null;	// DB検索結果を格納するリスト
		PurchaseOutputModel OutputModel = null;		// itemidのModelクラス



		try {
			list = data.queryForList(DETAIL_ITEM_QUERY, new Object[]{item_id});

			// 検索結果が空でないことをチェック
			if(!list.isEmpty()) {

				// Modelクラスのインスタンスを生成して検索結果の情報をセット
				OutputModel = new PurchaseOutputModel();

				for(Map<String, Object> map : list) {
					// for文で回してmapにid,name,age（リストの中身)をセットしていく。
					OutputModel.setItemId((String)map.get("item_id"));
					OutputModel.setItemName((String)map.get("item_name"));
					OutputModel.setPrice((Integer)map.get("price"));
					OutputModel.setQuantity((Integer)map.get("quantity"));
					OutputModel.setDetail((String)map.get("item_detail"));
				}
			}

		} catch(DataAccessException dae) {
			// 例外が発生した場合はModelクラスを空にして処理を終了させる
			OutputModel = null;
//			dae.printStackTrace();
		}

		return OutputModel;
	}


}
