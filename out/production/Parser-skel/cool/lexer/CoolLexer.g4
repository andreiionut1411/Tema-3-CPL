lexer grammar CoolLexer;

tokens { ERROR } 

@header{
    package cool.lexer;	
}

@members{    
    private void raiseError(String msg) {
        setText(msg);
        setType(ERROR);
    }
}

// Keywords
CLASS: 'class';
INHERITS: 'inherits';
TRUE: 'true';
FALSE: 'false';
NOT: 'not';
ISVOID: 'isvoid';
NEW: 'new';
IF: 'if';
THEN: 'then';
ELSE: 'else';
FI: 'fi';
WHILE: 'while';
LOOP: 'loop';
POOL: 'pool';
LET: 'let';
IN: 'in';
CASE: 'case';
ESAC: 'esac';
OF: 'of';

// Identifiers and Intgers
fragment DIGIT: [0-9];
fragment NON_ZERO_DIGIT: [1-9];
fragment LOWERCASE_LETTER: [a-z];
fragment UPPERCASE_LETTER: [A-Z];
fragment LETTER: LOWERCASE_LETTER | UPPERCASE_LETTER;
TYPE: UPPERCASE_LETTER (LOWERCASE_LETTER | UPPERCASE_LETTER | DIGIT | '_')*;
ID: LOWERCASE_LETTER (LOWERCASE_LETTER | UPPERCASE_LETTER | DIGIT | '_')*;
INTEGER: DIGIT | NON_ZERO_DIGIT DIGIT+;

fragment END_LINE: '\r\n' | '\n';

UNTERMINATED_STRING: '"' ('\\"' | '\\\r\n' | '\\\n' | ~["\r\n])*? END_LINE
{ raiseError("Unterminated string constant"); };

EOF_IN_STRING: '"' ('\\"' | ~["\n]) *? EOF
{ raiseError("EOF in string constant"); };

STRING: '"' ('\\"' | '\\' END_LINE | ~["\r\n]) *? '"'
{
    String originalString = getText();
    StringBuilder newString = new StringBuilder();
    boolean isEscaped = false;
    boolean containsNull = false;

    for (int i = 1; i < originalString.length() - 1; i++) {

        // If the String contains the null character, then it is an error.
        if (originalString.charAt(i) == '\u0000') {
            containsNull = true;
            raiseError("String contains null character");
            break;
        }

        if (!isEscaped) {
            if (originalString.charAt(i) == '\\')
                isEscaped = true;
            else
                newString.append(originalString.charAt(i));
        } else {
            if (originalString.charAt(i) == 'n')
                newString.append('\n');
            else if (originalString.charAt(i) == 'b')
                newString.append('\b');
            else if (originalString.charAt(i) == 't')
                newString.append('\t');
            else if (originalString.charAt(i) == 'f')
                newString.append('\f');
            else
                newString.append(originalString.charAt(i));
        }
    }

    // If the String is too long, then we return an error.
    if (!containsNull) {
        if (newString.length() > 1024) {
            raiseError("String constant too long");
        } else {
            setText(newString.toString());
        }
    }
};

// Removed quotation marks from the string

// OPERATORS
ASSIGNMENT: '<-';
CASE_RESULT: '=>';
DISPATCH: '@';

// Arithmetic operators
PLUS: '+';
MINUS: '-';
MUL: '*';
DIV: '/';
TILDE: '~';

// Comparison operators
LESS: '<';
LE: '<=';
EQUAL: '=';

// Delimiters
WS
    :   [ \n\f\r\t]+ -> skip
    ;

DOT: '.';
COMMA: ',';
SEMICOLON: ';';
COLON: ':';
OPEN_BRACKETS: '{';
CLOSED_BRACKETS: '}';
OPEN_PARANTHESIS: '(';
CLOSED_PARANTHESIS: ')';

// Comments
SINGLE_LINE_COMMENT: '--' .*? ('\n' | EOF) -> skip;
fragment NON_COMMENT: '(' ~'*' | ~'(' '*' | '*' ~')' | ~'*' ')' | ~[*()];
fragment COMMENT: '(*' NON_COMMENT*? '*)';
BLOCK_COMMENT: (COMMENT | '(*' NON_COMMENT*? BLOCK_COMMENT (NON_COMMENT*? BLOCK_COMMENT)* NON_COMMENT*? '*)') -> skip;

// Verify that there is no extra closing comment
CLOSED_COMMENT: '*)'
{ raiseError("Unmatched *)"); };

// Verify there is no unterminated comment.
UNTERMINATED_COMMENT: '(*' (NON_COMMENT*? BLOCK_COMMENT)* NON_COMMENT*? EOF
{ raiseError("EOF in comment"); };

// If the lexer reaches here, then it means there is a lexical mistake
UNKNOWN_CHARACTER: .
{
    String unk = getText();
    raiseError("Invalid character: " + unk);
};