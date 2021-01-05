// generated with ast extension for cup
// version 0.8
// 13/0/2020 0:3:28


package rs.ac.bg.etf.pp1.ast;

public class WithMulopFactList extends MulopFactList {

    private MulopFactList MulopFactList;
    private MulopFactor MulopFactor;

    public WithMulopFactList (MulopFactList MulopFactList, MulopFactor MulopFactor) {
        this.MulopFactList=MulopFactList;
        if(MulopFactList!=null) MulopFactList.setParent(this);
        this.MulopFactor=MulopFactor;
        if(MulopFactor!=null) MulopFactor.setParent(this);
    }

    public MulopFactList getMulopFactList() {
        return MulopFactList;
    }

    public void setMulopFactList(MulopFactList MulopFactList) {
        this.MulopFactList=MulopFactList;
    }

    public MulopFactor getMulopFactor() {
        return MulopFactor;
    }

    public void setMulopFactor(MulopFactor MulopFactor) {
        this.MulopFactor=MulopFactor;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(MulopFactList!=null) MulopFactList.accept(visitor);
        if(MulopFactor!=null) MulopFactor.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MulopFactList!=null) MulopFactList.traverseTopDown(visitor);
        if(MulopFactor!=null) MulopFactor.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MulopFactList!=null) MulopFactList.traverseBottomUp(visitor);
        if(MulopFactor!=null) MulopFactor.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("WithMulopFactList(\n");

        if(MulopFactList!=null)
            buffer.append(MulopFactList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MulopFactor!=null)
            buffer.append(MulopFactor.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [WithMulopFactList]");
        return buffer.toString();
    }
}
