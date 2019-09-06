package Visitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Stack;
import java.io.*;


public class NodeVisitor implements Visitor {

	public String content = "";
    public String Error = "";
    public int i=0;
    private HashMap<String, SymbolTable> Tables ;
    private Stack<SymbolTable> myStack; 
    
    public NodeVisitor() throws IOException {
    	super();
    	this.Tables = new HashMap<>();
    	this.myStack = new Stack<SymbolTable>();
    }
    
    @Override
    public String visit(VisitableNode node) {
        this.content = String.format("<%s>\n",node.data());
	        if(node.getLexem()!= null)
				this.content += String.format("%s\n",node.getLexem());
        Iterator<VisitableNode> childs = node.subtrees().iterator();
        VisitableNode currentNode;
        
        while(childs.hasNext()) {
            if(!(currentNode = childs.next()).isLeaf()){
                this.content += currentNode.accept(this);
            }
            else{
            	if(!currentNode.getChildList().isEmpty()) {
            		@SuppressWarnings("unchecked")
					ArrayList<VisitableNode<String>> tmp = currentNode.getChildList(); 
            		for(int i=0; i< tmp.size();i++)
            				this.content += tmp.get(i).accept(this);
            	}else if(!currentNode.data().equals("child_list")) { 
            		
		            		this.content += String.format("<%s>\n",currentNode.data());
		            		
		        			if(currentNode.getLexem()!= null)
		        				this.content += String.format("%s\n",currentNode.getLexem());
		        			this.content += String.format("</%s>\n",currentNode.data());
            		}
            }
        }
        this.content += String.format("</%s>\n",node.data());
        return this.content;
    }
    

    
    public String getCode(VisitableNode node) {
    	
    	Iterator<VisitableNode> childs = node.subtrees().iterator();
    	String row = new String();  
    	
    	if(node.data().equals("PROGRAM_NODE")) {
    		

    		ArrayList<VisitableNode<String>> decl_list = (ArrayList<VisitableNode<String>>)  (childs.next()).getChildList(); 
    		ArrayList<VisitableNode<String>> stat_list = (ArrayList<VisitableNode<String>>)  (childs.next()).getChildList(); 
    		
    		for(int i = decl_list.size()-1; i>=0; i--) 
    			row += getCode(decl_list.get(i)); 
    		
    		row += "\nint main(int argc, char** argv){\n";
    		for(int i = stat_list.size()-1; i>=0; i--) 
    			row += getCode(stat_list.get(i)); 
    		
    		row+= "\n return 0;\n}";
    		
    		
    		return row;
    		
    		
    	}else if(node.data().equals("PROC_DECL_NODE")) {
		    		String decl;
		    		String[] Par = null;
		    		
		    		row += "\nvoid "+node.getLexem()+"(";
		    		
		    		ArrayList<VisitableNode<String>> var_decl_list = (ArrayList<VisitableNode<String>>)  (childs.next()).getChildList(); 
		    		ArrayList<VisitableNode<String>> par_decl_list = (ArrayList<VisitableNode<String>>)  (childs.next()).getChildList(); 
		    		
		    		
		    		for(int i = var_decl_list.size()-1; i>=0; i--) {
		    			decl = getCode(var_decl_list.get(i));
		    			decl = decl.replace(';', ',');
		    			
		    			row+=decl;
					}
		    		
		    		for(int i = par_decl_list.size()-1; i>=0; i--) {
		    			decl = getCode(par_decl_list.get(i));
		    			decl = decl.replace(';', ',');
		    			
		    			row+=decl;
		    			
		    			decl = decl.replaceAll("int. |double. ", "");
		    			Par = decl.split(",");
					
		    		}
		    		
		    		row = row.substring(0,row.length()-2);
					row+=") {\n";
					
					childs.next();
					
					String body=getCode(childs.next());
					
					
					
					for(int i = 0; i<Par.length-1; i++) 
		    			body = body.replaceAll(Par[i],"*"+Par[i]);
		    			
					row+=body+"}\n";
					
					
					return row;
		    		
		    		
    		
    		
    	} else if(node.data().equals("VAR_DECL_NODE")||node.data().equals("PAR_DECLS")) {
    			
			    VisitableNode typeNode = childs.next();
			    row = this.printVarOp(childs.next(), row);
			    
			    if(node.data().equals("VAR_DECL_NODE")) {
			    	row = row.replaceAll("Boolean ", "int ");
	    			row = row.replaceAll("Double ", "double ");
	    			row = row.replaceAll("Integer ", "int ");
			    } else {
			    	row = row.replaceAll("Integer ", "int* ");
	    			row = row.replaceAll("Double ", "double* ");
	    			row = row.replaceAll("Boolean ", "int* ");
			    }
			    
			  
			    return row;
    		
    		
    	} else if(node.data().equals("READ_OP_NODE")) {
    		
    		row = "scanf(\"";
    		
    		ArrayList<VisitableNode<String>> Id_list = (ArrayList<VisitableNode<String>>)  (childs.next()).getChildList(); 
    		ArrayList<VisitableNode<String>> Type_list = (ArrayList<VisitableNode<String>>)  (childs.next()).getChildList(); 
    		
    		for(int i = Type_list.size()-1; i>=0; i--) {
    		
    			if(Type_list.get(i).data().equals("Integer") || Type_list.get(i).data().equals("Boolean"))    				
    				row+="%d ";
    			else 
    				row+="%lf ";	
    		}
    		row = row.substring(0,row.length()-1);
    		row+="\", ";
    		
    		for(int i = Id_list.size()-1; i>=0; i--) {
    			
    			row+= "&"+Id_list.get(i).getLexem()+", ";
    			
    			
    		}
    		row = row.substring(0,row.length()-2);
    		row+=");\n";
    		
    		return row;
    		
    		    		
    	}else if(node.data().equals("WRITE_OP_NODE")) {
    		
    		String tipo;
    		String tmp =",";;
    		row = "printf(\"";
    		
    		
    		ArrayList<VisitableNode<String>> Out_list = (ArrayList<VisitableNode<String>>)  (childs.next()).getChildList(); 
    		
		    		for(int i = Out_list.size()-1; i>=0;i--) {
		    			if(Out_list.get(i).data().equals("STRING_CONST")) {
		    				row += Out_list.get(i).getLexem().replace("\n", "\\"+"n");
		    			}else if(Out_list.get(i).data().equals("ID_NODE")) {
		    				tmp += Out_list.get(i).getLexem()+",";
		    				
		    				switch (Out_list.get(i).getType()) {
							case "Integer":
								row += "%d";
								break;
								
							case "Double":
								row += "%lf";
								break;
								
							case "Bool":
								row += "%d";
								break;
							}
		    			}else if(Out_list.get(i).data().equals("ADD_NODE") || Out_list.get(i).data().equals("MINUS_NODE") || Out_list.get(i).data().equals("MUL_NODE") || Out_list.get(i).data().equals("DIV_NODE")) {
		    				
		    				tmp+=getCode(Out_list.get(i))+",";
		    				
		    				switch (Out_list.get(i).getType()) {
								case "Integer":
									row += "%d";
									break;
									
								case "Double":
									row += "%lf";
									break;
									
								case "Bool":
									row += "%d";
									break;
							}
		    			}else if(Out_list.get(i).data().equals("INT_CONST") || Out_list.get(i).data().equals("DOUBLE_CONST")) 
		    				row += Out_list.get(i).getLexem();
		    				
		    		}
		    		tmp = tmp.substring(0,tmp.length()-1);
	    			row+="\""+tmp+");\n";
	    			
	    			return row;
	    			
		    		
    		} else if(node.data().equals("ADD_NODE") || node.data().equals("UMINUS_NODE") || node.data().equals("NOT_NODE") || node.data().equals("MINUS_NODE") || node.data().equals("MUL_NODE") || node.data().equals("DIV_NODE")||node.data().equals("GT_NODE") || node.data().equals("LT_NODE") || node.data().equals("GE_NODE") || node.data().equals("LE_NODE") || node.data().equals("EQ_NODE") || node.data().equals("OR_NODE") || node.data().equals("AND_NODE")) {
    	    	
    				row = getExpr(node,row);
    				
    				return row;
    			
    		} else if (node.data().equals("ASSIGN_NODE")) {
    				 row=childs.next().getLexem()+" = "+getCode(childs.next())+";\n";
    				 
    				 
    		}else if(node.data().equals("FOR_NODE")) {
				 row="for(";
				 VisitableNode<String> assign1 = childs.next();
				 VisitableNode<String> be = childs.next();
				 VisitableNode<String> assign2 = childs.next();
				 VisitableNode<String> cs = childs.next();
				 row += getCode(assign1);
				 row = row.substring(0,row.length()-1);
				 row += getCode(be) + ";";
				 row += getCode(assign2);
				 row = row.substring(0,row.length()-2);
				 row += ") {\n";
				 row += getCode(cs);
				 row += "}\n\n";
				 
				 return row;
				 
				 
		} else if(node.data().equals("ID_NODE")||node.data().equals("INT_CONST") ||node.data().equals("DOUBLE_CONST")||node.data().equals("TRUE")||node.data().equals("FALSE")) {
    			
    				return node.getLexem();
    				
    		} else if(node.data().equals("CALL_OP_NODE")) {
    			
    			row = node.getLexem()+'(';
    			
    			ArrayList<VisitableNode<String>> input_list = (ArrayList<VisitableNode<String>>)  (childs.next()).getChildList(); 
	    		ArrayList<VisitableNode<String>> output_list = (ArrayList<VisitableNode<String>>)  (childs.next()).getChildList(); 
	    		
	    		
	    		for(int i = output_list.size()-1; i>=0; i--) 
	    			row += getCode(output_list.get(i)) + ',';
	    			
	    		
	    		for(int i = input_list.size()-1; i>=0; i--) 
	    			row+= "&"+input_list.get(i).getLexem()+',';
	    	
	    		
	    		row = row.substring(0,row.length()-1);
				row+=");\n";
				
				
				
				return row;
        		
    		} else if(node.data().equals("WHILE_NODE")) {
    			
    			row="\nwhile("+getCode(childs.next())+") {\n"+getCode(childs.next())+"\n}";
    			
    			
    			return row;
    			
    		} else if(node.data().equals("IFTHENELSE_NODE") || node.data().equals("IFTHEN_NODE")) {
    			
    			row="\nif("+getCode(childs.next())+"){\n"+getCode(childs.next())+"}";
    			
    			
    			if(node.data().equals("IFTHENELSE_NODE")) {
    				row+=" else {\n"+getCode(childs.next())+"}";
    			}
    			
    			row+="\n";
    			
    			return row;
    			
    		} 
    	
    	
    	while(childs.hasNext()) {
			  
			  VisitableNode<String> Children; 
			  if(!(Children = childs.next()).isLeaf()){
				  
				  row += getCode(Children);
		            
		        } else if(!Children.getChildList().isEmpty()) {
		        	 
		        		@SuppressWarnings("unchecked")
						ArrayList<VisitableNode<String>> tmp = (ArrayList<VisitableNode<String>>) Children.getChildList(); 
		        		for(int i=tmp.size()-1; i>=0 ;i--) {
		        				row += getCode(tmp.get(i));
		        				
		        			}
		        } else {
		        	 row += getCode(Children);
		        	
		        	
		        }
			  
		  	}
    	
    	
    	
    	return row;
    	
    }
    
    
    public boolean SemanticAnalysis(VisitableNode node) {
    	Iterator<VisitableNode> childs = node.subtrees().iterator();
    	
    	//System.out.println("VISIT TO: "+node.getLexem()+"  "+node.data());
    			  
    	if(node.data().equals("PROGRAM_NODE")) {
    				      
	    				  Tables.put(node.data().toString(), new SymbolTable());
	    				  
	    				  myStack.push(Tables.get(node.data().toString()));
	    				  
		  				  
	    				  
    			  } else if(node.data().equals("PROC_DECL_NODE")) {
	    				  	
	    				    myStack.peek().addLexem(node.getLexem());  
	    				    myStack.peek().addAttribute(node.getLexem(), new Attributo<String>("Type","function"));
	    				    
		    				Tables.put(node.getLexem(), new SymbolTable());
		    				
		    				  
		    				myStack.push(Tables.get(node.getLexem()));
		    				myStack.peek().addLexem(node.getLexem());
		    				myStack.peek().addAttribute(node.getLexem(), new Attributo<String>("Type","function"));
	    				    
		    				VisitableNode<String> input = childs.next();
		    				VisitableNode<String> output = childs.next();
		    				
		    				
		    				myStack.peek().addAttribute(node.getLexem(), new Attributo<VisitableNode>("Input", input));
		    				myStack.peek().addAttribute(node.getLexem(), new Attributo<VisitableNode>("Output", output));
	    				    
		    				
		    				
		  				  
		    				
		    			  childs = node.subtrees().iterator();
	    				  
    			  } else if(node.data().equals("COMP_STAT_NODE")) {
    				 
    				  
    				  myStack.push(new SymbolTable());
    				  
    				 
    				  
    			  }
    				  		
    		
    			  if(node.data().equals("VAR_DECL_NODE") || node.data().equals("PAR_DECLS")) {
    				  	childs = node.subtrees().iterator();
	  				    VisitableNode typeNode = childs.next();
	  				    
	  				    
	  				    if( this.VarOpVisit(childs.next(), typeNode.data().toString()) == false) {  return false; }
	  				 	
	  				    return true;
    			  }
    			  
    			  
    			 
    			  
    			 
    			  
    			  while(childs.hasNext()) {
    				  
    				  VisitableNode<String> Children; 
    				  if(!(Children = childs.next()).isLeaf()){
    					  
				            if(!this.SemanticAnalysis(Children)) { return false; }
				            String Type = CheckTypeSystem(Children);
				            if(Type == null) return false;
    	    			  	else Children.setType(Type);
				            
				        } else if(!Children.getChildList().isEmpty()) {
				        	 
				        		@SuppressWarnings("unchecked")
								ArrayList<VisitableNode<String>> tmp = (ArrayList<VisitableNode<String>>) Children.getChildList(); 
				        		for(int i=0; i< tmp.size();i++) {
				        				
				        				if(!this.SemanticAnalysis(tmp.get(i))) { return false; }
				        				 String Type = CheckTypeSystem(tmp.get(i));
				        				 if(Type == null) return false;
				    	    			  	else tmp.get(i).setType(Type);
				        			}
				        } else {
				        	
				        	if(!this.SemanticAnalysis(Children)) { return false; }
				        	String Type = CheckTypeSystem(Children);
				            if(Type == null) return false;
    	    			  	else Children.setType(Type);
				        }
    				  
    				 
    			  	}
    			  
    			  
    			  
    			  
    			  if(node.data().equals("BODY_NODE")||node.data().equals("COMP_STAT_NODE")) {  myStack.pop(); }
    			  //System.out.println(node.getLexem()+"  ||  "+node.data());
    			  
    	return true;
    }
    
    private String CheckTypeSystem(VisitableNode node) {
    	
    	String NameNode = node.data().toString(); 
    	
    	//System.out.println(node.getLexem()+"  "+NameNode);
    	
    	Iterator<VisitableNode> childs = node.subtrees().iterator(); 
    	
    	
    	
    	
		  if(NameNode == "ID_NODE")  {
			  
			  String Type = "";
			  
			  
			  int i = myStack.size();
			  do {
				  
				  SymbolTable curTab = myStack.elementAt(myStack.size()-i);
				  if(curTab.containsID(node.getLexem())) {
					Type = (String) curTab.getLexem(node.getLexem(), "Type");  
				  }
				  //System.out.println("ITERAZIONE: "+i);
				  //curTab.printTable();
				  i--;
			  }while(i>0); 
				 
			  if(Type == "") { Error="ERRORE: Identificatore "+node.getLexem()+" non dichiarato"; return null;}
			  else return Type;
			  
		  }
		  
		  
		  switch(NameNode) {
		  case "Integer":
			  	return "Integer";
			  	
		  case "Double":
			  	return "Double";
			  	
		  case "Boolean":
			  	return "Boolean";
			  	
	  	  case "BOOL_CONST":
	  			return "Boolean";
	  			
	  	  case "INT_CONST":
			  	return "Integer";
		  		
		  case "DOUBLE_CONST":
			  	return "Double";
	  			
		  case "TRUE":
			  	return "Boolean";
	  			
		  case "FALSE":
		  		return "Boolean";
	  			
		  case "STRING_CONST":
		  		return "String";
	  			
	  }
		  
		  
    	
    	
    	
    	
    	
		VisitableNode FirstChildNode = childs.next(); 
		  
		
    	switch(NameNode) {
    	
    	case "COMP_STAT_NODE":
    			return "void";
    		
		
    	case "ASSIGN_NODE":
	    		VisitableNode SecondChildNode = childs.next();
	    		if((FirstChildNode.getType()=="Integer"||FirstChildNode.getType()=="Double") && (SecondChildNode.getType()=="Integer"||SecondChildNode.getType()=="Double")) {
	    			return "void";
	    		} else if (FirstChildNode.getType()=="Boolean" && SecondChildNode.getType()=="Boolean") {
	    			return "void";
	    		} else {
					Error=" Type mismatch (" + NameNode + ")->" + FirstChildNode.getLexem() +"\nnon è possibile assegnare un valore "+SecondChildNode.getType()+" ad una variabile di tipo "+FirstChildNode.getType();
					return null;
				}
    		
			
    	case "NOT_NODE":
    			if(FirstChildNode.getType()=="Boolean")
    				return "Boolean";
				else {
					Error="ERRORE: Type mismatch (" + NameNode + ")->" + node.getLexem();
					return null;
				}
			
    	
    	case "UMINUS_NODE":
	    		if(FirstChildNode.getType()=="Integer") {
	    			return "Integer";
	    		} else if(FirstChildNode.getType()=="Double"){
	    			return "Double";
	    		} else {
					Error="ERRORE: Type mismatch (" + NameNode + ")->" + node.getLexem() +"\nExpected Integer or Double after '-'";
					return null;
				}
    		
    	}
    	
    	if(NameNode=="WHILE_NODE"||NameNode=="IFTHEN_NODE"||NameNode=="IFTHENELESE_NODE") {
	    		if(FirstChildNode.getType()=="Boolean")
					return "void";
				else {
					Iterator<VisitableNode> tmp = childs.next().subtrees().iterator();
					  VisitableNode tmp2 = tmp.next();
					Error="ERRORE: Type mismatch (" + NameNode + ")->" + tmp2.data().toString()+"\nExpected a Boolean Expression as a statments condition";
					return null;
				}
    	
    	}else if(NameNode=="OR_NODE"||NameNode=="AND_NODE") {
	    		
    			VisitableNode SecondChildNode = childs.next();
	    		if(FirstChildNode.getType()=="Boolean" && SecondChildNode.getType()=="Boolean") 
	    			return "Boolean";
	    		else {
					Error="ERRORE: Type mismatch (" + NameNode + ")->" + node.getLexem();
					return null;
				}	
	    		
    	}else if(NameNode=="ADD_NODE"||NameNode=="MINUS_NODE"||NameNode=="MUL_NODE"||NameNode=="DIV_NODE") {
	    		
    			VisitableNode SecondChildNode = childs.next();
	    		if(FirstChildNode.getType()=="Integer"||FirstChildNode.getType()=="Double") 
	    			if(SecondChildNode.getType()=="Integer"||SecondChildNode.getType()=="Double")
	    				if(FirstChildNode.getType()=="Double"||SecondChildNode.getType()=="Double")
	    					return "Double";
	    				else
	    					return "Integer";
	    		
	    		 else {
					Error="ERRORE: Type mismatch (" + NameNode + ")->" + node.getLexem();
					return null;
				}
    		
    	}else if(NameNode=="GT_NODE"||NameNode=="GE_NODE"||NameNode=="LT_NODE"||NameNode=="LE_NODE"||NameNode=="EQ_NODE") {
	    		
    			VisitableNode SecondChildNode = childs.next();
    			
	    		if((FirstChildNode.getType()=="Integer"||FirstChildNode.getType()=="Double") && (SecondChildNode.getType()=="Integer"||SecondChildNode.getType()=="Double")) {
	    			return "Boolean";
	    		} else {
					Error="ERRORE: Type mismatch (" + NameNode + ")->" + node.getLexem();
					return null;
				}
    	} else if(NameNode=="READ_OP_NODE") {
    			
    			VisitableNode SecondChildNode = childs.next();
    			ArrayList<VisitableNode> First,Second;
    			First = FirstChildNode.getChildList();//lista identificatori
    			Second = SecondChildNode.getChildList();//lista tipi
    			
    			for(int i=0;i<FirstChildNode.getChildList().size();i++) {
    				//x,y<-int,double;
    				if(First.get(i).getType() != Second.get(i).getType()) {
    					Error="ERRORE: Type mismatch (" + NameNode + ")->" + node.getLexem();
						return null;
    				}
    			}
    		
    	} else if(NameNode=="VAR_DECL_NODE" || NameNode=="PAR_DECLS") {
			  
				return FirstChildNode.data().toString();
		  
	  
    	}else if(NameNode == "CALL_OP_NODE") {
    		
    		int j = 0, k = 0;
    		VisitableNode SecondChildNode = childs.next();
    		VisitableNode InputNode = (VisitableNode<String>) (Tables.get(node.getLexem())).getLexem(node.getLexem(), "Input");
    		VisitableNode OutputNode = (VisitableNode<String>) (Tables.get(node.getLexem())).getLexem(node.getLexem(), "Output");
    		
    		
    		VisitableNode input_child, output_child, expr_node;
    		
    		if((InputNode.getChildList().size())!=SecondChildNode.getChildList().size()-1) {
    			this.Error="Controllare numero paramentri di input."+InputNode.getChildList().size()+"!="+FirstChildNode.getChildList().size(); return null;
    		} else if ((OutputNode.getChildList().size())!=FirstChildNode.getChildList().size()-1) {
    			this.Error="Controllare numero paramentri di output."; return null;
    		}
    
    		for(int i=InputNode.getChildList().size()-1;i>=0;i--) {
    			
    			input_child = (VisitableNode<String>) InputNode.getChildList().get(i);
    			    			
    			for( j = j; j<(k+Integer.valueOf(input_child.getLexem())); j++) {
    				
    				expr_node = (VisitableNode) SecondChildNode.getChildList().get(j);
    				
    				if(expr_node.getType() != input_child.getType()) { this.Error="Type Missmatch in the function: "+node.getLexem()+"["+expr_node.getLexem()+" != "+input_child.getType()+"]"; return null;}
    				
    			}
    			k=j;
    			
    		}
    		
    		j = 0; k = 0;
    		
    		for(int i=OutputNode.getChildList().size()-1;i>=0;i--) {
    			
    			output_child = (VisitableNode<String>) OutputNode.getChildList().get(i);
    			    			
    			for( j = j; j<(k+Integer.valueOf(output_child.getLexem())); j++) {
    				
    				expr_node = (VisitableNode) FirstChildNode.getChildList().get(j);
    				
    				if(expr_node.getType() != output_child.getType()) { this.Error="Type Missmatch in the function: "+node.getLexem()+"["+expr_node.getLexem()+" != "+output_child.getType()+"]"; return null;}
    				
    			}
    			k=j;
    			
    		}
    		
    		
    		
    	}
    	
    	
		return "void";
    	
    
    	
    }
    
    private boolean VarOpVisit(VisitableNode node, String Type) {
    	
    	Iterator<VisitableNode> childs = node.subtrees().iterator();
		  VisitableNode IdNode = childs.next();
		  
		  SymbolTable tmp = myStack.peek();
		  if(tmp.addLexem(IdNode.getLexem()) == null) { this.Error = "Errore: "+IdNode.getLexem()+" Dichiarato più volte nello stesso Scope"; return false; }
		  myStack.peek().addAttribute(IdNode.getLexem(), new Attributo("Type",Type));
		  
		  node.setType(Type);
		  IdNode.setType(Type);
		  
		  if( childs.hasNext() && this.VarOpVisit(childs.next(),Type) == false) { return false; }
  	
		  return true;
    }
    
    private String printVarOp(VisitableNode node, String row) {
    	
    	Iterator<VisitableNode> childs = node.subtrees().iterator();
		  VisitableNode IdNode = childs.next();
		  
		  row = node.getType()+" "+IdNode.getLexem()+"; "+row;
		  
		  
		  if( childs.hasNext())
			  return this.printVarOp(childs.next(), row);
		
		  return row;
  	
		  
    }
    
    public String getExpr(VisitableNode node, String expr) {
    	Iterator<VisitableNode> childs = node.subtrees().iterator();
    	if(node.data().equals("ADD_NODE") || node.data().equals("MINUS_NODE") || node.data().equals("MUL_NODE") || node.data().equals("DIV_NODE")||node.data().equals("GT_NODE") || node.data().equals("LT_NODE") || node.data().equals("GE_NODE") || node.data().equals("LE_NODE") || node.data().equals("EQ_NODE") || node.data().equals("OR_NODE") || node.data().equals("AND_NODE")) {
    		expr+="(";
    		expr=getExpr(childs.next(),expr);
    		expr+=node.getLexem();
    		expr=getExpr(childs.next(),expr);
    		expr+=")";
    	} else if(node.data().equals("UMINUS_NODE")||node.data().equals("NOT_NODE")) {
    		expr+=node.getLexem()+"(";
    		expr=getExpr(childs.next(),expr);
    		expr+=")";
    	} else {
    		expr+=node.getLexem();
    	}
    	
    	
    	return expr;
    	
    }
    
    public void saveSource(String row){
        Writer writer = null;
        row = "#include <stdio.h>\n"+row;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("source.c"), "utf-8"));
            writer.write(row);
            writer.close();
            System.out.println("Sorgente scritto con successo. Vedi source.c\n");
        } catch (IOException ex) {
            System.out.println("Errore nella scrittura del file");
        } finally {
            try {writer.close();} catch (Exception ex) {
                System.out.println("Errore durante la chiusura del file");
            }
        }
    }
    
    public void saveFileXML(){
        Writer writer = null;

        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("file.xml"), "utf-8"));
            
            writer.write(this.content.replaceAll("<\n", "MIN\n"));
            writer.close();
        } catch (IOException ex) {
            System.out.println("Errore nella scrittura del file");
        } finally {
            try {writer.close();} catch (Exception ex) {
                System.out.println("Errore durante la chiusura del file");
            }
        }
    }


}
