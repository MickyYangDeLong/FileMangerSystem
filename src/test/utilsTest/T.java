package test.utilsTest;

import java.util.List;

import o.o.utils.FileUtils;

/*
 * author:Micky
 * version:1.0.0
 * Date:2017��9��8�� ����1:00:49
 */
public class T {
	public  void testMock(FileUtils fileUtils) {
		List<String> file = fileUtils.containFileName("", "");
		System.out.println(file.size());
	}

}
