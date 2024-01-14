parser grammar CoolParser;

options {
    tokenVocab = CoolLexer;
}

@header{
    package cool.parser;
}


program
    :   (cls+=class SEMICOLON)+ EOF
    ;

class
    :   CLASS type=TYPE (INHERITS inherited=TYPE)? OPEN_BRACKETS (features+=feature SEMICOLON)* CLOSED_BRACKETS   #class_declaration
    ;

formal: name=ID COLON type=TYPE #def_formal
    ;

var_definition: name=ID COLON type=TYPE (ASSIGNMENT assign=expr)?   #let_var
    ;

feature
    :   name=ID OPEN_PARANTHESIS (args+=formal (COMMA args+=formal)*)? CLOSED_PARANTHESIS COLON type=TYPE OPEN_BRACKETS e=expr CLOSED_BRACKETS      #def_func
    |   name=ID COLON type=TYPE (ASSIGNMENT assign=expr)?                                                                                           #def_var
    ;

expr
    :   ID                                                                                                                      #id
    |   INTEGER                                                                                                                 #int
    |   STRING                                                                                                                  #str
    |   TRUE                                                                                                                    #true
    |   FALSE                                                                                                                   #false
    |   e=expr (DISPATCH disp=TYPE)? DOT name=ID OPEN_PARANTHESIS (args+=expr (COMMA args+=expr)*)? CLOSED_PARANTHESIS          #func_call
    |   name=ID OPEN_PARANTHESIS (args+=expr (COMMA args+=expr)*)? CLOSED_PARANTHESIS                                           #implicit_func_call
    |   IF cond=expr THEN if_branch=expr ELSE else_branch=expr FI                                                               #if
    |   WHILE cond=expr LOOP loop_expr=expr POOL                                                                                #while
    |   OPEN_BRACKETS (e+=expr SEMICOLON)+ CLOSED_BRACKETS                                                                      #code_block
    |   LET local+=var_definition (COMMA local+=var_definition)* IN e=expr                                                      #let
    |   CASE e=expr OF (branch_name+=ID COLON branch_type+=TYPE CASE_RESULT res+=expr SEMICOLON)+ ESAC                          #case
    |   NEW type=TYPE                                                                                                           #new
    |   TILDE e=expr                                                                                                            #negative_int
    |   ISVOID e=expr                                                                                                           #is_void
    |   lhs=expr op=(MUL | DIV) rhs=expr                                                                                        #mul_div
    |   lhs=expr op=(PLUS | MINUS) rhs=expr                                                                                     #add_sub
    |   OPEN_PARANTHESIS e=expr CLOSED_PARANTHESIS                                                                              #par_expr
    |   lhs=expr op=(LESS | LE | EQUAL) rhs=expr                                                                                #compare
    |   NOT e=expr                                                                                                              #not
    |   name=ID ASSIGNMENT e=expr                                                                                               #assign
    ;
