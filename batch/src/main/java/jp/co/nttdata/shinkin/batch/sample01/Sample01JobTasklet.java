package jp.co.nttdata.shinkin.batch.sample01;

import javax.inject.Inject;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@Scope("step")
public class Sample01JobTasklet implements Tasklet {
	@Value("#{jobParameters[name]}")
	private String name;

	@Value("#{jobParameters[age]}")
	private Integer age;
	
	@Inject
	private Sample01Repository repos;
	
	@Inject
	private CreatePDFSample1 samplePDF;

	@Override
	public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
		log.info("call Sample01JobTasklet.");
		Thread.sleep(5000);
		log.info("###################################################");
		log.info("name = " + name + ", age = " + age);
		log.info("job count is "+repos.countJobs());
		log.info("###################################################");
		
		int createPDFresult = 0;
		
		//CreatePDFSample8 samplePDF = new CreatePDFSample8();
		
		//createPDFresult = samplePDF.createPDF();
		
		if(createPDFresult == 0) {
			log.info("create PDF is success");			
		}else {
			log.info("create PDF is error");
		}
		
		Thread.sleep(5000);
		log.info("thread end. name = " + name);
		return RepeatStatus.FINISHED;
	}

}
