grammar JavaScenario;

/*
 * Parser Rules
 */

start
    : (.)*? test EOF
    ;

test
    : TestAnnotation TestDeclaration scenario
    ;

scenario
    :  (.)*? asmDeclaration (.)*? (variableDeclaration | (.))* RCURLY
    ;

asmDeclaration
    : ASMID ID EQ NEW ASMID LPAREN RPAREN SEMI
    ;

variableDeclaration
    : ID (DOT ID)+ ID EQ ID (DOT ID)+ SEMI
    ;

/*
 * Lexer Rules
 */

fragment TEXT
    : (ESC_SEQ | ~["\\])*
    ;

fragment ESC_SEQ
    : '\\' [btnfr"'\\]
    ;

TestAnnotation
    : '@Test' LPAREN (.)*? RPAREN
    ;

TestDeclaration
    : 'public' (.)*? LCURLY
    ;

STRING
    : DOUBLE_QUOTES TEXT DOUBLE_QUOTES
    ;

ASMID
    : [a-zA-Z_][a-zA-Z_0-9]*[_ASM]
    ;

COMMENT
    : '/*' .*? '*/' -> skip
    ;

AND : 'and' ;
OR : 'or' ;
NOT : 'not' ;
EQ : '=' ;
COMMA : ',' ;
COLONS : ':' ;
SEMI : ';' ;
LPAREN : '(' ;
RPAREN : ')' ;
LCURLY : '{' ;
RCURLY : '}' ;
DOT : '.' ;
START : '*' ;
AT : '@' ;
DOUBLE_QUOTES : '"' ;
NEW : 'new' ;

INT : [0-9]+ ;
ID: [a-zA-Z_][a-zA-Z_0-9]* ;
WS: [ \t\n\r\f]+ -> skip ;