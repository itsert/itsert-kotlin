grammar IntegrationScript;
//rules
script: (dependencesDclrExpr ';'?)+ setupStmt? (statement)* tearDownStmt? EOF;
statement
    :   L_PAREN expr R_PAREN SEMI
    |   assertionExpr SEMI
    |   list    SEMI
    |   ID L_PAREN paramList? R_PAREN SEMI
    |   'var' ID ('=' expr)?    SEMI
    |   'const' ID '=' expr     SEMI
    |   ID L_SQUARE (INT | ID) R_SQUARE SEMI
    |   SEMI
    |   testDclrExpr
    |   functionDclrExpr
    |   conditionalExpr
    ;
dependencesDclrExpr
    :   'depends' 'on' dependencesOptions L_PAREN stringList? R_PAREN;

stringList :  STRING (',' STRING)*;

dependencesOptions
    :   ('services');

testAssign: 'given' 'that'? ID 'is' expr (COMMA ID 'is' expr)* SEMI;
testFunctionCall: 'from' ID ('with' ID 'as' expr (COMMA ID 'as' expr) *)? SEMI;
testFunctionDeclr: 'define' ID 'as function'? ('with' ID (COMMA ID))? 'where' block 'end' 'define'?;

testDclrExpr
    :   'test' (ID | STRING) block
    |   'scenerio' (ID | STRING) block
    ;
setupStmt
    : 'setUp' block;
tearDownStmt
    : 'tearDown' block;

functionDclrExpr
    :   'function' ID L_PAREN paramList? R_PAREN block
    ;
assertionExpr
    : 'assert' expr (':' STRING)?
    ;
conditionalExpr
    : 'if' expr block ('else' ( block | conditionalExpr))*
    ;
expr
    :   L_PAREN expr R_PAREN
    |   expr ('+'|'-') expr
    |   expr ('*'|'/') expr
    |   ID
    |   INT
    |   NUMBER
    |   STRING
    |   boolean
    |   expr ('gt' | '>') expr
    |   expr ('gte'| '>=') expr
    |   expr ('lt' | '<') expr
    |   expr ('lte' | '<=') expr
    |   expr ('eq' | '==') expr
    |   expr ('!eq' | '!=') expr
    |   expr 'and' expr
    |   expr 'or' expr
    |   ('not' | '!') expr
    ;
block   :   L_CURLY statement* R_CURLY;
paramList : (ID | INT | STRING) (COMMA (ID | INT | STRING))*;
list
    : L_SQUARE R_SQUARE
    | L_SQUARE value (COMMA value)* R_SQUARE
    ;
value: (INT | NUMBER| BOOLEAN| STRING| ID);
boolean
    :    (TRUE | FALSE);

//tokens
INT     : [0-9]+;
NUMBER
    : INT DOT INT
    | DOT INT
    | INT
    ;
ID      : [a-zA-Z]+;
STRING: '"' (Esc|.)*? '"';

fragment Esc
    : '\\' [btnfr"'\\]
    | '\\' ([0-3]? [0-7])? [0-7]
    | '\\' 'u'+ HexDigit HexDigit HexDigit HexDigit
    ;

fragment HexDigits
    : HexDigit ((HexDigit | '_')* HexDigit)?
    ;
fragment HexDigit
    : [0-9a-fA-F]
    ;

DOT  : '.';
L_PAREN :   '(';
R_PAREN :   ')';
L_CURLY :   '{';
R_CURLY :   '}';
L_SQUARE    :   '[';
R_SQUARE    :   ']';
ASSIGN  :   '=';
COMMA   :  ',';
ELLIPSIS:   '...';
SEMI    :   ';';
TRUE    :   'true';
FALSE   :  'false';
NEWLINE :   ('\r'? '\n') -> channel(HIDDEN);
WS      :   [ \t\r\n]    -> channel(HIDDEN);
LINE_COMMENT    : '//'  ~[\r\n]* -> channel(HIDDEN);
BLOCK_COMMENT   : '/*'  (.)*? '*/' -> channel(HIDDEN);
