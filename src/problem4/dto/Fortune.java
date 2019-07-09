package problem4.dto;

/**
 * 運勢マスタテーブルのDTOテーブル
 * @author k_oda
 *
 */
public class Fortune extends Common{

	private int fortuneId;
	private String fortuneName;


	public int getFortuneId() {
		return fortuneId;
	}
	public void setFortuneId(int fortuneId) {
		this.fortuneId = fortuneId;
	}


	public String getFortuneName() {
		return fortuneName;
	}
	public void setFortuneName(String fortuneName) {
		this.fortuneName = fortuneName;
	}
}
