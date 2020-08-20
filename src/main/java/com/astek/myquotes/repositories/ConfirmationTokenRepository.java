package com.astek.myquotes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.astek.myquotes.entitites.ConfirmationToken;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Integer> {
	 ConfirmationToken findByConfirmationToken(String confirmationToken);
}
