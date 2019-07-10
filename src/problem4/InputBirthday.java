package problem4;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 誕生日入力画面のサーブレット
 *
 * formのパス指定先：/kensyuJuly8th/DateCheck	日付の入力チェック処理へ
 * formから送られる情報：birthday	入力した誕生日
 * @author k_oda
 *
 */
@WebServlet("/InputBirthday")
public class InputBirthday extends HttpServlet {

	/**
	 * 誕生日入力画面を表示(Get時)
	 *
	 * @param request	リクエスト情報
	 * @param response	レスポンス情報
	 *
	 * @throws ServletException	サーブレット処理の例外
	 * @throws IOException		入出力処理の例外
	 * 遷移先：/kensyuJluy8th/DateCheck	入力チェックのサーブレットへ(過去情報やおみくじの取得、結果テーブルへの登録も合わせて行う)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

		/**
		 * レスポンスのMIMEタイプと文字エンコーディングを設定
		 */
		response.setContentType("text/html; charset = UTF-8");

		/**
		 * PrintWriter型オブジェクトを作成
		 */
		PrintWriter out = response.getWriter();

		/**
		 * エラーメッセージ用にセッションを用意
		 */
		HttpSession session = request.getSession();

		/**
		 * 誕生日入力画面のHTML
		 *
		 * if文：セッションにエラーメッセージが登録されてた場合、それを表示
		 * フォーム
		 * 送り先：/kensyuJuly8th/DateCheck	入力チェックのサーブレット
		 * 呼び出し方：post
		 */
		out.println("<html>");
		out.println("<head>");
		out.println("<meta chrset = \"UTF-8\" >");
		out.println("<title>誕生日入力画面</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h2>生年月日占い</h2>");
		if(session.getAttribute("error") != null) {
			out.println("<p>");
			out.println(session.getAttribute("error"));
			out.println("</p>");
		}
		out.println("誕生日を入力してください(例：2019-07-08)</ br>");
		out.println("<form action = \"/kensyuJuly8th/DateCheck\" method = \"post\" >");
		out.println("<input type = \"text\" name = \"birthday\" />");
		out.println("<input type = \"submit\" value = \"結果\" />");
		out.println("</form>");
		out.println("</body>");
		out.println("</html>");
	}

	/**
	 * 誕生日入力画面を表示(Post時)
	 *
	 * @param request	リクエスト情報
	 * @param response	レスポンス情報
	 *
	 * @throws ServletException	サーブレット処理の例外
	 * @throws IOException		入出力処理の例外
	 *
	 * doGetを呼び出す
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
