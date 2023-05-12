package com.petkpetk.service.config.file;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.petkpetk.service.common.PetkpetkImage;
import com.petkpetk.service.common.PetkpetkRawImage;
import com.petkpetk.service.common.StatusCode;
import com.petkpetk.service.config.exception.PetkpetkServerException;
import com.petkpetk.service.config.properties.LocalProperty;
import com.petkpetk.service.config.properties.ServerProperty;

@Repository
public class ImageLocalRepository<T extends PetkpetkImage> {

	public void save(T petkpetkImage, MultipartFile rawImage) {
		try {
			rawImage.transferTo(new File(createPath(petkpetkImage)));
		} catch (IOException | URISyntaxException e) {
			throw new ImageUploadFailureException();
		}
	}

	public void deleteFiles(List<T> itemImages) {
		itemImages.forEach(this::delete);
	}

	public void delete(T petkpetkImage) {
		if (petkpetkImage.getOriginalName().equals("defaultProfile.png")){
			return;
		}

		try {
			new File(createPath(petkpetkImage)).delete();
		} catch (URISyntaxException e) {
			throw new ImageUploadFailureException();
		}
	}

	public MultipartFile findByPetkpetkImage(T petkpetkImage) {

		try {
			Path path = Paths.get(createPath(petkpetkImage));
			byte[] content = Files.readAllBytes(path);
			String contentType = Files.probeContentType(path);
			return new PetkpetkRawImage(petkpetkImage.getImageUrl(), path.getFileName().toString(), contentType,
				content);
		} catch (IOException | URISyntaxException e) {
			throw new PetkpetkServerException(StatusCode.LOCAL_IMAGE_CONVERTING_FAILURE);
		}
	}

	private URI createPath(T petkpetkImage) throws URISyntaxException {
		return new URI("file", null, petkpetkImage.getImageUrl()
			.replace(ServerProperty.IMAGE_SERVER_LOCATION.getServerLocation(), getSystemStorage()), null);
	}

	private String getSystemStorage() {
		return LocalProperty.getInstance().getItemLocalStorage();
	}



	/**
	 * 아래 메서드들은 deprecated 될 예정이나, test code에서 사용되고 있으므로 추후 test code 리팩토링 후 삭제한다.
	 * TODO: test code 리팩토링
	 *
	 * @param uploadPath
	 * @param originalFileName
	 * @param fileData
	 * @return
	 * @throws IOException
	 */

	public String saveFiles(String uploadPath, String originalFileName, byte[] fileData) throws
		IOException {
		UUID uuid = UUID.randomUUID();
		String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
		String savedFileName = uuid.toString() + extension;
		String fileUploadFullUrl = uploadPath + "/" + savedFileName;

		Files.write(Paths.get(fileUploadFullUrl), fileData);
		return savedFileName;
	}

	public void deleteFile(String filePath, String originalImageName) {
		Optional.ofNullable(originalImageName)
			.filter(name -> !name.isEmpty())
			.map(name -> new File(filePath + "/" + name))
			.filter(File::exists)
			.ifPresent(File::delete);
	}
}
