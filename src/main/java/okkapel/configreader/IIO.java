package okkapel.configreader;

import java.util.List;

public interface IIO
{
    public List<String> readlines(Source s, boolean ignoreComments);
}
