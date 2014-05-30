package celestialwizardry.util;

public class Colour
{

    private static final char hexNums[] = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
    };

    private float red, green, blue, alpha;

    public Colour(float r, float g, float b, float a)
    {
        this.red = r;
        this.green = g;
        this.blue = b;
        this.alpha = a;
    }

    public Colour(float r, float g, float b)
    {
        this.red = r;
        this.green = g;
        this.blue = b;
    }

    public static Colour fromHexString(String s)
    { //TODO
        if (s.length() == 7)
        { //in format of #ff00ff
            char buf[] = new char[2];
            Colour ret = new Colour(1f, 1f, 1f, 1f);
            for (int i = 0; i < 3; i++)
            {
                buf[0] = s.charAt(1 + i * 2);
                buf[1] = s.charAt(1 + i * 2 + 1);
                switch (i)
                {
                    case 0: //red
                        System.out.println(hexDigitToDec(buf[0]) * 16 + hexDigitToDec(buf[1]));
//					ret.setRed();
                        break;
                }
            }
        }
        return null;
    }

    public float getRed()
    {
        return this.red;
    }

    public float getGreen()
    {
        return this.green;
    }

    public float getBlue()
    {
        return this.blue;
    }

    public float getAlpha()
    {
        return this.alpha;
    }

    public void setRed(float v)
    {
        this.red = v;
    }

    public void setGreen(float v)
    {
        this.green = v;
    }

    public void setBlue(float v)
    {
        this.blue = v;
    }

    public void setAlpha(float v)
    {
        this.alpha = v;
    }

    public String getAsHexString()
    {
        int retRed = (int) Math.floor(this.red * 256);
        int fnum = (int) Math.floor(retRed / 16); //special case if a color value is equal to 1f
        int lnum = retRed % 16;
        System.out.println(fnum + " " + lnum);
        return null;
    }

    private static int hexDigitToDec(char c)
    {
        for (int i = 0; i < hexNums.length; i++)
        {
            if (hexNums[i] == c)
            {
                return i;
            }
        }
        System.err.println("invalid character \"" + c + "\"");
        return -1;
    }

}
