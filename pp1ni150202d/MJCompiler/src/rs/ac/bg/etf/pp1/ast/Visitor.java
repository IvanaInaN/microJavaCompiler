// generated with ast extension for cup
// version 0.8
// 13/0/2020 0:3:28


package rs.ac.bg.etf.pp1.ast;

public interface Visitor { 

    public void visit(ListCondFact ListCondFact);
    public void visit(Mulop Mulop);
    public void visit(MaybeMinus MaybeMinus);
    public void visit(Relop Relop);
    public void visit(Assignop Assignop);
    public void visit(MethodType MethodType);
    public void visit(AssignConsts AssignConsts);
    public void visit(Var Var);
    public void visit(StatementList StatementList);
    public void visit(MulopFactor MulopFactor);
    public void visit(Addop Addop);
    public void visit(MulopFactList MulopFactList);
    public void visit(MaybeExpr MaybeExpr);
    public void visit(Factor Factor);
    public void visit(MaybeBracks MaybeBracks);
    public void visit(MaybeActPars MaybeActPars);
    public void visit(CondTerm CondTerm);
    public void visit(VarList VarList);
    public void visit(MaybeCommaConst MaybeCommaConst);
    public void visit(DeclList DeclList);
    public void visit(Designator Designator);
    public void visit(MaybeFormPars MaybeFormPars);
    public void visit(Condition Condition);
    public void visit(ListCondTerm ListCondTerm);
    public void visit(AssConstsList AssConstsList);
    public void visit(MaybeCondition MaybeCondition);
    public void visit(Stats Stats);
    public void visit(MaybeDesignatorStatement MaybeDesignatorStatement);
    public void visit(VarDeclList VarDeclList);
    public void visit(DesignatorArray DesignatorArray);
    public void visit(ActPars ActPars);
    public void visit(DesignatorStatement DesignatorStatement);
    public void visit(Decl Decl);
    public void visit(Statement Statement);
    public void visit(VarDecl VarDecl);
    public void visit(MaybeElse MaybeElse);
    public void visit(CondFact CondFact);
    public void visit(MaybeActParsWithParens MaybeActParsWithParens);
    public void visit(MethodDeclList MethodDeclList);
    public void visit(SubOption SubOption);
    public void visit(MaybeExprWithBrackets MaybeExprWithBrackets);
    public void visit(MaybeStatement MaybeStatement);
    public void visit(Consts Consts);
    public void visit(FormPars FormPars);
    public void visit(AddopTermList AddopTermList);
    public void visit(Sub Sub);
    public void visit(Add Add);
    public void visit(Div Div);
    public void visit(Mod Mod);
    public void visit(Mul Mul);
    public void visit(RelationGreaterEqual RelationGreaterEqual);
    public void visit(RelationLessequal RelationLessequal);
    public void visit(RelationGreater RelationGreater);
    public void visit(RelationLess RelationLess);
    public void visit(RelationNotequal RelationNotequal);
    public void visit(RelationEqual RelationEqual);
    public void visit(Assign Assign);
    public void visit(OneActual OneActual);
    public void visit(Actuals Actuals);
    public void visit(MaybeExprWithBracketsDerived2 MaybeExprWithBracketsDerived2);
    public void visit(MaybeExprWithBracketsDerived1 MaybeExprWithBracketsDerived1);
    public void visit(NoActPars NoActPars);
    public void visit(YesActPars YesActPars);
    public void visit(FactorDec FactorDec);
    public void visit(FactorInc FactorInc);
    public void visit(FactorExpr FactorExpr);
    public void visit(FactorNew FactorNew);
    public void visit(FactorVar FactorVar);
    public void visit(FactorBool FactorBool);
    public void visit(FactorChar FactorChar);
    public void visit(FactorNum FactorNum);
    public void visit(MulopFact MulopFact);
    public void visit(WithoutMulopFactList WithoutMulopFactList);
    public void visit(WithMulopFactList WithMulopFactList);
    public void visit(Term Term);
    public void visit(AddopTerm AddopTerm);
    public void visit(AddopTermListDerived2 AddopTermListDerived2);
    public void visit(AddopTermListDerived1 AddopTermListDerived1);
    public void visit(SubOptionNo SubOptionNo);
    public void visit(SubOptionYes SubOptionYes);
    public void visit(Expr Expr);
    public void visit(CondF CondF);
    public void visit(Cond Cond);
    public void visit(WithoutActPars WithoutActPars);
    public void visit(WithActPars WithActPars);
    public void visit(NameOfArray NameOfArray);
    public void visit(VarRef VarRef);
    public void visit(ArrayDesignator ArrayDesignator);
    public void visit(MemberOfClass MemberOfClass);
    public void visit(WhitoutExpr WhitoutExpr);
    public void visit(WithExpr WithExpr);
    public void visit(DesignatorStatementDec DesignatorStatementDec);
    public void visit(DesignatorStatementInc DesignatorStatementInc);
    public void visit(DesignatorStatementActPars DesignatorStatementActPars);
    public void visit(ErrorStatementAssign ErrorStatementAssign);
    public void visit(DesignatorStatementAssign DesignatorStatementAssign);
    public void visit(WithoutCommaConst WithoutCommaConst);
    public void visit(WithCommaConst WithCommaConst);
    public void visit(StatementStatementList StatementStatementList);
    public void visit(StatementReturn StatementReturn);
    public void visit(StatementPrint StatementPrint);
    public void visit(StatementRead StatementRead);
    public void visit(StatementContinue StatementContinue);
    public void visit(StatementBreak StatementBreak);
    public void visit(StatementElseIf StatementElseIf);
    public void visit(StatementDesignatorStatement StatementDesignatorStatement);
    public void visit(NoStmt NoStmt);
    public void visit(Stmt Stmt);
    public void visit(FormalParamDecl FormalParamDecl);
    public void visit(MoreFormalParamDecls MoreFormalParamDecls);
    public void visit(OneFormalParamDecl OneFormalParamDecl);
    public void visit(WithoutFormPars WithoutFormPars);
    public void visit(WithFormPars WithFormPars);
    public void visit(VoidMethodType VoidMethodType);
    public void visit(TypeMethodType TypeMethodType);
    public void visit(MethodTypeName MethodTypeName);
    public void visit(MethodDecl MethodDecl);
    public void visit(NoMethodDecl NoMethodDecl);
    public void visit(MethodDeclarations MethodDeclarations);
    public void visit(Varr Varr);
    public void visit(NoVarList NoVarList);
    public void visit(VarLst VarLst);
    public void visit(WithoutBracks WithoutBracks);
    public void visit(WithBracks WithBracks);
    public void visit(ErrorLastVarDecl ErrorLastVarDecl);
    public void visit(VarDeclVD VarDeclVD);
    public void visit(NoVarDeclList NoVarDeclList);
    public void visit(VarDeclLst VarDeclLst);
    public void visit(AssConst AssConst);
    public void visit(AssConstsListDerived2 AssConstsListDerived2);
    public void visit(AssConstsListDerived1 AssConstsListDerived1);
    public void visit(ConstBool ConstBool);
    public void visit(ConstChar ConstChar);
    public void visit(ConstNumber ConstNumber);
    public void visit(Type Type);
    public void visit(ConstDecl ConstDecl);
    public void visit(ConstD ConstD);
    public void visit(VarD VarD);
    public void visit(NoDeclLst NoDeclLst);
    public void visit(DeclLst DeclLst);
    public void visit(ProgName ProgName);
    public void visit(Program Program);

}
