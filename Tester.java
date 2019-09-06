
import java_cup.runtime.Symbol;
import java.io.FileReader;
import java.util.ArrayList;
import Visitor.VisitableNode;




public class Tester {

    public static void main(String[] args) throws Exception {
    	 String[] terminalNames = new String[] {
    			  "EOF",
    			  "error",
    			  "HEAD",
    			  "START",
    			  "SEMI",
    			  "COMMA",
    			  "DEF",
    			  "NAME",
    			  "LPAR",
    			  "RPAR",
    			  "COLON",
    			  "LGPAR",
    			  "RGPAR",
    			  "INT_CONST",
    			  "DOUBLE_CONST",
    			  "STRING_CONST",
    			  "PLUS",
    			  "MINUS",
    			  "TIMES",
    			  "DIV",
    			  "INT",
    			  "BOOL",
    			  "DOUBLE",
    			  "READ",
    			  "WRITE",
    			  "TRUE",
    			  "FALSE",
    			  "ASSIGN",
    			  "IF",
    			  "THEN",
    			  "WHILE",
    			  "DO",
    			  "ELSE",
    			  "GT",
    			  "GE",
    			  "LT",
    			  "LE",
    			  "EQ",
    			  "NOT",
    			  "AND",
    			  "OR",
    			  "UMINUS"
    			  };
    	 if(args.length == 1) {
    		 Lexer l = new Lexer(new FileReader(args[0]));

    		 java_cup.runtime.Symbol token = null;
    		 do {
    			 token = l.next_token();

    		 }while ( token.sym != 0);

    		 System.out.println( "Lexer: FINE");	



    		 YASPL2Cup p = new YASPL2Cup(new Lexer(new FileReader(args[0])),"");

    		 try{

    			 p.parse();
    			 
    		 } catch(Exception e){
    			 System.exit(-1);    		 }
    		 
    	 } else { System.out.println("Usage: java -jar YASPLcompiler.jar SourceName");}
    }
}
