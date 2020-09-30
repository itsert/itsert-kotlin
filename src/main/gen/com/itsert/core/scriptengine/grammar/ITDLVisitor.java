// Generated from /Users/opefago/Documents/projects/itsert/src/main/kotlin/com/itsert/core/scriptengine/grammar/ITDL.g4 by ANTLR 4.8
package com.itsert.core.scriptengine.grammar;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ITDLParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ITDLVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ITDLParser#script}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitScript(ITDLParser.ScriptContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StmtAssert}
	 * labeled alternative in {@link ITDLParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtAssert(ITDLParser.StmtAssertContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StmtTestDcl}
	 * labeled alternative in {@link ITDLParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtTestDcl(ITDLParser.StmtTestDclContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StmtAssign}
	 * labeled alternative in {@link ITDLParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtAssign(ITDLParser.StmtAssignContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StmtSemi}
	 * labeled alternative in {@link ITDLParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtSemi(ITDLParser.StmtSemiContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StmtCondtional}
	 * labeled alternative in {@link ITDLParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtCondtional(ITDLParser.StmtCondtionalContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StmtBlock}
	 * labeled alternative in {@link ITDLParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtBlock(ITDLParser.StmtBlockContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PrintExpr}
	 * labeled alternative in {@link ITDLParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintExpr(ITDLParser.PrintExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StmtFunDcl}
	 * labeled alternative in {@link ITDLParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtFunDcl(ITDLParser.StmtFunDclContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StmtFor}
	 * labeled alternative in {@link ITDLParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtFor(ITDLParser.StmtForContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StmtWhile}
	 * labeled alternative in {@link ITDLParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtWhile(ITDLParser.StmtWhileContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ReturnSt}
	 * labeled alternative in {@link ITDLParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnSt(ITDLParser.ReturnStContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StmtExpr}
	 * labeled alternative in {@link ITDLParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtExpr(ITDLParser.StmtExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link ITDLParser#exprStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprStatement(ITDLParser.ExprStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link ITDLParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(ITDLParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link ITDLParser#returnStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnStmt(ITDLParser.ReturnStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link ITDLParser#dependencesDclrExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDependencesDclrExpr(ITDLParser.DependencesDclrExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link ITDLParser#stringList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringList(ITDLParser.StringListContext ctx);
	/**
	 * Visit a parse tree produced by {@link ITDLParser#varDeclare}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDeclare(ITDLParser.VarDeclareContext ctx);
	/**
	 * Visit a parse tree produced by {@link ITDLParser#subDeclare}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubDeclare(ITDLParser.SubDeclareContext ctx);
	/**
	 * Visit a parse tree produced by {@link ITDLParser#dependencesOptions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDependencesOptions(ITDLParser.DependencesOptionsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ITDLParser#functionCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionCall(ITDLParser.FunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link ITDLParser#functionCallParameters}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionCallParameters(ITDLParser.FunctionCallParametersContext ctx);
	/**
	 * Visit a parse tree produced by {@link ITDLParser#functionDclrExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionDclrExpr(ITDLParser.FunctionDclrExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link ITDLParser#formalParamters}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormalParamters(ITDLParser.FormalParamtersContext ctx);
	/**
	 * Visit a parse tree produced by {@link ITDLParser#testDclrExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTestDclrExpr(ITDLParser.TestDclrExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link ITDLParser#setupStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSetupStmt(ITDLParser.SetupStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link ITDLParser#tearDownStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTearDownStmt(ITDLParser.TearDownStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link ITDLParser#assertionExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssertionExpr(ITDLParser.AssertionExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link ITDLParser#conditionalExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConditionalExpr(ITDLParser.ConditionalExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link ITDLParser#whileStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileStatement(ITDLParser.WhileStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link ITDLParser#forStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForStatement(ITDLParser.ForStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link ITDLParser#elseCondition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElseCondition(ITDLParser.ElseConditionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ITDLParser#thenStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitThenStatement(ITDLParser.ThenStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link ITDLParser#elseStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElseStatement(ITDLParser.ElseStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprListDcl}
	 * labeled alternative in {@link ITDLParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprListDcl(ITDLParser.ExprListDclContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprParen}
	 * labeled alternative in {@link ITDLParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprParen(ITDLParser.ExprParenContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprNum}
	 * labeled alternative in {@link ITDLParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprNum(ITDLParser.ExprNumContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprListIndex}
	 * labeled alternative in {@link ITDLParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprListIndex(ITDLParser.ExprListIndexContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprInt}
	 * labeled alternative in {@link ITDLParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprInt(ITDLParser.ExprIntContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprNotEq}
	 * labeled alternative in {@link ITDLParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprNotEq(ITDLParser.ExprNotEqContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprRef}
	 * labeled alternative in {@link ITDLParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprRef(ITDLParser.ExprRefContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprReAssign}
	 * labeled alternative in {@link ITDLParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprReAssign(ITDLParser.ExprReAssignContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprNot}
	 * labeled alternative in {@link ITDLParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprNot(ITDLParser.ExprNotContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprGt}
	 * labeled alternative in {@link ITDLParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprGt(ITDLParser.ExprGtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprStr}
	 * labeled alternative in {@link ITDLParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprStr(ITDLParser.ExprStrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprEq}
	 * labeled alternative in {@link ITDLParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprEq(ITDLParser.ExprEqContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprAddSub}
	 * labeled alternative in {@link ITDLParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprAddSub(ITDLParser.ExprAddSubContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprAnd}
	 * labeled alternative in {@link ITDLParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprAnd(ITDLParser.ExprAndContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprGte}
	 * labeled alternative in {@link ITDLParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprGte(ITDLParser.ExprGteContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprLt}
	 * labeled alternative in {@link ITDLParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprLt(ITDLParser.ExprLtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprOr}
	 * labeled alternative in {@link ITDLParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprOr(ITDLParser.ExprOrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprFunCall}
	 * labeled alternative in {@link ITDLParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprFunCall(ITDLParser.ExprFunCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprRange}
	 * labeled alternative in {@link ITDLParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprRange(ITDLParser.ExprRangeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExpBool}
	 * labeled alternative in {@link ITDLParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpBool(ITDLParser.ExpBoolContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprMultDiv}
	 * labeled alternative in {@link ITDLParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprMultDiv(ITDLParser.ExprMultDivContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprLte}
	 * labeled alternative in {@link ITDLParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprLte(ITDLParser.ExprLteContext ctx);
	/**
	 * Visit a parse tree produced by {@link ITDLParser#rangeExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRangeExpr(ITDLParser.RangeExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link ITDLParser#list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitList(ITDLParser.ListContext ctx);
	/**
	 * Visit a parse tree produced by {@link ITDLParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValue(ITDLParser.ValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link ITDLParser#bool}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBool(ITDLParser.BoolContext ctx);
}