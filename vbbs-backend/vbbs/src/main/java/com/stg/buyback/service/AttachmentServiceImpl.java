package com.stg.buyback.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.stg.buyback.entity.Attachment;
import com.stg.buyback.entity.Buyback;
import com.stg.buyback.repository.AttachmentRepository;
import com.stg.buyback.repository.BuybackRepository;
import com.stg.exception.VbbsException;


@Service
public class AttachmentServiceImpl implements AttachmentService {

	@Autowired
	private AttachmentRepository attachmentRepository;

	@Autowired
	private BuybackRepository buybackRepository;

	
	
	@Override
	public Attachment getFileById(int attachmentId) {
		Attachment optional = attachmentRepository.findById(attachmentId).orElseThrow(()->{
			throw new VbbsException("attachment is not found");
		});
		return optional;
		}

	@Override
	public List<Attachment> getFileByBuybackId(int id) throws VbbsException {
		return attachmentRepository.findByBuybackId(id);
	}

	@Override
	public Attachment saveFile(int buybackId, Attachment file) throws VbbsException {
			String fileName = file.getFileName();
			Buyback oldBuyback = buybackRepository.findById(buybackId).orElseThrow(() -> {
				throw new VbbsException("not found ");
			});
			Attachment dbFile = null;

			try {
				if (fileName.contains("..")) {
					throw new VbbsException("Sorry! Filename contains invalid path sequence " + fileName);
				}

				dbFile = new Attachment(fileName, file.getFileType(), file.getFile());
				dbFile.setBuyback(oldBuyback);
				dbFile.getBuyback().setBuybackId(oldBuyback.getBuybackId());
				return attachmentRepository.save(dbFile);
			} catch (VbbsException ex) {
				throw new VbbsException("Could not store file " + fileName + ". Please try again!");
			}
		}

	@Override
	public String deleteById(int id) throws VbbsException {
		attachmentRepository.findById(id).orElseThrow(()->new VbbsException("File with id"+id+"doesn't exist!"));
		attachmentRepository.deleteById(id);
		return "\"Success\"";
	}

	

}
