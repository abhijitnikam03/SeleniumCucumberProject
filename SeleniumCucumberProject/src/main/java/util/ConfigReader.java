package util;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {

	private Properties prop;
	
	public Properties init_prop() {
		
		prop = new Properties();
		try {
			FileInputStream fis=new FileInputStream("src/test/resource/config.properties");
			prop.load(fis);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return prop;
	}
}
