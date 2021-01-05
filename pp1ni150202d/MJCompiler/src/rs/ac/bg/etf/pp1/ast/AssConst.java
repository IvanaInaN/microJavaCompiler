// generated with ast extension for cup
// version 0.8
// 13/0/2020 0:3:28


package rs.ac.bg.etf.pp1.ast;

public class AssConst extends AssignConsts {

    private String constName;
    private Consts Consts;

    public AssConst (String constName, Consts Consts) {
        this.constName=constName;
        this.Consts=Consts;
        if(Consts!=null) Consts.setParent(this);
    }

    public String getConstName() {
        return constName;
    }

    public void setConstName(String constName) {
        this.constName=constName;
    }

    public Consts getConsts() {
        return Consts;
    }

    public void setConsts(Consts Consts) {
        this.Consts=Consts;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Consts!=null) Consts.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Consts!=null) Consts.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Consts!=null) Consts.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("AssConst(\n");

        buffer.append(" "+tab+constName);
        buffer.append("\n");

        if(Consts!=null)
            buffer.append(Consts.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [AssConst]");
        return buffer.toString();
    }
}
