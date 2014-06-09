package celestialwizardry.util;

import org.lwjgl.opengl.GL11;

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
        this.alpha = 1f;
    }
    
    /**Values between 0 and 255 (inclusive)*/
    public Colour(int r, int g, int b)
    {
    	this.red = (float) r / 255f;
    	this.green = (float) g / 255f;
    	this.blue = (float) b / 255f;
    	this.alpha = 1f;
    }
    
    /**Values between 0 and 255 (inclusive)*/
    public Colour(int r, int g, int b, int a)
    {
    	this.red = (float) r / 255f;
    	this.green = (float) g / 255f;
    	this.blue = (float) b / 255f;
    	this.alpha = (float) a / 255f;
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
    
    public void setGLColor() {
    	GL11.glColor4f(red, green, blue, alpha);
    }
    
    public int getRedI() {
    	return (int)Math.floor(this.red*255);
    }
    
    public int getGreenI() {
    	return (int)Math.floor(this.green*255);
    }
    
    public int getBlueI() {
    	return (int)Math.floor(this.blue*255);
    }
    
    public int getAlphaI() {
    	return (int)Math.floor(this.alpha*255);
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
    
    public int getPackedRGB() {
    	return ( getRedI() << 16 ) | ( getGreenI() << 8 ) | ( getBlueI() );
    }
    
    public int getPackedRGBA() {
    	return ( getRedI() << 24 ) | ( getGreenI() << 16 ) | ( getBlueI() << 8 ) | ( getAlphaI() );
    }
    
    public int getPackedARGB() {
    	return ( getAlphaI() << 24) | ( getRedI() << 16 ) | ( getGreenI() << 8 ) | ( getBlueI() );
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
    
    public static int packARGB(int alpha, int red, int green, int blue) {
    	return ( (alpha << 24) | (red << 16) | (green << 8) | (blue) );
    }
    
    public static int packRGBA(int red, int green, int blue, int alpha) {
    	return ( (red << 24) | (green << 16) | (blue << 8) | (alpha) );
    }
    
    public static int packRGB(int red, int green, int blue) {
    	return ( (red << 16) | (green << 8) | (blue) );
    }
    
    /**Not tested!*/
    public static Colour unpackRGB(int packed) {
    	return new Colour( (packed >> 16) & 255, (packed >> 8) & 255, (packed) & 255 );
    }
    
    /**Not tested!*/
    public static Colour unpackRGBA(int packed) {
    	return new Colour( (packed >> 24) & 255, (packed >> 16) & 255, (packed >> 8) & 255, (packed) & 255 );
    }
    
    /**Not tested!*/
    public static Colour unpackARGB(int packed) {
    	return new Colour( (packed >> 16) & 255, (packed >> 8) & 255, (packed) & 255, (packed >> 24) & 255 );
    }

}
