package Parse;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.jdt.internal.compiler.ast.SuperReference;



public class ActionMap {
	private static Map<String, String> ActionTable =new HashMap<String, String>();
	public ActionMap(){
		ActionTableIni();
		
	}
	public void ActionTableIni(){
		ActionTable.put("Navigate", "Navigate");
		ActionTable.put("TypeUser", "Type");
		ActionTable.put("TypePassWord", "Type");
		ActionTable.put("Click", "Click");
		ActionTable.put("ClickAndWait", "Click");
		ActionTable.put("screenshot", "screenshot");
		
	}
	public String TransActionName(String actionName){
		String actionClassName=null;
		try{
			actionClassName=ActionTable.get(actionName).trim();
			
		}catch(Exception e){	
			System.out.println("The action name " +actionName+" does not have a method to match");
			System.out.println(e.getMessage());
		}
		return actionClassName;
	}
}
