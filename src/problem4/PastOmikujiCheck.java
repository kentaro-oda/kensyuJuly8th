package problem4;

import java.sql.Date;
import java.util.Random;

import problem4.dao.OmikujiDao;
import problem4.dao.ResultDao;

/**
 * 過去に同日・同誕生日で検索されたものがないかをチェックし、おみくじコードを取得するクラス
 * 結果テーブルにあった場合：テーブルに登録されているおみくじコードを取得
 * なかった場合：乱数を生成し、それをおみくじコードとする
 *
 * GetOmikuji.javaへフォワード
 * @author k_oda
 */
public class PastOmikujiCheck{
	/**
	 * 過去に同日・同誕生日で検索されたものがないかをチェックし、おみくじコードを取得するクラス
	 *
	 * @param today			入力された誕生日
	 * @param birthday		実行当日
	 * @return omikujiId	結果テーブルから取得もしくは乱数で生成されたおみくじコード
	 */
	protected static Integer pastOmikujiCheck(Date today, Date birthday){

		/**
		 * 結果テーブルからおみくじコードを取得
		 * (合致する情報があった場合：整数値/なかった場合：null)
		 */
		Integer omikujiId = ResultDao.findByFortuneDayAndBirthday(today, birthday);

		/**
		 * omikujiIdがnullだった場合(過去に該当結果がなかった場合)
		 *
		 * OmikujiId用の乱数を生成
		 */
		if(omikujiId == null){
			Random r = new Random();
			int n = OmikujiDao.getOmikujiCount();
			omikujiId = r.nextInt(n);
		}

		/**
		 *	呼び出し元にomikujiIdを返却
		 */
		return omikujiId;
	}

}
