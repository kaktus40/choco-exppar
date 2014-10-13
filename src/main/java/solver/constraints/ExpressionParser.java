// Generated from solver/constraints/ExpressionParser.g4 by ANTLR 4.0
package solver.constraints;

/*
 * Copyright (c) 1999-2014, Ecole des Mines de Nantes
 * All rights reserved.
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the Ecole des Mines de Nantes nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE REGENTS AND CONTRIBUTORS ``AS IS'' AND ANY
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE REGENTS AND CONTRIBUTORS BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import solver.variables.VariableFactory;
import solver.Solver;
import solver.variables.IntVar;
import solver.constraints.Constraint;
import solver.constraints.ICF;
import solver.exception.SolverException;
import solver.constraints.Operator;
import solver.variables.VF;

import java.util.Arrays;
import gnu.trove.list.TIntList;
import gnu.trove.list.array.TIntArrayList;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ExpressionParser extends Parser {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		PL=7, SC=9, GT=4, LT=5, LB=1, WS=12, INT=11, EQ=3, RB=2, DT=10, MN=8, 
		NT=6;
	public static final String[] tokenNames = {
		"<INVALID>", "'{'", "'}'", "'='", "'>'", "'<'", "'!'", "'+'", "'-'", "';'", 
		"'.'", "INT", "WS"
	};
	public static final int
		RULE_assignment = 0, RULE_assgnt = 1, RULE_operator = 2, RULE_var = 3, 
		RULE_atom = 4, RULE_shexp = 5, RULE_expression = 6;
	public static final String[] ruleNames = {
		"assignment", "assgnt", "operator", "var", "atom", "shexp", "expression"
	};

	@Override
	public String getGrammarFileName() { return "ExpressionParser.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public ATN getATN() { return _ATN; }




	// the solver
	private Solver mSolver;

	private IntVar[] mVars;

	private List<IntVar> evars;

	private TIntList ecoeffs;

	private int tmp;

	private int result;

	private Operator comp_op;


	public ExpressionParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class AssignmentContext extends ParserRuleContext {
		public Solver aSolver;
		public IntVar[] ivars;
		public Constraint[] constraints;
		public List<Constraint> mConstraints = new ArrayList<Constraint>();
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public List<TerminalNode> SC() { return getTokens(ExpressionParser.SC); }
		public TerminalNode SC(int i) {
			return getToken(ExpressionParser.SC, i);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public AssignmentContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public AssignmentContext(ParserRuleContext parent, int invokingState, Solver aSolver, IntVar[] ivars) {
			super(parent, invokingState);
			this.aSolver = aSolver;
			this.ivars = ivars;
		}
		@Override public int getRuleIndex() { return RULE_assignment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).enterAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).exitAssignment(this);
		}
	}

	public final AssignmentContext assignment(Solver aSolver,IntVar[] ivars) throws RecognitionException {
		AssignmentContext _localctx = new AssignmentContext(_ctx, getState(), aSolver, ivars);
		enterRule(_localctx, 0, RULE_assignment);

		    this.mSolver = aSolver;
		    this.mVars = ivars.clone();
		    this.evars = new ArrayList<IntVar>();
		    this.ecoeffs = new TIntArrayList();
		    this.comp_op = Operator.NONE;
		    this.result = 0;
		    this.tmp = Integer.MIN_VALUE;

		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(18); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(14); expression();
				setState(15); match(SC);

				        _localctx.mConstraints.add(ICF.scalar(evars.toArray(new IntVar[0]),ecoeffs.toArray(),comp_op.toString(),VF.fixed(result, mSolver)));
				        // then clear data structures
				        evars.clear();
				        ecoeffs.clear();
				        comp_op = Operator.NONE;
				        result = 0;
				        tmp = Integer.MIN_VALUE;
				    
				}
				}
				setState(20); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==LB || _la==INT );
			}

			    ((AssignmentContext)_localctx).constraints =  new Constraint[_localctx.mConstraints.size()];
			    _localctx.mConstraints.toArray(_localctx.constraints);

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

	public static class AssgntContext extends ParserRuleContext {
		public TerminalNode GT() { return getToken(ExpressionParser.GT, 0); }
		public TerminalNode LT() { return getToken(ExpressionParser.LT, 0); }
		public TerminalNode EQ() { return getToken(ExpressionParser.EQ, 0); }
		public TerminalNode NT() { return getToken(ExpressionParser.NT, 0); }
		public AssgntContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assgnt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).enterAssgnt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).exitAssgnt(this);
		}
	}

	public final AssgntContext assgnt() throws RecognitionException {
		AssgntContext _localctx = new AssgntContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_assgnt);
		try {
			setState(37);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(22); match(EQ);
				comp_op=Operator.EQ;
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(24); match(NT);
				setState(25); match(EQ);
				comp_op=Operator.NQ;
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(27); match(GT);
				setState(28); match(EQ);
				comp_op=Operator.GE;
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(30); match(LT);
				setState(31); match(EQ);
				comp_op=Operator.LE;
				}
				break;

			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(33); match(GT);
				comp_op=Operator.GT;
				}
				break;

			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(35); match(LT);
				comp_op=Operator.LT;
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

	public static class OperatorContext extends ParserRuleContext {
		public Operator ope;
		public TerminalNode PL() { return getToken(ExpressionParser.PL, 0); }
		public TerminalNode MN() { return getToken(ExpressionParser.MN, 0); }
		public OperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).enterOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).exitOperator(this);
		}
	}

	public final OperatorContext operator() throws RecognitionException {
		OperatorContext _localctx = new OperatorContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_operator);
		try {
			setState(43);
			switch (_input.LA(1)) {
			case PL:
				enterOuterAlt(_localctx, 1);
				{
				setState(39); match(PL);
				((OperatorContext)_localctx).ope = Operator.PL;
				}
				break;
			case MN:
				enterOuterAlt(_localctx, 2);
				{
				setState(41); match(MN);
				((OperatorContext)_localctx).ope = Operator.MN;
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class VarContext extends ParserRuleContext {
		public IntVar aVar;
		public Token i;
		public TerminalNode LB() { return getToken(ExpressionParser.LB, 0); }
		public TerminalNode INT() { return getToken(ExpressionParser.INT, 0); }
		public TerminalNode RB() { return getToken(ExpressionParser.RB, 0); }
		public VarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_var; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).enterVar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).exitVar(this);
		}
	}

	public final VarContext var() throws RecognitionException {
		VarContext _localctx = new VarContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_var);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(45); match(LB);
			setState(46); ((VarContext)_localctx).i = match(INT);
			setState(47); match(RB);

			    int idx = Integer.parseInt((((VarContext)_localctx).i!=null?((VarContext)_localctx).i.getText():null));
			    if(idx <0 || idx > mVars.length - 1){
			        throw new SolverException("The "+idx+"^h variable from "+Arrays.toString(mVars)+" is null or out of bounds.");
			    }
			    ((VarContext)_localctx).aVar =  mVars[idx];
			    
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

	public static class AtomContext extends ParserRuleContext {
		public Token i;
		public VarContext v;
		public TerminalNode INT() { return getToken(ExpressionParser.INT, 0); }
		public VarContext var() {
			return getRuleContext(VarContext.class,0);
		}
		public TerminalNode DT() { return getToken(ExpressionParser.DT, 0); }
		public AtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).enterAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).exitAtom(this);
		}
	}

	public final AtomContext atom() throws RecognitionException {
		AtomContext _localctx = new AtomContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_atom);
		try {
			setState(66);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(50); ((AtomContext)_localctx).i = match(INT);

				        tmp = Integer.parseInt((((AtomContext)_localctx).i!=null?((AtomContext)_localctx).i.getText():null));
				    
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(52); ((AtomContext)_localctx).v = var();

				            evars.add(((AtomContext)_localctx).v.aVar);
				            ecoeffs.add(1);
				    
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(62);
				switch (_input.LA(1)) {
				case LB:
					{
					setState(55); ((AtomContext)_localctx).v = var();
					setState(56); match(DT);
					setState(57); ((AtomContext)_localctx).i = match(INT);
					}
					break;
				case INT:
					{
					setState(59); ((AtomContext)_localctx).i = match(INT);
					setState(60); match(DT);
					setState(61); ((AtomContext)_localctx).v = var();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}

				        evars.add(((AtomContext)_localctx).v.aVar);
				        ecoeffs.add(Integer.parseInt((((AtomContext)_localctx).i!=null?((AtomContext)_localctx).i.getText():null)));
				    
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

	public static class ShexpContext extends ParserRuleContext {
		public OperatorContext o;
		public List<AtomContext> atom() {
			return getRuleContexts(AtomContext.class);
		}
		public OperatorContext operator(int i) {
			return getRuleContext(OperatorContext.class,i);
		}
		public AtomContext atom(int i) {
			return getRuleContext(AtomContext.class,i);
		}
		public List<OperatorContext> operator() {
			return getRuleContexts(OperatorContext.class);
		}
		public ShexpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_shexp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).enterShexp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).exitShexp(this);
		}
	}

	public final ShexpContext shexp() throws RecognitionException {
		ShexpContext _localctx = new ShexpContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_shexp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(68); atom();

			         if(tmp > Integer.MIN_VALUE){
			            if(comp_op == Operator.NONE){ // LHS
			                result -= tmp;
			            }else{ // RHS
			                result += tmp;
			            }
			            // then reset tmp
			            tmp = Integer.MIN_VALUE;
			         }
			    
			setState(76);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==PL || _la==MN) {
				{
				{
				setState(70); ((ShexpContext)_localctx).o = operator();
				setState(71); atom();

				        if(tmp > Integer.MIN_VALUE){
				            if(comp_op == Operator.NONE){ // LHS
				                result += (((ShexpContext)_localctx).o.ope == Operator.PL?-tmp:tmp);
				            }else{ // RHS
				                result += (((ShexpContext)_localctx).o.ope == Operator.PL?tmp:-tmp);
				            }
				            // then reset tmp
				            tmp = Integer.MIN_VALUE;
				        }else if(((ShexpContext)_localctx).o.ope == Operator.MN){
				            int cidx = evars.size()-1;
				            int c = ecoeffs.get(cidx);
				            ecoeffs.set(cidx,-c);
				        }
				    
				}
				}
				setState(78);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	public static class ExpressionContext extends ParserRuleContext {
		public int a = 0;
		public AssgntContext assgnt() {
			return getRuleContext(AssgntContext.class,0);
		}
		public List<ShexpContext> shexp() {
			return getRuleContexts(ShexpContext.class);
		}
		public ShexpContext shexp(int i) {
			return getRuleContext(ShexpContext.class,i);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).exitExpression(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(79); shexp();
			setState(80); assgnt();
			((ExpressionContext)_localctx).a =  evars.size();
			setState(82); shexp();
			}

			    int cidx = evars.size();
			    for(int i = _localctx.a; i < cidx; i++){
			        int c = ecoeffs.get(i);
			        ecoeffs.set(i,-c);
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

	public static final String _serializedATN =
		"\2\3\16W\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\3\2\3"+
		"\2\3\2\3\2\6\2\25\n\2\r\2\16\2\26\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\5\3(\n\3\3\4\3\4\3\4\3\4\5\4.\n\4\3\5\3\5\3\5"+
		"\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6A\n\6\3\6"+
		"\3\6\5\6E\n\6\3\7\3\7\3\7\3\7\3\7\3\7\7\7M\n\7\f\7\16\7P\13\7\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\2\t\2\4\6\b\n\f\16\2\2Z\2\24\3\2\2\2\4\'\3\2\2\2\6-\3"+
		"\2\2\2\b/\3\2\2\2\nD\3\2\2\2\fF\3\2\2\2\16Q\3\2\2\2\20\21\5\16\b\2\21"+
		"\22\7\13\2\2\22\23\b\2\1\2\23\25\3\2\2\2\24\20\3\2\2\2\25\26\3\2\2\2\26"+
		"\24\3\2\2\2\26\27\3\2\2\2\27\3\3\2\2\2\30\31\7\5\2\2\31(\b\3\1\2\32\33"+
		"\7\b\2\2\33\34\7\5\2\2\34(\b\3\1\2\35\36\7\6\2\2\36\37\7\5\2\2\37(\b\3"+
		"\1\2 !\7\7\2\2!\"\7\5\2\2\"(\b\3\1\2#$\7\6\2\2$(\b\3\1\2%&\7\7\2\2&(\b"+
		"\3\1\2\'\30\3\2\2\2\'\32\3\2\2\2\'\35\3\2\2\2\' \3\2\2\2\'#\3\2\2\2\'"+
		"%\3\2\2\2(\5\3\2\2\2)*\7\t\2\2*.\b\4\1\2+,\7\n\2\2,.\b\4\1\2-)\3\2\2\2"+
		"-+\3\2\2\2.\7\3\2\2\2/\60\7\3\2\2\60\61\7\r\2\2\61\62\7\4\2\2\62\63\b"+
		"\5\1\2\63\t\3\2\2\2\64\65\7\r\2\2\65E\b\6\1\2\66\67\5\b\5\2\678\b\6\1"+
		"\28E\3\2\2\29:\5\b\5\2:;\7\f\2\2;<\7\r\2\2<A\3\2\2\2=>\7\r\2\2>?\7\f\2"+
		"\2?A\5\b\5\2@9\3\2\2\2@=\3\2\2\2AB\3\2\2\2BC\b\6\1\2CE\3\2\2\2D\64\3\2"+
		"\2\2D\66\3\2\2\2D@\3\2\2\2E\13\3\2\2\2FG\5\n\6\2GN\b\7\1\2HI\5\6\4\2I"+
		"J\5\n\6\2JK\b\7\1\2KM\3\2\2\2LH\3\2\2\2MP\3\2\2\2NL\3\2\2\2NO\3\2\2\2"+
		"O\r\3\2\2\2PN\3\2\2\2QR\5\f\7\2RS\5\4\3\2ST\b\b\1\2TU\5\f\7\2U\17\3\2"+
		"\2\2\b\26\'-@DN";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}