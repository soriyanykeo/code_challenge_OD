package soryany.com.codechallenge.Services;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * Created by soriyanykeo on 6/16/16.
 */
public class EmailPostRequest {
    public boolean Post(String id,String email){
        boolean isSaveEmail = false;
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost("https://sampleAPIs.com/setEmail");
        String message  = "id="+id+"&email="+email;
        try {
            post.setEntity(new StringEntity(message, "UTF-8"));
            post.setHeader("Content-type", "application/x-www-form-urlencoded");            HttpResponse resp = client.execute(post);
            String responseBody = EntityUtils.toString(resp.getEntity());
            boolean isContainEmail = responseBody.contains(email)  ;
            isSaveEmail = isContainEmail;
        }
        catch (Exception e){
            e.printStackTrace();
            isSaveEmail = false;
        }
        return isSaveEmail;
    }
}
