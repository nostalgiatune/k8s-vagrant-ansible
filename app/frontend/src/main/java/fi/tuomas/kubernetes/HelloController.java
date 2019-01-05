package fi.tuomas.kubernetes;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@RequestMapping("/health")
    public String health() {
        return "healthy";
    }

    @RequestMapping("/")
    public String index() {
        return callBackend();
    }
    
    private String callBackend() {
    	
    	URLConnection conn = null;
    	InputStream is = null;
    	try {
			conn = new URL("http://backend:8080/app/").openConnection();
			is = conn.getInputStream();
			String res = convertStreamToString(is);
			return res;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
    }
    
    private String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        String str = s.hasNext() ? s.next() : "";
        return str;
    }
    
    @Configuration
    public static class Context {
    	
    }
}