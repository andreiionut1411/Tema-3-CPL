// Generated from C:/Andrei/CPL/Tema 2/Parser-skel/src/cool/parser/CoolParser.g4 by ANTLR 4.13.1

    package cool.parser;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CoolParser}.
 */
public interface CoolParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CoolParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(CoolParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoolParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(CoolParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by the {@code class_declaration}
	 * labeled alternative in {@link CoolParser#class}.
	 * @param ctx the parse tree
	 */
	void enterClass_declaration(CoolParser.Class_declarationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code class_declaration}
	 * labeled alternative in {@link CoolParser#class}.
	 * @param ctx the parse tree
	 */
	void exitClass_declaration(CoolParser.Class_declarationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code def_formal}
	 * labeled alternative in {@link CoolParser#formal}.
	 * @param ctx the parse tree
	 */
	void enterDef_formal(CoolParser.Def_formalContext ctx);
	/**
	 * Exit a parse tree produced by the {@code def_formal}
	 * labeled alternative in {@link CoolParser#formal}.
	 * @param ctx the parse tree
	 */
	void exitDef_formal(CoolParser.Def_formalContext ctx);
	/**
	 * Enter a parse tree produced by the {@code let_var}
	 * labeled alternative in {@link CoolParser#var_definition}.
	 * @param ctx the parse tree
	 */
	void enterLet_var(CoolParser.Let_varContext ctx);
	/**
	 * Exit a parse tree produced by the {@code let_var}
	 * labeled alternative in {@link CoolParser#var_definition}.
	 * @param ctx the parse tree
	 */
	void exitLet_var(CoolParser.Let_varContext ctx);
	/**
	 * Enter a parse tree produced by the {@code def_func}
	 * labeled alternative in {@link CoolParser#feature}.
	 * @param ctx the parse tree
	 */
	void enterDef_func(CoolParser.Def_funcContext ctx);
	/**
	 * Exit a parse tree produced by the {@code def_func}
	 * labeled alternative in {@link CoolParser#feature}.
	 * @param ctx the parse tree
	 */
	void exitDef_func(CoolParser.Def_funcContext ctx);
	/**
	 * Enter a parse tree produced by the {@code def_var}
	 * labeled alternative in {@link CoolParser#feature}.
	 * @param ctx the parse tree
	 */
	void enterDef_var(CoolParser.Def_varContext ctx);
	/**
	 * Exit a parse tree produced by the {@code def_var}
	 * labeled alternative in {@link CoolParser#feature}.
	 * @param ctx the parse tree
	 */
	void exitDef_var(CoolParser.Def_varContext ctx);
	/**
	 * Enter a parse tree produced by the {@code new}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterNew(CoolParser.NewContext ctx);
	/**
	 * Exit a parse tree produced by the {@code new}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitNew(CoolParser.NewContext ctx);
	/**
	 * Enter a parse tree produced by the {@code par_expr}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterPar_expr(CoolParser.Par_exprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code par_expr}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitPar_expr(CoolParser.Par_exprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code compare}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterCompare(CoolParser.CompareContext ctx);
	/**
	 * Exit a parse tree produced by the {@code compare}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitCompare(CoolParser.CompareContext ctx);
	/**
	 * Enter a parse tree produced by the {@code false}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterFalse(CoolParser.FalseContext ctx);
	/**
	 * Exit a parse tree produced by the {@code false}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitFalse(CoolParser.FalseContext ctx);
	/**
	 * Enter a parse tree produced by the {@code code_block}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterCode_block(CoolParser.Code_blockContext ctx);
	/**
	 * Exit a parse tree produced by the {@code code_block}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitCode_block(CoolParser.Code_blockContext ctx);
	/**
	 * Enter a parse tree produced by the {@code while}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterWhile(CoolParser.WhileContext ctx);
	/**
	 * Exit a parse tree produced by the {@code while}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitWhile(CoolParser.WhileContext ctx);
	/**
	 * Enter a parse tree produced by the {@code add_sub}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAdd_sub(CoolParser.Add_subContext ctx);
	/**
	 * Exit a parse tree produced by the {@code add_sub}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAdd_sub(CoolParser.Add_subContext ctx);
	/**
	 * Enter a parse tree produced by the {@code func_call}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterFunc_call(CoolParser.Func_callContext ctx);
	/**
	 * Exit a parse tree produced by the {@code func_call}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitFunc_call(CoolParser.Func_callContext ctx);
	/**
	 * Enter a parse tree produced by the {@code int}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterInt(CoolParser.IntContext ctx);
	/**
	 * Exit a parse tree produced by the {@code int}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitInt(CoolParser.IntContext ctx);
	/**
	 * Enter a parse tree produced by the {@code str}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterStr(CoolParser.StrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code str}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitStr(CoolParser.StrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code not}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterNot(CoolParser.NotContext ctx);
	/**
	 * Exit a parse tree produced by the {@code not}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitNot(CoolParser.NotContext ctx);
	/**
	 * Enter a parse tree produced by the {@code is_void}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterIs_void(CoolParser.Is_voidContext ctx);
	/**
	 * Exit a parse tree produced by the {@code is_void}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitIs_void(CoolParser.Is_voidContext ctx);
	/**
	 * Enter a parse tree produced by the {@code true}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterTrue(CoolParser.TrueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code true}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitTrue(CoolParser.TrueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code let}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterLet(CoolParser.LetContext ctx);
	/**
	 * Exit a parse tree produced by the {@code let}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitLet(CoolParser.LetContext ctx);
	/**
	 * Enter a parse tree produced by the {@code mul_div}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterMul_div(CoolParser.Mul_divContext ctx);
	/**
	 * Exit a parse tree produced by the {@code mul_div}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitMul_div(CoolParser.Mul_divContext ctx);
	/**
	 * Enter a parse tree produced by the {@code id}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterId(CoolParser.IdContext ctx);
	/**
	 * Exit a parse tree produced by the {@code id}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitId(CoolParser.IdContext ctx);
	/**
	 * Enter a parse tree produced by the {@code if}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterIf(CoolParser.IfContext ctx);
	/**
	 * Exit a parse tree produced by the {@code if}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitIf(CoolParser.IfContext ctx);
	/**
	 * Enter a parse tree produced by the {@code negative_int}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterNegative_int(CoolParser.Negative_intContext ctx);
	/**
	 * Exit a parse tree produced by the {@code negative_int}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitNegative_int(CoolParser.Negative_intContext ctx);
	/**
	 * Enter a parse tree produced by the {@code implicit_func_call}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterImplicit_func_call(CoolParser.Implicit_func_callContext ctx);
	/**
	 * Exit a parse tree produced by the {@code implicit_func_call}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitImplicit_func_call(CoolParser.Implicit_func_callContext ctx);
	/**
	 * Enter a parse tree produced by the {@code case}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterCase(CoolParser.CaseContext ctx);
	/**
	 * Exit a parse tree produced by the {@code case}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitCase(CoolParser.CaseContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assign}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAssign(CoolParser.AssignContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assign}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAssign(CoolParser.AssignContext ctx);
}