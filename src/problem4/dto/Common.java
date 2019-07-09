package problem4.dto;

import java.sql.Date;

/**
 * 各テーブルに共通しているカラム(更新者、更新日、作成者、作成日)を集めたDTO抽象クラス
 * @author k_oda
 *
 */
public abstract class Common {
	private String updater;
	private Date updateDay;
	private String creater;
	private Date createDay;


	public String getUpdater() {
		return updater;
	}
	public void setUpdater(String updater) {
		this.updater = updater;
	}


	public Date getUpdateDay() {
		return updateDay;
	}
	public void setUpdateDay(Date updateDay) {
		this.updateDay = updateDay;
	}


	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}


	public Date getCreateDay() {
		return createDay;
	}
	public void setCreateDay(Date createDay) {
		this.createDay = createDay;
	}

}
