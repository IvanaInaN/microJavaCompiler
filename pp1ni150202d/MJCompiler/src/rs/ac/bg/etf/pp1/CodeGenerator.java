package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.CounterVisitor.FormParamCounter;
import rs.ac.bg.etf.pp1.CounterVisitor.VarCounter;
import rs.ac.bg.etf.pp1.ast.Add;
import rs.ac.bg.etf.pp1.ast.AddopTerm;
import rs.ac.bg.etf.pp1.ast.ArrayDesignator;
import rs.ac.bg.etf.pp1.ast.Designator;
import rs.ac.bg.etf.pp1.ast.DesignatorStatementActPars;
import rs.ac.bg.etf.pp1.ast.DesignatorStatementAssign;
import rs.ac.bg.etf.pp1.ast.DesignatorStatementDec;
import rs.ac.bg.etf.pp1.ast.DesignatorStatementInc;
import rs.ac.bg.etf.pp1.ast.Div;
import rs.ac.bg.etf.pp1.ast.Factor;
import rs.ac.bg.etf.pp1.ast.FactorBool;
import rs.ac.bg.etf.pp1.ast.FactorChar;
import rs.ac.bg.etf.pp1.ast.FactorDec;
import rs.ac.bg.etf.pp1.ast.FactorInc;
import rs.ac.bg.etf.pp1.ast.FactorNew;
import rs.ac.bg.etf.pp1.ast.FactorNum;
import rs.ac.bg.etf.pp1.ast.FactorVar;
import rs.ac.bg.etf.pp1.ast.FormalParamDecl;
import rs.ac.bg.etf.pp1.ast.MethodDecl;
import rs.ac.bg.etf.pp1.ast.MethodTypeName;
import rs.ac.bg.etf.pp1.ast.Mod;
import rs.ac.bg.etf.pp1.ast.Mul;
import rs.ac.bg.etf.pp1.ast.MulopFact;
import rs.ac.bg.etf.pp1.ast.MulopFactor;
import rs.ac.bg.etf.pp1.ast.NameOfArray;
import rs.ac.bg.etf.pp1.ast.ProgName;
import rs.ac.bg.etf.pp1.ast.Program;
import rs.ac.bg.etf.pp1.ast.StatementPrint;
import rs.ac.bg.etf.pp1.ast.StatementRead;
import rs.ac.bg.etf.pp1.ast.StatementReturn;
import rs.ac.bg.etf.pp1.ast.Sub;
import rs.ac.bg.etf.pp1.ast.SyntaxNode;
import rs.ac.bg.etf.pp1.ast.VarDecl;
import rs.ac.bg.etf.pp1.ast.VarRef;
import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;
import rs.ac.bg.etf.pp1.ast.YesActPars;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class CodeGenerator extends VisitorAdaptor {
	//poslednjaverzija
	private int varCount;
	
	private int paramCnt;
	
	private int mainPc;
	boolean inMain = false;
	private int size=0;
	int lastIndex= 0;
	
	public int getMainPc() {
		return mainPc;
	}
	
	@Override
	public void visit(MethodTypeName MethodTypeName) {
		if ("main".equalsIgnoreCase(MethodTypeName.getMethName())) {
			mainPc = Code.pc;
			inMain = true;
		}
		MethodTypeName.obj.setAdr(Code.pc);
		
		// Collect arguments and local variables.
		SyntaxNode methodNode = MethodTypeName.getParent();
		VarCounter varCnt = new VarCounter();
		methodNode.traverseTopDown(varCnt);
		FormParamCounter fpCnt = new FormParamCounter();
		methodNode.traverseTopDown(fpCnt);
		
		// Generate the entry.
		Code.put(Code.enter);
//		Code.put(o.getLevel());
//		Code.put(o.getLocalSymbols().size());
		Code.put(fpCnt.getCount());//moze put 0 jer radimo na nivou a bez parametara
		size=MethodTypeName.obj.getLocalSymbols().size();
		Code.put(size);
	}
	
	//////////////////////////////////////////////// one tri metode ////////////////////////////////////
	
//	public void chr(Obj c){
//		c.setAdr(Code.pc);
//		Code.put(Code.return_);
//	}
//	
//	public void ord(Obj o){
//		o.setAdr(Code.pc);
//		Code.put(Code.return_);
//	}
//	
//	public void len(Obj le) {
//		le.setAdr(Code.pc);
//		Code.put(Code.enter);
//		Code.put(1);
//		Code.put(1);
//		Code.put(Code.load_n);
//		Code.loadConst(1);
//		Code.put(Code.sub);
//		Code.put(Code.getfield);
//		Code.put2(0);
//		Code.put(Code.exit);
//		Code.put(Code.return_);
//	}
//	
//	public void visit(ProgName p){
//		Obj ch = Tab.find("chr")	;
//		Obj le= Tab.find("len");
//		Obj or = Tab.find("ord");
//		chr(ch);
//		ord(or);
//		len(le);
//	
//	}
	
	
	
	/////////////////////////////////////////////  deklaracija metode /////////////////////////////////////////
	
	@Override
	public void visit(MethodDecl MethodDecl) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	
	
	public void visit(ArrayDesignator arrDes){  
	
		if(arrDes.getParent() instanceof DesignatorStatementActPars || arrDes.getParent() instanceof Factor)	{
			if(arrDes.obj.getType().getElemType().getKind()==Struct.Char){
				//za char
				Code.put(Code.baload);
			}
			else{
				Code.put(Code.aload);
			}
		}
	}
	
	@Override
	public void visit(VarDecl VarDecl) {
		if(!inMain)varCount++;
	}

	@Override
	public void visit(FormalParamDecl FormalParam) {
		paramCnt++;
	}
	
		public void visit(DesignatorStatementAssign assign){ 
		
		Obj object=(assign.getDesignator()).obj;
		if(assign.getDesignator() instanceof ArrayDesignator){
			if((object.getType().getElemType()).equals(Tab.charType)){
				Code.put(Code.bastore);
			}else {
				Code.put(Code.astore);
			}
		}
		else{
			Code.store(object);
			}
			//Code.store(assign.getDesignator().obj);
		}
	
		
	@Override
	public void visit(StatementReturn ReturnStatement) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	public void visit(MulopFact mulFactor){
		if(mulFactor.getMulop() instanceof Mul){
			Code.put(Code.mul);
		}
		if(mulFactor.getMulop() instanceof Div){
			Code.put(Code.div);
		}
		if(mulFactor.getMulop() instanceof Mod){
			Code.put(Code.rem);
		}
	}
	
	public void visit(FactorNew factorNew) {
		Code.put(Code.newarray);
		if(factorNew.getType().struct == Tab.charType) {
			Code.put(0);
		} else if(factorNew.getType().struct == Tab.charType) {
			Code.put(0);
		} else {
			Code.put(1);
		}
	}
	
	public void visit(FactorNum factorNum){
		
		Obj numConst = new Obj(Obj.Con, "$", Tab.intType);
		numConst.setLevel(0);
		numConst.setAdr(factorNum.getN1());
		Code.load(numConst);
	}
	
	public void visit(FactorChar factorChar){
		
		Obj charConst=new Obj(Obj.Con,"$",Tab.charType);
		charConst.setLevel(0);
		charConst.setAdr(factorChar.getC1());
		Code.load(charConst);
	}
	
	
	public void visit(FactorBool factorBool){
		
		Obj boolConst = new Obj(Obj.Con, "$", SemanticAnalyzer.boolType);
		boolConst.setLevel(0);
		boolConst.setAdr(factorBool.getB1().equalsIgnoreCase("True")?1:0);
		Code.load(boolConst);
	}
	
	@Override
	public void visit(StatementPrint printStm){
		// ispis
		Struct type=printStm.getExpr().struct;
		if(type==Tab.charType){
			Code.loadConst(1);
			Code.put(Code.bprint);
		}else{
			Code.loadConst(4);
			Code.put(Code.print);
		}
		
	}
	
	public void visit(NameOfArray name){
		ArrayDesignator arrDesignator=(ArrayDesignator)(name.getParent());
		Code.load((arrDesignator).obj);
	}
	
	public void visit(VarRef v){
		
		if(v.obj.getKind()!=Obj.Meth){
			if(!(v.getParent() instanceof DesignatorStatementAssign)){
				Code.load(v.obj);
			}
		}
	}
	
	public void visit(StatementRead read){
		if(!(read.getDesignator() instanceof ArrayDesignator)){
			if(read.getDesignator().obj.getType()==Tab.charType){
				Code.put(Code.bread);
		}else{
				Code.put(Code.read); 
			}
		}else{
			if(read.getDesignator().obj.getType().getElemType().getKind()==Struct.Char){
				Code.put(Code.bread);
			}else{
				Code.put(Code.read);
			}
	
		}
		
		if(read.getDesignator() instanceof ArrayDesignator){
			if(read.getDesignator().obj.getType().getElemType().getKind()!=Struct.Char){
				Code.put(Code.astore);
			}else{
				Code.put(Code.bastore);	
			}
		}
		else{
			Obj object =read.getDesignator().obj;
			Code.store(object);
		}
	}
	
///////////////////////////////////////dekrementiranje i inkrementiranje
	
	public void visit(DesignatorStatementInc inc){
		// inkrementiranje
		Obj object=new Obj(Obj.Con, "", Tab.intType);
		object.setAdr(1);
		if(!(inc.getDesignator() instanceof ArrayDesignator)){
			// ako se ne radi o nizu
			Code.load(object);
			Code.put(Code.add);
			Code.store(inc.getDesignator().obj);
		}
		else{
			// ako jeste niz
			Code.put(Code.dup2);
			Code.put(Code.aload); 
			Code.load(object);
			Code.put(Code.add);
			Code.put(Code.astore);
		}
		
	}
	
	public void visit(FactorInc inc){
		Obj object=new Obj(Obj.Con, "", Tab.intType);
		object.setAdr(1);
		if(!(inc.getDesignator() instanceof ArrayDesignator)){
			// ako se ne radi o nizu
			System.out.print(inc.getDesignator().toString());
			Code.load(object);
			Code.load(inc.getDesignator().obj);
			Code.put(Code.add);
			Code.store(inc.getDesignator().obj);
		}
		else{
			// ako jeste niz
		
		
			//adresa, index, val
			//dodaj na stek adresu
			//dodaj na stek indeks
			//dodaj na stek vrednost
			inc.getDesignator().obj.getAdr();
			Code.put(Code.dup);
			Code.load(object);
			Code.put(Code.add);
			//Code.put(Code.astore);
			//Code.store(inc.getDesignator().obj);
		}
	}
	
	public void visit(FactorDec inc){
		Obj object=new Obj(Obj.Con, "", Tab.intType);
		object.setAdr(1);
		if(!(inc.getDesignator() instanceof ArrayDesignator)){
			// ako se ne radi o nizu
			System.out.print(inc.getDesignator().toString());
			Code.load(object);
			Code.load(inc.getDesignator().obj);
			Code.put(Code.add);
			Code.store(inc.getDesignator().obj);
		}
		else{
			// ako jeste niz
		
		
			//adresa, index, val
			//dodaj na stek adresu
			//dodaj na stek indeks
			//dodaj na stek vrednost
			inc.getDesignator().obj.getAdr();
			Code.put(Code.dup);
			Code.load(object);
			Code.put(Code.sub);
			//Code.put(Code.astore);
			//Code.store(inc.getDesignator().obj);
		}
	}
	
	public void visit(DesignatorStatementDec dec){
		
		Obj object=new Obj(Obj.Con, "", Tab.intType);
		object.setAdr(1);
		if(!(dec.getDesignator() instanceof ArrayDesignator)){
			Code.load(object);
			Code.put(Code.sub);
			Code.store(dec.getDesignator().obj);
		}else{
			Code.put(Code.dup2);
			// prvi sabirak
			Code.put(Code.aload); 
			Code.load(object);
			Code.put(Code.sub);
			Code.put(Code.astore);
		}
		
	}
	

public void visit(DesignatorStatementActPars funcCall){  //poziv procedure
	
	Obj object=funcCall.getDesignator().obj;
	// radi se o pozivu funkcije
	Code.put(Code.call);
	// izracunaj adresu na koju se skace
	Code.put2(object.getAdr()-Code.pc+1);  
	if(!object.getType().equals(Tab.noType)){
//	//ako nema povratnu vrednost, treba je skinuti sa steka
//		//while(size>=0){
		Code.put(Code.pop); 
//		//size--; }   
	}
	
}
	
////////////////////////////////////////// sabiranje/oduzimanje ////////////////////
	@Override
	public void visit(AddopTerm AddExpr) {
		if(AddExpr.getAddop() instanceof Sub){
			//radi se o oduzimanju
			Code.put(Code.sub);
		}else{
			//radi se o sabiranju
			Code.put(Code.add);
		}
	}
}
