package com.petkpetk.service.domain.shopping.entity.item;

import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.multipart.MultipartFile;

import com.petkpetk.service.common.AuditingFields;
import com.petkpetk.service.common.PetkpetkImage;
import com.petkpetk.service.config.properties.ServerProperty;
import com.petkpetk.service.domain.shopping.dto.item.ItemImageDto;
import com.petkpetk.service.domain.user.dto.security.UserAccountPrincipal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "item_image")
@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@DynamicUpdate
public class ItemImage extends AuditingFields implements PetkpetkImage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "item_image_id")
	private Long id;

	private String uniqueName;

	private String originalName;

	private String imageUrl;

	private String representativeImageYn = "N";

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "item_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	@ToString.Exclude
	private Item item;

	public ItemImage(String originalName, String uniqueName) {
		this.originalName = originalName;
		this.uniqueName = uniqueName;
		this.imageUrl = createImageUrl();
	}

	public void mapWith(Item item) {
		if (this.item == null) {
			this.item = item;
		}
	}

	public static ItemImage from(MultipartFile rawImage) {
		return new ItemImage(
			rawImage.getOriginalFilename(),
			createUniqueName(rawImage)
		);
	}

	public static ItemImage from(ItemImageDto itemImageDto) {
		return new ItemImage(itemImageDto.getOriginalName(), itemImageDto.getUniqueName());
	}

	public static ItemImage asRepresentative(MultipartFile rawImage) {
		ItemImage itemImage = ItemImage.from(rawImage);
		itemImage.setRepresentativeImageYn("Y");
		return itemImage;
	}

	private String createImageUrl() {
		return ServerProperty.IMAGE_SERVER_LOCATION.getServerLocation() + uniqueName;
	}

	private static String createUniqueName(MultipartFile rawImage) {
		return UUID.randomUUID() + extractExtension(rawImage);
	}

	private static String extractExtension(MultipartFile rawImage) {
		return rawImage.getOriginalFilename().substring(rawImage.getOriginalFilename().lastIndexOf("."));
	}

	@PrePersist
	public void anonymousSetup() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserAccountPrincipal userAccountPrincipal = (UserAccountPrincipal)authentication.getPrincipal();

		this.createdBy = userAccountPrincipal.getBusinessName();
		this.modifiedBy = userAccountPrincipal.getBusinessName();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof ItemImage))
			return false;
		ItemImage itemImage = (ItemImage)o;
		return uniqueName.equals(itemImage.uniqueName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(uniqueName);
	}
}
