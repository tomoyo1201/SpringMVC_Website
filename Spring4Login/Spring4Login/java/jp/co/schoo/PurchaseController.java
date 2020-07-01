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


	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@ModelAttribute PurchaseOutputModel purchase, Model model,
			HttpServletRequest request, HttpServletResponse response) {

		String nextpage = "/create/input";	// 戻り値（次画面を示す文字列）



		PurchaseInputModel pr= new PurchaseInputModel();




		model.addAttribute("pr", pr);

		List<Map<String, Object>> itemList = dao.itemList();


		model.addAttribute("output", itemList);


		return nextpage;
	}






    @RequestMapping(value ="Confirm", method = RequestMethod.POST)
    public String confirm(@ModelAttribute PurchaseInputModel purchase, Model model,
			HttpServletRequest request, HttpSession session) throws SQLException {




		PurchaseInputModel pr= new PurchaseInputModel();
		System.out.println(pr);
		String nextpage = "/create/confirm";

		System.out.println(pr.getItemId()+"list");

	     Enumeration<String> names = request.getParameterNames();



			String name  = "";		// 現在のパラメータ名
			String itemId= "";		// 商品ID
			String quantity= "";	// 購入数
			String bean ="";





	        while (names.hasMoreElements()) {

	            // 渡ってきたパラメータを順番に処理
	            // パラメータ名を取得
	            name = names.nextElement();

	            String temp = request.getParameter(name);

	            // 購入ボタンがクリックされている場合は「購入」のパラメータが取得できる
	            if ("buy".equals(request.getParameter(name))) {

	                // 購入ボタンに付属している値（value）が商品IDになる
	                itemId = name;
	            }
	        }


	// ここからの続きはservletのサンプルソースのBuyItemServlet.javaを参考にして実装。

			quantity = request.getParameter(itemId + "list");

//			pr.setQuantity(pr.getQuantity());

			request.setAttribute("quantity", quantity);

			List<Map<String, Object>> itemList = dao.selectItem(itemId);

			model.addAttribute("pr", pr);

			model.addAttribute("input", itemList);


		// GETメソッドのパラメータ名を取得




        return nextpage;
    }




}