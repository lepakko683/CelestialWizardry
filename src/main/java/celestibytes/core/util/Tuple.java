package celestibytes.core.util;

public class Tuple
{

    private Object a, b;

    public Tuple(Object a, Object b)
    {
        this.a = a;
        this.b = b;
    }

    public Object getA()
    {
        return this.a;
    }

    public Object getB()
    {
        return this.b;
    }


    public void setA(Object o)
    {
        this.a = o;
    }

    public void setB(Object o)
    {
        this.b = o;
    }


}
