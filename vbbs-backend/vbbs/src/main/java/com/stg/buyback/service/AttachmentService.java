package com.stg.buyback.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.stg.buyback.entity.Attachment;
import com.stg.exception.VbbsException;

public interface AttachmentService {
	
	//public abstract Attachment saveFile(int buybackId, Attachment file) throws VbbsException;
	
	public abstract Attachment getFileById(int AttachmentId) throws VbbsException;

	public abstract List<Attachment> getFileByBuybackId(int id) throws VbbsException;

	Attachment saveFile(int id, Attachment file) throws VbbsException;

	public abstract String deleteById(int id)  throws VbbsException;
	
	
}
