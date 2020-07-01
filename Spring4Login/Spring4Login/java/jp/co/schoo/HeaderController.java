package jp.co.schoo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.schoo.model.HomeOutputModel;
import jp.co.schoo.repository.HomeDao;

@Controller
public class HeaderController {

	@Autowired
	HomeDao dao;	// DAOクラス

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String register(HomeOutputModel header, Model model) {

		String nextpage = "/create/header";	// 戻り値（次画面を示す文字列）

		System.out.println("aaa");
		String username = header.getName();
		model.addAttribute("name", username);

	return nextpage;
	}


}
