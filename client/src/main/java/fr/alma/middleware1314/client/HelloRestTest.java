package fr.alma.middleware1314.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

/**
 * @author Arnaud Thimel : thimel@codelutin.com
 */
public class HelloRestTest {

    public static void main(String[] args) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpPost request = new HttpPost("http://localhost:8080/reader-services-rest-0.1-SNAPSHOT/service/sample/sayHello");

        request.setHeader("accept", "application/json");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("name", "France"));
        UrlEncodedFormEntity encodedFormEntity = new UrlEncodedFormEntity(params, "UTF-8");
        request.setEntity(encodedFormEntity);
        CloseableHttpResponse response = httpClient.execute(request);

        System.out.println(response);
        System.out.println(getContextAsString(response));
    }

    protected static String getContextAsString(HttpResponse response) throws IOException {

        StringWriter writer = new StringWriter();
        InputStream inputStream = response.getEntity().getContent();
        try {
            IOUtils.copy(inputStream, writer, "UTF-8");
        } finally {
            inputStream.close();
        }
        return writer.toString();
    }

}
