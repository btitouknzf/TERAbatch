package jp.co.nttdata.shinkin.batch.sample02;

public interface Sample02Repository {
	/**
	 * batch_job_requestを検索して、これまで登録されたジョブ数を取得する。
	 * @return
	 */
	public Integer countJobs();
}
