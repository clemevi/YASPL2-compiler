package Visitor;
import java.util.HashMap;
import java.util.Map;



public class SymbolTable {
	
	private HashMap<String, HashMap<String, Object>> table;

    public SymbolTable() {
    	
        this.table = new HashMap<String, HashMap<String,Object>>();
    }

    public void printTable() {
        System.out.println();
        System.out.println("------- Inizio stampa tabella -------");

        for (Map.Entry<String, HashMap<String, Object>> entry : table.entrySet()) {
            System.out.println("Key: " + entry.getKey());

            for (Map.Entry<String, Object> attributes : entry.getValue().entrySet()) {
                System.out.println(attributes.getKey() + ": " + attributes.getValue());
            }
            System.out.println();
        }
        
        System.out.println("------- FINE stampa tabella -------");
    }

    public Map.Entry<String,HashMap<String, Object>> addLexem(String lexem){
    	if(!table.containsKey(lexem)) {
            table.put(lexem, new HashMap<>());
            for(Map.Entry<String, HashMap<String, Object>> entry : table.entrySet()){
                if(entry.getKey().equals(lexem))
                    return entry;
            }
        }
      
        	return null;/*se la tabella dei simboli contiene già l'elemento ritorna null*/
        
       
    }
    
    public boolean containsID(String keyOfEntry) {
    	if(table.containsKey(keyOfEntry)) return true;
    	
    	return false;
    }

    public Object getLexem(String keyOfEntry, String attributeName){
        if(table.containsKey(keyOfEntry)) {
        	
        	return table.get(keyOfEntry).get(attributeName);
        }
        
        return null;
        
        
    }

    public <T> void addAttribute(String keyOfEntry, Attributo<T> attributo){
    	
    	if(table.containsKey(keyOfEntry))         	
    		table.get(keyOfEntry).put(attributo.getKey(), attributo.getValue()); 
    		
    	
    	
           
        }
}
