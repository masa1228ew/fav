package com.example.samuraitravel.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Service;

import com.example.samuraitravel.entity.Fav;
import com.example.samuraitravel.entity.House;
import com.example.samuraitravel.entity.User;
import com.example.samuraitravel.form.FavForm;
import com.example.samuraitravel.repository.FavRepository;

import jakarta.transaction.Transactional;

@Service
public class FavService {
	private final FavRepository favRepository;
	
	public FavService(FavRepository favRepository) {
		this.favRepository = favRepository;
	}
	@Transactional
	public String create(
			House house,User user,
			FavForm favForm) {
	  //すでにいいねしていた場合、いいねを取り消す
	  if(favRepository.existsByUserAndHouse(user, house) == true) {
	      favRepository.deleteByUserAndHouse(user, house);
	  }else {  //いいねしていなかった場合、投稿へのいいねを登録する
		 Fav fav = new Fav();
//	    fav.setFavId(favId);
	    fav.setUser(user);
	    fav.setHouse(house);
	    LocalDateTime ldt = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
	    fav.setCreatedAt(ldt);
	    fav.setUpdatedAt(favForm.getUpdatedAt());
	    favRepository.save(fav);
	  }

	    return "redirect:/postmain?postdetail";
	}

}
