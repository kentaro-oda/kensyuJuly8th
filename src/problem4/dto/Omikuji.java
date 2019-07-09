package problem4.dto;

/**
 * おみくじテーブルのDTOテーブル
 * @author k_oda
 *
 */
public class Omikuji extends Common{

	private int omikujiId;
	private Fortune fortune;
	private String negaigoto;
	private String akinai;
	private String gakumon;


	public int getOmikujiId() {
		return omikujiId;
	}
	public void setOmikujiId(int omikujiId) {
		this.omikujiId = omikujiId;
	}


	public Fortune getFortune() {
		return fortune;
	}
	public void setFortune(Fortune fortune) {
		this.fortune = fortune;
	}


	public String getNegaigoto() {
		return negaigoto;
	}
	public void setNegaigoto(String negaigoto) {
		this.negaigoto = negaigoto;
	}


	public String getAkinai() {
		return akinai;
	}
	public void setAkinai(String akinai) {
		this.akinai = akinai;
	}


	public String getGakumon() {
		return gakumon;
	}
	public void setGakumon(String gakumon) {
		this.gakumon = gakumon;
	}

}
