import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class EfficientMarkov extends BaseMarkov{
	protected Map<String, ArrayList<String>> myMap;
	
	public EfficientMarkov(){
		super(3);
		this.myMap = new HashMap<String, ArrayList<String>>(); 
	}
	
	public EfficientMarkov(int order){
		super(order);
		this.myMap = new HashMap<String, ArrayList<String>>(); 
	}
	
	public void setTraining(String text){
		myText = text;
		myMap.clear();
		for (int i=0; i<myText.length()-myOrder+1;i++){
			String temp = myText.substring(i, i+myOrder);
			if (!myMap.containsKey(temp)){
				ArrayList<String> newAl = new ArrayList<String>();
				if (i+myOrder>=myText.length()){
					newAl.add(PSEUDO_EOS);
				} else {
					newAl.add(myText.substring(i+myOrder, i+myOrder+1));
				}
				myMap.put(temp, newAl);
				
			} else{
				ArrayList<String> updateAl = myMap.get(temp);
				if (i+myOrder>=myText.length()){
					updateAl.add(PSEUDO_EOS);
				} else {
					updateAl.add(myText.substring(i+myOrder, i+myOrder+1));
				}
				myMap.put(temp, updateAl);	
			}	
		}	
	}
	
	public ArrayList<String> getFollows(String key){
		if (!myMap.containsKey(key)) throw new NoSuchElementException(key+" not in map");
		return myMap.get(key);	
	}

}
