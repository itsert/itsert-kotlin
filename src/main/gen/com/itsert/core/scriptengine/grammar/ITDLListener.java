// Generated from /Users/opefago/Documents/projects/itsert/src/main/kotlin/com/itsert/core/scriptengine/grammar/ITDL.g4 by ANTLR 4.8
package com.itsert.core.scriptengine.grammar;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ITDLParser}.
 */
public interface ITDLListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ITDLParser#script}.
	 * @param ctx the parse tree
	 */
	void enterScript(ITDLParser.ScriptContext ctx);
	/**
	 * Exit a parse tree produced by {@link ITDLParser#script}.
	 * @param ctx the parse tree
	 */
	void exitScript(ITDLParser.ScriptContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StmtAssert}
	 * labeled alternative in {@link ITDLParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStmtAssert(ITDLParser.StmtAssertContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StmtAssert}
	 * labeled alternative in {@link ITDLParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStmtAssert(ITDLParser.StmtAssertContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StmtTestDcl}
	 * labeled alternative in {@link ITDLParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStmtTestDcl(ITDLParser.StmtTestDclContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StmtTestDcl}
	 * labeled alternative in {@link ITDLParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStmtTestDcl(ITDLParser.StmtTestDclContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StmtAssign}
	 * labeled alternative in {@link ITDLParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStmtAssign(ITDLParser.StmtAssignContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StmtAssign}
	 * labeled alternative in {@link ITDLParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStmtAssign(ITDLParser.StmtAssignContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StmtSemi}
	 * labeled alternative in {@link ITDLParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStmtSemi(ITDLParser.StmtSemiContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StmtSemi}
	 * labeled alternative in {@link ITDLParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStmtSemi(ITDLParser.StmtSemiContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StmtCondtional}
	 * labeled alternative in {@link ITDLParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStmtCondtional(ITDLParser.StmtCondtionalContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StmtCondtional}
	 * labeled alternative in {@link ITDLParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStmtCondtional(ITDLParser.StmtCondtionalContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StmtBlock}
	 * labeled alternative in {@link ITDLParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStmtBlock(ITDLParser.StmtBlockContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StmtBlock}
	 * labeled alternative in {@link ITDLParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStmtBlock(ITDLParser.StmtBlockContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PrintExpr}
	 * labeled alternative in {@link ITDLParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterPrintExpr(ITDLParser.PrintExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PrintExpr}
	 * labeled alternative in {@link ITDLParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitPrintExpr(ITDLParser.PrintExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StmtFunDcl}
	 * labeled alternative in {@link ITDLParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStmtFunDcl(ITDLParser.StmtFunDclContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StmtFunDcl}
	 * labeled alternative in {@link ITDLParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStmtFunDcl(ITDLParser.StmtFunDclContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StmtFor}
	 * labeled alternative in {@link ITDLParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStmtFor(ITDLParser.StmtForContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StmtFor}
	 * labeled alternative in {@link ITDLParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStmtFor(ITDLParser.StmtForContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StmtWhile}
	 * labeled alternative in {@link ITDLParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStmtWhile(ITDLParser.StmtWhileContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StmtWhile}
	 * labeled alternative in {@link ITDLParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStmtWhile(ITDLParser.StmtWhileContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ReturnSt}
	 * labeled alternative in {@link ITDLParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterReturnSt(ITDLParser.ReturnStContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ReturnSt}
	 * labeled alternative in {@link ITDLParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitReturnSt(ITDLParser.ReturnStContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StmtExpr}
	 * labeled alternative in {@link ITDLParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStmtExpr(ITDLParser.StmtExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StmtExpr}
	 * labeled alternative in {@link ITDLParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStmtExpr(ITDLParser.StmtExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link ITDLParser#exprStatement}.
	 * @param ctx the parse tree
	 */
	void enterExprStatement(ITDLParser.ExprStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ITDLParser#exprStatement}.
	 * @param ctx the parse tree
	 */
	void exitExprStatement(ITDLParser.ExprStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link ITDLParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(ITDLParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link ITDLParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(ITDLParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link ITDLParser#returnStmt}.
	 * @param ctx the parse tree
	 */
	void enterReturnStmt(ITDLParser.ReturnStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link ITDLParser#returnStmt}.
	 * @param ctx the parse tree
	 */
	void exitReturnStmt(ITDLParser.ReturnStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link ITDLParser#dependencesDclrExpr}.
	 * @param ctx the parse tree
	 */
	void enterDependencesDclrExpr(ITDLParser.DependencesDclrExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link ITDLParser#dependencesDclrExpr}.
	 * @param ctx the parse tree
	 */
	void exitDependencesDclrExpr(ITDLParser.DependencesDclrExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link ITDLParser#stringList}.
	 * @param ctx the parse tree
	 */
	void enterStringList(ITDLParser.StringListContext ctx);
	/**
	 * Exit a parse tree produced by {@link ITDLParser#stringList}.
	 * @param ctx the parse tree
	 */
	void exitStringList(ITDLParser.StringListContext ctx);
	/**
	 * Enter a parse tree produced by {@link ITDLParser#varDeclare}.
	 * @param ctx the parse tree
	 */
	void enterVarDeclare(ITDLParser.VarDeclareContext ctx);
	/**
	 * Exit a parse tree produced by {@link ITDLParser#varDeclare}.
	 * @param ctx the parse tree
	 */
	void exitVarDeclare(ITDLParser.VarDeclareContext ctx);
	/**
	 * Enter a parse tree produced by {@link ITDLParser#subDeclare}.
	 * @param ctx the parse tree
	 */
	void enterSubDeclare(ITDLParser.SubDeclareContext ctx);
	/**
	 * Exit a parse tree produced by {@link ITDLParser#subDeclare}.
	 * @param ctx the parse tree
	 */
	void exitSubDeclare(ITDLParser.SubDeclareContext ctx);
	/**
	 * Enter a parse tree produced by {@link ITDLParser#dependencesOptions}.
	 * @param ctx the parse tree
	 */
	void enterDependencesOptions(ITDLParser.DependencesOptionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ITDLParser#dependencesOptions}.
	 * @param ctx the parse tree
	 */
	void exitDependencesOptions(ITDLParser.DependencesOptionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ITDLParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void enterFunctionCall(ITDLParser.FunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link ITDLParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void exitFunctionCall(ITDLParser.FunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link ITDLParser#functionCallParameters}.
	 * @param ctx the parse tree
	 */
	void enterFunctionCallParameters(ITDLParser.FunctionCallParametersContext ctx);
	/**
	 * Exit a parse tree produced by {@link ITDLParser#functionCallParameters}.
	 * @param ctx the parse tree
	 */
	void exitFunctionCallParameters(ITDLParser.FunctionCallParametersContext ctx);
	/**
	 * Enter a parse tree produced by {@link ITDLParser#functionDclrExpr}.
	 * @param ctx the parse tree
	 */
	void enterFunctionDclrExpr(ITDLParser.FunctionDclrExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link ITDLParser#functionDclrExpr}.
	 * @param ctx the parse tree
	 */
	void exitFunctionDclrExpr(ITDLParser.FunctionDclrExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link ITDLParser#formalParamters}.
	 * @param ctx the parse tree
	 */
	void enterFormalParamters(ITDLParser.FormalParamtersContext ctx);
	/**
	 * Exit a parse tree produced by {@link ITDLParser#formalParamters}.
	 * @param ctx the parse tree
	 */
	void exitFormalParamters(ITDLParser.FormalParamtersContext ctx);
	/**
	 * Enter a parse tree produced by {@link ITDLParser#testDclrExpr}.
	 * @param ctx the parse tree
	 */
	void enterTestDclrExpr(ITDLParser.TestDclrExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link ITDLParser#testDclrExpr}.
	 * @param ctx the parse tree
	 */
	void exitTestDclrExpr(ITDLParser.TestDclrExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link ITDLParser#setupStmt}.
	 * @param ctx the parse tree
	 */
	void enterSetupStmt(ITDLParser.SetupStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link ITDLParser#setupStmt}.
	 * @param ctx the parse tree
	 */
	void exitSetupStmt(ITDLParser.SetupStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link ITDLParser#tearDownStmt}.
	 * @param ctx the parse tree
	 */
	void enterTearDownStmt(ITDLParser.TearDownStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link ITDLParser#tearDownStmt}.
	 * @param ctx the parse tree
	 */
	void exitTearDownStmt(ITDLParser.TearDownStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link ITDLParser#assertionExpr}.
	 * @param ctx the parse tree
	 */
	void enterAssertionExpr(ITDLParser.AssertionExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link ITDLParser#assertionExpr}.
	 * @param ctx the parse tree
	 */
	void exitAssertionExpr(ITDLParser.AssertionExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link ITDLParser#conditionalExpr}.
	 * @param ctx the parse tree
	 */
	void enterConditionalExpr(ITDLParser.ConditionalExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link ITDLParser#conditionalExpr}.
	 * @param ctx the parse tree
	 */
	void exitConditionalExpr(ITDLParser.ConditionalExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link ITDLParser#whileStatement}.
	 * @param ctx the parse tree
	 */
	void enterWhileStatement(ITDLParser.WhileStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ITDLParser#whileStatement}.
	 * @param ctx the parse tree
	 */
	void exitWhileStatement(ITDLParser.WhileStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link ITDLParser#forStatement}.
	 * @param ctx the parse tree
	 */
	void enterForStatement(ITDLParser.ForStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ITDLParser#forStatement}.
	 * @param ctx the parse tree
	 */
	void exitForStatement(ITDLParser.ForStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link ITDLParser#elseCondition}.
	 * @param ctx the parse tree
	 */
	void enterElseCondition(ITDLParser.ElseConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ITDLParser#elseCondition}.
	 * @param ctx the parse tree
	 */
	void exitElseCondition(ITDLParser.ElseConditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ITDLParser#thenStatement}.
	 * @param ctx the parse tree
	 */
	void enterThenStatement(ITDLParser.ThenStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ITDLParser#thenStatement}.
	 * @param ctx the parse tree
	 */
	void exitThenStatement(ITDLParser.ThenStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link ITDLParser#elseStatement}.
	 * @param ctx the parse tree
	 */
	void enterElseStatement(ITDLParser.ElseStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ITDLParser#elseStatement}.
	 * @param ctx the parse tree
	 */
	void exitElseStatement(ITDLParser.ElseStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprListDcl}
	 * labeled alternative in {@link ITDLParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprListDcl(ITDLParser.ExprListDclContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprListDcl}
	 * labeled alternative in {@link ITDLParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprListDcl(ITDLParser.ExprListDclContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprParen}
	 * labeled alternative in {@link ITDLParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprParen(ITDLParser.ExprParenContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprParen}
	 * labeled alternative in {@link ITDLParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprParen(ITDLParser.ExprParenContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprNum}
	 * labeled alternative in {@link ITDLParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprNum(ITDLParser.ExprNumContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprNum}
	 * labeled alternative in {@link ITDLParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprNum(ITDLParser.ExprNumContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprListIndex}
	 * labeled alternative in {@link ITDLParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprListIndex(ITDLParser.ExprListIndexContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprListIndex}
	 * labeled alternative in {@link ITDLParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprListIndex(ITDLParser.ExprListIndexContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprInt}
	 * labeled alternative in {@link ITDLParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprInt(ITDLParser.ExprIntContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprInt}
	 * labeled alternative in {@link ITDLParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprInt(ITDLParser.ExprIntContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprNotEq}
	 * labeled alternative in {@link ITDLParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprNotEq(ITDLParser.ExprNotEqContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprNotEq}
	 * labeled alternative in {@link ITDLParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprNotEq(ITDLParser.ExprNotEqContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprRef}
	 * labeled alternative in {@link ITDLParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprRef(ITDLParser.ExprRefContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprRef}
	 * labeled alternative in {@link ITDLParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprRef(ITDLParser.ExprRefContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprReAssign}
	 * labeled alternative in {@link ITDLParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprReAssign(ITDLParser.ExprReAssignContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprReAssign}
	 * labeled alternative in {@link ITDLParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprReAssign(ITDLParser.ExprReAssignContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprNot}
	 * labeled alternative in {@link ITDLParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprNot(ITDLParser.ExprNotContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprNot}
	 * labeled alternative in {@link ITDLParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprNot(ITDLParser.ExprNotContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprGt}
	 * labeled alternative in {@link ITDLParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprGt(ITDLParser.ExprGtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprGt}
	 * labeled alternative in {@link ITDLParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprGt(ITDLParser.ExprGtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprStr}
	 * labeled alternative in {@link ITDLParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprStr(ITDLParser.ExprStrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprStr}
	 * labeled alternative in {@link ITDLParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprStr(ITDLParser.ExprStrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprEq}
	 * labeled alternative in {@link ITDLParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprEq(ITDLParser.ExprEqContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprEq}
	 * labeled alternative in {@link ITDLParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprEq(ITDLParser.ExprEqContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprAddSub}
	 * labeled alternative in {@link ITDLParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprAddSub(ITDLParser.ExprAddSubContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprAddSub}
	 * labeled alternative in {@link ITDLParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprAddSub(ITDLParser.ExprAddSubContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprAnd}
	 * labeled alternative in {@link ITDLParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprAnd(ITDLParser.ExprAndContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprAnd}
	 * labeled alternative in {@link ITDLParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprAnd(ITDLParser.ExprAndContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprGte}
	 * labeled alternative in {@link ITDLParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprGte(ITDLParser.ExprGteContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprGte}
	 * labeled alternative in {@link ITDLParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprGte(ITDLParser.ExprGteContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprLt}
	 * labeled alternative in {@link ITDLParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprLt(ITDLParser.ExprLtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprLt}
	 * labeled alternative in {@link ITDLParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprLt(ITDLParser.ExprLtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprOr}
	 * labeled alternative in {@link ITDLParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprOr(ITDLParser.ExprOrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprOr}
	 * labeled alternative in {@link ITDLParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprOr(ITDLParser.ExprOrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprFunCall}
	 * labeled alternative in {@link ITDLParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprFunCall(ITDLParser.ExprFunCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprFunCall}
	 * labeled alternative in {@link ITDLParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprFunCall(ITDLParser.ExprFunCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprRange}
	 * labeled alternative in {@link ITDLParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprRange(ITDLParser.ExprRangeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprRange}
	 * labeled alternative in {@link ITDLParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprRange(ITDLParser.ExprRangeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExpBool}
	 * labeled alternative in {@link ITDLParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpBool(ITDLParser.ExpBoolContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExpBool}
	 * labeled alternative in {@link ITDLParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpBool(ITDLParser.ExpBoolContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprMultDiv}
	 * labeled alternative in {@link ITDLParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprMultDiv(ITDLParser.ExprMultDivContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprMultDiv}
	 * labeled alternative in {@link ITDLParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprMultDiv(ITDLParser.ExprMultDivContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprLte}
	 * labeled alternative in {@link ITDLParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprLte(ITDLParser.ExprLteContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprLte}
	 * labeled alternative in {@link ITDLParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprLte(ITDLParser.ExprLteContext ctx);
	/**
	 * Enter a parse tree produced by {@link ITDLParser#rangeExpr}.
	 * @param ctx the parse tree
	 */
	void enterRangeExpr(ITDLParser.RangeExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link ITDLParser#rangeExpr}.
	 * @param ctx the parse tree
	 */
	void exitRangeExpr(ITDLParser.RangeExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link ITDLParser#list}.
	 * @param ctx the parse tree
	 */
	void enterList(ITDLParser.ListContext ctx);
	/**
	 * Exit a parse tree produced by {@link ITDLParser#list}.
	 * @param ctx the parse tree
	 */
	void exitList(ITDLParser.ListContext ctx);
	/**
	 * Enter a parse tree produced by {@link ITDLParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(ITDLParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link ITDLParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(ITDLParser.ValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link ITDLParser#bool}.
	 * @param ctx the parse tree
	 */
	void enterBool(ITDLParser.BoolContext ctx);
	/**
	 * Exit a parse tree produced by {@link ITDLParser#bool}.
	 * @param ctx the parse tree
	 */
	void exitBool(ITDLParser.BoolContext ctx);
}