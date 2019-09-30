import Exceptions.AvajException;
import Exceptions.CiclesException;

import java.io.*;
import java.util.List;

public class Simulator {

	public static PrintWriter	writer;
	private int					cicles;
	private List<Aircraft>		aircrafts;

	public static void main(String[] args) {

	}

	private void parseFile(String fileName) throws IOException, AvajException {
		BufferedReader	reader = new BufferedReader(new FileReader(fileName));
		String			line;
		String[]		splitted;
		Integer			lineNum = 0;

		if ((line = reader.readLine()) != null)
		{
			lineNum++;
			splitted = line.split(" ");
			if (splitted.length != 1)
				throw new CiclesException(lineNum);
		}
	}
}
