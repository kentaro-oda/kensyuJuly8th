package problem4;

import java.io.IOException;
import java.sql.Date;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import problem4.dao.OmikujiDao;
import problem4.dao.ResultDao;

/**
 * Servlet implementation class PastOmikujiCheck
 *
 * 過去に同日・同誕生日で検索されたものがないかをチェックしつつ、おみくじコードを取得
 * 結果テーブルにあった場合：テーブルに登録されているおみくじコードを取得
 * なかった場合：乱数を生成し、それをおみくじコードとする
 *
 * GetOmikuji.javaへフォワード
 * @author k_oda
 */
@WebServlet("/PastOmikujiCheck")
public class PastOmikujiCheck extends HttpServlet {

	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/**
		 * 入力チェックのサーブレットから送られてきた当日と誕生日の情報をDate型変数に格納
		 */
		Date today = (Date)request.getAttribute("today");
		Date birthday = (Date)request.getAttribute("birthday");

		/**
		 * 結果テーブルからおみくじコードを取得
		 * (合致する情報があった場合：整数値/なかった場合：null)
		 */
		Integer omikujiId = ResultDao.findByFortuneDayAndBirthday(today, birthday);

		/**
		 * omikujiIdがnullだった場合(過去に該当値がなかった場合)
		 *
		 * OmikujiId用の乱数を生成
		 */
		if(omikujiId == null){
			Random r = new Random();
			int n = OmikujiDao.getOmikujiCount();
			omikujiId = r.nextInt(n);
		}

		/**
		 * リクエストスコープにomikujiIdを格納
		 * おみくじの結果を取得するサーブレットへフォワード
		 */
		request.setAttribute("omikujiId", omikujiId);
		request.getRequestDispatcher("/GetOmikuji").forward(request, response);
	}

}
