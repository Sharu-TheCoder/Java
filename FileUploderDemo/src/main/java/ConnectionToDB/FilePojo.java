package ConnectionToDB;

public class FilePojo {
	int Id;
	String File_name;
	String file_path;
	String  file_size;
	String file_type;
	String  last_modified;
	
	
	
	
	public FilePojo(int id, String file_name, String file_path,
			String file_size, String file_type, String last_modified) {
		super();
		Id = id;
		File_name = file_name;
		this.file_path = file_path;
		this.file_size = file_size;
		this.file_type = file_type;
		this.last_modified = last_modified;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getFile_name() {
		return File_name;
	}
	public void setFile_name(String file_name) {
		File_name = file_name;
	}
	public String getFile_path() {
		return file_path;
	}
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}
	public String getFile_size() {
		return file_size;
	}
	public void setFile_size(String file_size) {
		this.file_size = file_size;
	}
	public String getFile_type() {
		return file_type;
	}
	public void setFile_type(String file_type) {
		this.file_type = file_type;
	}
	public String getLast_modified() {
		return last_modified;
	}
	public void setLast_modified(String last_modified) {
		this.last_modified = last_modified;
	}
	
	
}
