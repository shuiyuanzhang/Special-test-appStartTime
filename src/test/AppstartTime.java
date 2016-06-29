package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class AppstartTime {
	public static void main(String[] args) throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("冷启动时间： ＝＝＝＝＝＝＝＝＝＝＝＝");
		for(int i=0;i<10;i++){
			getFirstStartTime();
		}	
		Runtime.getRuntime().exec("adb shell input keyevent 4");
		sleep(1000);
		System.out.println("热启动时间： ＝＝＝＝＝＝＝＝＝＝＝＝");
		for(int i=0;i<10;i++){
			getSecondStartTime();
		}
	}
		
//	//输入待测试的packagename
//	public static String packageName() {
//		String packagename="com.android.updater";
//		return packagename;
//	}
	
	//输入待测试的Activity
	public static String packageActivity() {
//		String pStringActivity = "com.android.updater/.MainActivity";
		String pStringActivity="tran.com.android.taplaota/.activity.OtaActivity";
//		String pStringActivity="com.testdemo/.MainActivity";
		return pStringActivity;
	}
	
	
	public static void getFirstStartTime() throws Throwable{
		try {
			Process Time = Runtime.getRuntime().exec("adb shell am start -W -S -n "+packageActivity());
			InputStream in_time = Time.getInputStream();
			BufferedReader StartTime = new BufferedReader(new InputStreamReader(in_time));
			String totalTime = null;
			while ((totalTime=StartTime.readLine())!=null) {
				if(totalTime.contains("TotalTime")){
					System.out.println(totalTime.split(":")[1].trim());					
				}
//				System.out.println(totalTime);
			}
			StartTime.close();

		} catch (IOException e) {
			// TODO: handle exception
			System.out.println("IO ERROR");			
		} 
	}	
	
	
	public static void getSecondStartTime() throws Throwable{
		try {
			Process Time = Runtime.getRuntime().exec("adb shell am start -W -n "+packageActivity());
			InputStream in_time = Time.getInputStream();
			BufferedReader StartTime = new BufferedReader(new InputStreamReader(in_time));
			String totalTime = null;
			while ((totalTime=StartTime.readLine())!=null) {
				if(totalTime.contains("TotalTime")){
					System.out.println(totalTime.split(":")[1].trim());					
				}
//				System.out.println(totalTime);
			}	
			StartTime.close();
			Runtime.getRuntime().exec("adb shell input keyevent 4");
			sleep(2000);
		} catch (IOException e) {
			// TODO: handle exception
			System.out.println("IO ERROR");			
		} 		
	}
	public static void sleep(int time) {
		try {
			Thread.sleep(time);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}





