package com.stg.buyback.Controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.stg.buyback.entity.Attachment;
import com.stg.buyback.service.AttachmentService;


@CrossOrigin("*")
@RestController
@RequestMapping("attachment")
public class AttachmentController {

	@Autowired
	private AttachmentService attachmentService;

// @PostMapping(value="/createAttachment")
// public ResponseEntity<Attachment> CreateAttachment (@RequestBody Attachment attachment ){
//     Attachment attachment1 = attachmentService.createAttachment(attachment);
//     return new ResponseEntity<Attachment> (attachment1, HttpStatus.ACCEPTED);
// }

	@PostMapping("/uploadFile/{buybackId}")
	public Attachment uploadFile(@PathVariable int buybackId,@RequestBody Attachment file) {
		Attachment dbFile = attachmentService.saveFile(buybackId,file);

		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFile/").toString();


		return new Attachment(fileDownloadUri, dbFile.getFileType(), dbFile.getFile());
	}
	
//	 @GetMapping("/files")
//	  public ResponseEntity<List<Attachment>> getListFiles() {
//	    List<Attachment> files = attachmentService.().map(dbFile -> {
//	      String fileDownloadUri = ServletUriComponentsBuilder
//	          .fromCurrentContextPath()
//	          .path("/files/")
//	          .path(dbFile.getId())
//	          .toUriString();
//	      return new ResponseFile(
//	          dbFile.getName(),
//	          fileDownloadUri,
//	          dbFile.getType(),
//	          dbFile.getData().length);
//	    }).collect(Collectors.toList());
//	    return ResponseEntity.status(HttpStatus.OK).body(files);
//	  }
	
	
	 @GetMapping("/files/{id}")
	  public ResponseEntity<Attachment> getFile(@PathVariable int id) {
	    return new ResponseEntity<Attachment>(attachmentService.getFileById(id),HttpStatus.OK);
	  }

	 
	 @GetMapping("/downloadFile/{id}")
	  public ResponseEntity<byte[]> downloadFile(@PathVariable int id) {
	    Attachment fileDB = attachmentService.getFileById(id);
	    return ResponseEntity.ok()
	        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getFileName() + "\"")
	        .body(fileDB.getFile());
	  }
	  
	  @GetMapping("buyBackAttachment/{buybackId}")
	  public ResponseEntity<List<Attachment>> getFilesByBuybackId(@PathVariable int buybackId){
		  return new  ResponseEntity<List<Attachment>> (attachmentService.getFileByBuybackId(buybackId),HttpStatus.OK);
	  }
	  
	  @DeleteMapping(value = "deletebyid/{id}")
		public ResponseEntity<String> deleteById(@PathVariable("id") int id) {
			return new ResponseEntity<String>(attachmentService.deleteById(id), HttpStatus.OK);
			// return "success";
		}
	  
}
