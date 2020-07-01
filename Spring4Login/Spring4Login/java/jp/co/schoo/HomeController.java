package jp.co.schoo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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


	//	@RequestMapping(value = "/new", method = RequestMethod.POST)
	//	public String example(@ModelAttribute InsertInputModel insert, Model model) {
	//
	//		String nextpage = "/new/example";	// 戻り値（次画面を示す文字列）
	//		InsertInputModel insertModel = new InsertInputModel();
	//		// ログイン画面に表示するメッセージを設定
	//		insertModel.setMsg("IDとパスワードを入力してください.");
	//		// Modelクラスに設定
	//		model.addAttribute("insert", insertModel);
	//
	//
	//
	//
	//		return nextpage;
	//	}


	@RequestMapping(value = "/finish", method = RequestMethod.POST)
	public String finish(@ModelAttribute InsertInputModel insert, Model model) {

		// 戻り値（次画面を示す文字列）
		String nextpage = "/finish/finish";

		InsertOutputModel insertoutputModel1 =
				dao.insertselectUser(insert.getId(), insert.getPassword());

		InsertOutputModel insertoutputModel =
				dao.insertselectId(insert.getId());

		if(insertoutputModel1 != null) {
			// ログインに失敗している場合は入力画面用のModelクラスを新たに設定
			InsertOutputModel insertoutputModel5 = new InsertOutputModel();
			//ログイン画面に表示するメッセージを設定
			insertoutputModel5.setMsg("そのユーザーは登録されています");
			insertoutputModel5.setId(insert.getId());
			insertoutputModel5.setPassword(insert.getPassword());
			insertoutputModel5.setName(insert.getName());
			insertoutputModel5.setAge(insert.getAge());
			// Modelクラスに設定
			model.addAttribute("output", insertoutputModel5);


		}else
			if(insertoutputModel !=null){

				InsertInputModel insertinputModel = new InsertInputModel();

				insertinputModel.setMsg("同一ID存在のため、登録できません。");
				//				insertinputModel.setErr(1);

				model.addAttribute("insert", insertinputModel);



				nextpage = "/new/register";

			}

			else {

				System.out.println(insert.getId());
				System.out.println(insert.getPassword());
				System.out.println(insert.getMsg());
				InsertOutputModel insertoutputModel4 =
						dao.insertUser(insert.getId(), insert.getPassword(), insert.getName(), insert.getAge());
				insertoutputModel4.setMsg("ユーザー登録が完了しました。");
				// Modelクラスに値を設定
				//			model.addAttribute("insertoutput", insertoutputModel);
				// 次画面をユーザ画面に変更

				model.addAttribute("output", insertoutputModel4);

			}



		return nextpage;
	}

	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public void logoutPage(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		HttpSession session = request.getSession();

		session.removeAttribute("login_db");
		session.removeAttribute("user_db");
		response.sendRedirect("/schoo");
	}


	/*	@RequestMapping(value = "/new", params="example", method = RequestMethod.POST)
	public String ex(Model model) {

		// 戻り値（次画面を示す文字列）

		return "new/example";
	}

	 */

}