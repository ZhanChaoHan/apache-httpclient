package jachs.commons.httpclient;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.junit.Test;

import com.jachs.httpclient.HttpClientUtill;

/**
 * @author zhanchaohan
 * @see jachs.commons.Application  start server
 */
public class HttpClientGetTest {
	private HttpClientUtill httpClientUtill=new HttpClientUtill();
	
    @Test
    public void testGet() {
        String urlParam = "http://localhost:8080/get/A?pam1=ccc&pam2=dadawd";
        String result=httpClientUtill.sendGet ( urlParam );
        System.out.println(result);
    }
    @Test
    public void testGet2() throws UnsupportedEncodingException {
        String urlParam = "http://localhost:8080/get/A";
        String parmStr="?pam1="+URLEncoder.encode("中", "utf-8")+"&pam2="+URLEncoder.encode("国", "utf-8");
        String result=httpClientUtill.sendGet ( urlParam+parmStr );
        System.out.println(result);
    }
    
}