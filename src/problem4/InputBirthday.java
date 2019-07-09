package problem4;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

		response.setContentType("text/html; charset = UTF-8");
		PrintWriter out = response.getWriter();

		out.println("<html>");
		out.println("<head>");
		out.println("<meta chrset = \"UTF-8\" >");
		out.println("<title>誕生日入力画面</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h2>生年月日占い</h2>");
		if(request.getAttribute("error") != null) {
			out.println(request.getAttribute("error"));
		}
		out.println("誕生日を入力してください(例：2019-07-08)</ br>");
		out.println("<form action = \"/kensyuJuly8th/DateCheck\" method = \"post\" >");
		out.println("<input type = \"text\" name = \"birthday\" />");
		out.println("<input type = \"submit\" value = \"結果\" />");
		out.println("</form>");
		out.println("</body>");
		out.println("</html>");
	}

}
