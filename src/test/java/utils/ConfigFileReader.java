package utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {

	private Properties properties;
	private final String propertyFilePath= "src/test/resources/configuration.properties";


	public ConfigFileReader(){
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
		} 
	}

	public String getAppiumServerURL(){
		String AppiumServerURL = properties.getProperty("appium.server.url");
		if(AppiumServerURL!= null) return AppiumServerURL;
		else throw new RuntimeException("AppiumServerURL not specified in the Configuration.properties file."); 
	}

	public String getPlatformName(){
		String platformName = properties.getProperty("android.platform");
		if(platformName!= null) return platformName;
		else throw new RuntimeException("platformName not specified in the Configuration.properties file."); 
	}

	public String getPlatformVersion(){
		String platformVersion = properties.getProperty("android.platform.version");
		if(platformVersion != null) return platformVersion ;
		else throw new RuntimeException("platformVersion  not specified in the Configuration.properties file."); 
	}

	public String getDeviceName(){
		String deviceName = properties.getProperty("android.device.name");
		if(deviceName!= null) return deviceName;
		else throw new RuntimeException("deviceName not specified in the Configuration.properties file."); 
	}

	public String getUdid(){
		String udid = properties.getProperty("android.udid");
		if(udid!= null) return udid;
		else throw new RuntimeException("udid not specified in the Configuration.properties file."); 
	}

	public String getAppLocation(){
		String appLocation = properties.getProperty("android.app.location");
		if(appLocation!= null) return appLocation;
		else throw new RuntimeException("appLocation not specified in the Configuration.properties file."); 
	}

	public String getAppPackage(){
		String appPackage = properties.getProperty("android.app.packageName");
		if(appPackage!= null) return appPackage;
		else throw new RuntimeException("appPackage not specified in the Configuration.properties file."); 
	}

	public String getAppActivityName(){
		String appActivity = properties.getProperty("android.app.activityName");
		if(appActivity!= null) return appActivity;
		else throw new RuntimeException("appActivity not specified in the Configuration.properties file."); 
	}

	public String getFullReset(){
		String fullReset = properties.getProperty("android.app.full.reset");
		if(fullReset!= null) return fullReset;
		else throw new RuntimeException("fullReset not specified in the Configuration.properties file."); 
	}

	public String getNoReset(){
		String noReset = properties.getProperty("android.app.no.reset");
		if(noReset!= null) return noReset;
		else throw new RuntimeException("noReset not specified in the Configuration.properties file."); 
	}
	
	public String getUserName(){
		String userName = properties.getProperty("userName");
		if(userName!= null) return userName;
		else throw new RuntimeException("userName not specified in the Configuration.properties file."); 
	}
	
	public String getPassword(){
		String password = properties.getProperty("password");
		if(password!= null) return password;
		else throw new RuntimeException("password not specified in the Configuration.properties file."); 
	}
	
	public String getSearchItem(){
		String searchItem = properties.getProperty("searchItem");
		if(searchItem!= null) return searchItem;
		else throw new RuntimeException("searchItem not specified in the Configuration.properties file."); 
	}

}
