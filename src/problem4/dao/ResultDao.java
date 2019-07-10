package problem4.dao;

import java.sql.Date;
import java.sql.SQLException;
/**
 * 結果テーブル関連のDB処理を行うメソッドを集めたDAOクラス
 * @author k_oda
 *
 */
public class ResultDao extends DBFields{

	/**
	 * 占い日と誕生日からおみくじコードを取得するメソッド
	 *
	 * @param today		占い日
	 * @param birthday	入力された誕生日
	 * @return	rset.getInt("omikuji_id")(合致するものがあった場合)/null(合致するものがなかった場合)
	 */
	public static Integer findByFortuneDayAndBirthday(Date today, Date birthday) {
		try {
			/**
			 * DBと接続
			 */
			DBRelation.getConnection();

			/**
			 * SQL文の実行
			 */
			String sql = "SELECT omikuji_id FROM result WHERE fortune_day = ? AND birthday = ?";
			ps = conn.prepareStatement(sql);
			ps.setDate(1, today);
			ps.setDate(2, birthday);
			rset = ps.executeQuery();

			if(rset.next()) {
				return rset.getInt("omikuji_id");
			} else {
				return null;
			}
		}

		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		/**
		 * DBとの接続を切断
		 */
		finally {
			DBRelation.closeConnection();
		}

	}

	/**
	 * 結果テーブルに情報を登録するメソッド
	 *
	 * @param today		占い日
	 * @param birthday	誕生日
	 * @param omikujiId	おみくじコード
	 */
	public static void insertResult(Date today, Date birthday, int omikujiId){
		try {
			/**
			 * DBと接続
			 */
			DBRelation.getConnection();

			/**
			 * SQL文の実行
			 */
			String sql = "INSERT INTO result(fortune_day, birthday, omikuji_id, creater, create_day) VALUES (?, ?, ?, ?, ?)";
			ps = conn.prepareStatement(sql);
			ps.setDate(1, today);
			ps.setDate(2, birthday);
			ps.setInt(3, omikujiId);
			ps.setString(4, "小田健太郎");
			ps.setDate(5, today);
			ps.executeUpdate();
			conn.commit();
		}

		catch(SQLException e) {
			e.printStackTrace();
		}

		/**
		 * DBとの接続を切断
		 */
		finally {
			DBRelation.closeConnection();
		}
	}
}
