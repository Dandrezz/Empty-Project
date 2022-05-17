package com.montran.exam.parser.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.montran.exam.exceptions.LogException;
import com.montran.exam.log.Log;
import com.montran.exam.log.LogLevels;
import com.montran.exam.parser.ParserStrategy;
import com.montran.exam.parser.formats.FormatRtgs;

public class ParserIso implements ParserStrategy<FormatRtgs> {

	private Log log;
	private List<String> lines;
	private String path;

	public ParserIso() throws LogException {
		log = Log.getInstance();
		lines = new ArrayList<String>();
	}

	@Override
	public void loadFile() {
		File file = new File(path);
		try (InputStream inputStream = new FileInputStream(file);
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
			String linesRaw;
			while ((linesRaw = bufferedReader.readLine()) != null) {
				this.lines.add(linesRaw);
			}
		} catch (FileNotFoundException e) {
			log.write(e.getMessage(), LogLevels.ERROR);
		} catch (IOException e) {
			log.write(e.getMessage(), LogLevels.ERROR);
		}
	}

	@Override
	public List<FormatRtgs> processFile() {
		List<FormatRtgs> listFormat = new ArrayList<FormatRtgs>();
		for (String line : lines) {
			Pattern p = Pattern.compile("\\{(.+?)\\}");
			Matcher m = p.matcher(line);
			List<String> blocks = new ArrayList<String>();
			while (m.find()) {
				blocks.add(m.group(1));
			}
			FormatRtgs format = new FormatRtgs();
			format.setSenderCode(blocks.get(0).split(":")[1]);
			format.setReceiverCode(blocks.get(1).split(":")[1]);
			String blockCurrency = blocks.get(2).split(":")[1];
			format.setCurrency(blockCurrency.substring(0,3));
			format.setAmount(new BigDecimal(blockCurrency.substring(3)));
			format.setUniqueRef(blocks.get(3).split(":")[1]);
			listFormat.add(format);
		}
		return listFormat;
	}

}
