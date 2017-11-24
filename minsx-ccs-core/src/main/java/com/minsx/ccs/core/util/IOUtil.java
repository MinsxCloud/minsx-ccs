package com.minsx.ccs.core.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.zip.CheckedInputStream;

public class IOUtil {

	public static void InputStreamToFile(InputStream inputStream, File file) throws IOException {
		OutputStream os = new FileOutputStream(file);
		int bytesRead = 0;
		byte[] buffer = new byte[8192];
		while ((bytesRead = inputStream.read(buffer, 0, 8192)) != -1) {
			os.write(buffer, 0, bytesRead);
		}
		os.close();
		inputStream.close();
	}
	
	public static String getFileContent(File file) {
		StringBuilder result = new StringBuilder();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String s = null;
			while ((s = br.readLine()) != null) {
				result.append(System.lineSeparator() + s);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result.toString();
	}

	public static String readStreamAsString(InputStream in, String charset) throws IOException {

		if (in == null) {
			return "";
		}

		Reader reader = null;
		Writer writer = new StringWriter();
		String result;

		char[] buffer = new char[1024];
		try {
			int n = -1;
			reader = new BufferedReader(new InputStreamReader(in, charset));
			while ((n = reader.read(buffer)) != -1) {
				writer.write(buffer, 0, n);
			}

			result = writer.toString();
		} finally {
			in.close();
			if (reader != null) {
				reader.close();
			}
			if (writer != null) {
				writer.close();
			}
		}

		return result;
	}

	public static byte[] readStreamAsByteArray(InputStream in) throws IOException {

		if (in == null) {
			return new byte[0];
		}

		ByteArrayOutputStream output = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = -1;
		while ((len = in.read(buffer)) != -1) {
			output.write(buffer, 0, len);
		}
		output.flush();
		return output.toByteArray();
	}

	public static boolean checkFile(File file) {
		if (file == null) {
			return false;
		}

		boolean exists = false;
		boolean isFile = false;
		boolean canRead = false;
		try {
			exists = file.exists();
			isFile = file.isFile();
			canRead = file.canRead();
		} catch (SecurityException se) {
			// Swallow the exception and return false directly.
			return false;
		}

		return (exists && isFile && canRead);
	}

	public static void safeClose(InputStream inputStream) {
		if (inputStream != null) {
			try {
				inputStream.close();
			} catch (IOException e) {
			}
		}
	}

	public static void safeClose(OutputStream outputStream) {
		if (outputStream != null) {
			try {
				outputStream.close();
			} catch (IOException e) {
			}
		}
	}

	public static Long getCRCValue(InputStream inputStream) {
		if (inputStream instanceof CheckedInputStream) {
			return ((CheckedInputStream) inputStream).getChecksum().getValue();
		}
		return null;
	}

}
