package org.lexsoft.library.client;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import lombok.AllArgsConstructor;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

@AllArgsConstructor
public class RetroHttpClient {

  public HttpClient client;

  public HttpClient createHttpClient(){
    if(Objects.nonNull(client)) return client;
    client = HttpClientBuilder.create().build();
    return client;
  }

  public String createGetRequest(String uri ) throws IOException {
    client = createHttpClient();
    HttpResponse response = client.execute(new HttpGet(uri));
    String responseBody = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
    return responseBody;
  }
  public String createPostRequest(String uri,String body) throws IOException {
    client = createHttpClient();
    HttpPost post = new HttpPost(uri);
    post.setHeader("Content-Type","application/json");
    post.setHeader("Accept","application/json");
    post.setEntity(new StringEntity(body));
    HttpResponse response = client.execute(post);
    String responseBody = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
    return responseBody;
  }


}
