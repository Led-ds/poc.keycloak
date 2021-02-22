package first.application.poc.hello.world.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api")
public class HelloWorldController {

	@GetMapping(path = "/")
	public String index() {
		return "index";
	}
	
	@PostMapping(path = "/login/v1")
	public String login(@RequestParam("username") final String username, @RequestParam("password") final String password)
			throws UnsupportedOperationException, IOException {
		
		String uri = "http://10.0.2.2:8180/auth/realms/SpringBootKeycloak/protocol/openid-connect/token";

        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(uri);
        
        post.setHeader("User-Agent",
                "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36");
        
        List<BasicNameValuePair> urlParameters = new ArrayList<BasicNameValuePair>();
        
        urlParameters.add(new BasicNameValuePair("grant_type", "password"));
        urlParameters.add(new BasicNameValuePair("client_id", "login-app"));
        urlParameters.add(new BasicNameValuePair("username", username));
        urlParameters.add(new BasicNameValuePair("password", password));
        
        post.setEntity(new UrlEncodedFormEntity(urlParameters));
        
        HttpResponse response = client.execute(post);
        System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
        
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        StringBuffer result = new StringBuffer();
        String line1 = "";
        
        while ((line1 = rd.readLine()) != null) {
            result.append(line1);
        }
        
        System.out.println(result);
		
		return "index";
	}
}
