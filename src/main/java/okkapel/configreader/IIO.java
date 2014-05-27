package okkapel.configreader;

import java.util.List;

import okkapel.configreader.Source;

public interface IIO {
	public List<String> readlines(Source s, boolean ignoreComments);
}
