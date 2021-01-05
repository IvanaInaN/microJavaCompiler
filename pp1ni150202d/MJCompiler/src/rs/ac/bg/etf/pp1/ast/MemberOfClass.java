// generated with ast extension for cup
// version 0.8
// 13/0/2020 0:3:28


package rs.ac.bg.etf.pp1.ast;

public class MemberOfClass extends Designator {

    private String nameOfClass;
    private String nameOfElem;

    public MemberOfClass (String nameOfClass, String nameOfElem) {
        this.nameOfClass=nameOfClass;
        this.nameOfElem=nameOfElem;
    }

    public String getNameOfClass() {
        return nameOfClass;
    }

    public void setNameOfClass(String nameOfClass) {
        this.nameOfClass=nameOfClass;
    }

    public String getNameOfElem() {
        return nameOfElem;
    }

    public void setNameOfElem(String nameOfElem) {
        this.nameOfElem=nameOfElem;
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
        buffer.append("MemberOfClass(\n");

        buffer.append(" "+tab+nameOfClass);
        buffer.append("\n");

        buffer.append(" "+tab+nameOfElem);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MemberOfClass]");
        return buffer.toString();
    }
}
