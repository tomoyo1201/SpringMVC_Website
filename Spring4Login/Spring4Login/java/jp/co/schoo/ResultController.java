package jp.co.schoo;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.schoo.model.HomeOutputModel;
import jp.co.schoo.repository.PurchaseDao;

/**
 * Java入門 購入履歴一覧表示機能Controllerクラス.
 */

@Controller
public class ResultController {


	@Autowired
	PurchaseDao dao;	// DAOクラス

	/**
	 * 購入完了処理に移る前の直前の処理
	 */
	@RequestMapping(value = "Result", method = RequestMethod.POST)
	public String create(Model model,
			HttpServletRequest request, HttpSession session) throws SQLException  {

		String nextpage = "/create/result";	// 戻り値（次画面を示す文字列）
		//id取得
		String id = ((HomeOutputModel)request.getSession().getAttribute("user_db")).getId();
		//itemid取得
		String itemId  = request.getParameter("item_id");
		//quantity取得
		String quantity= request.getParameter("item_quantity");

		try {
			dao.updateItem(itemId, Integer.parseInt(quantity));
			dao.updateHistory(id, itemId, Integer.parseInt(quantity));
		} catch(SQLException e) {

			e.printStackTrace();

		}


		return nextpage;
	}

	/**
	 * 購入した履歴一覧表示
	 */
	@RequestMapping(value = "/create/history", method = RequestMethod.GET)
	public String history(Model model,
			HttpServletRequest request, HttpSession session) throws SQLException  {

		String nextpage = "/create/history";	// 戻り値（次画面を示す文字列）
		//id取得
		String id = ((HomeOutputModel)request.getSession().getAttribute("user_db")).getId();
		//購入一覧表示
		List<Map<String, Object>> selectHistory = dao.selectHistory(id);


		model.addAttribute("history", selectHistory);


		return nextpage;
	}

}
