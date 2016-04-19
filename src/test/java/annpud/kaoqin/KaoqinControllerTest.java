package annpud.kaoqin;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.time.LocalDate;

/**
 * Created by annpud on 16/4/14.
 */
public class KaoqinControllerTest {

    @Test
    public void timetest(){
        LocalDate d = LocalDate.parse("2013-10-01");
        System.out.println(d.toString());
    }
}