package com.example.Transion.app.service.impl;

import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Transion.app.model.Address;
import com.example.Transion.app.model.TransionUser;
import com.example.Transion.app.repository.AddressRepository;
import com.example.Transion.app.repository.TransionUserRepository;
import com.example.Transion.app.service.TransionUserService;

@Service
public class TransionUserServiceImpl implements TransionUserService {

	@Autowired
	TransionUserRepository transionUserRepo;
	
	@Autowired
	AddressRepository addressRepository;
	
	@Autowired
	JavaMailSender mail;

	@Override
	public Boolean exists(ObjectId id) {
		Optional<TransionUser> userCheck = transionUserRepo.findById(id);
		return userCheck.isPresent();
	}

	@Override
	public TransionUser save(TransionUser item) {
		return transionUserRepo.save(item);
	}

	@Override
	public List<TransionUser> save(List<TransionUser> items) {
		return transionUserRepo.saveAll(items);
	}

	@Override
	public Optional<TransionUser> findOne(ObjectId id) {
		return transionUserRepo.findById(id);
	}

	@Override
	public List<TransionUser> findAll() {
		return transionUserRepo.findAll();
	}

	@Override
	public void delete(ObjectId id) {
		transionUserRepo.deleteById(id);
	}

	@Override
	public void delete(TransionUser item) {
		transionUserRepo.delete(item);
	}

	@Override
	public void deleteAll() {
		transionUserRepo.deleteAll();
	}

	@Override
	public void deleteAll(List<TransionUser> items) {
		transionUserRepo.deleteAll(items);
	}

	@Override
	public String passwordEncrypt(String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.encode(password);
	}

	@Override
	public TransionUser findByUsername(String username) {
		return transionUserRepo.findByUsername(username);
	}

	@Override
	public Address saveAddress(Address address) {
		// TODO Auto-generated method stub
		return addressRepository.save(address);
	}

	@Override
	public void sendConfirmationEmail(TransionUser user) throws MessagingException {
		MimeMessage mimeMessage = mail.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
		
		String msg1 = "Dobar dan, <i>" + user.getFirstname() + "</i><br/> " + "Jos samo malo je potrebno da se uspesno registrujete. <br/> "
				+ "Posetite ovaj <a href='http://localhost:3000/potvrda/" + user.getId() + "'>link</a> i aktiviracete svoj profil. <br/>";
		String msg2 = "Prijatan dan Vam zeli <br/> <hr/> vas <span style='color:#265D0C; font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif; '>Transion</span> tim.";
		
		mimeMessage.setContent(msg1+msg2, "text/html");
		helper.setFrom("TransionTim");
		helper.setTo(user.getUsername());
		helper.setSubject("Transion potvrda registracije.");
		
		mail.send(mimeMessage);
	}

}
