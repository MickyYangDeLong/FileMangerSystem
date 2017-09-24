package o.o.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/*
 * author:Micky
 * version:1.0.0
 * Date:2017年8月18日 下午11:46:15
 */
public class FileUtils {
	public static void main(String[] args) throws IOException {
		/*
		 * FileUtils fileUtils = new FileUtils(); fileUtils.containFileName("d",
		 * "'");
		 */
		// testStream();
		// testStreamSorted();
		// System.out.println(containFileName("M:\\testStreamAndfile", "test"));
		// System.out.println(containSpecialString("M:\\testStreamAndfile" ,
		// "str"));
		System.out.println(containSpecialStrInfo(new File(
				"M:\\testStreamAndfile\\新建文本文档.txt"), "str"));
	}

	/*
	 * @parameter 指定路径filePath,指定文件名 fileName
	 * 
	 * @return 匹配文件的具体文件路径的列表
	 */
	public  List<String> containFileName(String filePath, String fileName) {
		List<String> filePathList = new ArrayList<String>();
		File dir = new File(filePath);
		List<File> files = new ArrayList<File>();
		if (dir.isDirectory()) {
			files = getterAllFiles(dir);
			files.stream().filter(f -> f.getName().contains(fileName))
					.forEach(f -> filePathList.add(f.getAbsolutePath()));
		} else if (dir.isFile() && dir.getName().contains(fileName)) {
			filePathList.add(dir.getAbsolutePath());
		}
		return filePathList;
	}

	/*
	 * 递归得到指定路径下的所有文件
	 */
	public static List<File> getterAllFiles(File dir) {
		List<File> files = new ArrayList<File>();
		List<File> childfiles = Arrays.asList(dir.listFiles());
		childfiles.stream().forEach(f -> {
			if (f.isDirectory()) {
				files.addAll(getterAllFiles(f));
			} else {
				files.add(f);
			}
		});
		return files;
	}

	/*
	 * @parameter 指定路径filePath,指定字符串specialString
	 * 
	 * @return 包含特殊字段的文件的具体文件路径的列表
	 */
	public static List<String> containSpecialString(String filePath,
			String specialString) {
		List<String> filePathList = new ArrayList<String>();
		File dir = new File(filePath);
		List<File> files = new ArrayList<File>();
		if (dir.isDirectory()) {
			files = getterAllFiles(dir);
			files.stream().filter(f -> isContainSpecialStr(f, specialString))
					.forEach(f -> filePathList.add(f.getAbsolutePath()));
		} else if (dir.isFile() && isContainSpecialStr(dir, specialString)) {
			filePathList.add(dir.getAbsolutePath());
		}
		return filePathList;
	}

	public static boolean isContainSpecialStr(File file, String str) {
		String strLine = "";
		boolean flag = false;
		try {
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(new FileInputStream(file)));
			while ((strLine = bufferedReader.readLine()) != null) {
				if (strLine.contains(str)) {
					flag = true;
				}
			}
			if (bufferedReader != null)
				bufferedReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return flag;
	}

	/*
	 * @parameter 指定具体文件
	 * 
	 * @return 指定字段在文件中的行数以及行具体信息的map
	 */
	public static Map<Integer, String> containSpecialStrInfo(File file,
			String str) throws FileNotFoundException {
		if (file.isDirectory()) {
			throw new FileNotFoundException(file
					+ " is a directory,please select again!");
		}
		Map<Integer, String> specialStrInfo = new LinkedHashMap<Integer, String>();

		String strLine = "";
		try {
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(new FileInputStream(file)));
			int row = 1;
			while ((strLine = bufferedReader.readLine()) != null) {
				if (strLine.contains(str)) {
					specialStrInfo.put(row, strLine);
				}
				row++;
			}
			if (bufferedReader != null)
				bufferedReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return specialStrInfo;
	}
}
