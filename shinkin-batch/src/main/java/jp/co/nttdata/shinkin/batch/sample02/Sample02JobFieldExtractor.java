package jp.co.nttdata.shinkin.batch.sample02;

import java.io.UnsupportedEncodingException;

import org.springframework.batch.item.file.transform.FieldExtractor;

public class Sample02JobFieldExtractor 
		implements FieldExtractor<Sample02JobOutputClass> {

	@Override
	public Object[] extract(Sample02JobOutputClass item) {
		// TODO 自動生成されたメソッド・スタブ
		Object[] values = new Object[1];
		values[0] = fillUpSpace(item.getOutputResult(), 6);
		
		return null;
	}
	
	private String fillUpSpace(String val, int num) {
		String charsetName = "UTF-8";
		int len=0;
		try {
			len = val.getBytes(charsetName).length;
		} catch (UnsupportedEncodingException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		String fillStr = "";
		for(int i=0; i<(num - len); i++ ) {
			fillStr += " ";
		}
		
		return fillStr + val;
	}

}
