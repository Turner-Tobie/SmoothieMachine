package org.elevenfifty.smoothieMachine.service;

import java.util.List;

import org.elevenfifty.smoothieMachine.beans.UserImage;
import org.elevenfifty.smoothieMachine.beans.Users;
import org.elevenfifty.smoothieMachine.repository.UserImageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class ImageService {

	private static final Logger log = LoggerFactory.getLogger(ImageService.class);

	@Autowired
	private UserImageRepository userImageRepo;
	
	public void saveImage(MultipartFile file, Users users) {

		if (!file.isEmpty()) {
			try {
				List<UserImage> images = userImageRepo.findByUserId(users.getId());
				UserImage img = (images.size() > 0) ? images.get(0) : new UserImage(users.getId());
				img.setContentType(file.getContentType());
				img.setImage(file.getBytes());
				userImageRepo.save(img);

				log.debug("Saved Image");
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	public void deleteImage(Users user) {
		log.debug("Removing Image");

		List<UserImage> images = userImageRepo.findByUserId(user.getId());

		for (UserImage img : images) {
			userImageRepo.delete(img);
		}
	}

}
