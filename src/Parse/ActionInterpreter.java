package Parse;

import java.util.Dictionary;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Element;

public class ActionInterpreter{
	public static void InterpreterAction(Map.Entry<String, Element> actionMapEntry){
		
		String actionName=actionMapEntry.getKey().trim();
		String actionClassNameString=null;;
		Class actionClass;
		try {
			Map<String, String> actionParam=new LinkedHashMap<String, String>();
			ActionMap actionMap=new ActionMap();
			
			actionClassNameString=actionMap.TransActionName(actionName);
			actionParam.put("actionName", actionClassNameString);
			Element PropertNode= actionMapEntry.getValue();
			
			
			List<Attribute> attributesList=PropertNode.attributes();
			for (Attribute attribute : attributesList) {
				//System.out.println(attribute.getName());
				String attributeNameString=attribute.getName().trim();
				actionParam.put(attributeNameString, PropertNode.attributeValue(attributeNameString));
			}
			//Create action and execute
			String actionPackageString="WebDriverAction";				
			actionClass = Class.forName(actionPackageString+"."+actionClassNameString);
			Action action=(Action)actionClass.newInstance();
			action.ActionParam=actionParam;
			action.Do();
		} catch (ClassNotFoundException e) {
			System.out.println("Class " +actionClassNameString+" not found");

		}
		catch (InstantiationException | IllegalAccessException e) {
			System.out.println("InstantiationException or IllegalAccessException occurs");

			
		}
		
	} 
}
