package com.PhysicalDiagnosis;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean @SessionScoped
public class TestBean {
	
	private String foo = "bar";
	
	public void runMe() {
		// not sure why this not showing up on console
		System.out.println("FORTNITE!!11!");
	}

	public String getFoo() {
		return foo;
	}

	public void setFoo(String foo) {
		this.foo = foo;
	}
}
