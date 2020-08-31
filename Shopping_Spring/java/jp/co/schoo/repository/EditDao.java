package jp.co.schoo.repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.mysql.jdbc.PreparedStatement;

import jp.co.schoo.model.PurchaseOutputModel;
public class EditDao {

	@Autowired
	JdbcTemplate data;

	private ResultSet  rs  = null;
	private PreparedStatement ps = null;


	private final String DETAIL_ITEM_QUERY = "select item.item_id, item.item_name, item.price, stock.quantity from user where item.item_id = ?";


	public List<Map<String,Object>> detailItem(String item_id)throws SQLException {

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
					OutputModel.setItemId(item_id);
					OutputModel.setItemName((String)map.get("item_name"));
					OutputModel.setPrice((Integer)map.get("item_price"));
					OutputModel.setQuantity((Integer)map.get("quantity"));
				}
			}

		} catch(DataAccessException dae) {
			// 例外が発生した場合はModelクラスを空にして処理を終了させる
			list = null;
			dae.printStackTrace();
		}

		return list;
	}

//	public List<Map<String, Object>> selectName(String itemName) throws SQLException {
//		List<Map<String, Object>> itemList = null;
//		PurchaseInputModel inputModel = null;
//
//		try {
//
//			// SQL文を生成
//			itemList = data.queryForList(SELECT_ITEMNAME_QUERY, new Object[]{ "%" + itemName + "%"});
//
//			if(!itemList.isEmpty()) {
//
//				// Modelクラスのインスタンスを生成して検索結果の情報をセット
//				inputModel = new PurchaseInputModel();
//
//				for(Map<String, Object> map : itemList) {
//
//					inputModel.setItemId((String)map.get("item_id"));
//					inputModel.setItemName((String)map.get("item_name"));
//					inputModel.setPrice((Integer)map.get("price"));
//					inputModel.setQuantity((Integer)map.get("quantity"));
//				}
//			}
//		}
//			catch(DataAccessException ce) {
//
//				// JDBCドライバが見つからなかった場合
//				itemList = null;
//				ce.printStackTrace();
//		}
//
//
//		return itemList;
//	}


}
