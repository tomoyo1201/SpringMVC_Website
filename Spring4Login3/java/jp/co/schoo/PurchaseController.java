package jp.co.schoo;

import java.sql.SQLException;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jp.co.schoo.model.HomeOutputModel;
import jp.co.schoo.model.PurchaseInputModel;
import jp.co.schoo.model.PurchaseOutputModel;
import jp.co.schoo.repository.PurchaseDao;

/**
 * Java入門 購入処理機能Controllerクラス.
 */
@Controller
public class PurchaseController {

	List<Map<String, Object>> cartList = new ArrayList<Map<String, Object>>();

//	Map<String, String> map = new HashMap<>();
//
//	List<String> indexes = new ArrayList<String>(map.keySet());



	@Autowired
	PurchaseDao dao;	// DAOクラス
/*	EditDao dao1;		// EditDAOクラス
*/	/**
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

//		//itemid取得
//		String itemId  = request.getParameter("item_id");
//		//quantity取得
//		String quantity= request.getParameter("item_quantity");
//
//		if(itemId!=null && quantity!=null) {
//
//
//		}


		return nextpage;
	}
	/**
	 * 購入継続画面に移る際の処理
	 */

    @RequestMapping(value ="Continue", method = RequestMethod.POST)
    public String keep(@ModelAttribute PurchaseInputModel purchase, Model model,
			HttpServletRequest request, HttpSession session) throws SQLException {



		String id = ((HomeOutputModel)request.getSession().getAttribute("user_db")).getId();
		//itemid取得
		String itemId  = request.getParameter("item_id");
		//quantity取得
		String quantity= request.getParameter("item_quantity");

			List<Map<String, Object>> itemList = dao.selectItem(itemId);

//			cartList.addAll(itemList);
			System.out.println("cart"+cartList);

			model.addAttribute("input", itemList);


			 return "forward:/create";
    }






    /**
     * 購入確認画面に移る際の処理
     * 同一id処理
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

    	//add-wakasugi
    	//リクエストパラメータから取得した購入数をMapへ追加
    	Map<String, Object> tempList = itemList.get(0);		//必ず1商品しか入ってないのでindex=0
    	tempList.put("amount", quantity);

    	//購入数を追加したMapをitemListへ追加
    	//1商品しか入ってないので、クリアして再度add
    	itemList.clear();
    	itemList.add(tempList);


    	System.out.println(itemList);

    	//ここから先変更しました”””””


    	String val1 = (String) tempList.get("item_id");

    	System.out.println("val1は"+val1);

    	//同一idをセットしたかフラグ(0: セットしてない、1: セットした)
    	int listSetFlg = 0;

    	for (int j=0; j<cartList.size(); j++)
    	{
    		tempList = cartList.get(j);


    		System.out.println("tempListとは"+tempList);

    		if(cartList.get(j).get("item_id").equals(val1)) {
    			tempList.put("amount", String.valueOf(Integer.valueOf((String)cartList.get(j).get("amount"))+Integer.parseInt(quantity)));
    			System.out.println("tempListとは"+tempList);
    			System.out.println("何番目か:"+j);

    			cartList.set(j, tempList);

    			listSetFlg = 1;
    		}
    	}

    	//doui
		if (listSetFlg == 0) {
    		cartList.addAll(itemList);
		}



    	System.out.println("cartの中身は"+cartList);

    	model.addAttribute("pr", pr);

    	model.addAttribute("input", cartList);

    	return nextpage;

    }


	/**
	 * 検索処理
	 */

    @RequestMapping(value="/Search",method=RequestMethod.GET)
    public String SearchGet(){

        return null;
    }

    @RequestMapping(value ="Search",method=RequestMethod.POST)
    public String SearchGet(Model model,
			HttpServletRequest request, HttpSession session) throws SQLException {

		String nextpage = "/create/input";	// 戻り値（次画面を示す文字列）
		//購入画面用modelクラスのインスタンス化
//		PurchaseInputModel pr= new PurchaseInputModel();
//		//モデルにセット
//		model.addAttribute("pr", pr);
//		//購入できるアイテムを表示させるためにリストに格納


		String key_ItemId = request.getParameter("key_itemId");
		String key_ItemName = request.getParameter("key_itemName");

		List<Map<String, Object>> itemList = dao.selectItem(key_ItemId);
		List<Map<String, Object>> itemList2 = dao.selectName(key_ItemName);
	//outputオブジェクトにこのリストをセットする
		model.addAttribute("output", itemList);
		model.addAttribute("output", itemList2);

		return nextpage;

    }

	/**
	 * カートのリストから削除
	 */



	@RequestMapping(value = "Remove", method = RequestMethod.POST)
    public String remove(Model model, HttpServletRequest request, HttpSession session) {

		System.out.println("cart"+cartList);


		String name  = "";		// 現在のパラメータ名
		String itemId= "";		// 商品ID
		String quantity= "";	// 購入数


		//add-wakasugi
		String removeItemId= "";		// 削除対象の商品ID

		// GETメソッドのパラメータ名を取得
	    Enumeration<String> names = request.getParameterNames();

		// removeボタンがクリックされた場所を特定
        while (names.hasMoreElements()) {

            // 渡ってきたパラメータを順番に処理
            // パラメータ名を取得
            name = names.nextElement();
            //ボタンの名前を取得してtempに代入
            String temp = request.getParameter(name);

            // removeボタンがクリックされている場合は
            if ("remove".equals(request.getParameter(name))) {

                //削除ボタンに付属している値（value）が商品IDになる
            	removeItemId = name;
                break;
            }
        }

		System.out.println("cart（削除前）"+cartList);

		int cnt = 0; //カウンター初期化
		for(Map<String, Object> map : cartList) {
			String temp =(String)map.get("item_id");//デバッグ確認用の一時変数
			if (removeItemId.equals((String)map.get("item_id"))) {
				cartList.remove(cnt);
				break;
			}
			cnt++;
		}
		System.out.println("cart（削除後）"+cartList);
 //add-wakasugi

		quantity = request.getParameter(itemId + "list");
		request.setAttribute("quantity", quantity);
//		cartList.remove(id);
		List<Map<String, Object>> cart1List = (List<Map<String, Object>>) session.getAttribute("input");

		model.addAttribute("input", cartList);
		System.out.println("cart"+cartList);



//		return "redirect:/confirm";
		return "/create/confirm";




	}

    @RequestMapping(value="/viewemp/{pageid}")
    public ModelAndView edit(@PathVariable int pageid, Model model) throws SQLException{
        int total=5;
        if(pageid==1){}
        else{
            pageid=(pageid-1)*total+1;
        }
       System.out.println(pageid);
       List<Map<String, Object>> itemList=dao.getItemByPage(total,pageid);
//          m.addAttribute("msg", list);

//       model.addAttribute("output", itemList);
       return new ModelAndView("/create/input", "output", itemList);
//        return "/create/input";
    }



    /**
     * 購入完了処理に移る前の直前の処理
     */
    @RequestMapping(value = "Result", method = RequestMethod.POST)
    public String create(Model model,
    		HttpServletRequest request, HttpSession session) throws SQLException  {

    	String nextpage = "/create/result";	// 戻り値（次画面を示す文字列）
    	//id取得
    	String id = ((HomeOutputModel)request.getSession().getAttribute("user_db")).getId();
    	//		//itemid取得
    	//		String itemId  = request.getParameter("item_id");
    	//		//quantity取得
    	//		String quantity= request.getParameter("item_quantity");

    	for(Map<String, Object> map : cartList) {
    		String itemId = (String) map.get("item_id");
    		String quantity = (String) map.get("amount");


    		try {
    			dao.updateItem(itemId, Integer.parseInt(quantity));
    			dao.updateHistory(id, itemId, Integer.parseInt(quantity));
    		} catch(SQLException e) {

    			e.printStackTrace();

    		}
    	}

    	cartList = new ArrayList<Map<String, Object>>();


    	return nextpage;
    }

    /**
     * 購入した履歴一覧表示
     */
    @RequestMapping(value = "/history", method = RequestMethod.GET)
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

    @RequestMapping(value = "/history", method = RequestMethod.POST)
    public String history1(Model model,
    		HttpServletRequest request, HttpSession session) throws SQLException  {

    	String nextpage = "/create/history";	// 戻り値（次画面を示す文字列）
    	//id取得
    	String id = ((HomeOutputModel)request.getSession().getAttribute("user_db")).getId();
    	//購入一覧表示
    	List<Map<String, Object>> selectHistory = dao.selectHistory(id);


    	model.addAttribute("history", selectHistory);


    	return nextpage;
    }

    /**
     * check一覧表示
     */
    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public String check(@ModelAttribute PurchaseInputModel purchase, Model model,
    		HttpServletRequest request, HttpSession session) throws SQLException {
    	PurchaseInputModel pr= new PurchaseInputModel();

    	String nextpage = "/create/check";	// 戻り値（次画面を示す文字列）
    	//id取得
    	String id = ((HomeOutputModel)request.getSession().getAttribute("user_db")).getId();
    	//購入一覧表示
    	List<Map<String, Object>> selectHistory = dao.selectHistory(id);


    	System.out.println("cartの中身は"+cartList);

    	model.addAttribute("pr", pr);

    	model.addAttribute("input", cartList);


    	return nextpage;
    }

    /**
     * item_id一覧表示
     * @throws SQLException
     */
    @RequestMapping(value="/new/detail/{item_id}")
    public String detail(@PathVariable String item_id,@ModelAttribute PurchaseOutputModel purchase, Model model,
			HttpServletRequest request, HttpServletResponse response) throws SQLException {

		//購入画面用modelクラスのインスタンス化
		PurchaseOutputModel pr= new PurchaseOutputModel();
		//モデルにセット
		model.addAttribute("pr", pr);
		//購入できるアイテムを表示させるためにリストに格納
		PurchaseOutputModel list = dao.detailItem(item_id);
		//outputオブジェクトにこのリストをセットする
		model.addAttribute("output", list);

    	return "/create/detail";

    }



    /**
     * 詳細画面からの
     */

    @RequestMapping(value ="Confirm1", method = RequestMethod.POST)
    public String confirm1(@ModelAttribute PurchaseInputModel purchase, Model model,
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
    		if("カートに入れて他の商品を購入する".equals(request.getParameter(name))) {
    			itemId = name;
    			nextpage = "/create/input";
    			PurchaseInputModel prd= new PurchaseInputModel();
    			//モデルにセット
    			model.addAttribute("pr", prd);
    			//購入できるアイテムを表示させるためにリストに格納
    			List<Map<String, Object>> itemList = dao.itemList();
    			//outputオブジェクトにこのリストをセットする
    			model.addAttribute("output", itemList);

    		}
    	}
    	//購入数の値を取得
    	quantity = request.getParameter(itemId + "list");
    	request.setAttribute("quantity", quantity);


    	//購入するアイテムの情報を取得
    	List<Map<String, Object>> itemList = dao.selectItem(itemId);

    	//add-wakasugi
    	//リクエストパラメータから取得した購入数をMapへ追加
    	Map<String, Object> tempList = itemList.get(0);		//必ず1商品しか入ってないのでindex=0
    	tempList.put("amount", quantity);

    	//購入数を追加したMapをitemListへ追加
    	//1商品しか入ってないので、クリアして再度add
    	itemList.clear();
    	itemList.add(tempList);


    	System.out.println(itemList);

    	//ここから先変更しました”””””


    	String val1 = (String) tempList.get("item_id");

    	System.out.println("val1は"+val1);

    	//同一idをセットしたかフラグ(0: セットしてない、1: セットした)
    	int listSetFlg = 0;

    	for (int j=0; j<cartList.size(); j++)
    	{
    		tempList = cartList.get(j);


    		System.out.println("tempListとは"+tempList);

    		if(cartList.get(j).get("item_id").equals(val1)) {
    			tempList.put("amount", String.valueOf(Integer.valueOf((String)cartList.get(j).get("amount"))+Integer.parseInt(quantity)));
    			System.out.println("tempListとは"+tempList);
    			System.out.println("何番目か:"+j);

    			cartList.set(j, tempList);

    			listSetFlg = 1;
    		}
    	}

    	//doui
		if (listSetFlg == 0) {
    		cartList.addAll(itemList);
		}



    	System.out.println("cartの中身は"+cartList);

    	model.addAttribute("pr", pr);

    	model.addAttribute("input", cartList);

    	return nextpage;

    }


}