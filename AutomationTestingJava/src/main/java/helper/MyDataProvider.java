package helper;

import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by User on 18.12.2018.
 */
public class MyDataProvider {

    @DataProvider(name="users")
    public static Iterator<Object[]> getCredentials() {

        List<Object[]> user_data = new ArrayList<Object[]>();
        user_data.add(new Object[]{"Hello", "World"});
        user_data.add(new Object[]{"Привет", "Валет"});
        user_data.add(new Object[]{"1230", "222"});
        user_data.add(new Object[]{"sdf1233", "!!!!!"});
        user_data.add(new Object[]{"11111", "2222233"});

        return user_data.iterator();
        /*return new Object[][] {
                {"Hello", "World"},
                {"Привет", "Валет"},
                {"1230", "222"},
                {"sdf1233", "!!!!!"}

        };*/
    }
}
