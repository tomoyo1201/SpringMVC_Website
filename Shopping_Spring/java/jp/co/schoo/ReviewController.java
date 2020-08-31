package jp.co.schoo;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.schoo.model.ReviewInputModel;
import jp.co.schoo.repository.ReviewDao;

@Controller
public class ReviewController {


	@Autowired
	ReviewDao dao;	// DAOクラス


	/**
	 * Java 評価Controllerクラス.
	 */
	@RequestMapping(value ="Review", method = RequestMethod.POST)
	public String Review( Model model,
			HttpServletRequest request, HttpSession session) throws SQLException {

		String nextpage = "/review/review";


		return nextpage;

	}


	/**
	 * Java 評価終了Controllerクラス.
	 */
	@RequestMapping(value ="PostReview", method = RequestMethod.POST)
	public String handleFormUpload(
			 Model model, HttpServletRequest request, HttpSession session) throws IOException{



//		String image = Base64.getEncoder().encodeToString(null);
//		File image1 = new File("/resources/images/Thankyou.jpg");
//		FileInputStream fis = new FileInputStream ( image1 );
//		dao.review(fis);

		String nextpage = "/review/finish";
		ReviewInputModel output= new ReviewInputModel();

		String title= request.getParameter("headline");
//		BLOB image= request.getParameter("myImage");
		String description= request.getParameter("contents");
		dao.review(title, description);


		return nextpage;

	}


}

