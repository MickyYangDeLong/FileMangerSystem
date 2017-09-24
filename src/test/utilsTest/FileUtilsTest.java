package test.utilsTest;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.omg.CORBA.Any;

import o.o.utils.FileUtils;
import mockit.Expectations;
import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;

/*
 * author:Micky
 * version:1.0.0
 * Date:2017年9月7日 下午11:46:12
 */
public class FileUtilsTest {

	@Mocked
	FileUtils fileUtils;

	@Test
	public void containFileNameTest() {

		new Expectations() {
			{
				List<String> strList = new ArrayList<>();
				strList.add("fileName");
				fileUtils.containFileName((String) any, (String) any);
				result = strList; // times = 1;
			}
		};

		// fileUtils = new MockUp<FileUtils>() {
		// @Mock
		// public List<String> containFileName(String filePath, String fileName)
		// {
		// List<String> strList = new ArrayList<>();
		// strList.add("fileName");
		// strList.add("fileName");
		// strList.add("fileName");
		// return strList;
		// }
		// }.getMockInstance();
		T t = new T();
		t.testMock(fileUtils);
	}

}
