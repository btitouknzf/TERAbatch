package jp.co.nttdata.shinkin.batch.sample01;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Hashtable;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import com.brainsellers.xml.JaxpXML;
import com.brainsellers.xml.common.XMLSuper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CreatePDFSample1 {
	
	@Autowired
	ResourceLoader resourceLoader;
	
	int createPDF() {
		
		try {
			
			System.out.println("ok");
			Resource resource = resourceLoader.getResource("classpath:Sample1.xml");
			System.out.println("ok2");
			String name = resource.getFilename();
			System.out.println("ok3");
			log.info("name:" + name);
			System.out.println("ok4");
			File file = resource.getFile();
			log.info("file.exists():" + file.exists());
			log.info("file.getAbsolutePath():" + file.getAbsolutePath());
			InputStream input = new FileInputStream(file); 
			
			FileSystem fs = FileSystems.getDefault();
			Path inputsource = fs.getPath("src/main/resources/Sample1.xml");
			String str = inputsource.toAbsolutePath().toString();
			System.out.println(str);
			
			InputStream inputfile = Files.newInputStream(inputsource, StandardOpenOption.READ);
			
			
			//1)　入出力ともにファイル名で指定する
			
			  XMLSuper xml = new JaxpXML(inputfile,
										"C:/gitbucket/batch_test/Sample111.pdf");
			
			//"C:/gitbucket/batch_test/Sample111.pdf"
			//"classpath:bizstream/xml/Sample1.xml"
			//"classpath:bizstream/PDF/Sample1.pdf"
			//"C:/brainsellers/biz-Stream_4_9_0/sample/java/Sample1.xml"
			//classpath:Sample1.xml NG
			//file:C:/brainsellers/biz-Stream_4_9_0/sample/java/Sample1.xml OK
			//2) レイアウト情報（XML)の解析を行う
			xml.parse();
			
			//3)セキュリティ等の文書情報はparse後に一度だけ設定をしてください。
			//PDF表示時に全体表示で表示する
			xml.setAutoResize();
			
			//2ページ目以降は改頁を行う
			for(int i=0; i<3; i++) {
				if(i !=0) {
					xml.newPage();
				}
				
				//4) ページ毎の設定は毎ページ設定する
				//<Layout>タグの幅と高さを頁サイズとして使用する
				xml.setPageSize();
				
				//レイアウト情報(XML)に渡すデータのセットを行う
				//("レイアウト情報（XML）のName属性で指定された名前","データ内容")をセットする
				Hashtable ht = new Hashtable();
				ht.put("company", "ブレインセラーズ・ドットコム株式会社");
				ht.put("address", "東京都港区赤坂X-XX-X");
				ht.put("phone", "TEL.XX-XXXX-XXXX");
				ht.put("fax", "FAX.XX-XXXX-XXXX");
				
				//5) データのセットおよび座標計算を行った後、PDFを生成する
				xml.toPDF(ht);
				
				//6) 1頁毎に使用したオブジェクトを開放する
				xml.flush();
				
			}
			
			//7) PDFの出力を行う
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
}
