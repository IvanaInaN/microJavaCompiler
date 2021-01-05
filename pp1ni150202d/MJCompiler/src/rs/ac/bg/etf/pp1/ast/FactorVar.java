// generated with ast extension for cup
// version 0.8
// 13/0/2020 0:3:28


package rs.ac.bg.etf.pp1.ast;

public class FactorVar extends Factor {

    private Designator Designator;
    private MaybeActParsWithParens MaybeActParsWithParens;

    public FactorVar (Designator Designator, MaybeActParsWithParens MaybeActParsWithParens) {
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
        this.MaybeActParsWithParens=MaybeActParsWithParens;
        if(MaybeActParsWithParens!=null) MaybeActParsWithParens.setParent(this);
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
    }

    public MaybeActParsWithParens getMaybeActParsWithParens() {
        return MaybeActParsWithParens;
    }

    public void setMaybeActParsWithParens(MaybeActParsWithParens MaybeActParsWithParens) {
        this.MaybeActParsWithParens=MaybeActParsWithParens;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Designator!=null) Designator.accept(visitor);
        if(MaybeActParsWithParens!=null) MaybeActParsWithParens.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
        if(MaybeActParsWithParens!=null) MaybeActParsWithParens.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        if(MaybeActParsWithParens!=null) MaybeActParsWithParens.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FactorVar(\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MaybeActParsWithParens!=null)
            buffer.append(MaybeActParsWithParens.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FactorVar]");
        return buffer.toString();
    }
}
