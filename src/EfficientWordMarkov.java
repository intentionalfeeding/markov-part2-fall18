import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
public class EfficientWordMarkov extends BaseWordMarkov{
	protected Map<WordGram, ArrayList<String>> myMap;
	
	public EfficientWordMarkov(){
		super(3);
		myMap = new HashMap<WordGram, ArrayList<String>>();
	}
	
	public EfficientWordMarkov(int order){
		super(order);
		myMap = new HashMap<WordGram, ArrayList<String>>();
	}
	
	public void setTraining(String[] words){
		myWords = words;
		myMap.clear();
		for (int i = 0; i<words.length-myOrder+1;i++){
			WordGram temp = new WordGram(words,i,myOrder);
			if (!myMap.containsKey(temp)){
				ArrayList<String> newAl = new ArrayList<String>();
				if (i+myOrder >= words.length){
					newAl.add(PSEUDO_EOS);
				} else{
					newAl.add(words[i+myOrder]);
				}
				myMap.put(temp, newAl);
			} else {
				ArrayList<String> updateAl = myMap.get(temp);
				if (i+myOrder >= words.length){
					updateAl.add(PSEUDO_EOS);
				} else{
					updateAl.add(words[i+myOrder]);
				}
				myMap.put(temp, updateAl);
			}
		}	
	}
	
	public ArrayList<String> getFollows(WordGram key){
		if (!myMap.containsKey(key)) throw new NoSuchElementException(key+" not in map");
		return myMap.get(key);	
	}
		
}
