package Parse;
import java.io.File;
import java.lang.annotation.Documented;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.bcel.generic.NEW;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.openqa.selenium.WebDriver;

import WebDriverAction.ConfigBuilder;

public class ParseTCXML {

	
	public static Map<String,Map<String, Element>> parseXML(){
		
		SAXReader reader=new SAXReader();
		File file=new File("TestCase.xml");
		Document document=null;
		try {
			document = reader.read(file);
		} catch (DocumentException e) {
			
			System.out.println("There exists document exception while parse xml file");
		}
		//CaseScript
		Element rootElement=document.getRootElement();
		//Get Case List by case tag
	
		List<Element> CaseList= rootElement.elements("Case");
		
		
		
		List<Element> actionList=null;
		String caseName=null;
		Map<String,Map<String, Element>> caseDetails=new LinkedHashMap<String,Map<String, Element>>();
		
		for (Element TestCase : CaseList) {
			//for each case node,parse its name
			caseName=TestCase.element("Name").getTextTrim();
			//System.out.println(caseName);
			actionList=TestCase.elements("Action");
			Map<String, Element> actionMap=new LinkedHashMap<String, Element>();
			for (Element action : actionList) {
				
				//for each action node of case
				String actionName=action.attributeValue("name").trim();
				Element propNode=action.element("Properties");	
					actionMap.put(actionName, propNode);
//					System.out.println("Case "+caseName+" action contains: "+actionName);
//					System.out.println(propNode);
				}
			
			
			caseDetails.put(caseName, actionMap);
			
		}
		
		return caseDetails;
	}
}

