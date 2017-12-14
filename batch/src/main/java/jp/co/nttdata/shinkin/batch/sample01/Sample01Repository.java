package jp.co.nttdata.shinkin.batch.sample01;

public interface Sample01Repository {
	/**
	 * batch_job_requestを検索して、これまで登録されたジョブ数を取得する。
	 * @return
	 */
	public Integer countJobs();
}
