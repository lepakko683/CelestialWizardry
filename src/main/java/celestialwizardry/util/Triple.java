package celestialwizardry.util;

public class Triple<D, E, F>
{

    private D a;
    private E b;
    private F c;

    public Triple(D a, E b, F c)
    {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public D getA()
    {
        return this.a;
    }

    public E getB()
    {
        return this.b;
    }

    public F getC()
    {
        return this.c;
    }

    public void setA(D o)
    {
        this.a = o;
    }

    public void setB(E o)
    {
        this.b = o;
    }

    public void setC(F o)
    {
        this.c = o;
    }

}
