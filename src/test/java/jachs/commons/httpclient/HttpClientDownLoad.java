package jachs.commons.httpclient;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.Test;

import com.jachs.httpclient.HttpClientUtill;

/***
 * 
 * @author zhanchaohan
 *
 */
public class HttpClientDownLoad {
	private HttpClientUtill httpClientUtill=new HttpClientUtill();
	
	
	@Test
	public void test1() throws FileNotFoundException, IOException {
		httpClientUtill.downLoad("https://f7.baidu.com/it/u=3406732922,3399065415&fm=222&app=108&size=f360,240&n=0&f=JPEG?sec=1655398800&t=046176cd88d2db747b1542ae811c8d79", new FileOutputStream("e://a.jpg"));
	}
	
	@Test
	public void test2() throws FileNotFoundException, IOException {
		httpClientUtill.downLoad("https://mbd.baidu.com/newspage/data/videolanding?nid=sv_4378093106087731564&sourceFrom=pc_feedlist", new FileOutputStream("e://a.js"));
	}
}
