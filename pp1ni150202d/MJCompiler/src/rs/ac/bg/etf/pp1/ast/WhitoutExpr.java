// generated with ast extension for cup
// version 0.8
// 13/0/2020 0:3:28


package rs.ac.bg.etf.pp1.ast;

public class WhitoutExpr extends MaybeExpr {

    public WhitoutExpr () {
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
        buffer.append("WhitoutExpr(\n");

        buffer.append(tab);
        buffer.append(") [WhitoutExpr]");
        return buffer.toString();
    }
}