// generated with ast extension for cup
// version 0.8
// 13/0/2020 0:3:28


package rs.ac.bg.etf.pp1.ast;

public class VarRef extends Designator {

    private String nameOfVar;

    public VarRef (String nameOfVar) {
        this.nameOfVar=nameOfVar;
    }

    public String getNameOfVar() {
        return nameOfVar;
    }

    public void setNameOfVar(String nameOfVar) {
        this.nameOfVar=nameOfVar;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarRef(\n");

        buffer.append(" "+tab+nameOfVar);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarRef]");
        return buffer.toString();
    }
}
