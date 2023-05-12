package com.petkpetk.service.config.converter;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.petkpetk.service.domain.user.entity.ProfileImage;

public class ImageConverterTest {

	/**
	 * 이 코드에서는 MockMultipartFile을 생성하여 List<MultipartFile>으로 만들고,
	 * 이를 ImageConverter에 주입하여 변환하는 테스트를 수행한다.
	 * imageConstructor 함수를 정의하여 ProfileImage 객체를 생성하도록 한다.
	 * 이 함수에서는 각 이미지에 대해 랜덤한 고유이름, 원본파일 이름, 이미지 URL을 생성하고,
	 * 이 정보로 ProfileImage 객체를 생성하여 반환한다.
	 * <p>
	 * 이렇게 생성된 ProfileImage 객체들이 List<ProfileImage>로 변환되는 것을 확인한다.
	 * assertThat을 사용하여 각 ProfileImage 객체의 속성값들이 올바른지 검증한다.
	 *
	 * @throws Exception
	 */

	@Test
	void testImageConverter() throws Exception {
		// given
		MockMultipartFile file1 = new MockMultipartFile("file", "test1.jpg", MediaType.IMAGE_JPEG_VALUE,
			"test image1".getBytes());
		MockMultipartFile file2 = new MockMultipartFile("file", "test2.jpg", MediaType.IMAGE_JPEG_VALUE,
			"test image2".getBytes());
		List<MultipartFile> files = Arrays.asList(file1, file2);

		// imageConstructor 함수 정의
		Function<MultipartFile, ProfileImage> imageConstructor = (file) -> {
			ProfileImage image = new ProfileImage();
			String uniqueName = UUID.randomUUID().toString();
			String originalName = file.getOriginalFilename();
			String imageUrl = "http://localhost:8080/" + uniqueName;
			image.setUniqueName(uniqueName);
			image.setOriginalName(originalName);
			image.setImageUrl(imageUrl);
			return image;
		};
		ImageConverter<ProfileImage> imageConverter = ImageConverter.of(imageConstructor);

		// when
		List<ProfileImage> images = imageConverter.convertToImages(files);

		// then
		assertThat(images.size()).isEqualTo(2);
		assertThat(images.get(0).getUniqueName()).isNotEmpty();
		assertThat(images.get(0).getOriginalName()).isEqualTo("test1.jpg");
		assertThat(images.get(0).getImageUrl()).isEqualTo("http://localhost:8080/" + images.get(0).getUniqueName());
		assertThat(images.get(1).getUniqueName()).isNotEmpty();
		assertThat(images.get(1).getOriginalName()).isEqualTo("test2.jpg");
		assertThat(images.get(1).getImageUrl()).isEqualTo("http://localhost:8080/" + images.get(1).getUniqueName());
	}
}
