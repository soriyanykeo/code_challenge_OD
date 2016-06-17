package soryany.com.codechallenge;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import soryany.com.codechallenge.DataStructures.ScheduleModel;
import soryany.com.codechallenge.DataStructures.Employee;
import soryany.com.codechallenge.DataStructures.SearchData;
import soryany.com.codechallenge.DataStructures.SortData;
import soryany.com.codechallenge.Services.EmailPostRequest;
import soryany.com.codechallenge.Services.GetJsonRequest;
import soryany.com.codechallenge.Services.PostJsonRequest;

/**
 * Created by soriyanykeo on 6/16/16.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        new Thread(new Runnable() {
            public void run() {
                UserJSONRequest jsonRequest = new UserJSONRequest();
                jsonRequest.getUserJSON();
            }
        }).start();

        //set up data
        Employee employee1 = new Employee();
        employee1.setIDNumber(0001);
        employee1.setName("John");
        employee1.setAddress("Austin,TX");
        employee1.setZipcode("78749");
        employee1.setSSN("000SN1");

        Employee employee2 = new Employee();
        employee2.setIDNumber(0002);
        employee2.setName("Roger");
        employee2.setAddress("Round Rock,TX");
        employee2.setZipcode("78727");
        employee2.setSSN("000SN2");

        Employee employee3 = new Employee();
        employee3.setIDNumber(0003);
        employee3.setName("Jarrod");
        employee3.setAddress("Austin,TX");
        employee3.setZipcode("78749");
        employee3.setSSN("000SN3");

        Employee employee4 = new Employee();
        employee4.setIDNumber(0004);
        employee4.setName("Kevin");
        employee4.setAddress("San Antonio,TX");
        employee4.setZipcode("78201");
        employee4.setSSN("000SN4");

        Employee employee5 = new Employee();
        employee5.setIDNumber(0005);
        employee5.setName("Jimmy");
        employee5.setAddress("Houston,TX");
        employee5.setZipcode("77005");
        employee5.setSSN("000SN5");

        Employee employee6 = new Employee();
        employee6.setIDNumber(0006);
        employee6.setName("Adam");
        employee6.setAddress("Austin,TX");
        employee6.setZipcode("78759");
        employee6.setSSN("000SN6");

        List<Employee> employeeList = new ArrayList<Employee>();
        employeeList.add(employee2);
        employeeList.add(employee1);
        employeeList.add(employee3);
        employeeList.add(employee5);
        employeeList.add(employee6);
        employeeList.add(employee4);


        SortData sortData = new SortData();
        //sort employee by idnumber
        sortData.sortDataByIDNumber(employeeList);


        SearchData searchData = new SearchData();
        //Search employee by name
        searchData.searchDataByName(employeeList,"adam");
        //Search employee by name
        searchData.searchDataByZipcode(employeeList,"78749");
        //Search employee by name
        searchData.searchDataBySSN(employeeList,"000SN4");


        //JSONREQUEST
        new Thread(new Runnable() {
            public void run() {
                //Email
                EmailPostRequest emailPostRequest = new EmailPostRequest();
                boolean isEmailSave= emailPostRequest.Post("1","skeo@gmail.com");
                String msgStr = "There was an error during configuration and the email is not configured.";
                if (isEmailSave){
                    msgStr = "Your email's saved. Thank you!";
                }
                showAlertMSG(msgStr);
                //GetJson
                GetJsonRequest getJsonRequest = new GetJsonRequest();
                final List<ScheduleModel> values = getJsonRequest.getJSON("12");
                Log.i("com.sk.codechallenge", "getJsonRequest:"+values);
                //PostJson
                PostJsonRequest postJsonRequest = new PostJsonRequest();
                postJsonRequest.Post("10");
            }
        }).start();


    }
}
