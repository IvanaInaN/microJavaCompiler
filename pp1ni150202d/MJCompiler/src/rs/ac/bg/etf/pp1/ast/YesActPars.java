// generated with ast extension for cup
// version 0.8
// 13/0/2020 0:3:28


package rs.ac.bg.etf.pp1.ast;

public class YesActPars extends MaybeActParsWithParens {

    private MaybeActPars MaybeActPars;

    public YesActPars (MaybeActPars MaybeActPars) {
        this.MaybeActPars=MaybeActPars;
        if(MaybeActPars!=null) MaybeActPars.setParent(this);
    }

    public MaybeActPars getMaybeActPars() {
        return MaybeActPars;
    }

    public void setMaybeActPars(MaybeActPars MaybeActPars) {
        this.MaybeActPars=MaybeActPars;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(MaybeActPars!=null) MaybeActPars.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MaybeActPars!=null) MaybeActPars.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MaybeActPars!=null) MaybeActPars.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("YesActPars(\n");

        if(MaybeActPars!=null)
            buffer.append(MaybeActPars.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [YesActPars]");
        return buffer.toString();
    }
}
