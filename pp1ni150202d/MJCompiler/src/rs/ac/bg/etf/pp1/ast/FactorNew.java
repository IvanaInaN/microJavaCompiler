// generated with ast extension for cup
// version 0.8
// 13/0/2020 0:3:28


package rs.ac.bg.etf.pp1.ast;

public class FactorNew extends Factor {

    private Type Type;
    private MaybeExprWithBrackets MaybeExprWithBrackets;

    public FactorNew (Type Type, MaybeExprWithBrackets MaybeExprWithBrackets) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.MaybeExprWithBrackets=MaybeExprWithBrackets;
        if(MaybeExprWithBrackets!=null) MaybeExprWithBrackets.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public MaybeExprWithBrackets getMaybeExprWithBrackets() {
        return MaybeExprWithBrackets;
    }

    public void setMaybeExprWithBrackets(MaybeExprWithBrackets MaybeExprWithBrackets) {
        this.MaybeExprWithBrackets=MaybeExprWithBrackets;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Type!=null) Type.accept(visitor);
        if(MaybeExprWithBrackets!=null) MaybeExprWithBrackets.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(MaybeExprWithBrackets!=null) MaybeExprWithBrackets.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(MaybeExprWithBrackets!=null) MaybeExprWithBrackets.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FactorNew(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MaybeExprWithBrackets!=null)
            buffer.append(MaybeExprWithBrackets.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FactorNew]");
        return buffer.toString();
    }
}
