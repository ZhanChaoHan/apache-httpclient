package jachs.commons.httpclient;


import org.junit.Test;

import com.jachs.httpclient.HttpClientUtill;

/**
 * @author zhanchaohan
 * @see jachs.commons.Application  start server
 */
public class HttpClientPostTest {
	private HttpClientUtill httpClientUtill=new HttpClientUtill();
	
    @Test
    public void testPost() {
        String url = "http://localhost:8080/post/A?pam1=aaa&pam2=bbb";
        String result=httpClientUtill.sendPost ( url, "" );
        System.out.println(result);
    }
    
    @Test
    public void testPost2() {
        String url = "http://localhost:8080/post/B";
        String json="{\"name\":\"jachs\",\"age\":25}";
        String result=httpClientUtill.sendPost ( url, json );
        System.out.println(result);
    }
    @Test
    public void testPost3() {
        String url = "http://localhost:8080/post/B";
        String json="{\"name\":\"张\",\"age\":22}";
        String result=httpClientUtill.sendPost ( url, json );
        System.out.println(result);
    }
}