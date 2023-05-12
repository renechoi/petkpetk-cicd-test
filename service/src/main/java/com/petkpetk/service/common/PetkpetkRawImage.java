package com.petkpetk.service.common;

import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class PetkpetkRawImage implements MultipartFile {

	private final String imageUrl;
	private final String originalFilename;
	private final String contentType;
	private final byte[] content;

	public PetkpetkRawImage(String imageUrl, String originalFilename, String contentType, byte[] content) {
		this.imageUrl = imageUrl;
		this.originalFilename = originalFilename;
		this.contentType = contentType;
		this.content = content;
	}

	@Override
	public String getName() {
		return imageUrl;
	}

	@Override
	public String getOriginalFilename() {
		return originalFilename;
	}

	@Override
	public String getContentType() {
		return contentType;
	}

	@Override
	public boolean isEmpty() {
		return content.length == 0;
	}

	@Override
	public long getSize() {
		return content.length;
	}

	@Override
	public byte[] getBytes() throws IOException {
		return content;
	}

	@Override
	public InputStream getInputStream() throws IOException {
		return new ByteArrayInputStream(content);
	}

	@Override
	public void transferTo(File dest) throws IOException, IllegalStateException {
		Files.write(dest.toPath(), content);
	}
}
