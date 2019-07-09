package problem4;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import problem4.dao.OmikujiDao;
import problem4.dao.ResultDao;
import problem4.dto.Omikuji;

/**
 * Servlet implementation class GetOmikuji
 *
 * おみくじテーブルから結果を取得
 * @author k_oda
 */
@WebServlet("/GetOmikuji")
public class GetOmikuji extends HttpServlet {

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/**
		 * リクエストスコープからomikujiIdを取得
		 */
		Integer omikujiId = (Integer)request.getAttribute("omikujiId");

		/**
		 * おみくじの結果を取得
		 */
		Omikuji omikuji = OmikujiDao.findByOmikujiId(omikujiId);

		/**
		 * リクエストスコープにおみくじのデータを格納
		 */
		request.setAttribute("omikuji", omikuji);

		/**
		 * リクエストスコープの当日と誕生日の情報をDate型変数に格納
		 * 過去に同日・同誕生日で検索されたものがないかをチェック
		 */
		Date today = (Date)request.getAttribute("today");
		Date birthday = (Date)request.getAttribute("birthday");
		Integer resultCheck = ResultDao.findByFortuneDayAndBirthday(today, birthday);

		/**
		 * 過去に同じものがなかった場合、結果テーブルに情報を登録するためのサーブレットにフォワード
		 */
		if(resultCheck == null) {
			request.getRequestDispatcher("/InsertResult").forward(request, response);
		}

		/**
		 * 過去に同じものがあった場合、結果を表示させるJSPにフォワード
		 */
		else {
			request.getRequestDispatcher("/omikuji.jsp").forward(request, response);
		}
	}

}
