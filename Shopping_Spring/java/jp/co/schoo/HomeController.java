package jp.co.schoo;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
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

import jp.co.schoo.model.HomeInputModel;
import jp.co.schoo.model.HomeOutputModel;
import jp.co.schoo.model.InsertInputModel;
import jp.co.schoo.model.InsertOutputModel;
import jp.co.schoo.repository.HomeDao;

/**
 * Java入門 ログイン機能Controllerクラス.
 */
@Controller
public class HomeController {

	public static final Integer ROLE_ADMIN =1;
	public static final Integer ROLE_USER =2;
	@Autowired
	HomeDao dao;	// DAOクラス

	/**
	 * ログインページアクセス時の処理.
	 * @param	model JSP画面と共有するためのModelクラス
	 * @return	次画面を示す文字列
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {

		// アクセス時に新しいログイン画面用Modelクラスのインスタンスを作成
		HomeInputModel inputModel = new HomeInputModel();
		// ログイン画面に表示するメッセージを設定
		inputModel.setMsg("IDとパスワードを入力してください.");
		// Modelクラスに設定
		model.addAttribute("input", inputModel);

		// ログイン画面を表示
		return "login";
	}

	/**
	 * ログイン処理.</br>
	 * ログイン成功時はユーザ画面、失敗時はログイン画面に移動.
	 * @param input	ログイン画面の入力値を格納したModelクラス
	 * @param model	JSP画面と共有するためのModelクラス
	 * @return	次画面を示す文字列
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String login(@ModelAttribute HomeInputModel input, Model model, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException  {

		String nextpage = "login";	// 戻り値（次画面を示す文字列）
		HttpSession session = request.getSession();

		// DAOクラスでログインユーザ検索を実行
		HomeOutputModel outputModel =
				dao.selectUser(input.getId(), input.getPassword());

		// DAOクラスの戻り値が空でなければユーザ画面に移動
		if(outputModel != null) {
			System.out.println(outputModel.getId());
			System.out.println(outputModel.getRole());
			// Modelクラスに値を設定
			model.addAttribute("output", outputModel);
			// 次画面をユーザ画面に変更
			nextpage = "user";
			session.setAttribute("user_db", outputModel);


		} else {

			// ログインに失敗している場合は入力画面用のModelクラスを新たに設定
			HomeInputModel inputModel = new HomeInputModel();
			// ログイン画面に表示するメッセージを設定
			inputModel.setMsg("ログインできませんでした...");
			// Modelクラスに設定
			model.addAttribute("input", inputModel);
		}

		return nextpage;
	}

	/**
	 * admin画面
	 */

	@RequestMapping(value = "/new/admin", method=RequestMethod.POST)
	public String adminPage(@ModelAttribute HomeOutputModel output, Model model)throws ServletException, IOException  {

		// DAOクラス
//		HomeOutputModel outputModel = dao.admin();
		List<Map<String, Object>> userList = dao.admin();

		// DAOクラスの戻り値が空でなければユーザ画面に移動
		if(userList != null) {
//			System.out.println(outputModel.getId());
//			System.out.println(outputModel.getRole());
			// Modelクラスに値を設定
			model.addAttribute("output", userList);
			// 次画面をユーザ画面に変更


		}


		return "/new/admin";

	}
	/**
	 * Configure画面
	 */
	@RequestMapping(value = "/new/edit/{id}")
	public String editPage(@PathVariable("id") String id, Model model, HttpServletRequest request, HttpSession session)
			throws ServletException, IOException  {


		String name  = "";		// 現在のパラメータ名
		HomeOutputModel outputModel = new HomeOutputModel();

		System.out.println(id);

		outputModel = dao.editUser(id);


		model.addAttribute("output", outputModel);

////
////		//add-wakasugi
//		String editUserId= "";		// 削除対象の商品ID
//
//		// GETメソッドのパラメータ名を取得
//	    Enumeration<String> names = request.getParameterNames();
//
//		// removeボタンがクリックされた場所を特定
//        while (names.hasMoreElements()) {
//
//            // 渡ってきたパラメータを順番に処理
//            // パラメータ名を取得
//            name = names.nextElement();
//            //ボタンの名前を取得してtempに代入
//            String temp = request.getParameter(name);
//
//            // editボタンがクリックされている場合は
//            if ("edit".equals(request.getParameter(name))) {
//
//                //削除ボタンに付属している値（value）が商品IDになる
//            	editUserId = name;
//        		HomeOutputModel outputModel =dao.editUser(editUserId);
//
//        		model.addAttribute("output", outputModel);
////                break;
//            }
//        }

		String nextpage = "/new/edit";

		return nextpage;

	}


	/**
	 * 新規ユーザ登録処理.</br>
	 * 登録成功時は完了画面(finish.jsp)、失敗時は再度入力を促す
	 */
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String register(@ModelAttribute InsertInputModel insert, Model model) {

		String nextpage = "/new/register";	// 戻り値（次画面を示す文字列）
		InsertInputModel insertModel = new InsertInputModel();
		// ログイン画面に表示するメッセージを設定
		insertModel.setMsg("項目を入力してください。");
		// Modelクラスに設定
		model.addAttribute("insert", insertModel);

		return nextpage;
	}

	/**
	 * 新規ユーザ登録完了処理.</br>
	 */
	@RequestMapping(value = "/finish", method = RequestMethod.POST)
	public String finish(@ModelAttribute InsertInputModel insert, Model model) {

		// 戻り値（次画面を示す文字列）
		String nextpage = "/finish/finish";
		//ユーザーの情報を取得
		InsertOutputModel insertoutputModel4 = dao.insertUser(insert.getId(), insert.getPassword(), insert.getName(), insert.getAge());
		insertoutputModel4.setMsg("ユーザー登録が完了しました。");
		//modelクラスにセット
		model.addAttribute("output", insertoutputModel4);

		return nextpage;
	}

	/**
	 * ログアウト処理.</br>
	 */
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public void logoutPage(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		//ログイン画面に戻る
		response.sendRedirect("/schoo");
	}

	/**
	 * update処理.</br>
	 */
	@RequestMapping(value = "/new/edit/{id}/update", method=RequestMethod.POST)
	public String Update(@PathVariable("id") String id,@ModelAttribute("output")  HomeOutputModel output,Model model, HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException  {

//		HomeOutputModel output = new HomeOutputModel();

		String name = request.getParameter("name");
//		String name = output.getName();
//		String password = output.getPassword();
		String password = request.getParameter("password");
//		int age = output.getAge();
		String age = request.getParameter("age");

//		String id = output.getId();
		System.out.println(name);
		System.out.println(password);
		System.out.println(age);
		System.out.println(output.getId());

		HomeOutputModel outputModel = dao.updateUser(id,password,name,age);


		model.addAttribute("output", outputModel);


//
//	String nextpage = "/new/admin";
	String nextpage ="/new/edit";;
	return nextpage;
	}


}