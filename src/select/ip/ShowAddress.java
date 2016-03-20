package select.ip;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONString;
import org.json.JSONStringer;

import Pattern.PatternUtil;

public class ShowAddress {
	private int errNum;
	private String  errMsg;
	private String country;
	private String carrier;
	private String province;
	private String city;
	private String httpUrl;
	private String httpArg;
	private String mykey;
	public ShowAddress(String mykey) {
		httpUrl = "http://apis.baidu.com/apistore/iplookupservice/iplookup";
		this.mykey = mykey;
	}

	/**
	 * @param urlAll
	 *            :请求接口
	 * @param httpArg
	 *            :参数
	 * @return 返回结果
	 */
	private  String request(String httpUrl, String httpArg) {
	    BufferedReader reader = null;
	    String result = null;
	    StringBuffer sbf = new StringBuffer();
	    httpUrl = httpUrl + "?" + httpArg;

	    try {
	        URL url = new URL(httpUrl);
	        HttpURLConnection connection = (HttpURLConnection) url
	                .openConnection();
	        connection.setRequestMethod("GET");
	        // 填入apikey到HTTP header
	        connection.setRequestProperty("apikey", mykey);
	        connection.connect();
	        InputStream is = connection.getInputStream();
	        reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
	        String strRead = null;
	        while ((strRead = reader.readLine()) != null) {
	            sbf.append(strRead);
	            sbf.append("\r\n");
	        }
	        reader.close();
	        result = sbf.toString();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return result;
	}
	
	private String jsonToString(String json){
		String result = "";
		
		try {
			JSONObject jsonObject = new JSONObject(json);
			errNum = jsonObject.getInt("errNum");
			errMsg = jsonObject.getString("errMsg");
			JSONObject retData = jsonObject.getJSONObject("retData");
			country = retData.getString("country");
			province = retData.getString("province");
			city = retData.getString("city");
			carrier = retData.getString("carrier");
			
			result =country+","+province+"省,"+city+"市,"+carrier;
		} catch (JSONException e) {
			System.out.println("jsonToString出错....打印错误信息开始====start====");
			e.printStackTrace();
			System.out.println("jsonToString错误信息打印结束======end====");
		}
		
		return result ;
	}
	
	public boolean getAddressOK(){
		if(errNum == 0 && errMsg.equals("success") ){
			return true;
		}else{
			return false;
		}
	}
	
	public String getResult(String ip){
		String result = "";
		httpArg = "ip="+ip;
		
		String jsonString = "";
		jsonString = request(httpUrl,httpArg);
		result = jsonToString(jsonString);
		
		if(getAddressOK()){
			System.out.println(httpArg +"  ----- > 查询成功");
		}else{
			System.out.println(httpArg +"  ----- > 查询失败");
		}
		
		return result;
	}
	
}
