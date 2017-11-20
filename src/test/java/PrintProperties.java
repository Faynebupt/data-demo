import org.junit.Test;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by 张少昆 on 2017/7/27.
 */
public class PrintProperties {
    @Test
    public void r(){
        Properties p = new Properties();
        try{
            p.load(PrintProperties.class.getResourceAsStream("application.properties"));
        } catch(IOException e){
            e.printStackTrace();
        }

        p.entrySet().forEach(e -> {
            System.out.println(e.getKey() + " -- " + e.getValue());
        });
    }

}
