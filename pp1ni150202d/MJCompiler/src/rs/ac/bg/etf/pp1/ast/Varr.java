// generated with ast extension for cup
// version 0.8
// 13/0/2020 0:3:28


package rs.ac.bg.etf.pp1.ast;

public class Varr extends Var {

    private String varName;
    private MaybeBracks MaybeBracks;

    public Varr (String varName, MaybeBracks MaybeBracks) {
        this.varName=varName;
        this.MaybeBracks=MaybeBracks;
        if(MaybeBracks!=null) MaybeBracks.setParent(this);
    }

    public String getVarName() {
        return varName;
    }

    public void setVarName(String varName) {
        this.varName=varName;
    }

    public MaybeBracks getMaybeBracks() {
        return MaybeBracks;
    }

    public void setMaybeBracks(MaybeBracks MaybeBracks) {
        this.MaybeBracks=MaybeBracks;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(MaybeBracks!=null) MaybeBracks.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MaybeBracks!=null) MaybeBracks.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MaybeBracks!=null) MaybeBracks.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Varr(\n");

        buffer.append(" "+tab+varName);
        buffer.append("\n");

        if(MaybeBracks!=null)
            buffer.append(MaybeBracks.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Varr]");
        return buffer.toString();
    }
}
