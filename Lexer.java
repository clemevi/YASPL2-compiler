/* The following code was generated by JFlex 1.6.1 */

import java_cup.runtime.*;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.6.1
 * from the specification file <tt>C:/Users/Clemente/Desktop/UNISA/Comp/progetto compilatori/specification.flex</tt>
 */
class Lexer implements java_cup.runtime.Scanner {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;
  public static final int STRING = 2;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0,  0,  1, 1
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\0\1\3\1\2\1\0\1\3\1\1\22\0\1\3\1\0\1\45"+
    "\3\0\1\43\1\0\1\35\1\36\1\5\1\47\1\30\1\40\1\11"+
    "\1\4\1\6\11\7\1\32\1\21\1\37\1\46\1\41\2\0\32\10"+
    "\1\0\1\50\4\0\1\14\1\24\1\10\1\15\1\13\1\31\1\10"+
    "\1\12\1\22\2\10\1\26\1\10\1\23\1\25\2\10\1\20\1\16"+
    "\1\17\1\27\1\10\1\42\3\10\1\33\1\44\1\34\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uff92\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\2\0\1\1\2\2\1\3\1\4\2\5\6\6\1\7"+
    "\3\6\1\10\1\6\1\11\1\12\1\13\1\14\1\15"+
    "\1\16\1\17\1\20\1\6\2\1\1\21\1\22\1\23"+
    "\1\24\1\25\1\26\1\2\2\0\3\6\1\27\4\6"+
    "\1\30\4\6\1\31\1\32\1\33\1\34\1\6\1\35"+
    "\1\36\1\37\1\40\1\41\1\42\1\43\2\0\1\44"+
    "\2\6\1\45\4\6\1\46\1\47\2\6\1\50\1\6"+
    "\2\0\1\51\1\52\2\6\1\53\1\54\1\55\3\6"+
    "\1\56\1\57\1\60\1\61";

  private static int [] zzUnpackAction() {
    int [] result = new int[98];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\51\0\122\0\173\0\122\0\244\0\122\0\315"+
    "\0\366\0\u011f\0\u0148\0\u0171\0\u019a\0\u01c3\0\u01ec\0\122"+
    "\0\u0215\0\u023e\0\u0267\0\122\0\u0290\0\122\0\122\0\122"+
    "\0\122\0\122\0\u02b9\0\u02e2\0\u030b\0\u0334\0\u035d\0\u0386"+
    "\0\122\0\u03af\0\122\0\u03d8\0\122\0\u0401\0\u042a\0\u0453"+
    "\0\u047c\0\u04a5\0\u04ce\0\u04f7\0\u0520\0\u0549\0\u0572\0\u059b"+
    "\0\u05c4\0\u011f\0\u05ed\0\u0616\0\u063f\0\u0668\0\122\0\122"+
    "\0\122\0\122\0\u0691\0\122\0\122\0\122\0\122\0\122"+
    "\0\122\0\122\0\u06ba\0\u06e3\0\u070c\0\u0735\0\u075e\0\u011f"+
    "\0\u0787\0\u07b0\0\u07d9\0\u0802\0\u011f\0\u011f\0\u082b\0\u0854"+
    "\0\u011f\0\u087d\0\u08a6\0\u070c\0\u011f\0\u011f\0\u08cf\0\u08f8"+
    "\0\u011f\0\u011f\0\u011f\0\u0921\0\u094a\0\u0973\0\u011f\0\u011f"+
    "\0\u011f\0\u011f";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[98];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\3\1\4\2\5\1\6\1\7\1\10\1\11\1\12"+
    "\1\3\1\13\1\14\1\12\1\15\1\16\1\17\1\12"+
    "\1\20\1\21\1\22\1\23\3\12\1\24\1\25\1\26"+
    "\1\27\1\30\1\31\1\32\1\33\1\34\1\35\1\36"+
    "\1\37\1\40\1\41\1\42\1\43\1\3\1\44\1\4"+
    "\1\5\42\44\1\45\2\44\1\46\53\0\1\5\52\0"+
    "\1\47\1\50\54\0\1\51\45\0\2\11\1\0\1\51"+
    "\45\0\3\12\1\0\7\12\1\0\6\12\1\0\1\12"+
    "\10\0\1\12\14\0\3\12\1\0\1\12\1\52\5\12"+
    "\1\0\6\12\1\0\1\12\10\0\1\12\14\0\3\12"+
    "\1\0\7\12\1\0\4\12\1\53\1\12\1\0\1\12"+
    "\10\0\1\12\14\0\3\12\1\0\1\12\1\54\5\12"+
    "\1\0\3\12\1\55\2\12\1\0\1\12\10\0\1\12"+
    "\14\0\3\12\1\0\5\12\1\56\1\12\1\0\6\12"+
    "\1\0\1\12\10\0\1\12\14\0\3\12\1\0\1\57"+
    "\5\12\1\60\1\0\6\12\1\0\1\12\10\0\1\12"+
    "\14\0\3\12\1\0\7\12\1\0\1\12\1\61\4\12"+
    "\1\0\1\62\10\0\1\12\14\0\3\12\1\0\7\12"+
    "\1\0\3\12\1\63\2\12\1\0\1\12\10\0\1\12"+
    "\14\0\3\12\1\0\7\12\1\0\3\12\1\64\2\12"+
    "\1\0\1\12\10\0\1\12\14\0\3\12\1\0\2\12"+
    "\1\65\4\12\1\0\3\12\1\66\2\12\1\0\1\12"+
    "\10\0\1\12\46\0\1\67\5\0\1\70\43\0\1\71"+
    "\55\0\1\72\10\0\3\12\1\0\1\73\6\12\1\0"+
    "\6\12\1\0\1\12\10\0\1\12\51\0\1\74\51\0"+
    "\1\75\52\0\1\76\2\0\1\44\2\0\42\44\1\0"+
    "\2\44\20\0\1\77\1\100\2\0\1\101\21\0\1\102"+
    "\3\0\1\47\1\4\1\5\46\47\5\103\1\104\43\103"+
    "\6\0\2\105\47\0\3\12\1\0\2\12\1\106\4\12"+
    "\1\0\6\12\1\0\1\12\10\0\1\12\14\0\3\12"+
    "\1\0\4\12\1\107\2\12\1\0\6\12\1\0\1\12"+
    "\10\0\1\12\14\0\3\12\1\0\7\12\1\0\6\12"+
    "\1\0\1\110\10\0\1\12\14\0\3\12\1\0\7\12"+
    "\1\0\5\12\1\111\1\0\1\12\10\0\1\12\14\0"+
    "\3\12\1\0\2\12\1\112\4\12\1\0\6\12\1\0"+
    "\1\12\10\0\1\12\14\0\3\12\1\0\1\12\1\113"+
    "\5\12\1\0\6\12\1\0\1\12\10\0\1\12\14\0"+
    "\3\12\1\0\7\12\1\0\5\12\1\114\1\0\1\12"+
    "\10\0\1\12\14\0\3\12\1\0\5\12\1\115\1\12"+
    "\1\0\6\12\1\0\1\12\10\0\1\12\14\0\3\12"+
    "\1\0\5\12\1\116\1\12\1\0\6\12\1\0\1\12"+
    "\10\0\1\12\14\0\3\12\1\0\7\12\1\0\3\12"+
    "\1\117\2\12\1\0\1\12\10\0\1\12\14\0\3\12"+
    "\1\0\7\12\1\0\4\12\1\120\1\12\1\0\1\12"+
    "\10\0\1\12\14\0\3\12\1\0\6\12\1\121\1\0"+
    "\6\12\1\0\1\12\10\0\1\12\14\0\3\12\1\0"+
    "\7\12\1\0\1\122\5\12\1\0\1\12\10\0\1\12"+
    "\6\0\5\103\1\123\43\103\4\0\1\5\1\104\51\0"+
    "\1\124\1\105\47\0\3\12\1\0\3\12\1\125\3\12"+
    "\1\0\6\12\1\0\1\12\10\0\1\12\14\0\3\12"+
    "\1\0\1\12\1\126\5\12\1\0\6\12\1\0\1\12"+
    "\10\0\1\12\14\0\3\12\1\0\7\12\1\0\2\12"+
    "\1\127\3\12\1\0\1\12\10\0\1\12\14\0\3\12"+
    "\1\0\6\12\1\130\1\0\6\12\1\0\1\12\10\0"+
    "\1\12\14\0\3\12\1\0\7\12\1\0\1\12\1\131"+
    "\4\12\1\0\1\12\10\0\1\12\14\0\3\12\1\0"+
    "\1\12\1\132\5\12\1\0\6\12\1\0\1\12\10\0"+
    "\1\12\14\0\3\12\1\0\7\12\1\0\4\12\1\133"+
    "\1\12\1\0\1\12\10\0\1\12\14\0\3\12\1\0"+
    "\4\12\1\134\2\12\1\0\6\12\1\0\1\12\10\0"+
    "\1\12\14\0\3\12\1\0\7\12\1\0\4\12\1\135"+
    "\1\12\1\0\1\12\10\0\1\12\6\0\4\103\1\5"+
    "\1\123\43\103\6\0\3\12\1\0\7\12\1\0\4\12"+
    "\1\136\1\12\1\0\1\12\10\0\1\12\14\0\3\12"+
    "\1\0\5\12\1\137\1\12\1\0\6\12\1\0\1\12"+
    "\10\0\1\12\14\0\3\12\1\0\1\12\1\140\5\12"+
    "\1\0\6\12\1\0\1\12\10\0\1\12\14\0\3\12"+
    "\1\0\1\12\1\141\5\12\1\0\6\12\1\0\1\12"+
    "\10\0\1\12\14\0\3\12\1\0\1\12\1\142\5\12"+
    "\1\0\6\12\1\0\1\12\10\0\1\12\6\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[2460];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unknown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\2\0\1\11\1\1\1\11\1\1\1\11\10\1\1\11"+
    "\3\1\1\11\1\1\5\11\6\1\1\11\1\1\1\11"+
    "\1\1\1\11\2\1\2\0\15\1\4\11\1\1\7\11"+
    "\2\0\16\1\2\0\16\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[98];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;
  
  /** 
   * The number of occupied positions in zzBuffer beyond zzEndRead.
   * When a lead/high surrogate has been read from the input stream
   * into the final zzBuffer position, this will have a value of 1;
   * otherwise, it will have a value of 0.
   */
  private int zzFinalHighSurrogate = 0;

  /* user code: */
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



  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  Lexer(java.io.Reader in) {
    this.zzReader = in;
  }


  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x110000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 150) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length - zzFinalHighSurrogate) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzBuffer.length*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
    }

    /* fill the buffer with new input */
    int requested = zzBuffer.length - zzEndRead;
    int numRead = zzReader.read(zzBuffer, zzEndRead, requested);

    /* not supposed to occur according to specification of java.io.Reader */
    if (numRead == 0) {
      throw new java.io.IOException("Reader returned 0 characters. See JFlex examples for workaround.");
    }
    if (numRead > 0) {
      zzEndRead += numRead;
      /* If numRead == requested, we might have requested to few chars to
         encode a full Unicode character. We assume that a Reader would
         otherwise never return half characters. */
      if (numRead == requested) {
        if (Character.isHighSurrogate(zzBuffer[zzEndRead - 1])) {
          --zzEndRead;
          zzFinalHighSurrogate = 1;
        }
      }
      /* potentially more input available */
      return false;
    }

    /* numRead < 0 ==> end of stream */
    return true;
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * Internal scan buffer is resized down to its initial length, if it has grown.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEOFDone = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = 0;
    zzFinalHighSurrogate = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
    if (zzBuffer.length > ZZ_BUFFERSIZE)
      zzBuffer = new char[ZZ_BUFFERSIZE];
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() throws java.io.IOException {
    if (!zzEOFDone) {
      zzEOFDone = true;
      yyclose();
    }
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public java_cup.runtime.Symbol next_token() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      boolean zzR = false;
      int zzCh;
      int zzCharCount;
      for (zzCurrentPosL = zzStartRead  ;
           zzCurrentPosL < zzMarkedPosL ;
           zzCurrentPosL += zzCharCount ) {
        zzCh = Character.codePointAt(zzBufferL, zzCurrentPosL, zzMarkedPosL);
        zzCharCount = Character.charCount(zzCh);
        switch (zzCh) {
        case '\u000B':
        case '\u000C':
        case '\u0085':
        case '\u2028':
        case '\u2029':
          yyline++;
          yycolumn = 0;
          zzR = false;
          break;
        case '\r':
          yyline++;
          yycolumn = 0;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
            yycolumn = 0;
          }
          break;
        default:
          zzR = false;
          yycolumn += zzCharCount;
        }
      }

      if (zzR) {
        // peek one character ahead if it is \n (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof) 
            zzPeek = false;
          else 
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = ZZ_LEXSTATE[zzLexicalState];

      // set up zzAction for empty match case:
      int zzAttributes = zzAttrL[zzState];
      if ( (zzAttributes & 1) == 1 ) {
        zzAction = zzState;
      }


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL) {
            zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
            zzCurrentPosL += Character.charCount(zzInput);
          }
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
              zzCurrentPosL += Character.charCount(zzInput);
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
        zzAtEOF = true;
            zzDoEOF();
          { return new java_cup.runtime.Symbol(sym.EOF); }
      }
      else {
        switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
          case 1: 
            { throw new Error("Illegal character <"+
yytext()+"> col: " + yycolumn + " row: " + yyline  );
            }
          case 50: break;
          case 2: 
            { /* ignore */
            }
          case 51: break;
          case 3: 
            { return symbol(sym.DIV);
            }
          case 52: break;
          case 4: 
            { return symbol(sym.TIMES);
            }
          case 53: break;
          case 5: 
            { return symbol(sym.INT_CONST,yytext());
            }
          case 54: break;
          case 6: 
            { return symbol(sym.NAME,yytext());
            }
          case 55: break;
          case 7: 
            { return symbol(sym.SEMI);
            }
          case 56: break;
          case 8: 
            { return symbol(sym.COMMA);
            }
          case 57: break;
          case 9: 
            { return symbol(sym.COLON);
            }
          case 58: break;
          case 10: 
            { return symbol(sym.LGPAR);
            }
          case 59: break;
          case 11: 
            { return symbol(sym.RGPAR);
            }
          case 60: break;
          case 12: 
            { return symbol(sym.LPAR);
            }
          case 61: break;
          case 13: 
            { return symbol(sym.RPAR);
            }
          case 62: break;
          case 14: 
            { return symbol(sym.LT);
            }
          case 63: break;
          case 15: 
            { return symbol(sym.MINUS);
            }
          case 64: break;
          case 16: 
            { return symbol(sym.GT);
            }
          case 65: break;
          case 17: 
            { string.setLength(0); yybegin(STRING);
            }
          case 66: break;
          case 18: 
            { return symbol(sym.ASSIGN);
            }
          case 67: break;
          case 19: 
            { return symbol(sym.PLUS);
            }
          case 68: break;
          case 20: 
            { string.append( yytext() );
            }
          case 69: break;
          case 21: 
            { yybegin(YYINITIAL);
return symbol(sym.STRING_CONST,
string.toString());
            }
          case 70: break;
          case 22: 
            { string.append('\\');
            }
          case 71: break;
          case 23: 
            { return symbol(sym.DO);
            }
          case 72: break;
          case 24: 
            { return symbol(sym.IF);
            }
          case 73: break;
          case 25: 
            { return symbol(sym.READ);
            }
          case 74: break;
          case 26: 
            { return symbol(sym.LE);
            }
          case 75: break;
          case 27: 
            { return symbol(sym.WRITE);
            }
          case 76: break;
          case 28: 
            { return symbol(sym.GE);
            }
          case 77: break;
          case 29: 
            { return symbol(sym.AND);
            }
          case 78: break;
          case 30: 
            { return symbol(sym.OR);
            }
          case 79: break;
          case 31: 
            { return symbol(sym.EQ);
            }
          case 80: break;
          case 32: 
            { string.append('\t');
            }
          case 81: break;
          case 33: 
            { string.append('\r');
            }
          case 82: break;
          case 34: 
            { string.append('\n');
            }
          case 83: break;
          case 35: 
            { string.append('\"');
            }
          case 84: break;
          case 36: 
            { return symbol(sym.DOUBLE_CONST,yytext());
            }
          case 85: break;
          case 37: 
            { return symbol(sym.DEF);
            }
          case 86: break;
          case 38: 
            { return symbol(sym.INT);
            }
          case 87: break;
          case 39: 
            { return symbol(sym.NOT);
            }
          case 88: break;
          case 40: 
            { return symbol(sym.FOR);
            }
          case 89: break;
          case 41: 
            { return symbol(sym.HEAD);
            }
          case 90: break;
          case 42: 
            { return symbol(sym.ELSE);
            }
          case 91: break;
          case 43: 
            { return symbol(sym.THEN);
            }
          case 92: break;
          case 44: 
            { return symbol(sym.TRUE);
            }
          case 93: break;
          case 45: 
            { return symbol(sym.BOOL);
            }
          case 94: break;
          case 46: 
            { return symbol(sym.START);
            }
          case 95: break;
          case 47: 
            { return symbol(sym.FALSE);
            }
          case 96: break;
          case 48: 
            { return symbol(sym.WHILE);
            }
          case 97: break;
          case 49: 
            { return symbol(sym.DOUBLE);
            }
          case 98: break;
          default:
            zzScanError(ZZ_NO_MATCH);
        }
      }
    }
  }


}