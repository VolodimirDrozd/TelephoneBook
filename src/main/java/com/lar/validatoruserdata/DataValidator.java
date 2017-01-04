package com.lar.validatoruserdata;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class DataValidator {

	public abstract int getQuantityLeters();

	public abstract String getRegexPattern();

	public boolean validatorTitle(String title) {
		String world = title.replaceAll("\\s+", "");
		String regexPattern = getRegexPattern();
		if (world.toCharArray().length >= getQuantityLeters()) {
			Pattern p = Pattern.compile(regexPattern);
			Matcher m = p.matcher(world);
			return m.matches();
		}
		return false;

	}
}