package com.lar.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.List;

import javax.annotation.PostConstruct;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public abstract class JsonDAO {

	protected Gson gson;

	@PostConstruct
	protected void init() {
		gson = new Gson();
	}

	protected abstract File getFileWithEntity();

	protected JsonReader createJsonReader() {
		JsonReader jsonReader = null;
		try {
			Reader reader = new FileReader(getFileWithEntity());
			jsonReader = new JsonReader(reader);
			jsonReader.setLenient(true);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonReader;
	}

	@SuppressWarnings("rawtypes")
	protected List writeToDBFile(List list) {
		try {
			Writer osWriter;
			osWriter = new FileWriter(getFileWithEntity(), false);
			gson.toJson(list, osWriter);
			osWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	public void deleteAll() {
		PrintWriter pw;
		try {
			pw = new PrintWriter(getFileWithEntity());
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
