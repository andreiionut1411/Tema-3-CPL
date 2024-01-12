// Generated from C:/Andrei/CPL/Tema 2/Parser-skel/src/cool/parser/CoolParser.g4 by ANTLR 4.13.1

    package cool.parser;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class CoolParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		ERROR=1, CLASS=2, INHERITS=3, TRUE=4, FALSE=5, NOT=6, ISVOID=7, NEW=8, 
		IF=9, THEN=10, ELSE=11, FI=12, WHILE=13, LOOP=14, POOL=15, LET=16, IN=17, 
		CASE=18, ESAC=19, OF=20, TYPE=21, ID=22, INTEGER=23, UNTERMINATED_STRING=24, 
		EOF_IN_STRING=25, STRING=26, ASSIGNMENT=27, CASE_RESULT=28, DISPATCH=29, 
		PLUS=30, MINUS=31, MUL=32, DIV=33, TILDE=34, LESS=35, LE=36, EQUAL=37, 
		WS=38, DOT=39, COMMA=40, SEMICOLON=41, COLON=42, OPEN_BRACKETS=43, CLOSED_BRACKETS=44, 
		OPEN_PARANTHESIS=45, CLOSED_PARANTHESIS=46, SINGLE_LINE_COMMENT=47, BLOCK_COMMENT=48, 
		CLOSED_COMMENT=49, UNTERMINATED_COMMENT=50, UNKNOWN_CHARACTER=51;
	public static final int
		RULE_program = 0, RULE_class = 1, RULE_formal = 2, RULE_var_definition = 3, 
		RULE_feature = 4, RULE_expr = 5;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "class", "formal", "var_definition", "feature", "expr"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, "'class'", "'inherits'", "'true'", "'false'", "'not'", "'isvoid'", 
			"'new'", "'if'", "'then'", "'else'", "'fi'", "'while'", "'loop'", "'pool'", 
			"'let'", "'in'", "'case'", "'esac'", "'of'", null, null, null, null, 
			null, null, "'<-'", "'=>'", "'@'", "'+'", "'-'", "'*'", "'/'", "'~'", 
			"'<'", "'<='", "'='", null, "'.'", "','", "';'", "':'", "'{'", "'}'", 
			"'('", "')'", null, null, "'*)'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "ERROR", "CLASS", "INHERITS", "TRUE", "FALSE", "NOT", "ISVOID", 
			"NEW", "IF", "THEN", "ELSE", "FI", "WHILE", "LOOP", "POOL", "LET", "IN", 
			"CASE", "ESAC", "OF", "TYPE", "ID", "INTEGER", "UNTERMINATED_STRING", 
			"EOF_IN_STRING", "STRING", "ASSIGNMENT", "CASE_RESULT", "DISPATCH", "PLUS", 
			"MINUS", "MUL", "DIV", "TILDE", "LESS", "LE", "EQUAL", "WS", "DOT", "COMMA", 
			"SEMICOLON", "COLON", "OPEN_BRACKETS", "CLOSED_BRACKETS", "OPEN_PARANTHESIS", 
			"CLOSED_PARANTHESIS", "SINGLE_LINE_COMMENT", "BLOCK_COMMENT", "CLOSED_COMMENT", 
			"UNTERMINATED_COMMENT", "UNKNOWN_CHARACTER"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "CoolParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public CoolParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramContext extends ParserRuleContext {
		public ClassContext class_;
		public List<ClassContext> cls = new ArrayList<ClassContext>();
		public TerminalNode EOF() { return getToken(CoolParser.EOF, 0); }
		public List<TerminalNode> SEMICOLON() { return getTokens(CoolParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(CoolParser.SEMICOLON, i);
		}
		public List<ClassContext> class_() {
			return getRuleContexts(ClassContext.class);
		}
		public ClassContext class_(int i) {
			return getRuleContext(ClassContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(15); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(12);
				((ProgramContext)_localctx).class_ = class_();
				((ProgramContext)_localctx).cls.add(((ProgramContext)_localctx).class_);
				setState(13);
				match(SEMICOLON);
				}
				}
				setState(17); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==CLASS );
			setState(19);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ClassContext extends ParserRuleContext {
		public ClassContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_class; }
	 
		public ClassContext() { }
		public void copyFrom(ClassContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class Class_declarationContext extends ClassContext {
		public Token type;
		public Token inherited;
		public FeatureContext feature;
		public List<FeatureContext> features = new ArrayList<FeatureContext>();
		public TerminalNode CLASS() { return getToken(CoolParser.CLASS, 0); }
		public TerminalNode OPEN_BRACKETS() { return getToken(CoolParser.OPEN_BRACKETS, 0); }
		public TerminalNode CLOSED_BRACKETS() { return getToken(CoolParser.CLOSED_BRACKETS, 0); }
		public List<TerminalNode> TYPE() { return getTokens(CoolParser.TYPE); }
		public TerminalNode TYPE(int i) {
			return getToken(CoolParser.TYPE, i);
		}
		public TerminalNode INHERITS() { return getToken(CoolParser.INHERITS, 0); }
		public List<TerminalNode> SEMICOLON() { return getTokens(CoolParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(CoolParser.SEMICOLON, i);
		}
		public List<FeatureContext> feature() {
			return getRuleContexts(FeatureContext.class);
		}
		public FeatureContext feature(int i) {
			return getRuleContext(FeatureContext.class,i);
		}
		public Class_declarationContext(ClassContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterClass_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitClass_declaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitClass_declaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassContext class_() throws RecognitionException {
		ClassContext _localctx = new ClassContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_class);
		int _la;
		try {
			_localctx = new Class_declarationContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(21);
			match(CLASS);
			setState(22);
			((Class_declarationContext)_localctx).type = match(TYPE);
			setState(25);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==INHERITS) {
				{
				setState(23);
				match(INHERITS);
				setState(24);
				((Class_declarationContext)_localctx).inherited = match(TYPE);
				}
			}

			setState(27);
			match(OPEN_BRACKETS);
			setState(33);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ID) {
				{
				{
				setState(28);
				((Class_declarationContext)_localctx).feature = feature();
				((Class_declarationContext)_localctx).features.add(((Class_declarationContext)_localctx).feature);
				setState(29);
				match(SEMICOLON);
				}
				}
				setState(35);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(36);
			match(CLOSED_BRACKETS);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FormalContext extends ParserRuleContext {
		public FormalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formal; }
	 
		public FormalContext() { }
		public void copyFrom(FormalContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class Def_formalContext extends FormalContext {
		public Token name;
		public Token type;
		public TerminalNode COLON() { return getToken(CoolParser.COLON, 0); }
		public TerminalNode ID() { return getToken(CoolParser.ID, 0); }
		public TerminalNode TYPE() { return getToken(CoolParser.TYPE, 0); }
		public Def_formalContext(FormalContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterDef_formal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitDef_formal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitDef_formal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FormalContext formal() throws RecognitionException {
		FormalContext _localctx = new FormalContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_formal);
		try {
			_localctx = new Def_formalContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(38);
			((Def_formalContext)_localctx).name = match(ID);
			setState(39);
			match(COLON);
			setState(40);
			((Def_formalContext)_localctx).type = match(TYPE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Var_definitionContext extends ParserRuleContext {
		public Var_definitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_var_definition; }
	 
		public Var_definitionContext() { }
		public void copyFrom(Var_definitionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class Let_varContext extends Var_definitionContext {
		public Token name;
		public Token type;
		public ExprContext assign;
		public TerminalNode COLON() { return getToken(CoolParser.COLON, 0); }
		public TerminalNode ID() { return getToken(CoolParser.ID, 0); }
		public TerminalNode TYPE() { return getToken(CoolParser.TYPE, 0); }
		public TerminalNode ASSIGNMENT() { return getToken(CoolParser.ASSIGNMENT, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Let_varContext(Var_definitionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterLet_var(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitLet_var(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitLet_var(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Var_definitionContext var_definition() throws RecognitionException {
		Var_definitionContext _localctx = new Var_definitionContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_var_definition);
		int _la;
		try {
			_localctx = new Let_varContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(42);
			((Let_varContext)_localctx).name = match(ID);
			setState(43);
			match(COLON);
			setState(44);
			((Let_varContext)_localctx).type = match(TYPE);
			setState(47);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ASSIGNMENT) {
				{
				setState(45);
				match(ASSIGNMENT);
				setState(46);
				((Let_varContext)_localctx).assign = expr(0);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FeatureContext extends ParserRuleContext {
		public FeatureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_feature; }
	 
		public FeatureContext() { }
		public void copyFrom(FeatureContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class Def_funcContext extends FeatureContext {
		public Token name;
		public FormalContext formal;
		public List<FormalContext> args = new ArrayList<FormalContext>();
		public Token type;
		public ExprContext e;
		public TerminalNode OPEN_PARANTHESIS() { return getToken(CoolParser.OPEN_PARANTHESIS, 0); }
		public TerminalNode CLOSED_PARANTHESIS() { return getToken(CoolParser.CLOSED_PARANTHESIS, 0); }
		public TerminalNode COLON() { return getToken(CoolParser.COLON, 0); }
		public TerminalNode OPEN_BRACKETS() { return getToken(CoolParser.OPEN_BRACKETS, 0); }
		public TerminalNode CLOSED_BRACKETS() { return getToken(CoolParser.CLOSED_BRACKETS, 0); }
		public TerminalNode ID() { return getToken(CoolParser.ID, 0); }
		public TerminalNode TYPE() { return getToken(CoolParser.TYPE, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public List<FormalContext> formal() {
			return getRuleContexts(FormalContext.class);
		}
		public FormalContext formal(int i) {
			return getRuleContext(FormalContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(CoolParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CoolParser.COMMA, i);
		}
		public Def_funcContext(FeatureContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterDef_func(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitDef_func(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitDef_func(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class Def_varContext extends FeatureContext {
		public Token name;
		public Token type;
		public ExprContext assign;
		public TerminalNode COLON() { return getToken(CoolParser.COLON, 0); }
		public TerminalNode ID() { return getToken(CoolParser.ID, 0); }
		public TerminalNode TYPE() { return getToken(CoolParser.TYPE, 0); }
		public TerminalNode ASSIGNMENT() { return getToken(CoolParser.ASSIGNMENT, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Def_varContext(FeatureContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterDef_var(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitDef_var(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitDef_var(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FeatureContext feature() throws RecognitionException {
		FeatureContext _localctx = new FeatureContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_feature);
		int _la;
		try {
			setState(75);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				_localctx = new Def_funcContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(49);
				((Def_funcContext)_localctx).name = match(ID);
				setState(50);
				match(OPEN_PARANTHESIS);
				setState(59);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ID) {
					{
					setState(51);
					((Def_funcContext)_localctx).formal = formal();
					((Def_funcContext)_localctx).args.add(((Def_funcContext)_localctx).formal);
					setState(56);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(52);
						match(COMMA);
						setState(53);
						((Def_funcContext)_localctx).formal = formal();
						((Def_funcContext)_localctx).args.add(((Def_funcContext)_localctx).formal);
						}
						}
						setState(58);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(61);
				match(CLOSED_PARANTHESIS);
				setState(62);
				match(COLON);
				setState(63);
				((Def_funcContext)_localctx).type = match(TYPE);
				setState(64);
				match(OPEN_BRACKETS);
				setState(65);
				((Def_funcContext)_localctx).e = expr(0);
				setState(66);
				match(CLOSED_BRACKETS);
				}
				break;
			case 2:
				_localctx = new Def_varContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(68);
				((Def_varContext)_localctx).name = match(ID);
				setState(69);
				match(COLON);
				setState(70);
				((Def_varContext)_localctx).type = match(TYPE);
				setState(73);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ASSIGNMENT) {
					{
					setState(71);
					match(ASSIGNMENT);
					setState(72);
					((Def_varContext)_localctx).assign = expr(0);
					}
				}

				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprContext extends ParserRuleContext {
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	 
		public ExprContext() { }
		public void copyFrom(ExprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NewContext extends ExprContext {
		public Token type;
		public TerminalNode NEW() { return getToken(CoolParser.NEW, 0); }
		public TerminalNode TYPE() { return getToken(CoolParser.TYPE, 0); }
		public NewContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterNew(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitNew(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitNew(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class Par_exprContext extends ExprContext {
		public ExprContext e;
		public TerminalNode OPEN_PARANTHESIS() { return getToken(CoolParser.OPEN_PARANTHESIS, 0); }
		public TerminalNode CLOSED_PARANTHESIS() { return getToken(CoolParser.CLOSED_PARANTHESIS, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Par_exprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterPar_expr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitPar_expr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitPar_expr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class CompareContext extends ExprContext {
		public ExprContext lhs;
		public Token op;
		public ExprContext rhs;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode LESS() { return getToken(CoolParser.LESS, 0); }
		public TerminalNode LE() { return getToken(CoolParser.LE, 0); }
		public TerminalNode EQUAL() { return getToken(CoolParser.EQUAL, 0); }
		public CompareContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterCompare(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitCompare(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitCompare(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FalseContext extends ExprContext {
		public TerminalNode FALSE() { return getToken(CoolParser.FALSE, 0); }
		public FalseContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterFalse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitFalse(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitFalse(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class Code_blockContext extends ExprContext {
		public ExprContext expr;
		public List<ExprContext> e = new ArrayList<ExprContext>();
		public TerminalNode OPEN_BRACKETS() { return getToken(CoolParser.OPEN_BRACKETS, 0); }
		public TerminalNode CLOSED_BRACKETS() { return getToken(CoolParser.CLOSED_BRACKETS, 0); }
		public List<TerminalNode> SEMICOLON() { return getTokens(CoolParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(CoolParser.SEMICOLON, i);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public Code_blockContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterCode_block(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitCode_block(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitCode_block(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class WhileContext extends ExprContext {
		public ExprContext cond;
		public ExprContext loop_expr;
		public TerminalNode WHILE() { return getToken(CoolParser.WHILE, 0); }
		public TerminalNode LOOP() { return getToken(CoolParser.LOOP, 0); }
		public TerminalNode POOL() { return getToken(CoolParser.POOL, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public WhileContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterWhile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitWhile(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitWhile(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class Add_subContext extends ExprContext {
		public ExprContext lhs;
		public Token op;
		public ExprContext rhs;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode PLUS() { return getToken(CoolParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(CoolParser.MINUS, 0); }
		public Add_subContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterAdd_sub(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitAdd_sub(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitAdd_sub(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class Func_callContext extends ExprContext {
		public ExprContext e;
		public Token disp;
		public Token name;
		public ExprContext expr;
		public List<ExprContext> args = new ArrayList<ExprContext>();
		public TerminalNode DOT() { return getToken(CoolParser.DOT, 0); }
		public TerminalNode OPEN_PARANTHESIS() { return getToken(CoolParser.OPEN_PARANTHESIS, 0); }
		public TerminalNode CLOSED_PARANTHESIS() { return getToken(CoolParser.CLOSED_PARANTHESIS, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode ID() { return getToken(CoolParser.ID, 0); }
		public TerminalNode DISPATCH() { return getToken(CoolParser.DISPATCH, 0); }
		public TerminalNode TYPE() { return getToken(CoolParser.TYPE, 0); }
		public List<TerminalNode> COMMA() { return getTokens(CoolParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CoolParser.COMMA, i);
		}
		public Func_callContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterFunc_call(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitFunc_call(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitFunc_call(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IntContext extends ExprContext {
		public TerminalNode INTEGER() { return getToken(CoolParser.INTEGER, 0); }
		public IntContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterInt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitInt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitInt(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StrContext extends ExprContext {
		public TerminalNode STRING() { return getToken(CoolParser.STRING, 0); }
		public StrContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterStr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitStr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitStr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NotContext extends ExprContext {
		public ExprContext e;
		public TerminalNode NOT() { return getToken(CoolParser.NOT, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public NotContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterNot(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitNot(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitNot(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class Is_voidContext extends ExprContext {
		public ExprContext e;
		public TerminalNode ISVOID() { return getToken(CoolParser.ISVOID, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Is_voidContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterIs_void(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitIs_void(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitIs_void(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class TrueContext extends ExprContext {
		public TerminalNode TRUE() { return getToken(CoolParser.TRUE, 0); }
		public TrueContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterTrue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitTrue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitTrue(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class LetContext extends ExprContext {
		public Var_definitionContext var_definition;
		public List<Var_definitionContext> local = new ArrayList<Var_definitionContext>();
		public ExprContext e;
		public TerminalNode LET() { return getToken(CoolParser.LET, 0); }
		public TerminalNode IN() { return getToken(CoolParser.IN, 0); }
		public List<Var_definitionContext> var_definition() {
			return getRuleContexts(Var_definitionContext.class);
		}
		public Var_definitionContext var_definition(int i) {
			return getRuleContext(Var_definitionContext.class,i);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public List<TerminalNode> COMMA() { return getTokens(CoolParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CoolParser.COMMA, i);
		}
		public LetContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterLet(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitLet(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitLet(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class Mul_divContext extends ExprContext {
		public ExprContext lhs;
		public Token op;
		public ExprContext rhs;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode MUL() { return getToken(CoolParser.MUL, 0); }
		public TerminalNode DIV() { return getToken(CoolParser.DIV, 0); }
		public Mul_divContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterMul_div(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitMul_div(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitMul_div(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IdContext extends ExprContext {
		public TerminalNode ID() { return getToken(CoolParser.ID, 0); }
		public IdContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterId(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitId(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitId(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IfContext extends ExprContext {
		public ExprContext cond;
		public ExprContext if_branch;
		public ExprContext else_branch;
		public TerminalNode IF() { return getToken(CoolParser.IF, 0); }
		public TerminalNode THEN() { return getToken(CoolParser.THEN, 0); }
		public TerminalNode ELSE() { return getToken(CoolParser.ELSE, 0); }
		public TerminalNode FI() { return getToken(CoolParser.FI, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public IfContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterIf(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitIf(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitIf(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class Negative_intContext extends ExprContext {
		public ExprContext e;
		public TerminalNode TILDE() { return getToken(CoolParser.TILDE, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Negative_intContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterNegative_int(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitNegative_int(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitNegative_int(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class Implicit_func_callContext extends ExprContext {
		public Token name;
		public ExprContext expr;
		public List<ExprContext> args = new ArrayList<ExprContext>();
		public TerminalNode OPEN_PARANTHESIS() { return getToken(CoolParser.OPEN_PARANTHESIS, 0); }
		public TerminalNode CLOSED_PARANTHESIS() { return getToken(CoolParser.CLOSED_PARANTHESIS, 0); }
		public TerminalNode ID() { return getToken(CoolParser.ID, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(CoolParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CoolParser.COMMA, i);
		}
		public Implicit_func_callContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterImplicit_func_call(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitImplicit_func_call(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitImplicit_func_call(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class CaseContext extends ExprContext {
		public ExprContext e;
		public Token ID;
		public List<Token> branch_name = new ArrayList<Token>();
		public Token TYPE;
		public List<Token> branch_type = new ArrayList<Token>();
		public ExprContext expr;
		public List<ExprContext> res = new ArrayList<ExprContext>();
		public TerminalNode CASE() { return getToken(CoolParser.CASE, 0); }
		public TerminalNode OF() { return getToken(CoolParser.OF, 0); }
		public TerminalNode ESAC() { return getToken(CoolParser.ESAC, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> COLON() { return getTokens(CoolParser.COLON); }
		public TerminalNode COLON(int i) {
			return getToken(CoolParser.COLON, i);
		}
		public List<TerminalNode> CASE_RESULT() { return getTokens(CoolParser.CASE_RESULT); }
		public TerminalNode CASE_RESULT(int i) {
			return getToken(CoolParser.CASE_RESULT, i);
		}
		public List<TerminalNode> SEMICOLON() { return getTokens(CoolParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(CoolParser.SEMICOLON, i);
		}
		public List<TerminalNode> ID() { return getTokens(CoolParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(CoolParser.ID, i);
		}
		public List<TerminalNode> TYPE() { return getTokens(CoolParser.TYPE); }
		public TerminalNode TYPE(int i) {
			return getToken(CoolParser.TYPE, i);
		}
		public CaseContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterCase(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitCase(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitCase(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AssignContext extends ExprContext {
		public Token name;
		public ExprContext e;
		public TerminalNode ASSIGNMENT() { return getToken(CoolParser.ASSIGNMENT, 0); }
		public TerminalNode ID() { return getToken(CoolParser.ID, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public AssignContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterAssign(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitAssign(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitAssign(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 10;
		enterRecursionRule(_localctx, 10, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(163);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				{
				_localctx = new IdContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(78);
				match(ID);
				}
				break;
			case 2:
				{
				_localctx = new IntContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(79);
				match(INTEGER);
				}
				break;
			case 3:
				{
				_localctx = new StrContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(80);
				match(STRING);
				}
				break;
			case 4:
				{
				_localctx = new TrueContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(81);
				match(TRUE);
				}
				break;
			case 5:
				{
				_localctx = new FalseContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(82);
				match(FALSE);
				}
				break;
			case 6:
				{
				_localctx = new Implicit_func_callContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(83);
				((Implicit_func_callContext)_localctx).name = match(ID);
				setState(84);
				match(OPEN_PARANTHESIS);
				setState(93);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 43997725008880L) != 0)) {
					{
					setState(85);
					((Implicit_func_callContext)_localctx).expr = expr(0);
					((Implicit_func_callContext)_localctx).args.add(((Implicit_func_callContext)_localctx).expr);
					setState(90);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(86);
						match(COMMA);
						setState(87);
						((Implicit_func_callContext)_localctx).expr = expr(0);
						((Implicit_func_callContext)_localctx).args.add(((Implicit_func_callContext)_localctx).expr);
						}
						}
						setState(92);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(95);
				match(CLOSED_PARANTHESIS);
				}
				break;
			case 7:
				{
				_localctx = new IfContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(96);
				match(IF);
				setState(97);
				((IfContext)_localctx).cond = expr(0);
				setState(98);
				match(THEN);
				setState(99);
				((IfContext)_localctx).if_branch = expr(0);
				setState(100);
				match(ELSE);
				setState(101);
				((IfContext)_localctx).else_branch = expr(0);
				setState(102);
				match(FI);
				}
				break;
			case 8:
				{
				_localctx = new WhileContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(104);
				match(WHILE);
				setState(105);
				((WhileContext)_localctx).cond = expr(0);
				setState(106);
				match(LOOP);
				setState(107);
				((WhileContext)_localctx).loop_expr = expr(0);
				setState(108);
				match(POOL);
				}
				break;
			case 9:
				{
				_localctx = new Code_blockContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(110);
				match(OPEN_BRACKETS);
				setState(114); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(111);
					((Code_blockContext)_localctx).expr = expr(0);
					((Code_blockContext)_localctx).e.add(((Code_blockContext)_localctx).expr);
					setState(112);
					match(SEMICOLON);
					}
					}
					setState(116); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 43997725008880L) != 0) );
				setState(118);
				match(CLOSED_BRACKETS);
				}
				break;
			case 10:
				{
				_localctx = new LetContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(120);
				match(LET);
				setState(121);
				((LetContext)_localctx).var_definition = var_definition();
				((LetContext)_localctx).local.add(((LetContext)_localctx).var_definition);
				setState(126);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(122);
					match(COMMA);
					setState(123);
					((LetContext)_localctx).var_definition = var_definition();
					((LetContext)_localctx).local.add(((LetContext)_localctx).var_definition);
					}
					}
					setState(128);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(129);
				match(IN);
				setState(130);
				((LetContext)_localctx).e = expr(11);
				}
				break;
			case 11:
				{
				_localctx = new CaseContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(132);
				match(CASE);
				setState(133);
				((CaseContext)_localctx).e = expr(0);
				setState(134);
				match(OF);
				setState(142); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(135);
					((CaseContext)_localctx).ID = match(ID);
					((CaseContext)_localctx).branch_name.add(((CaseContext)_localctx).ID);
					setState(136);
					match(COLON);
					setState(137);
					((CaseContext)_localctx).TYPE = match(TYPE);
					((CaseContext)_localctx).branch_type.add(((CaseContext)_localctx).TYPE);
					setState(138);
					match(CASE_RESULT);
					setState(139);
					((CaseContext)_localctx).expr = expr(0);
					((CaseContext)_localctx).res.add(((CaseContext)_localctx).expr);
					setState(140);
					match(SEMICOLON);
					}
					}
					setState(144); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==ID );
				setState(146);
				match(ESAC);
				}
				break;
			case 12:
				{
				_localctx = new NewContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(148);
				match(NEW);
				setState(149);
				((NewContext)_localctx).type = match(TYPE);
				}
				break;
			case 13:
				{
				_localctx = new Negative_intContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(150);
				match(TILDE);
				setState(151);
				((Negative_intContext)_localctx).e = expr(8);
				}
				break;
			case 14:
				{
				_localctx = new Is_voidContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(152);
				match(ISVOID);
				setState(153);
				((Is_voidContext)_localctx).e = expr(7);
				}
				break;
			case 15:
				{
				_localctx = new Par_exprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(154);
				match(OPEN_PARANTHESIS);
				setState(155);
				((Par_exprContext)_localctx).e = expr(0);
				setState(156);
				match(CLOSED_PARANTHESIS);
				}
				break;
			case 16:
				{
				_localctx = new NotContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(158);
				match(NOT);
				setState(159);
				((NotContext)_localctx).e = expr(2);
				}
				break;
			case 17:
				{
				_localctx = new AssignContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(160);
				((AssignContext)_localctx).name = match(ID);
				setState(161);
				match(ASSIGNMENT);
				setState(162);
				((AssignContext)_localctx).e = expr(1);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(195);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(193);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
					case 1:
						{
						_localctx = new Mul_divContext(new ExprContext(_parentctx, _parentState));
						((Mul_divContext)_localctx).lhs = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(165);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(166);
						((Mul_divContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==MUL || _la==DIV) ) {
							((Mul_divContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(167);
						((Mul_divContext)_localctx).rhs = expr(7);
						}
						break;
					case 2:
						{
						_localctx = new Add_subContext(new ExprContext(_parentctx, _parentState));
						((Add_subContext)_localctx).lhs = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(168);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(169);
						((Add_subContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==PLUS || _la==MINUS) ) {
							((Add_subContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(170);
						((Add_subContext)_localctx).rhs = expr(6);
						}
						break;
					case 3:
						{
						_localctx = new CompareContext(new ExprContext(_parentctx, _parentState));
						((CompareContext)_localctx).lhs = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(171);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(172);
						((CompareContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 240518168576L) != 0)) ) {
							((CompareContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(173);
						((CompareContext)_localctx).rhs = expr(4);
						}
						break;
					case 4:
						{
						_localctx = new Func_callContext(new ExprContext(_parentctx, _parentState));
						((Func_callContext)_localctx).e = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(174);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(177);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==DISPATCH) {
							{
							setState(175);
							match(DISPATCH);
							setState(176);
							((Func_callContext)_localctx).disp = match(TYPE);
							}
						}

						setState(179);
						match(DOT);
						setState(180);
						((Func_callContext)_localctx).name = match(ID);
						setState(181);
						match(OPEN_PARANTHESIS);
						setState(190);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 43997725008880L) != 0)) {
							{
							setState(182);
							((Func_callContext)_localctx).expr = expr(0);
							((Func_callContext)_localctx).args.add(((Func_callContext)_localctx).expr);
							setState(187);
							_errHandler.sync(this);
							_la = _input.LA(1);
							while (_la==COMMA) {
								{
								{
								setState(183);
								match(COMMA);
								setState(184);
								((Func_callContext)_localctx).expr = expr(0);
								((Func_callContext)_localctx).args.add(((Func_callContext)_localctx).expr);
								}
								}
								setState(189);
								_errHandler.sync(this);
								_la = _input.LA(1);
							}
							}
						}

						setState(192);
						match(CLOSED_PARANTHESIS);
						}
						break;
					}
					} 
				}
				setState(197);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 5:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 6);
		case 1:
			return precpred(_ctx, 5);
		case 2:
			return precpred(_ctx, 3);
		case 3:
			return precpred(_ctx, 16);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u00013\u00c7\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0001\u0000\u0001\u0000\u0001\u0000\u0004\u0000\u0010"+
		"\b\u0000\u000b\u0000\f\u0000\u0011\u0001\u0000\u0001\u0000\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0003\u0001\u001a\b\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0005\u0001 \b\u0001\n\u0001\f\u0001"+
		"#\t\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0003\u00030\b\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0005\u00047\b\u0004\n\u0004\f\u0004:\t\u0004\u0003\u0004"+
		"<\b\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0003\u0004J\b\u0004\u0003\u0004L\b\u0004\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0005\u0005Y\b\u0005\n\u0005"+
		"\f\u0005\\\t\u0005\u0003\u0005^\b\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0004\u0005s\b"+
		"\u0005\u000b\u0005\f\u0005t\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0005\u0005}\b\u0005\n\u0005\f\u0005\u0080"+
		"\t\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0004\u0005\u008f\b\u0005\u000b\u0005\f\u0005\u0090"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0003\u0005"+
		"\u00a4\b\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0003\u0005\u00b2\b\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0005\u0005\u00ba\b\u0005\n\u0005"+
		"\f\u0005\u00bd\t\u0005\u0003\u0005\u00bf\b\u0005\u0001\u0005\u0005\u0005"+
		"\u00c2\b\u0005\n\u0005\f\u0005\u00c5\t\u0005\u0001\u0005\u0000\u0001\n"+
		"\u0006\u0000\u0002\u0004\u0006\b\n\u0000\u0003\u0001\u0000 !\u0001\u0000"+
		"\u001e\u001f\u0001\u0000#%\u00e4\u0000\u000f\u0001\u0000\u0000\u0000\u0002"+
		"\u0015\u0001\u0000\u0000\u0000\u0004&\u0001\u0000\u0000\u0000\u0006*\u0001"+
		"\u0000\u0000\u0000\bK\u0001\u0000\u0000\u0000\n\u00a3\u0001\u0000\u0000"+
		"\u0000\f\r\u0003\u0002\u0001\u0000\r\u000e\u0005)\u0000\u0000\u000e\u0010"+
		"\u0001\u0000\u0000\u0000\u000f\f\u0001\u0000\u0000\u0000\u0010\u0011\u0001"+
		"\u0000\u0000\u0000\u0011\u000f\u0001\u0000\u0000\u0000\u0011\u0012\u0001"+
		"\u0000\u0000\u0000\u0012\u0013\u0001\u0000\u0000\u0000\u0013\u0014\u0005"+
		"\u0000\u0000\u0001\u0014\u0001\u0001\u0000\u0000\u0000\u0015\u0016\u0005"+
		"\u0002\u0000\u0000\u0016\u0019\u0005\u0015\u0000\u0000\u0017\u0018\u0005"+
		"\u0003\u0000\u0000\u0018\u001a\u0005\u0015\u0000\u0000\u0019\u0017\u0001"+
		"\u0000\u0000\u0000\u0019\u001a\u0001\u0000\u0000\u0000\u001a\u001b\u0001"+
		"\u0000\u0000\u0000\u001b!\u0005+\u0000\u0000\u001c\u001d\u0003\b\u0004"+
		"\u0000\u001d\u001e\u0005)\u0000\u0000\u001e \u0001\u0000\u0000\u0000\u001f"+
		"\u001c\u0001\u0000\u0000\u0000 #\u0001\u0000\u0000\u0000!\u001f\u0001"+
		"\u0000\u0000\u0000!\"\u0001\u0000\u0000\u0000\"$\u0001\u0000\u0000\u0000"+
		"#!\u0001\u0000\u0000\u0000$%\u0005,\u0000\u0000%\u0003\u0001\u0000\u0000"+
		"\u0000&\'\u0005\u0016\u0000\u0000\'(\u0005*\u0000\u0000()\u0005\u0015"+
		"\u0000\u0000)\u0005\u0001\u0000\u0000\u0000*+\u0005\u0016\u0000\u0000"+
		"+,\u0005*\u0000\u0000,/\u0005\u0015\u0000\u0000-.\u0005\u001b\u0000\u0000"+
		".0\u0003\n\u0005\u0000/-\u0001\u0000\u0000\u0000/0\u0001\u0000\u0000\u0000"+
		"0\u0007\u0001\u0000\u0000\u000012\u0005\u0016\u0000\u00002;\u0005-\u0000"+
		"\u000038\u0003\u0004\u0002\u000045\u0005(\u0000\u000057\u0003\u0004\u0002"+
		"\u000064\u0001\u0000\u0000\u00007:\u0001\u0000\u0000\u000086\u0001\u0000"+
		"\u0000\u000089\u0001\u0000\u0000\u00009<\u0001\u0000\u0000\u0000:8\u0001"+
		"\u0000\u0000\u0000;3\u0001\u0000\u0000\u0000;<\u0001\u0000\u0000\u0000"+
		"<=\u0001\u0000\u0000\u0000=>\u0005.\u0000\u0000>?\u0005*\u0000\u0000?"+
		"@\u0005\u0015\u0000\u0000@A\u0005+\u0000\u0000AB\u0003\n\u0005\u0000B"+
		"C\u0005,\u0000\u0000CL\u0001\u0000\u0000\u0000DE\u0005\u0016\u0000\u0000"+
		"EF\u0005*\u0000\u0000FI\u0005\u0015\u0000\u0000GH\u0005\u001b\u0000\u0000"+
		"HJ\u0003\n\u0005\u0000IG\u0001\u0000\u0000\u0000IJ\u0001\u0000\u0000\u0000"+
		"JL\u0001\u0000\u0000\u0000K1\u0001\u0000\u0000\u0000KD\u0001\u0000\u0000"+
		"\u0000L\t\u0001\u0000\u0000\u0000MN\u0006\u0005\uffff\uffff\u0000N\u00a4"+
		"\u0005\u0016\u0000\u0000O\u00a4\u0005\u0017\u0000\u0000P\u00a4\u0005\u001a"+
		"\u0000\u0000Q\u00a4\u0005\u0004\u0000\u0000R\u00a4\u0005\u0005\u0000\u0000"+
		"ST\u0005\u0016\u0000\u0000T]\u0005-\u0000\u0000UZ\u0003\n\u0005\u0000"+
		"VW\u0005(\u0000\u0000WY\u0003\n\u0005\u0000XV\u0001\u0000\u0000\u0000"+
		"Y\\\u0001\u0000\u0000\u0000ZX\u0001\u0000\u0000\u0000Z[\u0001\u0000\u0000"+
		"\u0000[^\u0001\u0000\u0000\u0000\\Z\u0001\u0000\u0000\u0000]U\u0001\u0000"+
		"\u0000\u0000]^\u0001\u0000\u0000\u0000^_\u0001\u0000\u0000\u0000_\u00a4"+
		"\u0005.\u0000\u0000`a\u0005\t\u0000\u0000ab\u0003\n\u0005\u0000bc\u0005"+
		"\n\u0000\u0000cd\u0003\n\u0005\u0000de\u0005\u000b\u0000\u0000ef\u0003"+
		"\n\u0005\u0000fg\u0005\f\u0000\u0000g\u00a4\u0001\u0000\u0000\u0000hi"+
		"\u0005\r\u0000\u0000ij\u0003\n\u0005\u0000jk\u0005\u000e\u0000\u0000k"+
		"l\u0003\n\u0005\u0000lm\u0005\u000f\u0000\u0000m\u00a4\u0001\u0000\u0000"+
		"\u0000nr\u0005+\u0000\u0000op\u0003\n\u0005\u0000pq\u0005)\u0000\u0000"+
		"qs\u0001\u0000\u0000\u0000ro\u0001\u0000\u0000\u0000st\u0001\u0000\u0000"+
		"\u0000tr\u0001\u0000\u0000\u0000tu\u0001\u0000\u0000\u0000uv\u0001\u0000"+
		"\u0000\u0000vw\u0005,\u0000\u0000w\u00a4\u0001\u0000\u0000\u0000xy\u0005"+
		"\u0010\u0000\u0000y~\u0003\u0006\u0003\u0000z{\u0005(\u0000\u0000{}\u0003"+
		"\u0006\u0003\u0000|z\u0001\u0000\u0000\u0000}\u0080\u0001\u0000\u0000"+
		"\u0000~|\u0001\u0000\u0000\u0000~\u007f\u0001\u0000\u0000\u0000\u007f"+
		"\u0081\u0001\u0000\u0000\u0000\u0080~\u0001\u0000\u0000\u0000\u0081\u0082"+
		"\u0005\u0011\u0000\u0000\u0082\u0083\u0003\n\u0005\u000b\u0083\u00a4\u0001"+
		"\u0000\u0000\u0000\u0084\u0085\u0005\u0012\u0000\u0000\u0085\u0086\u0003"+
		"\n\u0005\u0000\u0086\u008e\u0005\u0014\u0000\u0000\u0087\u0088\u0005\u0016"+
		"\u0000\u0000\u0088\u0089\u0005*\u0000\u0000\u0089\u008a\u0005\u0015\u0000"+
		"\u0000\u008a\u008b\u0005\u001c\u0000\u0000\u008b\u008c\u0003\n\u0005\u0000"+
		"\u008c\u008d\u0005)\u0000\u0000\u008d\u008f\u0001\u0000\u0000\u0000\u008e"+
		"\u0087\u0001\u0000\u0000\u0000\u008f\u0090\u0001\u0000\u0000\u0000\u0090"+
		"\u008e\u0001\u0000\u0000\u0000\u0090\u0091\u0001\u0000\u0000\u0000\u0091"+
		"\u0092\u0001\u0000\u0000\u0000\u0092\u0093\u0005\u0013\u0000\u0000\u0093"+
		"\u00a4\u0001\u0000\u0000\u0000\u0094\u0095\u0005\b\u0000\u0000\u0095\u00a4"+
		"\u0005\u0015\u0000\u0000\u0096\u0097\u0005\"\u0000\u0000\u0097\u00a4\u0003"+
		"\n\u0005\b\u0098\u0099\u0005\u0007\u0000\u0000\u0099\u00a4\u0003\n\u0005"+
		"\u0007\u009a\u009b\u0005-\u0000\u0000\u009b\u009c\u0003\n\u0005\u0000"+
		"\u009c\u009d\u0005.\u0000\u0000\u009d\u00a4\u0001\u0000\u0000\u0000\u009e"+
		"\u009f\u0005\u0006\u0000\u0000\u009f\u00a4\u0003\n\u0005\u0002\u00a0\u00a1"+
		"\u0005\u0016\u0000\u0000\u00a1\u00a2\u0005\u001b\u0000\u0000\u00a2\u00a4"+
		"\u0003\n\u0005\u0001\u00a3M\u0001\u0000\u0000\u0000\u00a3O\u0001\u0000"+
		"\u0000\u0000\u00a3P\u0001\u0000\u0000\u0000\u00a3Q\u0001\u0000\u0000\u0000"+
		"\u00a3R\u0001\u0000\u0000\u0000\u00a3S\u0001\u0000\u0000\u0000\u00a3`"+
		"\u0001\u0000\u0000\u0000\u00a3h\u0001\u0000\u0000\u0000\u00a3n\u0001\u0000"+
		"\u0000\u0000\u00a3x\u0001\u0000\u0000\u0000\u00a3\u0084\u0001\u0000\u0000"+
		"\u0000\u00a3\u0094\u0001\u0000\u0000\u0000\u00a3\u0096\u0001\u0000\u0000"+
		"\u0000\u00a3\u0098\u0001\u0000\u0000\u0000\u00a3\u009a\u0001\u0000\u0000"+
		"\u0000\u00a3\u009e\u0001\u0000\u0000\u0000\u00a3\u00a0\u0001\u0000\u0000"+
		"\u0000\u00a4\u00c3\u0001\u0000\u0000\u0000\u00a5\u00a6\n\u0006\u0000\u0000"+
		"\u00a6\u00a7\u0007\u0000\u0000\u0000\u00a7\u00c2\u0003\n\u0005\u0007\u00a8"+
		"\u00a9\n\u0005\u0000\u0000\u00a9\u00aa\u0007\u0001\u0000\u0000\u00aa\u00c2"+
		"\u0003\n\u0005\u0006\u00ab\u00ac\n\u0003\u0000\u0000\u00ac\u00ad\u0007"+
		"\u0002\u0000\u0000\u00ad\u00c2\u0003\n\u0005\u0004\u00ae\u00b1\n\u0010"+
		"\u0000\u0000\u00af\u00b0\u0005\u001d\u0000\u0000\u00b0\u00b2\u0005\u0015"+
		"\u0000\u0000\u00b1\u00af\u0001\u0000\u0000\u0000\u00b1\u00b2\u0001\u0000"+
		"\u0000\u0000\u00b2\u00b3\u0001\u0000\u0000\u0000\u00b3\u00b4\u0005\'\u0000"+
		"\u0000\u00b4\u00b5\u0005\u0016\u0000\u0000\u00b5\u00be\u0005-\u0000\u0000"+
		"\u00b6\u00bb\u0003\n\u0005\u0000\u00b7\u00b8\u0005(\u0000\u0000\u00b8"+
		"\u00ba\u0003\n\u0005\u0000\u00b9\u00b7\u0001\u0000\u0000\u0000\u00ba\u00bd"+
		"\u0001\u0000\u0000\u0000\u00bb\u00b9\u0001\u0000\u0000\u0000\u00bb\u00bc"+
		"\u0001\u0000\u0000\u0000\u00bc\u00bf\u0001\u0000\u0000\u0000\u00bd\u00bb"+
		"\u0001\u0000\u0000\u0000\u00be\u00b6\u0001\u0000\u0000\u0000\u00be\u00bf"+
		"\u0001\u0000\u0000\u0000\u00bf\u00c0\u0001\u0000\u0000\u0000\u00c0\u00c2"+
		"\u0005.\u0000\u0000\u00c1\u00a5\u0001\u0000\u0000\u0000\u00c1\u00a8\u0001"+
		"\u0000\u0000\u0000\u00c1\u00ab\u0001\u0000\u0000\u0000\u00c1\u00ae\u0001"+
		"\u0000\u0000\u0000\u00c2\u00c5\u0001\u0000\u0000\u0000\u00c3\u00c1\u0001"+
		"\u0000\u0000\u0000\u00c3\u00c4\u0001\u0000\u0000\u0000\u00c4\u000b\u0001"+
		"\u0000\u0000\u0000\u00c5\u00c3\u0001\u0000\u0000\u0000\u0013\u0011\u0019"+
		"!/8;IKZ]t~\u0090\u00a3\u00b1\u00bb\u00be\u00c1\u00c3";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}