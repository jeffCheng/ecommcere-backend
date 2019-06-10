package com.easybuy.shopping.service.imp;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.easybuy.shopping.dto.PromoModel;
import com.easybuy.shopping.model.Promo;
import com.easybuy.shopping.repository.PromoRepository;
import com.easybuy.shopping.service.PromoService;

public class PromoServiceImp implements PromoService{

	@Autowired
	PromoRepository promoRepository;
	
	@Override
	public PromoModel getPromoByProdcut(long productId) {
		Promo promo = promoRepository.findByProductId(productId);
		
		PromoModel promoModel = new  PromoModel();
		BeanUtils.copyProperties(promoModel, promo);
		if(promoModel.getStartDate().after(new Date())) promoModel.setStatus(1);
		else if(promoModel.getEndDate().before(new Date())) promoModel.setStatus(3);
		else promoModel.setStatus(2);
		
		return promoModel;
	}

}
