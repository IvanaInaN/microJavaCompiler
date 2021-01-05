package rs.ac.bg.etf.pp1;

import java.util.Stack;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.AddopTerm;
import rs.ac.bg.etf.pp1.ast.ArrayDesignator;
import rs.ac.bg.etf.pp1.ast.AssConst;
import rs.ac.bg.etf.pp1.ast.ConstBool;
import rs.ac.bg.etf.pp1.ast.ConstChar;
import rs.ac.bg.etf.pp1.ast.ConstDecl;
import rs.ac.bg.etf.pp1.ast.ConstNumber;
import rs.ac.bg.etf.pp1.ast.Consts;
import rs.ac.bg.etf.pp1.ast.DesignatorStatementAssign;
import rs.ac.bg.etf.pp1.ast.DesignatorStatementDec;
import rs.ac.bg.etf.pp1.ast.DesignatorStatementInc;
import rs.ac.bg.etf.pp1.ast.Expr;
import rs.ac.bg.etf.pp1.ast.FactorBool;
import rs.ac.bg.etf.pp1.ast.FactorChar;
import rs.ac.bg.etf.pp1.ast.FactorDec;
import rs.ac.bg.etf.pp1.ast.FactorExpr;
import rs.ac.bg.etf.pp1.ast.FactorInc;
import rs.ac.bg.etf.pp1.ast.FactorNew;
import rs.ac.bg.etf.pp1.ast.FactorNum;
import rs.ac.bg.etf.pp1.ast.FactorVar;
import rs.ac.bg.etf.pp1.ast.FormalParamDecl;
import rs.ac.bg.etf.pp1.ast.MaybeBracks;
import rs.ac.bg.etf.pp1.ast.MethodDecl;
import rs.ac.bg.etf.pp1.ast.MethodTypeName;
import rs.ac.bg.etf.pp1.ast.MulopFactor;
import rs.ac.bg.etf.pp1.ast.ProgName;
import rs.ac.bg.etf.pp1.ast.Program;
import rs.ac.bg.etf.pp1.ast.StatementPrint;
import rs.ac.bg.etf.pp1.ast.StatementReturn;
import rs.ac.bg.etf.pp1.ast.SubOptionYes;
import rs.ac.bg.etf.pp1.ast.SyntaxNode;

import rs.ac.bg.etf.pp1.ast.Type;
import rs.ac.bg.etf.pp1.ast.Var;
import rs.ac.bg.etf.pp1.ast.VarDeclVD;
import rs.ac.bg.etf.pp1.ast.VarRef;
import rs.ac.bg.etf.pp1.ast.Varr;
import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;
import rs.ac.bg.etf.pp1.ast.VoidMethodType;
import rs.ac.bg.etf.pp1.ast.WithBracks;
import rs.ac.bg.etf.pp1.ast.WithExpr;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class SemanticAnalyzer extends VisitorAdaptor {
	
	int nVars;
	boolean errorDetected = false;
	
	int printCallCount = 0;
	boolean returnFound = false;
	Struct currentType =null;
	
	boolean OkType =true;
	int last = -13;
	Obj inMethod = null;
	
	public Stack<Struct> tempStack=new Stack<Struct>();
	public static Struct boolType = new Struct(Struct.Bool);
	
	Logger log = Logger.getLogger(getClass());

	public void report_error(String message, SyntaxNode info) {
		errorDetected = true;
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.error(msg.toString());
	}

	public void report_info(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message); 
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.info(msg.toString());
	}
///////////////////////////////////////////////////////deklarisanje programa /////////////////
	public void visit(Program program) {		
		nVars = Tab.currentScope.getnVars();
		Tab.chainLocalSymbols(program.getProgName().obj);
		Tab.closeScope();
	}
	

	public void visit(ProgName progName) {
		progName.obj = Tab.insert(Obj.Prog, progName.getPName(), Tab.noType);
		Tab.openScope();     	
	}
	
///////////////////////////////////////////////////// deklarisanje tipa ////////////////
	
	public void visit(Type type){
		String typeName= type.getTypeName();
		Obj myType=Tab.find(typeName);
		if(myType != Tab.noObj){
			if( myType.getKind()==Obj.Type ){
				type.struct = myType.getType();
				currentType = type.struct; 
			}else{
				report_error("Greska: "+typeName+"nije tip!",type);
				currentType= Tab.noType;
				type.struct = Tab.noType;
			}
		}else{
			report_error("Tip " + typeName + "ne postoji u tabeli simbola", null);
			type.struct = Tab.noType;
		}
	}
	
	public void visit(VoidMethodType voidType){
		currentType = Tab.noType;
	}
	
/////////////////////////////////////////////// deklarisanje promenljive ///////////////
	
	public void visit(VarDeclVD vd){
		Struct typeOfVar = vd.getType().struct;
		String nameOfVar = vd.getVarName();
		// proveravamo da li je promenljiva tipa niz
		if(vd.getMaybeBracks() instanceof WithBracks){
			if(typeOfVar != Tab.noType){
				// pravimo novi tip , niz
				typeOfVar= new Struct(Struct.Array, typeOfVar);
			}
		}
	
		if(Tab.currentScope.findSymbol(nameOfVar)==null){// onda ga dodajemo 
			if(typeOfVar != Tab.noType){
				Tab.insert(Obj.Var, nameOfVar,typeOfVar);
				if(typeOfVar.getKind()==Struct.Array){
					report_info("Deklarisan niz "+nameOfVar, vd);
				}else{
					String nameOfType =vd.getType().getTypeName();
					report_info("Dodata promenljiva "+nameOfVar+" tipa "+nameOfType+" u tabelu simbola!", vd);
					}
			}
		}else{// onda je greska
			report_error("Greska: " + vd.getVarName()+ " je vec deklarisan/o!", vd);
		}
	}
	
	public void visit(Varr var){
		
		Struct typeOfVar = currentType;
		// proveravamo da li je promenljiva tipa niz
		if(var.getMaybeBracks() instanceof WithBracks){
			if(currentType != Tab.noType){
				// pravimo novi tip , niz
				typeOfVar= new Struct(Struct.Array, typeOfVar);
				//report_info("Deklarisan je niz "+var.getVarName(), var);
			}
		}
		String nameOfVar=var.getVarName();
		if(Tab.currentScope.findSymbol(nameOfVar)==null){// onda ga dodajemo 
			
			if(typeOfVar != Tab.noType){
				if(typeOfVar.getKind()==Struct.Array){
					report_info("Deklarisan niz "+nameOfVar, var);
				}else{
					report_info("Dodata promenljiva "+nameOfVar+" u tabelu simbola!", var);}
				Tab.insert(Obj.Var, nameOfVar,typeOfVar);
			}
		}else{// onda je greska multiple def
			report_error("Greska: " + nameOfVar+ " je vec deklarisan/o/a!", var);
		}
	}
//////////////////////////////////////////// deklaracija konstanti	/////////////////////////////
	
	public void visit(ConstDecl constD){
		Type type= constD.getType();
		String nameOfConst = constD.getConstName();
		if(Tab.find(nameOfConst) != Tab.noObj){
			report_info( nameOfConst+" vec postoji u tabeli simbola", constD );
		}else{
			if(type.struct!= Tab.noType){
			if(OkType)	{
			Tab.insert(Obj.Con, nameOfConst, type.struct);
			Tab.currentScope.findSymbol(nameOfConst).setAdr(last);
			report_info("Dodata konstanta " +  nameOfConst+" koja ima vrednost: " +last, constD );
			}
		   }
		}
	}
	
	public void visit(AssConst constD){
		if(Tab.find(constD.getConstName()) != Tab.noObj){
			report_info( constD.getConstName()+" vec postoji u tabeli simbola", constD );
		}else{
			if(currentType!= Tab.noType){
			if(OkType)	{
			Tab.insert(Obj.Con, constD.getConstName(), currentType);
			Tab.currentScope.findSymbol(constD.getConstName()).setAdr(last);
			report_info("Dodata konstanta " +  constD.getConstName()+" koja ima vrednost: " +last, constD );
			}
		   }
		}
	}
///////////////////////////////////////// konstante	////////////////////////////////
	
	public void visit(ConstNumber constNum){
		last= constNum.getN1();
		if(currentType.getKind()!= Struct.Int) OkType = false;
	}
	
	public void visit(ConstChar constC){
		last=constC.getC1();
		if(currentType.getKind()!=Struct.Char) OkType = false;
	}
	
	public void visit(ConstBool constB){
		last= constB.getB1()=="true"? 1:0;
		if(currentType.getKind()!=Struct.Bool) OkType = false;
	}
////////////////////////////// print//////////////////////////////////
	
	public void visit(StatementPrint printStmt){
		Struct s= (!tempStack.isEmpty())? tempStack.pop() : null;
		if(s!=null){
			printStmt.getExpr().struct = s;
		if(!(s.getKind()==Struct.Int || s.getKind()==Struct.Char || s.getKind()==Struct.Bool)){
			report_error("Print zahteva int,char ili bool!!!", printStmt);
		}else{
		printCallCount++;} 
		}else{
			report_info("Stek je prazan", printStmt);
		}
	}

	
////////////////////// deklarisanje metode ///////////////////////////
	
	public void visit(MethodDecl md){
		Obj metObj = Tab.find(md.getMethodTypeName().getMethName());
		int numParam =metObj.getLevel();
		Tab.chainLocalSymbols(inMethod);
		Tab.closeScope();
	}
	
	public void visit(MethodTypeName mn){
		// potrazim u tabeli simbola, ako je ima greska ako je nema dodam je u tabelu simbola
		//proverimo da li je tip korektan, da li postoji
		mn.obj = Tab.currentScope.findSymbol(mn.getMethName());
		Struct struct = currentType;
		if(mn.obj == null){
			mn.obj=Tab.insert(Obj.Meth, mn.getMethName(),struct );
			inMethod = mn.obj;
			Tab.openScope();
			report_info("Obradjuje se funkicija " + mn.getMethName() + "", mn);
		}else{
			report_error("Metoda "+mn.getMethName()+" je vec definisana", mn);
		}
		
	}
//////////////////////////////////////// array  /////////////////////////////
		
	public void visit(ArrayDesignator arrd){
		String nameOfArray=arrd.getNameOfArray().getName();
		Obj arrayObj =Tab.find(nameOfArray);
		arrd.obj=arrayObj;
		if(arrayObj == Tab.noObj){
			report_error("Niz "+nameOfArray+" nije definisan!!!", arrd);
		}else{
			if(arrayObj.getType().getKind()== Struct.Array){
				Struct sType= (!tempStack.isEmpty())? tempStack.pop() : null;
				if(sType== null) {report_info("Stek je prazan!", arrd);}
				else{
				if(sType.getKind()==Struct.Int){
					report_info("Pristupa se nizu "+ nameOfArray, arrd);
					tempStack.push(arrayObj.getType().getElemType());
				}else{
					tempStack.push(Tab.noType);
					report_error("Index niza mora biti tipa int", arrd);
				}
				}
			}else{
				tempStack.push(Tab.noType);
				report_error("Neadekvatan tip, ocekivani tip je bio niz!", arrd);
			}
			
		}
		
	}

	public void visit(FormalParamDecl fpd){ 
		Struct fpType= fpd.getType().struct;
		if(fpd.getMaybeBracks() instanceof WithBracks){
				if(fpType!=Tab.noType){
					fpType=new Struct(Struct.Array, fpType);
						report_info("Napravljen novi niz " , null );
					}
			}
		Obj p=Tab.currentScope.findSymbol(fpd.getName());
		
		if(p==null){
			if(fpType!=Tab.noType){
					Tab.insert(Obj.Var, fpd.getName(), fpType);
					report_info("Dodat simbol " +  fpd.getName()+" u tabelu simbola " , fpd );}
			}else {
			report_info("Greska, simbol " + fpd.getName()+ " vec postoji,ne moze se vise puta dodati", fpd);
			}
	}
/////////////////////////////////////////////// factor konstanta ///////////////////////////////////
	
	public void visit(FactorNum factorNumConst){
		tempStack.push(Tab.intType);	
	}
	
	public void visit(FactorChar factorCharConst){
		tempStack.push(Tab.charType);
	}
	
	
	public void visit(FactorBool factorBoolConst){
		tempStack.push(boolType);
	}
////////////////////////////////////////////// new /////////////////////////////////////////////

	public void visit(FactorNew faktorNew){
		Struct type =(!tempStack.isEmpty())? tempStack.pop() : null ;
		Struct s = null;
		if(type != null){
		if(!type.equals(Tab.intType)){
			report_info("Greska sa nizom ", faktorNew);
			s= Tab.noType;
		}else{
			s= new Struct(Struct.Array,currentType);
		}
			tempStack.push(s);
		}
		
	}
////////////////////////////////////////////////varRef//////////////////////////////////////////
	
	public void visit(VarRef varRef){
		Obj o=Tab.find(varRef.getNameOfVar());
		varRef.obj=o;
		if(o.equals(Tab.noObj)){
			report_error("ne postoji promjenljiva "+varRef.getNameOfVar(), varRef);
		}
		else{
			if((o.getKind()!=Obj.Type && o.getKind()!=Obj.Prog)   ){
					tempStack.push(o.getType());
//					hash.put(vr, stek.size());
//					vr.setI(stek.size());       
			}else{
				tempStack.push(Tab.noType);
				report_error("Greska u radu sa nizom", varRef);
			}
		}	
	}
	

	
	
public void visit(DesignatorStatementInc a){
		
		if(a.getDesignator() instanceof VarRef){
//			if(a.getDesignator().obj.getType()!=Tab.intType){
//				report_error("Greska, ++ mora da se radi nad promjenljivom tipa INT",null);
//			}
		}
		else if(a.getDesignator() instanceof ArrayDesignator){
			report_info("Info "+a.getDesignator().toString(), null);
//			if(a.getDesignator().obj.getType().getElemType().getKind()!=Struct.Int){
//				report_error("Greska, ++ mora da se radi nad promjenljivom tipa INT",null);
//			}
			
		}
		else{
			report_error("Greska",null);
		}
	}
	
public void visit(DesignatorStatementAssign dsa){
	report_info("dsa", dsa);
}
	
public void visit(DesignatorStatementDec a){
		
		if(a.getDesignator() instanceof VarRef){
//			if(a.getDesignator().obj.getType()!=Tab.intType){
//				report_error("Greska, -- mora da se radi nad promjenljivom tipa INT",null);
//			}
		}
		else if(a.getDesignator() instanceof ArrayDesignator){
//			if(a.getDesignator().obj.getType().getElemType().getKind()!=Struct.Int){
//				report_error("Greska, -- mora da se radi nad promjenljivom tipa INT",null);
//			}
			
		}
		else{
			report_error("Greska, -- mora sa int ili sa array-om",null);
		}
	}
	
	public void visit(Expr e){		
		if(e.getMaybeMinus() instanceof SubOptionYes){
			
			Struct ty=(!tempStack.isEmpty()) ? tempStack.pop() : null;
			if(ty!=null){
			if(ty.getKind()==Struct.Class){
				ty=Tab.intType;
			}
			if(ty.equals(Tab.intType)){
				tempStack.push(ty);
			}
			else{
				report_error("Greska nova, null",null);
				tempStack.push(Tab.noType);
			}}
		}	
	}
	
/////////////////////////////////////////// return statement ///////////////////////////////////
	
	public void visit(StatementReturn statementReturn){
		
		Struct tipReturn = (statementReturn.getMaybeExpr() instanceof WithExpr) ?
				((!tempStack.isEmpty()) ? tempStack.pop() : null) : Tab.noType;
		if(tipReturn != null){
		if(!tipReturn.compatibleWith(inMethod.getType())){
			report_error("Greska, povratni tip funkcije nije ok" ,statementReturn);
		}}		
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////


	public void visit(AddopTerm addExpr){// proveri ovo jos malo 
		
		Struct op1 =(!tempStack.isEmpty())? tempStack.pop() : null ;
		Struct op2 =(!tempStack.isEmpty())? tempStack.pop() : null ;
		report_info("prvi "+op1.getKind(),addExpr);
		report_info("drugi "+op2.getKind(),addExpr);
		if(op1!=null && op2!=null){
			Struct s=null;
			if(op1.getKind()==Struct.Int && op2.getKind()==Struct.Int){
				op1=Tab.intType;
				op2=Tab.intType;
				s=Tab.intType;
			}else{
				s= Tab.noType;
			}
			report_info("addExpr "+s.getKind(), addExpr);
			tempStack.push(s);
		}
		
	}
	
	public void  visit(FactorVar fv){
		report_info("faktor var ", fv);
	}
	
	public void visit(FactorExpr fe){
		report_info("faktor expr ", fe);
		
	}
	
	public void visit(FactorInc inc){
		report_info("Deklarisan INC ",inc);
	}
	
	public void visit(FactorDec dec){
		report_info("Deklarisan DEC ",dec);
	}
	
	public void visit(MulopFactor mulopFactor){
		Struct op1 =(!tempStack.isEmpty())? tempStack.pop() : null ;
		Struct op2 =(!tempStack.isEmpty())? tempStack.pop() : null ;
		if(op1!=null && op2!=null){
			Struct s=null;
			if(op1.getKind()==Struct.Class && op2.getKind()==Struct.Class){
				op1=Tab.intType;
				op2=Tab.intType;
				s=Tab.intType;
				
			}else{
				s= Tab.noType;
				report_error("Operacije mul, div i mod zahtevaju operande tipa int!", mulopFactor);
			}
			tempStack.push(s);	}
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////
		
	
	public boolean passed() {
		return !errorDetected;
	}
	
}
