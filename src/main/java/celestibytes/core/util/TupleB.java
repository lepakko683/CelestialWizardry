package celestibytes.core.util;

public class TupleB<D, E>
{
    private D a;
    private E b;

    public TupleB(D a, E b)
    {
        this.a = a;
        this.b = b;
    }

    public D getA()
    {
        return this.a;
    }

    public E getB()
    {
        return this.b;
    }


    public void setA(D o)
    {
        this.a = o;
    }

    public void setB(E o)
    {
        this.b = o;
    }
}
