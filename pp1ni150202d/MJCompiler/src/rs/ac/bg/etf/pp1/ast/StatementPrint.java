// generated with ast extension for cup
// version 0.8
// 13/0/2020 0:3:28


package rs.ac.bg.etf.pp1.ast;

public class StatementPrint extends Statement {

    private Expr Expr;
    private MaybeCommaConst MaybeCommaConst;

    public StatementPrint (Expr Expr, MaybeCommaConst MaybeCommaConst) {
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
        this.MaybeCommaConst=MaybeCommaConst;
        if(MaybeCommaConst!=null) MaybeCommaConst.setParent(this);
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public MaybeCommaConst getMaybeCommaConst() {
        return MaybeCommaConst;
    }

    public void setMaybeCommaConst(MaybeCommaConst MaybeCommaConst) {
        this.MaybeCommaConst=MaybeCommaConst;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Expr!=null) Expr.accept(visitor);
        if(MaybeCommaConst!=null) MaybeCommaConst.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
        if(MaybeCommaConst!=null) MaybeCommaConst.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        if(MaybeCommaConst!=null) MaybeCommaConst.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("StatementPrint(\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MaybeCommaConst!=null)
            buffer.append(MaybeCommaConst.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [StatementPrint]");
        return buffer.toString();
    }
}
