package test.select.ip;

import select.ip.ShowAddress;

public class TestShowAddress {

	public static void main(String[] args) {
		ShowAddress showAddress = new ShowAddress();
		String result = showAddress.getResult("116.2.242.202");
		System.out.println(result);
	}

}
