package jp.co.nttdata.shinkin.batch.sample01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import com.brainsellers.xml.JaxpXML;
import com.brainsellers.xml.common.XMLSuper;
import com.brainsellers.xml.datatypes.HashtableType;
import com.brainsellers.xml.datatypes.RecordType;
import com.brainsellers.xml.datatypes.StringType;
import com.brainsellers.xml.fo.datasource.ResourceException;

public class CreatePDFSample8 {
	
	// ＸＭＬファイル名
	private String xml_file = "C:/brainsellers/biz-Stream_4_9_0/sample/java/Sample8.xml";
	
	// ＰＤＦファイル名
	private String pdf_file = "C:/gitbucket/batch_test/Sample888.pdf";
	
	// データファイル名
	private String data_file = "C:/brainsellers/biz-Stream_4_9_0/sample/java/Sample8.txt";
	
	int createPDF() {
		
		//データの取得
		HashtableType hashtable_data = this.getHashtableData();
		RecordType record_data = this.getRecordData();
		
		printUsage();
		
		try {
			
			FileSystem fs = FileSystems.getDefault();
			Path inputsource = fs.getPath("src/main/resources/bizstream/xml/Sample8/Sample8.xml");
			String str = inputsource.toAbsolutePath().toString();
			System.out.println(str);
			
			InputStream inputfile = Files.newInputStream(inputsource, StandardOpenOption.READ);

			XMLSuper xml = new JaxpXML(inputfile, pdf_file);
			//XMLSuper xml = new JaxpXML(xml_file, pdf_file);
			
			//XMLの解析
			xml.parse();
			
			//DataSourceの設定
			//帳票全体に設定する項目
			xml.setDataSource("hashtable-data", hashtable_data);
			//繰り返しを要する項目
			xml.setDataSource("record-data", record_data);
			
			//PDF出力
			xml.calcDataSize();
			xml.toPDF();
			//PDF保存
			xml.close();
			
		}catch (com.brainsellers.pdf.PDFRuntimeException e) {
			// TODO: handle exception
			e.printStackTrace();
			return 1;
		}catch (java.io.FileNotFoundException e) {
			e.printStackTrace();
			return 1;
		}catch (java.io.IOException e) {
			e.printStackTrace();
			return 1;
		}catch (java.lang.Exception e) {
			e.printStackTrace();
			return 1;
		}
		return 0;
	}
	
	/**
	 * 使用方法の表示
	 */
	private void printUsage(){
		System.out.println("\nUsage:");
		System.out.println("java Sample8 xml_filename pdf_filename [data_filename]");
	}
	
	/**
	 * データの取得
	 * @return  データを格納したHashtableType
	 */
	public HashtableType getHashtableData( ){
		HashtableType data = new HashtableType();
		if(this.data_file == null) {
			return data;
		}
		
		String strline = null;
		
		try{
			//BufferedReader br = new BufferedReader(new FileReader(this.data_file));
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(this.data_file),"Shift_JIS"));
			
			while ((strline = br.readLine())!=null ) {
				StringTokenizer st = new StringTokenizer(strline, "=");
				String token1;
				
				try {
						token1 = st.nextToken();
				}catch (NoSuchElementException e) {
						token1 = "";
				}
				
				StringType token2;
				
				try {
						token2 = new StringType(st.nextToken());
				}catch (NoSuchElementException e) {
						token2 = new StringType("");
				}
				
				data.put(token1,token2);
			}
		} catch(java.io.IOException e) {
			e.printStackTrace();
		}
		return data;
	}
	
	/**
	 * データの取得
	 * @return  データを格納したRecordType
	 */
	public RecordType getRecordData( ){
		RecordType	data = new RecordType();
		
		for(int idx=1; idx<40; idx++){
			HashtableType hash = new HashtableType();
			hash.put("製品コード", new StringType("製品コード"+idx));
			hash.put("製品名", new StringType("製品名"+idx));
			hash.put("数量", new StringType(""+idx*10));
			hash.put("単価", new StringType(""+idx*100));
			hash.put("金額", new StringType(""+idx*1000));
			data.add(hash);
		}
		return data;
	}
	
}
