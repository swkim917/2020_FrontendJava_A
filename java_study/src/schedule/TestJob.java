package schedule;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class TestJob implements Job {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		long now = System.currentTimeMillis() / 1000;
		System.out.println("스케줄 작업 실행됨 - " + now);
	}
}
