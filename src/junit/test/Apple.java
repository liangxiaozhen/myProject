package junit.test;

/**
 * Created by Administrator on 2017/5/4.
 */
public class Apple {
    private String b;
    private short c;

    public Apple(String b, short c) {
        this.b = b;
        this.c = c;
    }


    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public short getC() {
        return c;
    }

    public void setC(short c) {
        this.c = c;
    }

    @Override
    public String toString() {
        return "[b:"+b+",c:"+c+"]";
    }
}