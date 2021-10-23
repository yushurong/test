package work;





import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;

public class shiyan7 {
	public static void main(String[] args) throws Exception {
		 Long timestamp = System.currentTimeMillis();
		 	String secret = "SECdc6a90d4da528b97b1b729984b1c895813f2f5fef70c93a0a44374d15492181f";
	        String stringToSign = timestamp + "\n" + secret;
	        Mac mac = Mac.getInstance("HmacSHA256");
	        mac.init(new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256"));
	        byte[] signData = mac.doFinal(stringToSign.getBytes("UTF-8"));
	        String sign = URLEncoder.encode(new String(Base64.encodeBase64(signData)),"UTF-8");
	        // System.out.println(timestamp);
	        // System.out.println(sign); 
	        //https://oapi.dingtalk.com/robot/send?access_token=db184277fc3a53cf63bd5078c7e8c402219ca135bc6ac378356714cb494460f9
	        
	        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/robot/send?"
	        		+ "access_token=db184277fc3a53cf63bd5078c7e8c402219ca135bc6ac378356714cb494460f9"
	        		+"&timestamp="+timestamp+"&sign="+sign);
	        OapiRobotSendRequest request = new OapiRobotSendRequest();
	        request.setMsgtype("text");
	        OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
	        text.setContent("hello world");
	        request.setText(text);
	        OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();        
	        at.setIsAtAll(true);	        
	        request.setAt(at);
	        OapiRobotSendResponse response = client.execute(request);
	}
	
}
