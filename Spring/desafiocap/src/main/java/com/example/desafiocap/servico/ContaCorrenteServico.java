package com.example.desafiocap.servico;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;


import com.example.desafiocap.modelo.ContaCorrente;
import com.example.desafiocap.repositorio.ContaCorrenteRepositorio;

@Service
public class ContaCorrenteServico {

	@Autowired
	ContaCorrenteRepositorio ccRep;
	
	public ContaCorrente atualizar(Long idConta, ContaCorrente cc) {
		ContaCorrente ccSalva = ccRep.findOne(idConta);
		
		if (ccSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		BeanUtils.copyProperties(cc, ccSalva, "idConta");
		return ccRep.save(ccSalva);
	}

}
