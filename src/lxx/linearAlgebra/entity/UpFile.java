package lxx.linearAlgebra.entity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class UpFile {

	private String upResult;
	private List<File> files;
	
	public UpFile() {
		files = new ArrayList<File>();
	}
	
	public UpFile(String upResult) {
		files = new ArrayList<File>();
		this.upResult = upResult;
	}
	
	public String getUpResult() {
		return upResult;
	}
	public void setUpResult(String upResult) {
		this.upResult = upResult;
	}

	public List<File> getFile() {
		return files;
	}
	
}
