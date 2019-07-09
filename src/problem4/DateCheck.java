package problem4;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DateCheck
 *
 * 誕生日の入力チェック
 * 成功：PastOmikujiCheck.javaへフォワード
 * 失敗：InputBirthday.javaへリダイレクト
 * @author k_oda
 */
@WebServlet("/DateCheck")
public class DateCheck extends HttpServlet {

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/**
		 * InputBirthdayから送られてきた情報をString型に変換
		 */
		String birthday = (String)request.getParameter("birthday");

		/**
		 * 入力された誕生日を正規表現でチェック
		 */
		Pattern p = Pattern.compile("^\\d{4}-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])$");
		Matcher m = p.matcher(birthday);
		if(m.find()) {
			try {

			/**
			 * 入力された誕生日をsql.Date型に変換
			 */
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu-MM-dd").withResolverStyle(ResolverStyle.STRICT);
			LocalDate.parse(birthday, dtf);
			Date sqlBirthday = Date.valueOf(birthday);

			/**
			 * 当日の日付を取得してsql.Date型に変換
			 */
			java.util.Date now = new java.util.Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			sdf.setLenient(false);
			String now2 = sdf.format(now);
			Date today = Date.valueOf(now2);

			/**
			 * 変換した当日情報と誕生日をそれぞれリクエストスコープに格納
			 * 過去に同じ日付で同じ誕生日が入力されていないかをチェックするサーブレットへフォワード
			 */
			request.setAttribute("today", today);
			request.setAttribute("birthday", sqlBirthday);
			request.getRequestDispatcher("/PastOmikujiCheck").forward(request, response);

			}
			/**
			 * 形式は正しいが、存在しない日付が入力されたとき(例：2019-02-31など)
			 *
			 * リクエストスコープにエラーメッセージを格納
			 * 誕生日入力画面へリダイレクト
			 */
			catch(DateTimeParseException e) {
				request.setAttribute("error", "※日付が正しくありません。");
				response.sendRedirect("/kensyuJuly8th/InputBirthday");
			}
		}
		/**
		 * 正規表現に当てはまらなかったとき(例：20190807、2019-98-76など)
		 *
		 * リクエストスコープにエラーメッセージを格納
		 * 誕生日入力画面へリダイレクト
		 */
		else {
			request.setAttribute("error", "※入力形式が正しくありません。");
			response.sendRedirect("/kensyuJuly8th/InputBirthday");
		}
	}

}
