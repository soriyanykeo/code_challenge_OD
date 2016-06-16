package soryany.com.codechallenge.DataStructures;

import android.util.Log;

import java.util.List;

/**
 * Created by soriyanykeo on 6/16/16.
 */
public class SortData {
    public void sortDataByIDNumber(List<Employee> employeeList){
        int min;
        for (int i = 0; i < employeeList.size(); i++) {
            // Assume first element is min
            min = i;
            for (int j = i + 1; j < employeeList.size(); j++) {
                if (employeeList.get(j).getIDNumber() < employeeList.get(min).getIDNumber())
                {
                    min = j;
                }
            }
            if (min != i) {
                final Employee employeeTemp = employeeList.get(i);
                employeeList.set(i,employeeList.get(min));
                employeeList.set(min,employeeTemp);
            }
            // I print the in ascending order
            Log.i("com.sk.codechallenge", "sortDataByIDNumber:"+employeeList.get(i).IDnumber);
        }
    }
}
