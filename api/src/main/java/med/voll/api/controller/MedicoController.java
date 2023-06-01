package med.voll.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.medico.DadosCadastroMedicos;
import med.voll.api.medico.DadosListagemMedico;
import med.voll.api.medico.Medico;
import med.voll.api.medico.MedicoRepository;

@RestController
@RequestMapping("medicos")
public class MedicoController {

	@Autowired
	private MedicoRepository medicoRepository;
	
	@PostMapping
	@Transactional
	public void cadastrar(@RequestBody @Valid DadosCadastroMedicos dados) {
		medicoRepository.save(new Medico(dados));
	}
	
	/*
	@GetMapping
	public List<DadosListagemMedico> listar(){
		return medicoRepository.findAll().stream().map(DadosListagemMedico::new).toList();
	} 
	*/
	
	@GetMapping
	public Page<DadosListagemMedico> listar(Pageable paginacao){
		return medicoRepository.findAll(paginacao).map(DadosListagemMedico::new);
	}
	
}
