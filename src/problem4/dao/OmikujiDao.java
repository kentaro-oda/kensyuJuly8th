package problem4.dao;

import java.sql.SQLException;

import problem4.dto.Fortune;
import problem4.dto.Omikuji;


/**
 * おみくじテーブル関連のDB処理を行うメソッドを集めたDAOクラス
 * @author k_oda
 *
 */
public class OmikujiDao extends DBFields{
	/**
	 * おみくじテーブルの行数を取得するメソッド
	 * @return	rset.getInt("count")：おみくじテーブルの行数/0:エラー発生時
	 */
	public static int getOmikujiCount(){
		try {
			/**
			 * DBと接続
			 */
			DBRelation.getConnection();

			/**
			 * SQL文の実行
			 */
			String sql = "SELECT COUNT(*) FROM omikuji";
			ps = conn.prepareStatement(sql);
			rset = ps.executeQuery();
			rset.next();
			return rset.getInt("count");
		}
		catch(SQLException e) {
			return 0;
		}

		/**
		 * DBとの接続を切断
		 */
		finally {
			DBRelation.closeConnection();
		}
	}

	/**
	 * おみくじコードから運勢名、願い事、商い、学問を取得するメソッド
	 * @param omikujiId	WHERE句の条件として使うおみくじコード
	 * @return	omikuji(上記の4つの項目が格納されたおみくじ結果)/null(エラー発生時)
	 */
	public static Omikuji findByOmikujiId(int omikujiId){
		try {
			/**
			 * DBと接続
			 */
			DBRelation.getConnection();

			/**
			 * SQL文の実行
			 */
			String sql = "SELECT f.fortune_name, o.negaigoto, o.akinai, o.gakumon FROM fortune f INNER JOIN omikuji o "
					+ "ON f.fortune_id = o.fortune_id WHERE omikuji_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, omikujiId);
			rset = ps.executeQuery();
			rset.next();

			/**
			 * 運勢名を設定するためにFortune型変数を作り、そこに運勢名を格納
			 */
			Fortune fortune = new Fortune();
			fortune.setFortuneName(rset.getString("fortune_name"));

			/**
			 * Omikuji型変数を作り、必要な情報を格納し返す
			 */
			Omikuji omikuji = new Omikuji();
			omikuji.setOmikujiId(omikujiId);
			omikuji.setFortune(fortune);
			omikuji.setNegaigoto(rset.getString("negaigoto"));
			omikuji.setAkinai(rset.getString("akinai"));
			omikuji.setGakumon(rset.getString("gakumon"));
			return omikuji;
		}

		/**
		 * エラー発生時にnullを返す
		 */
		catch(Exception e) {
			return null;
		}

		/**
		 * DBとの接続を切断
		 */
		finally {
			DBRelation.closeConnection();
		}
	}
}
