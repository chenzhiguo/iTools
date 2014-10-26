package org.uugu.itools.http;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;

/**
 * Http访问工具类
 * @author Silence
 *
 */
public class HttpUtil {

	public static void main(String[] args) throws Exception {
//		Map<String, String> params = new HashMap<String, String>();
//		params.put("log","admin");
//		params.put("pwd","zhimakaimena");
//		//Test test = doGet("http://noteme.cn/wp-login.php", params, Test.class);
//		Test test2 = doPost("http://noteme.cn/wp-login.php", params, Test.class);
//		//System.out.println(test.getUserName());
//		System.out.println(test2.getPassword());
	}

	/**
	 * Get访问方式
	 * @param url 访问地址
	 * @param params 请求参数
	 * @param clazz 返回数据背解析成为的对象类型
	 * @param argClasses 返回集合类型时的泛型类型
	 * @return
	 * @throws Exception
	 */
	public static <T> T doGet(String url, Map<String, String> params,
			Class<T> clazz, Class<?>... argClasses) throws Exception {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		ObjectMapper objectMapper = new ObjectMapper();
		String responseBody = "";
		try {
			RequestBuilder requestBuilder = RequestBuilder.get().setUri(
					new URI(url));
			if (params != null) {
				for (Entry<String, String> entry : params.entrySet()) {
					requestBuilder.addParameter(entry.getKey(),
							entry.getValue());
				}
			}
			HttpUriRequest httpGet = requestBuilder.build();
			httpGet.addHeader("Content-Type", "text/html;charset=UTF-8");
			CloseableHttpResponse response = httpclient.execute(httpGet);

			try {
				HttpEntity entity = response.getEntity();
				// 判断执行结果返回状态
				int status = response.getStatusLine().getStatusCode();
				if (status >= 200 && status < 300) {
					responseBody = entity != null ? EntityUtils
							.toString(entity) : null;
				} else {
					throw new ClientProtocolException(
							"Unexpected response status: " + status);
				}
				// 判断返回类型是否为JSON
				ContentType contentType = ContentType.getOrDefault(entity);
				if (!contentType.getMimeType().equals(
						ContentType.APPLICATION_JSON.getMimeType())) {
					throw new ClientProtocolException(
							"Unexpected content type: " + contentType);
				}
				EntityUtils.consume(entity);
			} finally {
				response.close();
			}
		} finally {
			httpclient.close();
		}

		if (argClasses.length > 0) {
			JavaType javaType = objectMapper.getTypeFactory()
					.constructParametricType(clazz, argClasses);
			return objectMapper.readValue(responseBody, javaType);
		} else {
			return objectMapper.readValue(responseBody, clazz);
		}

	}

	/**
	 * Post访问方式
	 * @param url 访问地址
	 * @param params 请求参数
	 * @param clazz 返回数据背解析成为的对象类型
	 * @param argClasses 返回集合类型时的泛型类型
	 * @return
	 * @throws Exception
	 */
	public static <T> T doPost(String url, Map<String, String> params,
			Class<T> clazz, Class<?>... argClasses) throws Exception {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		ObjectMapper objectMapper = new ObjectMapper();
		String responseBody = "";
		HttpPost httpPost = new HttpPost();
		httpPost.addHeader("Content-Type", "text/html;charset=UTF-8");
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		if (params != null) {
			for (Entry<String, String> entry : params.entrySet()) {
				nvps.add(new BasicNameValuePair(entry.getKey(), entry
						.getValue()));
			}
		}
		httpPost.setEntity(new UrlEncodedFormEntity(nvps));
		CloseableHttpResponse response = httpclient.execute(httpPost);

		try {
			HttpEntity entity = response.getEntity();
			// 判断执行结果返回状态
			int status = response.getStatusLine().getStatusCode();
			if (status >= 200 && status < 300) {
				responseBody = entity != null ? EntityUtils.toString(entity)
						: null;
			} else {
				throw new ClientProtocolException(
						"Unexpected response status: " + status);
			}
			// 判断返回类型是否为JSON
			ContentType contentType = ContentType.getOrDefault(entity);
			if (!contentType.getMimeType().equals(
					ContentType.APPLICATION_JSON.getMimeType())) {
				throw new ClientProtocolException("Unexpected content type: "
						+ contentType);
			}
			EntityUtils.consume(entity);
		} finally {
			response.close();
			httpclient.close();
		}

		if (argClasses.length > 0) {
			JavaType javaType = objectMapper.getTypeFactory()
					.constructParametricType(clazz, argClasses);
			return objectMapper.readValue(responseBody, javaType);
		} else {
			return objectMapper.readValue(responseBody, clazz);
		}

	}
}
