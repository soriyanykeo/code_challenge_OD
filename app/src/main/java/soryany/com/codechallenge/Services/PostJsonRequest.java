package soryany.com.codechallenge.Services;

import android.util.Log;

import soryany.com.codechallenge.DataStructures.ScheduleModel;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by soriyanykeo on 6/16/16.
 */
public class PostJsonRequest {
    public String getCheckSum() {
        return checkSum;
    }

    public void setCheckSum(String checkSum) {
        this.checkSum = checkSum;
    }

    private String checkSum;
    public void Post(List<ScheduleModel> scheduleList,String id){
        HttpClient client = new DefaultHttpClient();
        String message ;
        HttpPost post = new HttpPost("https://sampleAPIs.com/schedule/setSchedule");
        JSONObject object = new JSONObject();
        try {
            JSONArray arrayList = new JSONArray();
            int i=0;
            while (i<scheduleList.size()) {
                JSONObject childObject = new JSONObject();
                int fromH = scheduleList.get(i).fromHour;
                if (scheduleList.get(i).fromMode.equals("PM") && scheduleList.get(i).fromHour<12){
                    fromH = fromH+12;
                }

                int untilH = scheduleList.get(i).untilHour;
                if (scheduleList.get(i).untilMode.equals("PM") && scheduleList.get(i).untilHour<12){
                    untilH = untilH+12;
                }
                if (scheduleList.get(i).fromMode.equals("AM") && fromH==12)
                    fromH = 0;
                if (scheduleList.get(i).untilMode.equals("AM") && untilH==12)
                    untilH=0;

                System.out.println("from:"+fromH+", until:"+untilH);
                System.out.println("schedule : "+scheduleList.get(i));
                childObject.put("title",scheduleList.get(i).title);
                childObject.put("from", fromH+"_"+scheduleList.get(i).fromMin);
                childObject.put("until", untilH+"_"+scheduleList.get(i).untilMin);
                childObject.put("setTemp", scheduleList.get(i).temp);
                childObject.put("isOn", scheduleList.get(i).isOn);
                arrayList.put(childObject);
                i++;
            }

            object.put("schedule",arrayList);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        String childMessage = object.toString();
        message = "accountid="+id+"&schedule="+childMessage;
        try {
            post.setEntity(new StringEntity(message, "UTF-8"));
            post.setHeader("Content-type", "application/x-www-form-urlencoded");            HttpResponse resp = client.execute(post);
           
            String responseBody = EntityUtils.toString(resp.getEntity());
            try {
                JSONObject resultObject = new JSONObject(responseBody);
                System.out.println("resp value : "+resultObject.getString("error")+"\n"+resultObject.getString("checksum")+"\n"+resultObject.getString("schedule"));
                setCheckSum(resultObject.getString("checksum"));
            } catch (JSONException e) {
                e.printStackTrace();
                setCheckSum("");
            }
        }
        catch (Exception e){
            e.printStackTrace();
            setCheckSum("");
        }
    }

}
