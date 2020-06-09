package cn.jiang.station.platform.service;

import org.junit.Test;
import org.python.util.PythonInterpreter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * @Description python工具
 * @Created jiang
 */
public class PythonDemo {
    public static void main(String[] args) {

    }

    @Test
    public void test2() {
        //解决报错Cannot import site module and its dependencies: No module named site
        Properties props = new Properties();
        props.put("python.home", "path to the Lib folder");
        props.put("python.console.encoding", "UTF-8");
        props.put("python.security.respectJavaAccessibility", "false");
        props.put("python.import.site", "false");
        Properties preprops = System.getProperties();
        PythonInterpreter.initialize(preprops, props, new String[0]);
        PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.exec("num=[13,7,2,9,14,7,6]; ");
        interpreter.exec("print(sorted(num));");
    }

    //传入参数调用cmd执行python
    @Test
    public void test1() throws IOException, InterruptedException {
        String a = "1";
        String b = "2";
        Process proc = null;
        BufferedReader in = null;
        try {
            String pyRec = this.getClass().getResource("/python/pytest1.py").getPath().substring(1);
            System.out.println(pyRec);
            String[] args = new String[]{"python", pyRec, a, b};
            proc = Runtime.getRuntime().exec(args);
            in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            proc.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
