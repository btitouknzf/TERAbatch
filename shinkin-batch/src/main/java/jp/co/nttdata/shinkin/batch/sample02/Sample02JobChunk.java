package jp.co.nttdata.shinkin.batch.sample02;


import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@Scope("step")
public class Sample02JobChunk implements ItemProcessor<Sample02JobClass, Sample02JobOutputClass> {
	@Value("#{jobParameters[name]}")
	private String name;

	@Value("#{jobParameters[age]}")
	private Integer age;
	
	@Override
	public Sample02JobOutputClass process(Sample02JobClass sample02JobClass) throws Exception {
		// TODO 自動生成されたメソッド・スタブ
		Sample02JobOutputClass outputResult = new Sample02JobOutputClass();
		outputResult.setOutputResult("現在までのバッチジョブリクエスト数：" + sample02JobClass.getResultJob() + "件");
		
		log.info("call Sample02JobChunk.");
		Thread.sleep(5000);
		log.info("###################################################");
		log.info("name = " + name + ", age = " + age);
		log.info("job count is "+sample02JobClass.getResultJob());
		log.info("###################################################");
		
		return outputResult;
	}

}
