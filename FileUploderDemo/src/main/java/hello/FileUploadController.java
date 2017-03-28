package hello;

import hello.storage.StorageFileNotFoundException;
import hello.storage.StorageService;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ConnectionToDB.FilePojo;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
public class FileUploadController {
	JSONObject obj=new JSONObject();
    private final StorageService storageService;

    @Autowired
    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }
    @GetMapping("/")
    public String listUploadedFiles(Model model) throws IOException {

        model.addAttribute("files", storageService
                .loadAll()
                .map(path ->
                        MvcUriComponentsBuilder
                                .fromMethodName(FileUploadController.class, "serveFile", path.getFileName().toString())
                                .build().toString())
                .collect(Collectors.toList()));

        return "uploadForm";
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+file.getFilename()+"\"")
                .body(file);
    }

    @PostMapping("/")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) throws IOException {
    
        storageService.store(file);
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");
        String name=file.getOriginalFilename();
         byte[] b = file.getBytes();
        String fileSize = Objects.toString(file.getSize(), null);
        System.out.println("bytes : "+Arrays.toString(b));
        System.out.println("size : "+fileSize);
        System.out.println("file name is "+name);
        String fileType=file.getContentType();
        System.out.println("file Type is "+fileType);
        
        
                File convFile = new File( file.getOriginalFilename());
                file.transferTo(convFile);
                System.out.println("cov file is"+convFile);
       
                System.out.println("Before Format : " + convFile.lastModified());
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

    	System.out.println("After Format : " + sdf.format(convFile.lastModified()));

Date lastModified = new Date(convFile.lastModified()); 
SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
String formattedDateString = formatter.format(lastModified); 
System.out.println("After"+formattedDateString);

for(int i=1;i<100;i++){
	FilePojo file1 =new FilePojo(i, name, " ", fileSize, fileType, formattedDateString);
	Connection c = null;
    Statement stmt = null;
    try {
      Class.forName("org.sqlite.JDBC");
      String dbURL = "jdbc:sqlite:/home/consultadd/fileDB.db";
      c = DriverManager.getConnection(dbURL);
      System.out.println(c.getMetaData());
      Statement stmt1 = c.createStatement();
      System.out.println("Opened database successfully"+stmt1);
      ResultSet rs = stmt1.executeQuery("SELECT * FROM File_uploader;" );
     /*kunjan 
http://www.sqlitetutorial.net/sqlite-java/insert/


      String sql = "INSERT INTO File_uploader(col1,col2,col3) VALUES(?,?,?)";
      PreparedStatement pstmt = c.prepareStatement(sql);

          pstmt.setString(1, val1);
          pstmt.setDouble(2, val2);
          pstmt.setString(3, val3);
          pstmt.executeUpdate();
   
      */
      while ( rs.next() ) {
         int id = rs.getInt("id");
        
      }
      rs.close();
      stmt1.close();
      c.close();
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
    }
	}
       
        return "redirect:/";
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}
