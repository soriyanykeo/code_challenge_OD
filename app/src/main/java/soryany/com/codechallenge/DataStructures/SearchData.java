package soryany.com.codechallenge.DataStructures;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by soriyanykeo on 6/16/16.
 */
public class SearchData {
    public List<Employee> searchDataByName(List<Employee> employeeList,String name){
        List<Employee> employees = new ArrayList<Employee>();
        for (int i = 0; i < employeeList.size(); i++) {
            if (employeeList.get(i).getName().toLowerCase().equals(name.toLowerCase())){
                Log.i("com.sk.codechallenge", "searchDataByName:"+employeeList.get(i).getIDNumber() + "," +employeeList.get(i).getName() + "," +employeeList.get(i).getAddress()+","+ "," +employeeList.get(i).getZipcode()+","+employeeList.get(i).getSSN() );
                employees.add(employeeList.get(i));
            }
        }
        return employees;
    }
    public List<Employee> searchDataByZipcode(List<Employee> employeeList,String zipcode){
        List<Employee> employees = new ArrayList<Employee>();
        for (int i = 0; i < employeeList.size(); i++) {
            if (employeeList.get(i).getZipcode().toLowerCase().equals(zipcode.toLowerCase())){
                Log.i("com.sk.codechallenge", "searchDataByZipcode:"+employeeList.get(i).getIDNumber() + "," +employeeList.get(i).getName() + "," +employeeList.get(i).getAddress()+","+ "," +employeeList.get(i).getZipcode()+","+employeeList.get(i).getSSN() );
                employees.add(employeeList.get(i));
            }
        }
        return employees;
    }
    public List<Employee> searchDataBySSN(List<Employee> employeeList,String ssn){
        List<Employee> employees = new ArrayList<Employee>();
        for (int i = 0; i < employeeList.size(); i++) {
            if (employeeList.get(i).getSSN().toLowerCase().equals(ssn.toLowerCase())){
                Log.i("com.sk.codechallenge", "searchDataBySSN:"+employeeList.get(i).getIDNumber() + "," +employeeList.get(i).getName() + "," +employeeList.get(i).getAddress()+","+ "," +employeeList.get(i).getZipcode()+","+employeeList.get(i).getSSN() );
                employees.add(employeeList.get(i));
            }
        }
        return employees;
    }
}
