package com.example.desafiocap.recurso;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.desafiocap.modelo.ContaCorrente;
import com.example.desafiocap.repositorio.ContaCorrenteRepositorio;
import com.example.desafiocap.servico.ContaCorrenteServico;

@RestController
@RequestMapping("/contacorrente")
public class ContaCorrenteRecurso {
	
	@Autowired
	private ContaCorrenteRepositorio ccrep;
	
	@Autowired
	private ContaCorrenteServico ccServico;
	
	//Listar todas as contas cadastradas
	@GetMapping
	public List<ContaCorrente> listar(){
		
		return ccrep.findAll();
	}
	
	//Criar Conta Corrente através de Post
	@PostMapping
	public ResponseEntity<ContaCorrente> criar(@RequestBody ContaCorrente cc, HttpServletResponse response) {
		//Salva a nova categoria e retorna a categoria criada
		ContaCorrente ccSalva = ccrep.save(cc);
		
		//Montar o Header de retorno - Location
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
		.buildAndExpand(ccSalva.getIdConta()).toUri();
		
		//Adicionar o Header ao retorno
		response.setHeader("Location",uri.toASCIIString());
		
		//Retorna, no body, o que foi criado
		return ResponseEntity.created(uri).body(ccSalva);
	}
	
	//Listar somente uma conta corrente
	@GetMapping("/{conta}")
	public ResponseEntity<?> buscarPeloCodigo(@PathVariable Long conta) {

		ContaCorrente cc =  ccrep.findOne(conta);
		//Se recurso estiver vazio, retorna 404. Se não, retorna o que foi solicitado
		return !(cc==null) ?ResponseEntity.ok(cc) : ResponseEntity.notFound().build();
	}
	
	@PostMapping("/depositar/{idConta}/{deposito}")
	public ResponseEntity<ContaCorrente> depositar(@PathVariable Long idConta, @PathVariable BigDecimal deposito, @Valid @RequestBody ContaCorrente cc ){
		
		//Busca valor atual que está em conta
		ContaCorrente contaAtual =  ccrep.findOne(idConta);
		
		//Faz a operação de depósito (soma) no valor
		BigDecimal saldo = contaAtual.getSaldoConta();
		saldo = saldo.add(deposito);
		
		//Insere o valor na lista que será enviada
		cc.setSaldoConta(saldo);
		
		//Chama o serviço para atualizar
		ContaCorrente ccSalva = ccServico.atualizar(idConta, cc);
		return ResponseEntity.ok(ccSalva);
		
	}
	
	@PostMapping("/sacar/{idConta}/{saque}")
	public ResponseEntity<ContaCorrente> sacar(@PathVariable Long idConta, @PathVariable BigDecimal saque, @Valid @RequestBody ContaCorrente cc ){
		
		//Busca valor atual que está em conta
		ContaCorrente contaAtual =  ccrep.findOne(idConta);
		
		//Faz a operação de depósito (soma) no valor
		BigDecimal saldo = contaAtual.getSaldoConta();
		saldo = saldo.subtract(saque);
		
		//Insere o valor na lista que será enviada
		cc.setSaldoConta(saldo);
		
		//Chama o serviço para atualizar
		ContaCorrente ccSalva = ccServico.atualizar(idConta, cc);
		return ResponseEntity.ok(ccSalva);
		
	}

}
