package jp.co.schoo;

import java.sql.SQLException;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.schoo.model.PurchaseInputModel;
import jp.co.schoo.model.PurchaseOutputModel;
import jp.co.schoo.repository.PurchaseDao;

/**
 * Java入門 購入処理機能Controllerクラス.
 */
@Controller
public class PurchaseController {


	@Autowired
	PurchaseDao dao;	// DAOクラス

	/**
	 * アイテム購入画面に移る際の直前の処理
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@ModelAttribute PurchaseOutputModel purchase, Model model,
			HttpServletRequest request, HttpServletResponse response) {

		String nextpage = "/create/input";	// 戻り値（次画面を示す文字列）
		//購入画面用modelクラスのインスタンス化
		PurchaseInputModel pr= new PurchaseInputModel();
		//モデルにセット
		model.addAttribute("pr", pr);
		//購入できるアイテムを表示させるためにリストに格納
		List<Map<String, Object>> itemList = dao.itemList();
		//outputオブジェクトにこのリストをセットする
		model.addAttribute("output", itemList);


		return nextpage;
	}





	/**
	 * 購入確認画面に移る際の処理
	 */
    @RequestMapping(value ="Confirm", method = RequestMethod.POST)
    public String confirm(@ModelAttribute PurchaseInputModel purchase, Model model,
			HttpServletRequest request, HttpSession session) throws SQLException {



    	//購入確認画面用modelクラスのインスタンス化
		PurchaseInputModel pr= new PurchaseInputModel();

		String nextpage = "/create/confirm";
		//コンソールで値がとれているかを見る
		System.out.println(pr.getItemId()+"list");
		// GETメソッドのパラメータ名を取得
	    Enumeration<String> names = request.getParameterNames();



			String name  = "";		// 現在のパラメータ名
			String itemId= "";		// 商品ID
			String quantity= "";	// 購入数

			// 購入ボタンがクリックされた場所を特定
	        while (names.hasMoreElements()) {

	            // 渡ってきたパラメータを順番に処理
	            // パラメータ名を取得
	            name = names.nextElement();
	            //ボタンの名前を取得してtempに代入
	            String temp = request.getParameter(name);

	            // 購入ボタンがクリックされている場合は「購入」のパラメータが取得できる
	            if ("buy".equals(request.getParameter(name))) {

	                // 購入ボタンに付属している値（value）が商品IDになる
	                itemId = name;
	            }
	        }
	        //購入数の値を取得
			quantity = request.getParameter(itemId + "list");
			request.setAttribute("quantity", quantity);
			//購入するアイテムの情報を取得
			List<Map<String, Object>> itemList = dao.selectItem(itemId);

			model.addAttribute("pr", pr);

			model.addAttribute("input", itemList);

        return nextpage;
    }




}