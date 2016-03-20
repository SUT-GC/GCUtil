package test.select.ip;

import select.ip.ShowAddress;

public class TestShowAddress {

	public static void main(String[] args) {
		ShowAddress showAddress = new ShowAddress( "**********这里填写百度api通行码**********");
		String result = showAddress.getResult("116.2.242.202");
		result = showAddress.getResult("119.119.38.210");
		System.out.println(result);
	}

}
