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

	private static void testStreamSorted() {
		Rule rule = new Rule("r1", "m1", 1);
		Rule rule2 = new Rule("r2", "m2", 2);
		Rule rule3 = new Rule("r3", "m3", 6);
		Rule rule4 = new Rule("r4", "m4", 4);
		Rule rule5 = new Rule("r5", "m5", 5);
		Map<Rule, String> map = new HashMap<Rule, String>();
		Map<Rule, String> map2 = new LinkedHashMap<Rule, String>();
		map.put(rule, "");
		map.put(rule2, "");
		map.put(rule3, "");
		map.put(rule4, "");
		map.put(rule5, "");
		List<Rule> list = new ArrayList<Rule>();
		list.addAll(map.keySet());
		list.stream().sorted((p1, p2) -> p1.getAge() > p2.getAge() ? 1 : -1)
				.forEach(p -> map2.put(p, map.get(p)));// 666
		for (Entry<Rule, String> m : map.entrySet()) {
			System.out.println(m.getKey().toString());
		}
		System.out.println();
		for (Entry<Rule, String> m : map2.entrySet()) {
			System.out.println(m.getKey().toString());
		}

	}

	private static void testStream() throws IOException {
		File file = new File("M:\\testStreamAndfile\\testStream.txt");
		System.out.println(file);
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
				fileOutputStream);
		DataOutputStream dataOutputStream = new DataOutputStream(
				bufferedOutputStream);
		// dataOutputStream.writeChars("hello world!");
		dataOutputStream.writeUTF("hello world!");
		dataOutputStream.flush();
		dataOutputStream.close();
		DataInputStream in = new DataInputStream(new BufferedInputStream(
				new FileInputStream(file)));
		// System.out.println(in.readInt());
		// System.out.println(in.readBoolean());
		System.out.println(in.readUTF());
		in.close();
		File file2 = new File("M:\\testStreamAndfile\\testObjctStream.txt");
		FileOutputStream fileOutputStream2 = new FileOutputStream(file2);
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(
				fileOutputStream2);
		TestObjectInputStream testObjectInputStream = new TestObjectInputStream();
		objectOutputStream.writeObject(testObjectInputStream);
		objectOutputStream.flush();
		objectOutputStream.close();
	}

	/*
	 * @parameter 指定路径filePath,指定文件名 fileName
	 * 
	 * @return 匹配文件的具体文件路径的列表
	 */
	public static List<String> containFileName(String filePath, String fileName) {
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
