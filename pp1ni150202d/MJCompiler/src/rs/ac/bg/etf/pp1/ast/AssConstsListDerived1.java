// generated with ast extension for cup
// version 0.8
// 13/0/2020 0:3:28


package rs.ac.bg.etf.pp1.ast;

public class AssConstsListDerived1 extends AssConstsList {

    private AssConstsList AssConstsList;
    private AssignConsts AssignConsts;

    public AssConstsListDerived1 (AssConstsList AssConstsList, AssignConsts AssignConsts) {
        this.AssConstsList=AssConstsList;
        if(AssConstsList!=null) AssConstsList.setParent(this);
        this.AssignConsts=AssignConsts;
        if(AssignConsts!=null) AssignConsts.setParent(this);
    }

    public AssConstsList getAssConstsList() {
        return AssConstsList;
    }

    public void setAssConstsList(AssConstsList AssConstsList) {
        this.AssConstsList=AssConstsList;
    }

    public AssignConsts getAssignConsts() {
        return AssignConsts;
    }

    public void setAssignConsts(AssignConsts AssignConsts) {
        this.AssignConsts=AssignConsts;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(AssConstsList!=null) AssConstsList.accept(visitor);
        if(AssignConsts!=null) AssignConsts.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(AssConstsList!=null) AssConstsList.traverseTopDown(visitor);
        if(AssignConsts!=null) AssignConsts.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(AssConstsList!=null) AssConstsList.traverseBottomUp(visitor);
        if(AssignConsts!=null) AssignConsts.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("AssConstsListDerived1(\n");

        if(AssConstsList!=null)
            buffer.append(AssConstsList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(AssignConsts!=null)
            buffer.append(AssignConsts.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [AssConstsListDerived1]");
        return buffer.toString();
    }
}
