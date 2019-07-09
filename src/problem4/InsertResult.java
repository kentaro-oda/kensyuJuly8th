package problem4;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import problem4.dao.ResultDao;
import problem4.dto.Omikuji;

/**
 * Servlet implementation class InsertResult
 *
 * 結果テーブルに情報を登録
 *
 * omikuji.jspにフォワード
 * @author k_oda
 */
@WebServlet("/InsertResult")
public class InsertResult extends HttpServlet {

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/**
		 * リクエストスコープから当日・誕生日・おみくじコードを取得
		 */
		Date today = (Date)request.getAttribute("today");
		Date birthday = (Date)request.getAttribute("birthday");
		Omikuji omikuji = (Omikuji)request.getAttribute("omikuji");
		int omikujiId = omikuji.getOmikujiId();

		/**
		 * 結果テーブルに情報を登録
		 */
		ResultDao.insertResult(today, birthday, omikujiId);

		/**
		 * omikuji.jspにフォワード
		 */
		request.getRequestDispatcher("/omikuji.jsp").forward(request, response);
	}

}
