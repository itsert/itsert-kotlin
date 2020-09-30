grammar ITDL;
//rules
script: (dependencesDclrExpr ';'?)+ setupStmt? (statement)* tearDownStmt? EOF;

statement
    :   assertionExpr SEMI   #StmtAssert
    |   testDclrExpr    #StmtTestDcl
    |   varDeclare   #StmtAssign
    |   SEMI    #StmtSemi
    |   conditionalExpr #StmtCondtional
    |   block   #StmtBlock
    |   PRINT expr SEMI #PrintExpr
    |   functionDclrExpr    #StmtFunDcl
    |   forStatement   #StmtFor
    |   whileStatement #StmtWhile
    |   returnStmt #ReturnSt
    |   exprStatement #StmtExpr
    ;

exprStatement: expr SEMI;
block   :   L_CURLY statement* R_CURLY;
returnStmt: RETURN expr?;
dependencesDclrExpr
    :   DEPENDS ON dependencesOptions  stringList?;

stringList :  Id (',' Id)*;

varDeclare: VAR subDeclare SEMI;
subDeclare: Id (ASSIGN expr)?;

dependencesOptions
    :   (SERVICES);

functionCall: Id L_PAREN functionCallParameters? R_PAREN;

functionCallParameters: ((Id | expr) (COMMA (Id | expr))*);

functionDclrExpr
    :   FUNCTION Id L_PAREN formalParamters? R_PAREN block
    ;
formalParamters: (Id (COMMA Id)*);

testDclrExpr
    :   TEST (Id | STRING) block
    |   SCENERIO (Id | STRING) block
    ;

setupStmt
    : SETUP block;
tearDownStmt
    : TEARDOWN block;


assertionExpr
    : ASSERT expr (':' STRING)?
    ;
conditionalExpr
    : IF L_PAREN expr R_PAREN thenStatement (elseCondition)*
    ;

whileStatement: WHILE L_PAREN expr R_PAREN thenStatement;
forStatement   : FOR L_PAREN Id IN (rangeExpr | list| Id | STRING) R_PAREN thenStatement ;

elseCondition: ELSE (IF  L_PAREN expr R_PAREN)? elseStatement;
thenStatement:  ( block | statement);
elseStatement: ( block | statement);
expr
    :   L_PAREN expr R_PAREN #ExprParen
    |   Id  #ExprRef
    |   INT #ExprInt
    |   NUMBER  #ExprNum
    |   STRING  #ExprStr
    |   rangeExpr  #ExprRange
    |   Id L_SQUARE INT R_SQUARE #ExprListIndex
    |   subDeclare #ExprReAssign
    |   expr ('*'|'/') expr #ExprMultDiv
    |   expr ('+'|'-') expr #ExprAddSub
    |   list    #ExprListDcl
    |   bool #ExpBool
    |   expr ('>') expr #ExprGt
    |   expr ('>=') expr    #ExprGte
    |   expr ('<') expr    #ExprLt
    |   expr ('<=') expr  #ExprLte
    |   expr ('equals') expr   #ExprEq
    |   expr ('!equals') expr  #ExprNotEq
    |   expr 'and' expr #ExprAnd
    |   expr 'or' expr  #ExprOr
    |   ('not' | '!') expr  #ExprNot
    |   functionCall    #ExprFunCall
    ;
rangeExpr: INT ELLIPSIS INT;
list    : L_SQUARE (value (COMMA value)*)? R_SQUARE
    ;
value: (INT | NUMBER| BOOL| STRING| Id);
bool
    :    (TRUE | FALSE);

//tokens
INT     : [0-9]+;
NUMBER
    : INT DOT INT
    | DOT INT
    | INT
    ;

//TODO figure out why is is not working
IS: 'is';
IF: I F;
IN: I N;
TO: T O;
AS: A S;
AT: A T;
FOR: F O R;
VAR: V A R;
GIVEN: (G I V E N);
PRINT: P R I N T;
DEPENDS: D E P E N D S;
GREATER: G R E A T E R;
THAN: T H A N;
OR: O R;
EQUALS: E Q U A L S;
ELSE: E L S E;
ON: O N;
AND: A N D;
ASSERT: A S S E R T;
FUNCTION: F U N;
WHILE: W H I L E;
TEST: T E S T;
CASE: C A S E;
SCENERIO: S C E N E R I O;
RETURNED: R E T U R N E D;
SERVICES: S E R V I C E S;
CONSTANT: C O N S T ;
END     : E N D;
TRUE    :   T R U E;
FALSE   :  F A L S E;
SETUP   :   S E T U P;
TEARDOWN: T E A R D O W N;
RETURN: R E T U R N;
BOOL: (TRUE | FALSE);

ALPHA   :  [a-zA-Z];
STRING: '"' (Esc|.)*? '"';
DOT  : '.';
L_PAREN :   '(';
R_PAREN :   ')';
L_CURLY :   '{';
R_CURLY :   '}';
L_SQUARE    :   '[';
R_SQUARE    :   ']';
ASSIGN  :   '=';
COMMA   :  ',';
ELLIPSIS:   '..';
SEMI    :   ';';
ADD : '+';
SUB:    '-';
DIV:    '/';
MULT:   '*';

NEWLINE :   ('\r'? '\n') -> channel(HIDDEN);
WS      :   [ \t\r\n]    -> channel(HIDDEN);
LINE_COMMENT    : '//'  ~[\r\n]* -> channel(HIDDEN);
BLOCK_COMMENT   : '/*'  (.)*? '*/' -> channel(HIDDEN);
fragment Id  : ('-' | '_'| ALPHA)+;
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

fragment A : [aA];
fragment B : [bB];
fragment C : [cC];
fragment D : [dD];
fragment E : [eE];
fragment F : [fF];
fragment G : [gG];
fragment H : [hH];
fragment I : [iI];
fragment J : [jJ];
fragment K : [kK];
fragment L : [lL];
fragment M : [mM];
fragment N : [nN];
fragment O : [oO];
fragment P : [pP];
fragment Q : [qQ];
fragment R : [rR];
fragment S : [sS];
fragment T : [tT];
fragment U : [uU];
fragment V : [vV];
fragment W : [wW];
fragment X : [xX];
fragment Y : [yY];
fragment Z : [zZ];