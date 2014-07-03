package celestialwizardry.config;

public class RuneConfigPart
{

    private String[] entryLines;
    private int partId;

    public RuneConfigPart(String[] entryLines, int partId)
    {
        this.entryLines = entryLines;
        this.partId = partId;
    }

    public int getLineCount()
    {
        return entryLines.length;
    }

    public String getLine(int i)
    {
        if (entryLines != null && i < entryLines.length)
        {
            return entryLines[i];
        }
        return null;
    }

}
