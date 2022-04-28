package jachs.commons.httpclient;

import java.io.IOException;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpState;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.cookie.CookieSpec;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Test;

/***
 * 
 * @author zhanchaohan
 *
 */
public class CookieTest {
	private static final int CONNECTIONTIMEOUT = 15000;
	private static final int PARAMETERTIMEOUT = 60000;

	private static final String LOGON_STR = "localhost";
	private static final int LOGON_PORT = 8080;
	
	//拦截器拦截未登录获取cookie无法访问
	@Test
	public void test2() throws HttpException, IOException {
		HttpClient client = new HttpClient();
		client.getHostConfiguration().setHost(LOGON_STR, LOGON_PORT);
		GetMethod get = new GetMethod("/test/geta?pam1=ccs&pam2=dadna");
		client.executeMethod(get);
		System.out.println(get.getResponseBodyAsString());
		get.releaseConnection();
	}
	@Test
	public void test1() throws HttpException, IOException {
		HttpClient client = new HttpClient();

		client.getHostConfiguration().setHost(LOGON_STR, LOGON_PORT);

		// 模拟登录
		PostMethod post = new PostMethod("/session/login");
		NameValuePair name = new NameValuePair("user", "jarm");
		NameValuePair pass = new NameValuePair("password", "123456");

		post.setRequestBody(new NameValuePair[] { name, pass });

		// 设置 HttpClient 接收 Cookie,用与浏览器一样的策略
		client.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
		int status = client.executeMethod(post);

		System.out.println("status:\t"+status+"\t"+post.getResponseBodyAsString());
		post.releaseConnection();

		 // 获得登陆后的 Cookie
        Cookie[] cookiesA = client.getState().getCookies();
        for (Cookie cookie : cookiesA) {
			System.out.println(cookie.getName());
		}
        //获取cookie后访问
        client.getHostConfiguration().setHost(LOGON_STR, LOGON_PORT);
		GetMethod get = new GetMethod("/test/geta?pam1=ccs&pam2=dadna");
		client.executeMethod(get);
		System.out.println(get.getResponseBodyAsString());
		get.releaseConnection();
	}
	//给定个模拟cookie
	@Test
	public void test5() throws HttpException, IOException {
		HttpState initialState = new HttpState();
		
		Cookie  mycookie = new Cookie("localhost", "mycookie", "stuff","/", null, false);

        initialState.addCookie(mycookie);

		HttpClient client = new HttpClient();
		
		client.setState(initialState);
		
		client.getHostConfiguration().setHost(LOGON_STR, LOGON_PORT);
		GetMethod get = new GetMethod("/test/geta?pam1=ccs&pam2=dadna");
		client.executeMethod(get);
		System.out.println(get.getResponseBodyAsString());
		get.releaseConnection();
	}
}
