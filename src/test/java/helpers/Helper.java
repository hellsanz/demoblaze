package helpers;

import java.io.File;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Helper {
	
	private String userName;
	private String password;
	private int numScrShot = 0;
	private File myScreenShot;
	public void sleeper(int seconds) {
		try {
			Thread.sleep(seconds*1000);
		}catch (InterruptedException e){
			e.printStackTrace();
		}		
	}
	
	public void implicitSleeper(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);	
	}
	
	public String user_Alphanumeric() {      
	      this.userName = "";
	      String possible[] = {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
	      String fullName[] = {"carlos","mariano","roberto","pedro","alejandro","juan","manolo","mario","ricardo","esteban"};
	      for (int i = 0; i < 6; i++) {
	        int numero = (int)(Math.random()*62);  
	          this.userName += possible[numero];            
	      }
		  int numero = (int)(Math.random()*10);
	      return fullName[numero]+this.userName;
	}
	public String pass_Alphanumeric() {
	      this.password = "";
	      String possible[] = {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};

	      for (int i = 0; i < 10; i++) {
	        int numero = (int)(Math.random()*62);
	          this.password += possible[numero];
	      }
	      return this.password;
	}
	public Boolean singUpCheck(String msg) {      
		
        if (msg.equals("Sign up successful.")) {          
          return true;
        }
        if (msg.equals("This user already exist."))  {          
          return false;
        }
        return false;
    }
	public Boolean addToCartAlert(String msg) {
		if (msg.equals("Product added")) {
		  return true;
		}
		return false;
	}
	public void takingScreenShot(WebDriver driver, String name){

		myScreenShot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

		try {
			FileUtils.copyFile(myScreenShot,new File("src/test/screenshots/"+"ScrShot_"+numScrShot+"_"+name+"_"+System.currentTimeMillis()+".png"));
			numScrShot++;
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}

