package Parse;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.http.client.methods.Configurable;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.openqa.selenium.WebDriver;

import WebDriverAction.ConfigBuilder;

public class DemoMain {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {

		//Parse Testcase xml file.				
		try{
		Map<String, Map<String, Element>> caseDetailsMap=ParseTCXML.parseXML();		
		
		for(Map.Entry<String, Map<String, Element>> caseEntry:caseDetailsMap.entrySet()){
			
			System.out.println("Case "+caseEntry.getKey()+" Start Testing");	
			Map<String, Element>  actions=(LinkedHashMap<String, Element>)caseDetailsMap.get(caseEntry.getKey());
			for(Map.Entry<String, Element> action : actions.entrySet()){
				System.out.println("case "+caseEntry.getKey()+" action: "+action.getKey());
				ActionInterpreter.InterpreterAction(action);
			}
			System.out.println("Case "+caseEntry.getKey()+" Pass");
			
		}
			
		}catch(Exception e){
			System.out.println("Case fail");	
			System.out.println(e.getStackTrace());	
		}finally{
			
			WebDriver driver=ConfigBuilder.Driver;
			driver.close();
		}

}
}

