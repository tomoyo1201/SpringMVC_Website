package jp.co.schoo.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.schoo.model.HomeOutputModel;
import jp.co.schoo.model.InsertOutputModel;

/**
 * Java入門 ログイン機能DAOクラス.
 */
@Repository
public class HomeDao {

	@Autowired
	JdbcTemplate data;	// Spring JDBCのテンプレートクラス

	/** ユーザ検索用のSQL文 */
	private final String SELECT_USER_QUERY =
			"select name, age from user where id = ? and pass = ?";

	/**
	 * ユーザ検索処理.
	 * @param id		ログインユーザID
	 * @param password	ログインパスワード
	 * @return	ユーザ画面用のModelクラス
	 */
	//ユーザー検索(ID,passwordを用いて)
	private final String INSERT_USER_QUERY =
			"insert into user(id, pass, name, age) values(? , ?, ?, ?)";


	//ユーザー検索(IDのみ)
	private final String ID_USER_QUERY =
			"select name, age from user where id = ?";




	/**
	 * idとパスワードでユーザ検索
	 */
	public HomeOutputModel selectUser(String id, String password) {

		List<Map<String, Object>> list = null;	// DB検索結果を格納するリスト
		HomeOutputModel outputModel = null;		// ユーザ画面用のModelクラス

		try {
			// ユーザ検索処理を実行
			list = data.queryForList(SELECT_USER_QUERY, new Object[]{id, password});

			// 検索結果が空でないことをチェック
			if(!list.isEmpty()) {

				// Modelクラスのインスタンスを生成して検索結果の情報をセット
				outputModel = new HomeOutputModel();

				for(Map<String, Object> map : list) {
					// for文で回してmapにid,name,age（リストの中身)をセットしていく。
					outputModel.setId(id);
					outputModel.setName((String)map.get("name"));
					outputModel.setAge((Integer)map.get("age"));
				}
			}

		} catch(DataAccessException dae) {
			// 例外が発生した場合はModelクラスを空にして処理を終了させる
			outputModel = null;
		}

		return outputModel;
	}


	/**
	 * 新規ユーザ登録
	 */
	public InsertOutputModel insertUser(String id, String password, String name, String age) {


		InsertOutputModel insertoutputModel = null;

		try {
			insertoutputModel = new InsertOutputModel();
			insertoutputModel.setId(id);
			insertoutputModel.setPassword(password);
			insertoutputModel.setName(name);
			insertoutputModel.setAge(age);
		}catch(DataAccessException e) {
			insertoutputModel = null;

		}

		data.update(INSERT_USER_QUERY,  new Object[]{id, password, name, Integer.parseInt(age)});
		System.out.println("■■■■■isnert実行後■■：" + data.toString());

		return insertoutputModel;
	}


	/**
	 * 新規ユーザ登録でidとパスワードが同じユーザが存在しないかをチェックするメソッド
	 */
	public InsertOutputModel insertselectUser(String id, String password) {

		List<Map<String, Object>> list = null;	// DB検索結果を格納するリスト
		InsertOutputModel insertoutputModel = null;		// ユーザ画面用のModelクラス

		try {
			// ユーザ検索処理を実行
			list = data.queryForList(SELECT_USER_QUERY, new Object[]{id, password});

			// 検索結果が空でないことをチェック
			if(!list.isEmpty()) {

				// Modelクラスのインスタンスを生成して検索結果の情報をセット
				insertoutputModel = new InsertOutputModel();

				for(Map<String, Object> map : list) {

					insertoutputModel.setId(id);
					insertoutputModel.setPassword((String)map.get("password"));
					insertoutputModel.setName((String)map.get("name"));




				}
			}

		} catch(DataAccessException dae) {
			// 例外が発生した場合はModelクラスを空にして処理を終了させる
			insertoutputModel = null;
		}

		return insertoutputModel;
	}
	/**
	 * 新規ユーザ登録で既に既存のidが登録されていないかをチェックするメソッド
	 */
	public InsertOutputModel insertselectId(String id) {

		List<Map<String, Object>> list = null;	// DB検索結果を格納するリスト
		InsertOutputModel insertoutputModel = null;		// ユーザ画面用のModelクラス

		try {
			// ユーザ検索処理を実行
			list = data.queryForList(ID_USER_QUERY, new Object[]{id});

			// 検索結果が空でないことをチェック
			if(!list.isEmpty()) {

				// Modelクラスのインスタンスを生成して検索結果の情報をセット
				insertoutputModel = new InsertOutputModel();



				insertoutputModel.setId(id);


			}

		} catch(DataAccessException dae) {
			// 例外が発生した場合はModelクラスを空にして処理を終了させる
			insertoutputModel = null;
		}

		return insertoutputModel;
	}
}


