import java_cup.runtime.*;

%%

%class Lexer
%unicode 
%cup
%line
%column

%{
public YASPL2Sym sym;
//private SymbolTable table = new SymbolsTable();

StringBuffer string = new StringBuffer();
private Symbol symbol(int type) {
		return new Symbol(type, yyline, yycolumn);
	}

private Symbol symbol(int type, Object value) {
		 //java.util.Map.Entry entry = table.addLexem(value.toString());
    	//table.addAttribute(value.toString(), new Attributo("class", type + ""));
		
		return new Symbol(type, yyline, yycolumn, value);
	}

%}

LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]
WhiteSpace = {LineTerminator} | [ \t\f]


TraditionalComment = "/*" [^*] ~"*/" | "/*" "*"+ "/"
EndOfLineComment = "//" {InputCharacter}* {LineTerminator}?
Comment = {TraditionalComment} | {EndOfLineComment} 

Digit = [0-9]
Digits = {Digit}*
NoZeroDigit = [1-9]
Letter = [a-zA-Z]
Identifier = {Letter}({Letter}|{Digit})*

DecIntegerLiteral = 0 | {NoZeroDigit}{Digits}
DecFloatingPointLiteral = (0 | {NoZeroDigit}{Digits} ) \.( ({Digits}{NoZeroDigit}) | 0 )

%state STRING
%%
/* keywords */
<YYINITIAL> "head" { return symbol(sym.HEAD); }
<YYINITIAL> "start" { return symbol(sym.START); }
<YYINITIAL> ";" { return symbol(sym.SEMI); }
<YYINITIAL> "int" { return symbol(sym.INT); }
<YYINITIAL> "bool" { return symbol(sym.BOOL); }
<YYINITIAL> "double" { return symbol(sym.DOUBLE); }
<YYINITIAL> "," { return symbol(sym.COMMA); }
<YYINITIAL> "def" { return symbol(sym.DEF); }
<YYINITIAL> ":" { return symbol(sym.COLON); }
<YYINITIAL> "{" { return symbol(sym.LGPAR); }
<YYINITIAL> "}" { return symbol(sym.RGPAR); }
<YYINITIAL> "(" { return symbol(sym.LPAR); }
<YYINITIAL> ")" { return symbol(sym.RPAR); }
<YYINITIAL> "<-" { return symbol(sym.READ); }
<YYINITIAL> "->" { return symbol(sym.WRITE); }
<YYINITIAL> "true" { return symbol(sym.TRUE); }
<YYINITIAL> "false" { return symbol(sym.FALSE); }

<YYINITIAL> "if" { return symbol(sym.IF); }
<YYINITIAL> "then" { return symbol(sym.THEN); }
<YYINITIAL> "while" { return symbol(sym.WHILE); }
<YYINITIAL> "for" { return symbol(sym.FOR); }
<YYINITIAL> "do" { return symbol(sym.DO); }
<YYINITIAL> "else" { return symbol(sym.ELSE); }
<YYINITIAL> "not" { return symbol(sym.NOT); }
<YYINITIAL> "&&" { return symbol(sym.AND); }
<YYINITIAL> "||" { return symbol(sym.OR); }



<YYINITIAL> {
/* identifiers */
{Identifier} { return symbol(sym.NAME,yytext()); }
/* literals */
{DecIntegerLiteral} { return symbol(sym.INT_CONST,yytext()); }
{DecFloatingPointLiteral} { return symbol(sym.DOUBLE_CONST,yytext()); }
\" { string.setLength(0); yybegin(STRING); }

/* comments */
{Comment} { /* ignore */ }



/* operators */
">=" { return symbol(sym.GE); }
"<=" { return symbol(sym.LE); }
"==" { return symbol(sym.EQ); }
"<" { return symbol(sym.LT); }
">" { return symbol(sym.GT); }
"=" { return symbol(sym.ASSIGN); }
"+" { return symbol(sym.PLUS); }
"-" { return symbol(sym.MINUS); }
"*" { return symbol(sym.TIMES); }
"/" { return symbol(sym.DIV); }
"-" { return symbol(sym.UMINUS); }


}//YYINITIAL

<STRING> {
\" { yybegin(YYINITIAL);
return symbol(sym.STRING_CONST,
string.toString()); }
[^\n\r\"\\]+ { string.append( yytext() ); }
\\t { string.append('\t'); }
\\n { string.append('\n'); }
\\r { string.append('\r'); }
\\\" { string.append('\"'); }
\\ { string.append('\\'); }
}

/* whitespace */
{WhiteSpace} { /* ignore */ }

/*%EOFVALUE { return null;}*/

/* error fallback */
[^] { throw new Error("Illegal character <"+
yytext()+"> col: " + yycolumn + " row: " + yyline  ); }