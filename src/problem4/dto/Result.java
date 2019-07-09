package problem4.dto;

import java.sql.Date;

/**
 * 結果テーブルのDTOテーブル
 * @author k_oda
 *
 */
public class Result extends Common {
	private Date fortuneDay;
	private Date birthday;
	private Omikuji omikuji;


	public Date getFortuneDay() {
		return fortuneDay;
	}
	public void setFortuneDay(Date fortuneDay) {
		this.fortuneDay = fortuneDay;
	}


	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}


	public Omikuji getOmikuji() {
		return omikuji;
	}
	public void setOmikuji(Omikuji omikuji) {
		this.omikuji = omikuji;
	}

}
